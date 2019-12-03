/**
 * Created by ZhangPei on 2019/11/27.
 */
class NormalLoginForm extends React.Component {
    state = {
        refreshQRCode: "/refreshQRcode?" + Math.random()
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) { //如果为空则校验通过
                //请求后台
                fetch("http://localhost:8080/loginSqlProduct", {
                    body: values,
                    mode: 'no-cors',
                    //请求后台
                    method: "POST",
                }).then(function (resp) {
                    return resp.json();
                }).then(function (result) {
                    console.log("返回json对象", result);
                }).catch(function (e) {
                    console.log("请求出错，错误信息：" + e);
                });
            }
        });
    };

    refreshQRCode = () => {
        this.setState(
            Object.assign({}, this.state, {refreshQRCode: "/refreshQRcode?" + Math.random()}
            ))
    };

    render() {
        console.log("猜想下登录页面的属性：", this.state);
        const {getFieldDecorator} = this.props.form;
        return (
            <antd.Form onSubmit={this.handleSubmit} className="login-form">
                <div className={"logo login-logo"} style={{backgroundSize: "cover"}}/>
                <antd.Form.Item>
                    {getFieldDecorator('username', {
                        rules: [{required: true, message: '请输入用户名'}],
                    })(
                        <antd.Input
                            prefix={<antd.Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>}
                            placeholder="用户名"
                        />,
                    )}
                </antd.Form.Item>
                <antd.Form.Item>
                    {getFieldDecorator('password', {
                        rules: [{required: true, message: '请输入密码'}],
                    })(
                        <antd.Input
                            prefix={<antd.Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>}
                            type="password"
                            placeholder="密码"
                        />,
                    )}
                </antd.Form.Item>
                <antd.Form.Item>
                    {getFieldDecorator('remember', {
                        valuePropName: 'checked',
                        initialValue: true,
                    })(<antd.Checkbox>记住密码</antd.Checkbox>)}
                    <a className="login-form-forgot" href="">
                        忘记密码？
                    </a>
                    <antd.Button type="primary" htmlType="submit" className="login-form-button">
                        登录
                    </antd.Button>
                    没有账号？<a href="/toRegist">注册</a>
                    <a href="/loginSqlProduct?username=root&password=root" id="quikLogin"
                       style={{float: "right"}}>一键快捷登录</a>
                    <img src={this.state.refreshQRCode} id="QRcode"/>
                    <div style={{textAlign: "center"}}>
                        <a id="refreshQRcode" href="javascript:void(0)" onClick={this.refreshQRCode}>刷新二维码</a>
                    </div>
                </antd.Form.Item>
            </antd.Form>
        );
    }
}

const WrappedNormalLoginForm = antd.Form.create({name: 'normal_login'})(NormalLoginForm);

ReactDOM.render(<WrappedNormalLoginForm/>, document.getElementById("root"));