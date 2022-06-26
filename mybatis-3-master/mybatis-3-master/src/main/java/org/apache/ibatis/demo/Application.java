package org.apache.ibatis.demo;

import org.apache.ibatis.demo.mapper.BlogMapper;
import org.apache.ibatis.demo.model.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Application {
  public static void main(String[] args) throws IOException {
    String resource = "org/apache/ibatis/demo/mybatis-config.xml";
    //将配置文件流对象
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //解析XML核心配置、映射文件,sql语句;注册Mapper
    /** SqlSessionFactoryBuilder 建造者模式 */
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder(/**这里什么都不干*/).build(/**开始解析*/inputStream);
    //织入插件（JDK动态代理）。虽然叫openSession但没有打开数据库连接池
    SqlSession session = sqlSessionFactory.openSession();
    //底层采用JDK动态代理创建Mapper接口对象
    BlogMapper mapper = session.getMapper(BlogMapper.class);
    //jdbc交互,真正执行获取的时候去连接数据库
    /**代理对象只有Debug才能进去*/
    Blog blog1 = mapper.selectBlog(1);
    //只有第一个session commit后二级缓存才能生效
    session.commit();
    System.out.println(blog1.getAuthor());

    /**第二次查询，debug缓存*/
    SqlSession session2 = sqlSessionFactory.openSession();
    BlogMapper mapper2 = session2.getMapper(BlogMapper.class);
    Blog blog2 = mapper2.selectBlog(1);


//        Blog blog = session.selectOne("org.apache.ibatis.demo.mapper.BlogMapper.selectBlog", 1);
//        System.out.println(blog.toString());

  }

}
