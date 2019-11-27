/**
 * Created by ZhangPei on 2019/11/27.
 */

const config = {
    item: [{key: "docNm", cellName: "文档名称"}, {
        key: "docUniqueIdentity",
        cellName: "文档唯一标识"
    }, {
        key: "uploadBy",
        cellName: "上传人"
    }], width: "90%", url: "/queryDocs", isPaging: true
};

const props = {
    name: 'file',
    action: '/uploadDoc',
    headers: {
        authorization: 'authorization-text'
    },
    data: function (file) {
        return {fileNm: file.name, size: file.size}
    },
    /*onChange(info) {
        console.log("测试info内容", info);
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            antd.message.success(info.file.name + " 上传成功！");
        } else if (info.file.status === 'error') {
            antd.message.error(info.file.name + "上传失败！");
        }
    }*/
};

class Table extends React.Component {

    state = {
        currentPageNum: 1,      //当前页数
        perPageCount: 10,       //每页条数
        total: 0,
        data: [],
        refresh: false
    };

    onChange = (info) => {
        console.log("测试info内容", info);
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            antd.message.success(info.file.name + " 上传成功！");
            console.log("上传陈宫", this.state);
            this.setState(
                Object.assign({}, this.state, {refresh: true})
            )
        } else if (info.file.status === 'error') {
            antd.message.error(info.file.name + "上传失败！");
        }
    };

    //组件将要挂载时
    componentWillMount() {

    }

    //组件挂载完毕请求后台数据
    componentDidMount() {
        console.log("组件挂载完毕！", this.props.config.url);
        this.getTableData();
    }

    //修改每页条数后在次数进行数据重新加载，该方法为发送请求的最佳位置
    componentDidUpdate(preProps, preState) {
        if ((preState.currentPageNum != this.state.currentPageNum)
            || (preState.perPageCount != this.state.perPageCount) || this.state.refresh) {
            this.getTableData();
        }
    }

    //组件将要接收新属性时
    componentWillReceiveProps() {

    }

    //点击下一页
    nextPageNum = () => {
        if (Math.ceil((this.state.total / this.state.perPageCount)) === this.state.currentPageNum) {
            return ;
        }
        this.setState((prevState, props) => {
                if (prevState.currentPageNum >= (Math.floor(prevState.data.total / prevState.perPageCount) + 1)) {
                    return {
                        currentPageNum: parseInt(prevState.currentPageNum)
                    }
                } else {
                    return {
                        currentPageNum: parseInt(prevState.currentPageNum + 1)
                    }
                }
            }
        );
    };

    //点击上一页
    prePageNum = () => {
        if (Math.ceil((this.state.total / this.state.perPageCount)) === 0) {
            return;
        }
        this.setState((prevState, props) => {
            if (prevState.currentPageNum <= 1) {
                return {
                    currentPageNum: 1
                }
            } else {
                return {
                    currentPageNum: parseInt(prevState.currentPageNum - 1)
                }
            }
        });
    };

    //修改每页条数
    changePerPageCount = (event) => {
        if (event.target) {
            this.setState({
                perPageCount: event.target.value
            });

            //重新请求后台数据
            this.getTableData();
        }
    };

    //跳转至指定页
    breakPageNum = (event) => {
        if (event.target && event.target.value > 0) {
            if (event.target.value >= Math.ceil((this.state.total / this.state.perPageCount))) {
                this.setState({
                    currentPageNum: Math.ceil((this.state.total / this.state.perPageCount))
                })
            } else {
                this.setState({
                    currentPageNum: parseInt(event.target.value)
                })
            }
        }

        //向后台请求数据
        // this.getTableData();
    };

    //向后台请求数据
    getTableData = () => {

        if (!this.props.config.url) {
            return;
        }
        let _this = this;
        var formData = new FormData();
        formData.append("start", ((this.state.currentPageNum - 1) * this.state.perPageCount).toString());
        formData.append("limit", this.state.perPageCount.toString());
        fetch(this.props.config.url, {
            body: formData,

            //请求后台
            method: "POST",
        }).then(function (resp) {
            return resp.json();
        }).then(function (result) {
            if (result) {
                _this.setState({
                    data: result.beans,
                    total: result.bean.total
                })
            }
        }).catch(function (e) {
            console.log("请求出错，错误信息：" + e);
        });
    };

    render() {
        return (
            <div>
                <div style={{width: this.props.config.width, margin: "0 auto"}}>
                    {/*表格数据显示部分*/}
                    <table width="100%" border="1px solid black" cellSpacing="0px">
                        <tbody>
                        <tr className="thead-tr">
                            <th>序号</th>
                            {this.props.config.item.map((cell, index) =>
                                <th key={index}>{cell.cellName}</th>
                            )}
                            <th width="130px">操作</th>
                        </tr>

                        {this.state.data ? this.state.data.map((row, index) =>
                            <tr className="tbody-tr" key={index}>
                                <td>{index + 1}</td>
                                {this.props.config.item.map((item, index) =>
                                    <td key={index}>
                                        {row[item.key]}
                                    </td>
                                )}
                                <td width="130px">
                                    <a href={`/lookOnline?uuid=${row.docUniqueIdentity}`}>在线预览</a>&nbsp;&nbsp;&nbsp;
                                    <a href={`/downLoadDoc?uuid=${row.docUniqueIdentity}`}>下载</a>
                                </td>
                            </tr>
                        ) : null}
                        </tbody>
                    </table>

                    {/*展示是否分页部分*/}
                    {this.props.config.isPaging ?
                        <div style={{
                            float: "right",
                            width: this.props.config.width,
                            marginTop: "10px"
                        }}>
                            <p style={{
                                float: "right",
                                lineHeight: "30px",
                                margin: "0px"
                            }}>跳转至 <input
                                type="text"
                                onBlur={this.breakPageNum}
                                style={{
                                    width: "30px",
                                    height: "20px",
                                    outline: "none",
                                    border: "1px solid rgb(221,221,221)",
                                    textAlign: "center"
                                }}/> 页</p>
                            <p style={{
                                float: "right",
                                lineHeight: "30px",
                                margin: "0px"
                            }}>每页条数 <select
                                style={{outline: "none", appearance: "none"}}
                                onChange={this.changePerPageCount}>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>&nbsp; 共计 <span>{this.state.total}</span> 条 {Math.ceil((this.state.total / this.state.perPageCount))}页，当前第 <span>{this.state.currentPageNum}</span> 页，
                            </p>
                            <div style={{clear: "both", float: "right"}}>
                                <a href="javascript:void(0)" onClick={this.prePageNum}>上一页</a>&nbsp;
                                <a href="javascript:void(0)" onClick={this.nextPageNum}>下一页</a>
                            </div>
                        </div> : ""}
                </div>
                <div style={{clear: "both", width: this.props.config.width, margin: "20px auto"}}>
                    <antd.Upload {...props} onChange={this.onChange}>
                        <antd.Button>
                            <antd.Icon type="upload"/>
                            上传文档
                        </antd.Button>
                    </antd.Upload>
                </div>
            </div>
        )
    }
}

ReactDOM.render(
    <Table config={config}/>,
    document.getElementById('root')
);