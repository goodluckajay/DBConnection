package com.syne;

import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class StudentJDBCTemplate implements StudentDAO {
	
   private DataSource dataSource;
   //private JdbcTemplate jdbcTemplate;
   private NamedParameterJdbcTemplate namedJdbcTemplate;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
   }
   
   // Insert Records
   public void create(String name, Integer age) {
      String SQL = "insert into Student (name, age) values (:name, :age)";
      SqlParameterSource sqlParam = new MapSqlParameterSource("name", name)
    		  							.addValue("age", age); 
      namedJdbcTemplate.update( SQL,sqlParam);
      System.out.println("Created Record Name = " + name + " Age = " + age);
      return;
   }
   // Getting all Records
   public List<Student> listStudents() {
	      String SQL = "select * from Student";
	      List <Student> students = namedJdbcTemplate.query(SQL, new HashMap<>(), new StudentMapper());
	      return students;
	   }
   
   // Get Record on the basis of Id 
   // Even though we are getting record on the basis of Id so ideally it seems to return on 1 recored but API is designed in such
   //  a way that i would return List of Records
   public List<Student> getStudent(Integer id) {
      String SQL = "select * from Student where id = :id";
      SqlParameterSource sqlParam = new MapSqlParameterSource("id", id);
      List<Student> student = namedJdbcTemplate.query(SQL, sqlParam, new StudentMapper());
      
      return student;
   }
   
   // Delete the record on the basis of Id
   public void delete(Integer id) {
      String SQL = "delete from Student where id = :id";
      SqlParameterSource sqlParam = new MapSqlParameterSource("id", id);
      namedJdbcTemplate.update(SQL, sqlParam);
      System.out.println("Deleted Record with ID = " + id );
      return;
   }
 
   // Update Age on the basis of Id
   public void update(Integer Id, Integer age){
      String SQL = "UPDATE STUDENT set AGE = :age where ID = :Id";
      SqlParameterSource sqlParam = new MapSqlParameterSource("age", age)
										.addValue("Id", Id); 
      namedJdbcTemplate.update(SQL, sqlParam);
      System.out.println("Updated Record with ID = " + Id );
      return;
   }
   



   
}