import java.sql.*;
import java.util.*;
import java.lang.*;

public class LinuxMain {
    static final String DB_URL = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2712/Group52?autoReconnect=true&useSSL=false";
    static final String USER = "Group52";
    static final String PASS = "CSCI3170";

    public static void main(String[] args) throws SQLException {

        //default select 1

        while(true){
            String main_manu = "-----Main menu------\n" +
                    "What kinds of operation would you like to perform?\n" +
                    "1. Operations for administrator\n" +
                    "2. Operations for salesperson\n" +
                    "3. Operations for manager\n" +
                    "4. Exit this program\n" +
                    "Enter Your Choice:";
            System.out.print(main_manu);
            Scanner menu_scan = new Scanner(System.in);
            int menu_choice = menu_scan.nextInt();
            if(menu_choice == 1){
                LinuxAdmin admin = new LinuxAdmin();
                admin.Administrator_menu();
            }else if(menu_choice == 2){

            }else if(menu_choice==3){

            }else if(menu_choice == 4){
                System.out.println("Exit main menu");
                break;
            }
        }

        System.out.println("END MAIN");
    }//end main
}//end main clase
//mysql --host=projgw --port=2633 -u Group52 -p
//CSCI3170

/*
  insert into table(colname1 , colname2) values('value1','value2'); //insert statement
  //format(GETDATE() ,  'MM/dd/yyyy')
        */
