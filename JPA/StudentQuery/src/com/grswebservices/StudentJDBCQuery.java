package com.grswebservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentJDBCQuery {
	
	private static final String JDBC_DRIVER = 
			"com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL = 
			"jdbc:mysql://localhost/udemy?serveTimezone=UTC";
	private static final String USERNAME = "bob";
	private static final String PASSWORD = "Pa$$w0rd";
	private Statement statement;
	
	public void handleDatabase() {
		
		 Connection connection = null;
		 statement = null;
		 ResultSet resultSet = null;
		 
		 try {
			Class.forName(JDBC_DRIVER);
			
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			
			statement = connection.createStatement();
		
			String sqlQuery = "SELECT * FROM students";
			resultSet = statement.executeQuery(sqlQuery);
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				
				Student student = new Student(id, name, age);
				
				System.out.println(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
			 try {
				 resultSet.close();
				 connection.close();
				 statement.close();
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
		}
	}
	

}
