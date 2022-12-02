import java.sql.*;
public class LinuxConnectionTest {

    public static void main(String[] args)
    {
        try
        {

            Class.forName(  "com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection( "jdbc:mysql://projgw.cse.cuhk.edu.hk:2712/Group52?autoReconnect=true&useSSL=false", "Group52", "CSCI3170");

            if(conn != null){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }

            conn.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
