package com.company;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
	try
    {
        Class.forName("org.sqlite.JDBC")
        Connection co = DriverManager.getConnection(jdbc:sqlite:users.db);
        System.out.println("Connected");
    }
	catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
