package com.syne;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class StudentJDBCDaoImpl extends SimpleJdbcDaoSupport {
	
   // get All Student
	
	public List<Student> getAllStudent()
	{
		String sql = "select * from student";
		List<Student> student = this.getJdbcTemplate().query(sql, new StudentMapper());
		return student;
		
	}
}