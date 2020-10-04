package com.syne;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Note: Very very useful example [We are doing CRUD operations with NamedParameterJdbcTemplate API]
// we are making a connection to MYSQL DB 
// we are NOT using annotation
// we are using traditional xml approach
// see beans.xml for DataSource (all properties of DriverManagerDataSource are required)
// need to add SQL connector of MYSQL DB in classpath (make sure it as per your MYSQL version)
// See... we are not dealing with Connection object because NamedParameterJdbcTemplate class do it internally.

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

      StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");
      System.out.println(studentJDBCTemplate);
      
      System.out.println("------ Insert Records--------" );
      studentJDBCTemplate.create("Zara", 11);
      studentJDBCTemplate.create("Nuha", 2);
      studentJDBCTemplate.create("Ayan", 15);
	
	
      
      System.out.println("------Listing All Records--------" );
      List<Student> students = studentJDBCTemplate.listStudents();
      
      for (Student record : students) {
         System.out.print("ID : " + record.getId() );
         System.out.print(", Name : " + record.getName() );
         System.out.println(", Age : " + record.getAge());
      }
      
      System.out.println("----Updating Record with ID = 27 -----" );
      studentJDBCTemplate.update(27, 20);

      System.out.println("----Listing Particular with ID = 27 -----" );
      List<Student> student = studentJDBCTemplate.getStudent(27); // pass the respetive ID Here
      System.out.print("ID : " + student.get(0).getId() );
      System.out.print(", Name : " + student.get(0).getName() );
      System.out.println(", Age : " + student.get(0).getAge());
      
      
      System.out.println("----------- Delete Particular Record with ID = 27 -------------------");
      studentJDBCTemplate.delete(27);
     
   }
}