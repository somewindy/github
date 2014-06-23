package com.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.bean.Blog;
import com.mybatis.bean.BlogMapper;

public class SqlSessionUtils {
	public static SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "com/mybatis/jdbc.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		return sessionFactory;
	}
	
	public static void main(String[] args) throws IOException {
		SqlSession session=getSqlSessionFactory().openSession();
		try{
		BlogMapper mapper=session.getMapper(BlogMapper.class);
		Blog blog=mapper.selectBlog(101);
		}finally{
		session.close();
		}
	}
}
