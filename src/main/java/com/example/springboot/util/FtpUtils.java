package com.example.springboot.util;

import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ZhangPei on 2019/8/15.
 */
public class FtpUtils {
    private static final Logger logger = LoggerFactory.getLogger(FtpUtils.class);


    // ftp服务器地址
    public String hostname = "172.18.255.78";
    // ftp服务器端口号默认为21
    public Integer port = 21;
    // ftp登录账号
    public String username = "maxt";
    // ftp登录密码
    public String password = "mtyj@246536";

    public FTPClient ftpClient = null;

    /**
     * 初始化ftp服务器
     */
    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            logger.error("正在连接ftp服务器:" + this.hostname + ":" + this.port);
            ftpClient.connect(hostname, port); // 连接ftp服务器
            ftpClient.login(username, password); // 登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("连接ftp服务器失败:" + this.hostname + ":" + this.port);
            }
            logger.error("连接ftp服务器成功:" + this.hostname + ":" + this.port);
        } catch (MalformedURLException e) {
            logger.error("连接ftp服务器发生异常:" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("连接ftp服务器发生IO异常:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param pathname
     *            ftp服务保存地址
     * @param fileName
     *            上传到ftp的文件名
     * @param originfilename
     *            待上传文件的名称（绝对地址） *
     * @return
     */
    public boolean uploadFile(String pathname, String fileName, String originfilename) {
        InputStream inputStream = null;
        try {
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));
            initFtpClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            // 观察是否真的上传成功
            boolean storeFlag = ftpClient.storeFile(fileName, inputStream);
            System.err.println("storeFlag==" + storeFlag);
            inputStream.close();
            ftpClient.logout();
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 上传文件
     *
     * @param pathname
     *            ftp服务保存地址
     * @param fileName
     *            上传到ftp的文件名
     * @param inputStream
     *            输入文件流
     * @return
     */
    public boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
        try {
            System.out.println("开始上传文件");
            initFtpClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    // 改变目录路径
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");

            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    // 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    // 判断ftp服务器文件是否存在
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    // 创建目录
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * * 下载文件 *
     *
     * @param pathname
     *            FTP服务器文件目录 *
     * @param filename
     *            文件名称 *
     * @param localpath
     *            下载后的文件路径 *
     * @return
     */
    public boolean downloadFile(String pathname, String filename, String localpath) {
        boolean flag = false;
        OutputStream os = null;
        try {
            System.out.println("开始下载文件");
            initFtpClient();
            // 切换FTP目录
            boolean changeFlag = ftpClient.changeWorkingDirectory(pathname);
            System.err.println("changeFlag==" + changeFlag);

            ftpClient.enterLocalPassiveMode();
            ftpClient.setRemoteVerificationEnabled(false);
            // 查看有哪些文件夹 以确定切换的ftp路径正确
            String[] a = ftpClient.listNames();
            System.err.println(a[0]);

            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (filename.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * * 删除文件 *
     *
     * @param pathname
     *            FTP服务器保存目录 *
     * @param filename
     *            要删除的文件名称 *
     * @return
     */
    public boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        try {
            logger.error("开始删除文件");
            initFtpClient();
            // 切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功");
        } catch (Exception e) {
            System.out.println("删除文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 批量下载ftp服务器上的文件
     * @param serverPath
     * @return
     */
    public boolean batchDownloadFileFromFtpServer(String serverPath) {
        boolean flag = false;
        OutputStream os;
        try {
            initFtpClient();    //初始化ftp连接
            boolean changeFlag = ftpClient.changeWorkingDirectory(serverPath);    //切换到ftp服务器根地址
            if (changeFlag) {
                //切换成功，罗列出该文件夹下所有文件
                ftpClient.enterLocalPassiveMode();      //启用被动模式
                ftpClient.configure(new FTPClientConfig("org.apache.commons.net.ftp.parser.UnixFTPEntryParser"));
                ftpClient.setRemoteVerificationEnabled(false);

                FTPFile[] ftpFiles = ftpClient.listFiles();     //罗列文件夹下所有的文件
                for (FTPFile file : ftpFiles) {
                    InputStream inputStream = ftpClient.retrieveFileStream(file.getName());     //下载文件为输入流

                    //将文件拷贝到相应文件夹下
                    File newFile = new File("/Users/smile_mylife/Desktop/ftp_test/" + file.getName());
                    os = new FileOutputStream(newFile);
                    FileCopyUtils.copy(inputStream, os);

                    //拷贝成功，删除ftp服务器上文件
                    ftpClient.dele(file.getName());

                    //关闭流
                    inputStream.close();
                    os.close();
                }
                ftpClient.logout();
                flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static void main(String[] args) throws IOException {
        /*FtpUtils ftp = new FtpUtils();
        ftp.initFtpClient();
        FTPFile[] ftpFiles = ftp.ftpClient.listFiles("");
        ftp.batchDownloadFileFromFtpServer("/test/");
//        File file = new File("/User/smile_mylife/Desktop/test.txt");
        System.out.println("ok");*/

    }
}
