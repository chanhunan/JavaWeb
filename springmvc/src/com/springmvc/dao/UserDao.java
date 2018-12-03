package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.bean.User;

public class UserDao {
	 /**
	   * @Fields jdbcTemplate : TODO
	   */
	 
	private JdbcTemplate jdbcTemplate;
	 
	  /**
	   * spring�ṩ����
	   * 
	   * @param jdbcTemplate
	   */
	  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
	  }


	  /**
	   * ����û�
	   * 
	   * @param student
	   * @return ����ֵ���ͣ� boolean
	   * @author janinus
	   */
	  public boolean addUser(User user) {
	  String sql = "insert into user(Id,name,password) values(0,\""+user.getName()+"\",\""+user.getPassword()+"\")";
	  System.out.println(sql);
	  return jdbcTemplate.update(sql) == 1;
	  }
	  
	  /**
	   * 
	   * UserMapper���ݿ�ӳ��
	   * 
	   * @ClassName UserMapper
	   */
	 
	  class UserMapper implements RowMapper<User> {
	 
		  @Override
		  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
		    User user = new User();
		    user.setId(rs.getInt(1));
		    user.setName(rs.getString(2));
		    user.setPassword(rs.getString(3));
		 
		    return user;
		  }
	 
	  }


}
