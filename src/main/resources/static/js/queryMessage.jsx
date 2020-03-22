/**
 * Created by ZhangPei on 2019/11/27.
 */

const config = {
    item: [{
        key: "interName",
        cellName: "接口名称"
    }, {
        key: "requestUrl",
        cellName: "请求地址"
    }, {
        key: "contentType",
        cellName: "报文格式"
    }, {
        key: "requestMessage",
        cellName: "报文内容"
    }], width: "90%", url: "/queryMessages", isPaging: true
};

const props = {
    name: 'file',
    action: '/uploadMessage',
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
        breakPage: "",
        isFind: false,
        queryKeyWord: "",
        isQuery: false
    };

    onChange = (info) => {
        console.log("测试info内容", info);
        console.log(info.event);
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            antd.message.success(info.file.name + " 上传成功！");
            this.setState(
                Object.assign({}, this.state, {isQuery: true})
            )
        } else if (info.file.status === 'error') {
            antd.message.error(info.file.name + " 上传失败，失败原因：" + info.file.response.rtnMsg);
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
        if (this.state.isQuery) {
            this.getTableData();
            this.setState(Object.assign({}, this.state, {refresh: false, isQuery: false, breakPage: ""}))
        }
    }

    //组件将要接收新属性时
    componentWillReceiveProps() {

    }

    //点击下一页
    nextPageNum = () => {
        this.setState(
            Object.assign({}, this.state, {breakPage: "", isQuery: true})
        );
        console.log("点击下一页：", this.state.total / this.state.perPageCount);
        if (Math.ceil((this.state.total / this.state.perPageCount)) === this.state.currentPageNum) {
            return;
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
        this.setState(
            Object.assign({}, this.state, {breakPage: "", isQuery: true})
        );
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
            this.setState(Object.assign({}, this.state, {
                perPageCount: event.target.value,
                currentPageNum: 1,
                isQuery: true
            }));
        }
    };

    //跳转至指定页
    breakPageNum = (event) => {
        if (event.target && event.target.value > 0) {
            if (event.target.value >= Math.ceil((this.state.total / this.state.perPageCount))) {
                this.setState(Object.assign({}, this.state, {
                    currentPageNum: Math.ceil((this.state.total / this.state.perPageCount)), isQuery: true
                }))
            } else {
                this.setState(Object.assign({}, this.state, {
                    currentPageNum: parseInt(event.target.value),
                    isQuery: true
                }))
            }
        } else {
            this.setState(Object.assign({}, this.state, {
                breakPage: ""
            }))
        }
    };

    changebreakPageNum = (event) => {
        if (event.target) {
            this.setState(
                Object.assign({}, this.state, {
                    breakPage: event.target.value,
                })
            )
        }
    };

    //向后台请求数据
    getTableData = () => {

        if (!this.props.config.url) {
            return;
        }
        let _this = this;

        var formData = new FormData();
        if (this.state.queryWords) {
            formData.append("keyWords", this.state.queryWords);   //如果是查询页面过来的
            formData.append("start", "0");
        } else {
            formData.append("start", ((this.state.currentPageNum - 1) * this.state.perPageCount).toString());
        }

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

    searchDoc = (value) => {
        this.setState(Object.assign({}, this.state, {
            currentPageNum: 1,
            isQuery: true,
            queryWords: value
        }));
    };

    render() {
        return (
            <div>
                <div style={{width: this.props.config.width, margin: "0 auto"}}>
                    <antd.Input.Search
                        placeholder="搜索"
                        onSearch={this.searchDoc}
                        enterButton
                        style={{width: 300, marginBottom: "20px"}}/>
                    <span style={{color: "red", marginLeft: "10px"}}>上传文档为.json格式结尾，且符合postman导出报文格式</span>
                    {/*表格数据显示部分*/}
                    <div className="scroll_x_style">
                        <table width="100%" border="1px solid black" cellSpacing="0px">
                            <tbody>
                            <tr className="thead-tr">
                                <th>序号</th>
                                {this.props.config.item.map((cell, index) => {
                                        if (index === 0) {
                                            return <th style={{whiteSpace: "nowrap"}} key={index}>{cell.cellName}</th>
                                        } else {
                                            return <th key={index}>{cell.cellName}</th>
                                        }
                                    }
                                )}
                            </tr>

                            {this.state.data ? this.state.data.map((row, index) =>
                                <tr className="tbody-tr" key={index}>
                                    <td>{index + 1}</td>
                                    {this.props.config.item.map((item, index) => {
                                            if (index === 3) {
                                                return <td style={{textAlign: "left"}} key={index}>{row[item.key]}</td>
                                            } else if (index === 0) {
                                                return <td style={{whiteSpace: "nowrap"}} key={index}>{row[item.key]}</td>
                                            } else {
                                                return <td key={index}>{row[item.key]}</td>
                                            }
                                        }
                                    )}
                                </tr>
                            ) : null}
                            </tbody>
                        </table>
                    </div>

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
                                onChange={this.changebreakPageNum} o
                                style={{
                                    width: "30px",
                                    height: "20px",
                                    outline: "none",
                                    border: "1px solid rgb(221,221,221)",
                                    textAlign: "center"
                                }} value={this.state.breakPage}/> 页</p>
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
                            上传报文
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