import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class MainManu {
    static final String DB_URL = "jdbc:mysql://localhost:3306/asm3?useSSL=false";
    static final String USER = "root";
    static final String PASS = "password";
    static String select_all_query = "SELECT * FROM ";

    public static void main(String[] args) throws SQLException {

        //default select 1
        Administrator admid = new Administrator();
        admid.Administrator_menu();



    }//end main
}//end main clase


/*
  insert into table(colname1 , colname2) values('value1','value2'); //insert statement
  //format(GETDATE() ,  'MM/dd/yyyy')
        */
