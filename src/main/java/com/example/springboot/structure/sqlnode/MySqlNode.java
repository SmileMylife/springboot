package com.example.springboot.structure.sqlnode;

import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.SqlNode;

/**
 * Created by ZhangPei on 2019/9/4.
 */
public class MySqlNode implements SqlNode {
    @Override
    public boolean apply(DynamicContext dynamicContext) {

        return false;
    }
}
