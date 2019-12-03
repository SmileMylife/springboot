/*import {
    Form, Input, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button, AutoComplete,
} from 'antd';
import React from "react";

const {Option} = Select;
const AutoCompleteOption = AutoComplete.Option;*/


class RegistrationForm extends React.Component {
    state = {
        confirmDirty: false,
        autoCompleteResult: [],
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };

    handleConfirmBlur = e => {
        const {value} = e.target;
        this.setState({confirmDirty: this.state.confirmDirty || !!value});
    };

    compareToFirstPassword = (rule, value, callback) => {
        const {form} = this.props;
        if (value && value !== form.getFieldValue('password')) {
            callback('Two passwords that you enter is inconsistent!');
        } else {
            callback();
        }
    };

    validateToNextPassword = (rule, value, callback) => {
        const {form} = this.props;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], {force: true});
        }
        callback();
    };

    handleWebsiteChange = value => {
        let autoCompleteResult;
        if (!value) {
            autoCompleteResult = [];
        } else {
            autoCompleteResult = ['.com', '.org', '.net'].map(domain => `${value}${domain}`);
        }
        this.setState({autoCompleteResult});
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {autoCompleteResult} = this.state;

        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 5, offset: 2},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 15,
                    offset: 5,
                },
            },
        };
        const prefixSelector = getFieldDecorator('prefix', {
            initialValue: '86',
        })(
            <antd.Select style={{width: 70}}>
                <antd.Select.Option value="86">+86</antd.Select.Option>
                <antd.Select.Option value="87">+87</antd.Select.Option>
            </antd.Select>,
        );

        const websiteOptions = autoCompleteResult.map(website => (
            <antd.AutoComplete.Option.AutoCompleteOption
                key={website}>{website}</antd.AutoComplete.Option.AutoCompleteOption>
        ));

        return (
            <antd.Form {...formItemLayout} onSubmit={this.handleSubmit} style={{marginTop: 30}}>
                <antd.Form.Item label="E-mail">
                    {getFieldDecorator('邮箱地址', {
                        rules: [
                            {
                                type: 'email',
                                message: '邮箱格式不正确',
                            },
                            {
                                required: true,
                                message: '请输入邮箱地址！',
                            },
                        ],
                    })(<antd.Input/>)}
                </antd.Form.Item>
                <antd.Form.Item label="密码">
                    {getFieldDecorator('password', {
                        rules: [
                            {
                                required: true,
                                message: '请输入密码',
                            },
                            {
                                validator: this.validateToNextPassword,
                            },
                        ],
                    })(<antd.Input.Password/>)}
                </antd.Form.Item>
                <antd.Form.Item label="确认密码">
                    {getFieldDecorator('confirm', {
                        rules: [
                            {
                                required: true,
                                message: '请确认您的密码！',
                            },
                            {
                                validator: this.compareToFirstPassword,
                            },
                        ],
                    })(<antd.Input.Password onBlur={this.handleConfirmBlur}/>)}
                </antd.Form.Item>
                <antd.Form.Item label={<span>昵称&nbsp;
                    <antd.Tooltip title="你想让别人找到你吗？"> <antd.Icon
                        type="question-circle-o"/> </antd.Tooltip></span>}>
                    {getFieldDecorator('nickname', {
                        rules: [{required: true, message: '请输入您的昵称！', whitespace: true}],
                    })(<antd.Input/>)}
                </antd.Form.Item>
                {/*<antd.Form.Item label="居住地">
                    {getFieldDecorator('residence', {
                        initialValue: ['zhejiang', 'hangzhou', 'xihu'],
                        rules: [
                            {type: 'array', required: true, message: '请选择居住地'},
                        ],
                    })(<antd.Cascader options={residences}/>)}
                </antd.Form.Item>*/}
                <antd.Form.Item label="电话号码">
                    {getFieldDecorator('phone', {
                        rules: [{required: true, message: '请输入您的电话号码！'}],
                    })(<antd.Input addonBefore={prefixSelector} style={{width: '100%'}}/>)}
                </antd.Form.Item>
                {/*<antd.Form.Item label="站点">
                    {getFieldDecorator('website', {
                        rules: [{required: true, message: '请输入站点地址！'}],
                    })(
                        <antd.AutoComplete
                            dataSource={websiteOptions}
                            onChange={this.handleWebsiteChange}
                            placeholder="站点"
                        >
                            <antd.Input/>
                        </antd.AutoComplete>,
                    )}
                </antd.Form.Item>*/}
                <antd.Form.Item label="验证码" extra="">
                    <antd.Row gutter={8}>
                        <antd.Col span={12}>
                            {getFieldDecorator('captcha', {
                                rules: [{required: true, message: '请输入验证码！'}],
                            })(<antd.Input/>)}
                        </antd.Col>
                        <antd.Col span={12}>
                            <antd.Button>获取验证码</antd.Button>
                        </antd.Col>
                    </antd.Row>
                </antd.Form.Item>
                <div style={{textAlign: "center"}}>
                    <antd.Form.Item {...tailFormItemLayout}>
                        {getFieldDecorator('agreement', {
                            valuePropName: 'checked',
                        })(
                            <antd.Checkbox>
                                我已阅读 <a href="">同意</a>
                            </antd.Checkbox>,
                        )}
                    </antd.Form.Item>
                    <antd.Form.Item {...tailFormItemLayout}>
                        <antd.Button type="primary" htmlType="submit">
                            注册
                        </antd.Button>
                    </antd.Form.Item>
                </div>
            </antd.Form>
        );
    }
}

const WrappedRegistrationForm = antd.Form.create({name: 'register'})(RegistrationForm);

ReactDOM.render(<WrappedRegistrationForm/>, document.getElementById("root"));
