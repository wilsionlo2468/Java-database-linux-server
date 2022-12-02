import java.sql.*;
import java.util.*;
import java.lang.*;

public class MainMenu {
    static final String DB_URL = "jdbc:mysql://localhost:3306/asm3?useSSL=false";
    static final String USER = "root";
    static final String PASS = "password";

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
                Administrator admin = new Administrator();
                admin.Administrator_menu();
            }else if(menu_choice == 2){
                Salesperson sales = new Salesperson();
                sales.Salesperson_main();
            }else if(menu_choice==3){

            }else if(menu_choice == 4){
                System.out.println("Exit main menu");
                break;
            }
        }

        System.out.println("END MAIN");
    }//end main
}//end main clase

