import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.lang.*;


public class sqltemp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/asm3?useSSL=false";
    static final String USER = "root";
    static final String PASS = "password";
    static String select_all_query = "SELECT * FROM ";

    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        String tb1 = "CREATE TABLE category " +
                "(cid  int(1) unsigned not null," +
                "cname varchar(20) not null," +
                " PRIMARY KEY ( cid ))";
        String tb2 = "CREATE TABLE manufacturer " +
                "(mid  int(2) unsigned not null," +
                "mname varchar(20) not null," +
                "maddress varchar(50) not null,"+
                "mphonenumber int(8) unsigned not null,"+
                "PRIMARY KEY ( mid ))";
        String tb3 = "CREATE TABLE part " +
                "(pid int(3) unsigned not null," +
                "pname varchar(20) not null," +
                "pprice int(5) unsigned not null," +
                "mid int(2) unsigned not null," +
                "cid int(1) unsigned not null," +
                "pwarrentyperiod int(2) unsigned not null," +
                "pavailablequantity int(2) unsigned not null," +
                " PRIMARY KEY ( pid ))";
        String tb4 = "CREATE TABLE salesperson " +
                "(sid int(2) unsigned not null," +
                "sname varchar(20) not null," +
                "saddress varchar(50) not null," +
                "sphonenumber int(8) unsigned not null," +
                "sexperience int(1) unsigned not null," +
                " PRIMARY KEY ( sid ))";
        String tb5 = "CREATE TABLE transaction " +
                "(tid int(4) unsigned not null," +
                "pid int(3) unsigned not null," +
                "sid int(2) unsigned not null," +
                "tdate varchar(10) not null," +
                " PRIMARY KEY ( tid ) )";

        String openning = "-----Operations for administrator menu-----\n" +
                "What kinds of operation would you like to perform?\n" +
                "1.Create all tables\n" +
                "2.Delete all tables\n" +
                "3.Load from datafile\n" +
                "4.Show content of a table\n" +
                "5.Return to the main menu\n" +
                "Enter Your Choose: ";


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            while(true){
                System.out.print(openning);
                Scanner scan = new Scanner(System.in);
                int menu_input = scan.nextInt();
                if(menu_input == 5 ){
                    System.out.println("exit");
                    break;
                }//break while

               switch (menu_input) {
                        case 1:
                        // operation 1 : create all table
                            System.out.println("Processing...Done! Database is initialiized");
                          try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate("DROP TABLE IF EXISTS category");
                            stmt.executeUpdate("DROP TABLE IF EXISTS manufacturer");
                            stmt.executeUpdate("DROP TABLE IF EXISTS part");
                            stmt.executeUpdate("DROP TABLE IF EXISTS salesperson");
                            stmt.executeUpdate("DROP TABLE IF EXISTS transaction");

                            stmt.executeUpdate(tb1);
                            stmt.executeUpdate(tb2);
                            stmt.executeUpdate(tb3);
                            stmt.executeUpdate(tb4);
                            stmt.executeUpdate(tb5);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                          break;

                        case 2:
                        //operation2: Delete all table
                            System.out.println("Delete all table");
                            try {
                                stmt.executeUpdate("DROP TABLE IF EXISTS category");
                                stmt.executeUpdate("DROP TABLE IF EXISTS manufacturer");
                                stmt.executeUpdate("DROP TABLE IF EXISTS part");
                                stmt.executeUpdate("DROP TABLE IF EXISTS salesperson");
                                stmt.executeUpdate("DROP TABLE IF EXISTS transaction");
                            }catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 3:
                            // operation 3 : operation3 load all table
                            System.out.println("loading all table");
                            try {
                                File file = new File("src/category.txt");
                                scan = new Scanner(file);
                                while (scan.hasNextLine()){
                                    String temp[]   = scan.nextLine().split("\t");
//                                    System.out.println(Arrays.toString(temp));
                                    String updateString = "INSERT INTO category (cid, cname) VALUES (?, ?)";
                                    PreparedStatement insertrecord = conn.prepareStatement(updateString);
                                    insertrecord.setInt( 1, Integer.parseInt(temp[0]) );
                                    insertrecord.setString(2,temp[1]);
                                    insertrecord.executeUpdate();
                                }

                                file = new File("src/manufacturer.txt");
                                scan = new Scanner(file);
                                while (scan.hasNextLine()){
                                    String temp2[]   = scan.nextLine().split("\t");
//                                    System.out.println(Arrays.toString(temp2));
                                    String updateString = "INSERT INTO manufacturer (mid,mname,maddress," +
                                            "mphonenumber ) VALUES (?, ?, ?, ?)";
                                    PreparedStatement insertrecord = conn.prepareStatement(updateString);
                                    insertrecord.setInt( 1, Integer.parseInt(temp2[0]) );
                                    insertrecord.setString(2,temp2[1]);
                                    insertrecord.setString(3,temp2[2]);
                                    insertrecord.setInt(4,Integer.parseInt(temp2[3]) );
                                    insertrecord.executeUpdate();
                                }
                                file = new File("src/part.txt");
                                scan = new Scanner(file);
                                while (scan.hasNextLine()){
                                    String temp3[]   = scan.nextLine().split("\t");
//                                    System.out.println(Arrays.toString(temp3));
                                    String updateString = "INSERT INTO part (pid, pname, pprice, mid, cid," +
                                            "pwarrentyperiod, pavailablequantity ) VALUES (?, ?, ?, ?, ?, ?, ?)";
                                    PreparedStatement insertrecord = conn.prepareStatement(updateString);
                                    insertrecord.setInt( 1, Integer.parseInt(temp3[0]) );
                                    insertrecord.setString(2,temp3[1]);
                                    insertrecord.setInt( 3, Integer.parseInt(temp3[2]) );
                                    insertrecord.setInt(4,Integer.parseInt(temp3[3]));
                                    insertrecord.setInt( 5, Integer.parseInt(temp3[4]) );
                                    insertrecord.setInt(6,Integer.parseInt(temp3[5]) );
                                    insertrecord.setInt(7,Integer.parseInt(temp3[6]) );
                                    insertrecord.executeUpdate();
                                }

                                file = new File("src/salesperson.txt");
                                scan = new Scanner(file);
                                while (scan.hasNextLine()){
                                    String temp4[]   = scan.nextLine().split("\t");
//                                    System.out.println(Arrays.toString(temp4));
                                    String updateString = "INSERT INTO salesperson (sid, sname, saddress," +
                                            " sphonenumber,sexperience) VALUES (?, ?, ?, ?, ?)";
                                    PreparedStatement insertrecord = conn.prepareStatement(updateString);
                                    insertrecord.setInt( 1, Integer.parseInt(temp4[0]) );
                                    insertrecord.setString(2,temp4[1]);
                                    insertrecord.setString( 3, temp4[2] );
                                    insertrecord.setInt(4,Integer.parseInt(temp4[3]));
                                    insertrecord.setInt(5,Integer.parseInt(temp4[4]));
                                    insertrecord.executeUpdate();
                                }

                                file = new File("src/transaction.txt");
                                scan = new Scanner(file);
                                while (scan.hasNextLine()) {
                                    String temp5[] = scan.nextLine().split("\t");
//                                    System.out.println(Arrays.toString(temp5));
                                    String updateString = "INSERT INTO transaction (tid, pid, sid, tdate)" +
                                            " VALUES (?, ?, ?, ?)";
                                    PreparedStatement insertrecord = conn.prepareStatement(updateString);
                                    insertrecord.setInt(1, Integer.parseInt(temp5[0]));
                                    insertrecord.setInt(2, Integer.parseInt(temp5[1]));
                                    insertrecord.setInt(3, Integer.parseInt(temp5[2]));
                                    insertrecord.setString(4, temp5[3]);
                                    insertrecord.executeUpdate();
                                }
                            }catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 4:
                            //operation4 show content of a table
                            System.out.print("Which table would you like to show: ");
                            Scanner tablename_scan = new Scanner(System.in);
                            String tablename = tablename_scan.nextLine();
                            String select_all_query = "SELECT * FROM ";

                            if(tablename.toLowerCase().equals("category")){
                                System.out.println("Content of table category: ");
                                //select * from category;
                                stmt = conn.createStatement();
                                String sql = select_all_query + tablename ;
                                ResultSet rs = stmt.executeQuery( sql );
                                System.out.println("| c_id | c_name |");
                                while (rs.next()) {
                                    // Retrieve by column name
                                    System.out.print("| " + rs.getInt(1) + " "  );
                                    System.out.print("| " + rs.getString(2) + " " );
                                    System.out.println("|");
                                }
                            }
                            if(tablename.toLowerCase().equals("manufacturer")){
                                System.out.println("Content of table manufacturer: ");
                                //select * from manufacturer;
                                stmt = conn.createStatement();
                                String sql = select_all_query + tablename ;
                                ResultSet rs = stmt.executeQuery( sql );
                                System.out.println("| m_id | m_name | m_address | m_phonenumber |");
                                while (rs.next()) {
                                // Retrieve by column index
                                System.out.print("| " + rs.getInt(1) + " "  );
                                System.out.print("| " + rs.getString(2) + " " );
                                System.out.print("| " + rs.getString(3) + " "  );
                                System.out.print("| " + rs.getInt(4) + " " );
                                System.out.println("|");
                                }
                            }
                            if(tablename.toLowerCase().equals("part")){
                                System.out.println("Content of table manufacturer: ");
                                //select * from part;
                                stmt = conn.createStatement();
                                String sql = select_all_query + tablename ;
                                ResultSet rs = stmt.executeQuery( sql );
                                System.out.println("| p_id | p_name | p_price | mid | cid | " +
                                "p_warrenty_period | p_available_quantity |");
                                while (rs.next()) {
                                // Retrieve by column index
                                System.out.print("| " + rs.getInt(1) + " "  );
                                System.out.print("| " + rs.getString(2) + " " );
                                System.out.print("| " + rs.getInt(3) + " "  );
                                System.out.print("| " + rs.getInt(4) + " " );
                                System.out.print("| " + rs.getInt(5) + " "  );
                                System.out.print("| " + rs.getInt(6) + " " );
                                System.out.print("| " + rs.getInt(7) + " " );
                                System.out.println("|");
                                }
                            }
                            if(tablename.toLowerCase().equals("salesperson")){
                                System.out.println("Content of table salesperson: ");
                                //select * from salesperson;
                                stmt = conn.createStatement();
                                String sql = select_all_query + tablename ;
                                ResultSet rs = stmt.executeQuery( sql );
                                System.out.println("| s_id | s_name | s_address | s_phone_number | s_experience |");
                                while (rs.next()) {
                                // Retrieve by column index
                                System.out.print("| " + rs.getInt(1) + " "  );
                                System.out.print("| " + rs.getString(2) + " " );
                                System.out.print("| " + rs.getString(3) + " "  );
                                System.out.print("| " + rs.getInt(4) + " " );
                                System.out.print("| " + rs.getInt(5) + " "  );
                                System.out.println("|");
                                }
                            }
                            if(tablename.toLowerCase().equals("transaction")){
                                System.out.println("Content of table transaction: ");
                                //select * from salesperson;
                                stmt = conn.createStatement();
                                String sql = select_all_query + tablename ;
                                ResultSet rs = stmt.executeQuery( sql );
                                System.out.println("| t_id | p_id | s_id | tdate |");
                                while (rs.next()) {
                                    // Retrieve by column index
                                    System.out.print("| " + rs.getInt(1) + " "  );
                                    System.out.print("| " + rs.getInt(2) + " " );
                                    System.out.print("| " + rs.getInt(3) + " "  );
                                    System.out.print("| " + rs.getString(4) + " " );
                                    System.out.println("|");
                                }
                            }
                        break;
                // Extract data from result set
               }//end switch
            }//end while
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }//end outer try
    }
}


/*
  insert into table(colname1 , colname2) values('value1','value2'); //insert statement
  //format(GETDATE() ,  'MM/dd/yyyy')
        */
