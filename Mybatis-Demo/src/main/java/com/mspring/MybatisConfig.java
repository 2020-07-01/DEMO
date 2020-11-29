package com.mspring;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import jdk.nashorn.internal.runtime.UserAccessorProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName : MybatisConfig
 * @Author : yq
 * @Date: 2020-11-29
 * @Description :
 */
@Configuration
public class MybatisConfig {


    /**
     * 初始化SqlSessionFactory
     *
     * @return
     */
    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        try {
            //获取SqlSessionFactory
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 初始化数据源
     *
     * @return
     * @throws SQLException
     */
    @Bean
    public DataSource getDataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriver(new Driver());
        druidDataSource.setUrl("jdbc:mysql://139.196.186.217:3306/Blog");
        druidDataSource.setName("root");
        druidDataSource.setPassword("123456");

        return druidDataSource;
    }


}
