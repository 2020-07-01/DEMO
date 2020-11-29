package com.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatis.bean.Blog;
import com.mybatis.mapper.BlogMapper;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : MybatisApplication
 * @Author : yq
 * @Date: 2020-11-29
 * @Description :
 */
public class MybatisApplication {


    public static void main(String[] args) {
        /**
         * xml文件配置
         */
        SqlSessionFactory sqlSessionFactory = xmlInitSqlSessionFactory();

        MybatisApplication main = new MybatisApplication();
        main.test5(sqlSessionFactory);

    }

    public void test5(SqlSessionFactory sqlSessionFactory) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = blogMapper.selectBlog(2);
        System.out.println(blog);
    }

    /**
     * 缓存测试
     *
     * @param sqlSessionFactory
     */
    public void test3(SqlSessionFactory sqlSessionFactory) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogList = blogMapper.selectAll();
        System.out.println(blogList);


        SqlSession session2 = sqlSessionFactory.openSession(true);

        BlogMapper blogMapper2 = session2.getMapper(BlogMapper.class);
        sqlSession.commit();

        List<Blog> blogList2 = blogMapper2.selectAll();


        System.out.println(blogList2);
    }


    /**
     * xml文件配置
     *
     * @return
     */
    public static SqlSessionFactory xmlInitSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;

    }


    /**
     * 自动提交事务
     */
    public void test1(SqlSessionFactory sqlSessionFactory) {

        /**
         * 自动提交事务
         */
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setName("test");
            blogMapper.insert(blog);
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 手动提交事务
     *
     * @param sqlSessionFactory
     */
    public void test2(SqlSessionFactory sqlSessionFactory) {
        //手动提交
        SqlSession session1 = sqlSessionFactory.openSession();
        try {

            BlogMapper blogMapper = session1.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setName("test");
            blogMapper.insert(blog);
            session1.commit();
            session1.close();
        } catch (Exception e) {
            session1.rollback();
            session1.close();
        }

    }


    public static SqlSessionFactory configurationInitSqlSessionFactory() {
        DataSource dataSource = getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);


        configuration.addMapper(BlogMapper.class);
        //通过包名扫描注册mapper
        configuration.addMappers("");

        configuration.setProxyFactory(new CglibProxyFactory());

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }

    /**
     * 数据源
     *
     * @return
     */
    private static DataSource getDataSource() {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName("");
        druidDataSource.setPassword("");
        druidDataSource.setUrl("");
        return druidDataSource;
    }
}
