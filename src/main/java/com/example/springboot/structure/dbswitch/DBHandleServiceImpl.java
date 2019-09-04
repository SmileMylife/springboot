package com.example.springboot.structure.dbswitch;

import org.springframework.stereotype.Component;

/**
 * 数据源操作工具类
 * Created by ZhangPei on 2018/11/30.
 */
@Component
public class DBHandleServiceImpl {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();

    /**
     * 设置数据库标志
     * @param dbKey
     */
    public static void setDbSource(String dbKey) {
        threadLocal.set(dbKey);
    }

    /**
     * 获取数据库标志
     * @return
     */
    public static Object getDbSource() {
        return threadLocal.get();
    }

    /**
     * 删除数据库标志
     */
    public static void removeDbSource() {
        threadLocal.remove();
    }
}
