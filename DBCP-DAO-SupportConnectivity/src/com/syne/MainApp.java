package com.syne;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Note: Very very useful example [We are doing Select operations with SimpleJdbcDaoSupport API]
// we are making a connection to MYSQL DB 
// we are NOT using annotation
// we are using traditional xml approach
// see beans.xml for DataSource (all properties of DriverManagerDataSource are required)
// need to add SQL connector of MYSQL DB in classpath (make sure it as per your MYSQL version)
// See... we are not dealing with Connection object because we SimpleJdbcDaoSupport class do it internally.

// In this example we are using DAOSupport class instead of JdbcTemplate OR NamedParameterJdbcTemplate. This is the another way
// Here, we are not initializing jdbcTemplate or NamedJdbcTemplate because we are using DAO Support class.
// We are have JdbcDaoSupport and NamedParameterJdbcDaoSupport class but here we are using SimpleJdbcDaoSupport.

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

      StudentJDBCDaoImpl studentJDBCDaoImpl = (StudentJDBCDaoImpl)context.getBean("studentJDBCDaoImpl");
     
      System.out.println("------Records Creation--------" );
      List<Student> student = studentJDBCDaoImpl.getAllStudent();
      System.out.println(student.get(0).getName() + "#" + student.get(0).getAge() + "#" + student.get(0).getId());

      
      
   }
}