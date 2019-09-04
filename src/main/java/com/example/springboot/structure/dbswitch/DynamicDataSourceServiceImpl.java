package com.example.springboot.structure.dbswitch;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangPei on 2018/11/30.
 */
@Component
@Primary
public class DynamicDataSourceServiceImpl extends AbstractRoutingDataSource {
    @Value("${defaultDatasource}")
    private String defaultDatasource;

    public DynamicDataSourceServiceImpl() throws SQLException, ClassNotFoundException {
        Map<Object, Object> map = queryAllDatasources();
        setTargetDataSources(map);
        setDefaultTargetDataSource(MapUtils.getObject(map, defaultDatasource));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DBHandleServiceImpl.getDbSource();
    }

    /**
     * 查询所有配置源，此处有弊端，如果数据库中新增数据源不能自动识别并加载进去
     */
    private Map<Object, Object> queryAllDatasources() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ngwf", "root", "root");
        String sql = "SELECT * FROM T_SR_DATASOURCE";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet == null) {
            throw new RuntimeException("查询数据源查询失败");
        }

        String dbKey, driverClassName, url, username, password;
        Map<Object, Object> map = new HashMap<>();

        BasicDataSource basicDataSource;
        while (resultSet.next()) {
            dbKey = resultSet.getString("DB_KEY");
            driverClassName = resultSet.getString("DRIVE_CLASS_NAME");
            url = resultSet.getString("URL");
            username = resultSet.getString("USERNAME");
            password = resultSet.getString("PASSWORD");

            basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(driverClassName);
            basicDataSource.setUrl(url);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
            map.put(dbKey, basicDataSource);
        }
        return map;
    }
}
