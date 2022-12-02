import java.sql.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Date;




public class LinuxSales {

    public static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db52?autoReconnect=true&useSSL=false";
    public static String dbUsername = "Group52";
    public static String dbPassword = "CSCI3170";

    static Connection con = null;

    public static void search() {
        Statement stmt = null;
        String search = """
                    Choose the search criterion:
                    1. Part Name
                    2. Manufacturer Name
                    Choose the search criterion: \s""";

        try {
            System.out.print(search);
            Scanner scan1 = new Scanner(System.in);
            int search1 = scan1.nextInt();

            System.out.print("Type in the Search Keyword: ");
            Scanner scan2 = new Scanner(System.in);
            String search2 = scan2.nextLine();


            System.out.println("Choose order");
            System.out.println("1. By price, ascending order");
            System.out.println("2. By price, descending order");
            System.out.print("Choose the search criterion: ");
            Scanner scan3 = new Scanner(System.in);
            int search3 = scan3.nextInt();


            stmt = con.createStatement();

            //for search by part name
            String psql = """
                        SELECT P.pid, P.pname, M.mname, C.cname, P.pavailablequantity, P.pwarrantyperiod, P.pprice
                        FROM part P, manufacturer M, category C
                        WHERE P.mid = M.mid AND P.cid = C.cid AND P.pname = """ ;

            //for search by manufacturer name
            String msql = """
                        SELECT P.pid, P.pname, M.mname, C.cname, P.pavailablequantity, P.pwarrantyperiod, P.pprice
                        FROM part P, manufacturer M, category C
                        WHERE P.mid = M.mid AND P.cid = C.cid AND M.mname = """ ;
            String a = "'" + search2 + "'";

            if(search1 == 1){
                if(search3 == 1) {
                    //ascending order & search by part name
                    String order = " ORDER BY P.pprice ASC;";
                    String sql1 = psql +a+ order;
                    ResultSet r1 = stmt.executeQuery(sql1);
                    System.out.println("| ID | Nname | Manufacturer | Category | Quantity | Warranty | Price |");
                    while (r1.next()) {
                        System.out.print("| " + r1.getInt(1) + " "  );
                        System.out.print("| " + r1.getString(2) + " " );
                        System.out.print("| " + r1.getString(3) + " "  );
                        System.out.print("| " + r1.getString(4) + " " );
                        System.out.print("| " + r1.getString(5) + " "  );
                        System.out.print("| " + r1.getString(6) + " "  );
                        System.out.print("| " + r1.getString(7) + " "  );
                        System.out.println("|");
                    }

                } else if (search3 == 2) {
                    //descending order & search by part name
                    String order = " ORDER BY P.pprice DESC;";
                    String sql2 = psql +a+ order;
                    ResultSet r2 = stmt.executeQuery(sql2);
                    System.out.println("| ID | Nname | Manufacturer | Category | Quantity | Warranty | Price |");
                    while (r2.next()) {
                        System.out.print("| " + r2.getInt(1) + " "  );
                        System.out.print("| " + r2.getString(2) + " " );
                        System.out.print("| " + r2.getString(3) + " "  );
                        System.out.print("| " + r2.getString(4) + " " );
                        System.out.print("| " + r2.getString(5) + " "  );
                        System.out.print("| " + r2.getString(6) + " "  );
                        System.out.print("| " + r2.getString(7) + " "  );
                        System.out.println("|");
                    }
                }
            }

            if(search1 == 2){
                if(search3 == 1) {
                    //ascending order & search by manufacturer name
                    String order = " ORDER BY P.pprice ASC;";
                    String sql3 = msql +a+ order;
                    ResultSet r3 = stmt.executeQuery(sql3);
                    System.out.println("| ID | Nname | Manufacturer | Category | Quantity | Warranty | Price |");
                    while (r3.next()) {
                        System.out.print("| " + r3.getInt(1) + " "  );
                        System.out.print("| " + r3.getString(2) + " " );
                        System.out.print("| " + r3.getString(3) + " "  );
                        System.out.print("| " + r3.getString(4) + " " );
                        System.out.print("| " + r3.getString(5) + " "  );
                        System.out.print("| " + r3.getString(6) + " "  );
                        System.out.print("| " + r3.getString(7) + " "  );
                        System.out.println("|");
                    }
                } else if (search3 == 2) {
                    //descending order & search by manufacturer name
                    String order = " ORDER BY P.pprice DESC;";
                    String sql4 = msql +a+ order;
                    ResultSet r4 = stmt.executeQuery(sql4);
                    System.out.println("| ID | Nname | Manufacturer | Category | Quantity | Warranty | Price |");
                    while (r4.next()) {
                        System.out.print("| " + r4.getInt(1) + " "  );
                        System.out.print("| " + r4.getString(2) + " " );
                        System.out.print("| " + r4.getString(3) + " "  );
                        System.out.print("| " + r4.getString(4) + " " );
                        System.out.print("| " + r4.getString(5) + " "  );
                        System.out.print("| " + r4.getString(6) + " "  );
                        System.out.print("| " + r4.getString(7) + " "  );
                        System.out.println("|");
                    }
                }
            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }

    }

    public static void sell() {
        Statement stmt = null;
        ResultSet r = null;



        try {
            System.out.print("Enter The Part ID: ");
            Scanner scan4 = new Scanner(System.in);
            int search1 = scan4.nextInt();

            System.out.print("Enter The Salesperson ID: ");
            Scanner scan5 = new Scanner(System.in);
            int search2 = scan5.nextInt();

            stmt = con.createStatement();
            //find quantity
            String sql = """
                    SELECT pavailablequantity
                    FROM part
                    WHERE pid = """;
            String fsql = sql  + String.valueOf(search1)  ;
            r = stmt.executeQuery(fsql);

            Integer t = null; //for storing quantity no.
            String t1 = null; //for storing part name
            String t2 = null; //for storing manufacturer name

            while (r.next()) {
                t = r.getInt(1);
            }
            Integer ts1 = null;
            Integer ts2 = null;
            String maxsid = """
                            SELECT MAX(sid)
                            FROM salesperson""";
            ResultSet s1 = stmt.executeQuery(maxsid);
            while (s1.next()) {
                ts1 = s1.getInt(1);
            }
            String minsid = """
                            SELECT MIN(sid)
                            FROM salesperson""";
            ResultSet s2 = stmt.executeQuery(minsid);
            while (s2.next()) {
                ts2 = s2.getInt(1);
            }
            if(t == null || search2 > ts1 || search2 < ts2){
                // if the part does not exist
                Integer tp1 = null;
                Integer tp2 = null;
                String maxpart = """
                            SELECT MAX(pid)
                            FROM part""";
                ResultSet p1 = stmt.executeQuery(maxpart);
                while (p1.next()) {
                    tp1 = p1.getInt(1);
                }
                String minpart = """
                            SELECT MIN(pid)
                            FROM part""";
                ResultSet p2 = stmt.executeQuery(minpart);
                while (p2.next()) {
                    tp2 = p2.getInt(1);
                }
                if (search1 > tp1 || search1 < tp2) {
                    System.out.println("The part is unavailable! Please choose another part.");
                }


                if (search2 > ts1 || search2 < ts2) {
                    System.out.println("The Salesperson ID does not exist.");
                }

            }else if( t > 0 ) {
                //for quantity more than 1
                System.out.print("Product: ");
                String sql1 = """
                    SELECT P.pname, M.mname
                    FROM part P, manufacturer M
                    WHERE P.cid = M.mid AND P.pid = """;
                String fsql1 = sql1 + String.valueOf(search1) ;
                ResultSet r1 = stmt.executeQuery(fsql1);

                while (r1.next()) {
                    t1 = r1.getString(1);
                    t2 = r1.getString(2);
                }
                Integer w = t - 1; //quantity - 1
                //update quantity
                String sql0 = """
                    UPDATE part
                    SET pavailablequantity = """;
                String sql00 = " WHERE pid = ";
                String fsql0 = sql0 + w + sql00 + search1;
                stmt.executeUpdate(fsql0);
                //print the message
                String m = t2 + " " + t1 + "(id: " + String.valueOf(search1) + ") " + "Remaining Quality: " + String.valueOf(w);
                System.out.println(m);

                Integer tid = null; //for storing transaction ID
                //finding the last transaction ID
                String tran = """
                            SELECT MAX(tid)
                            FROM Transaction""";
                ResultSet tran1 = stmt.executeQuery(tran);
                if(tran1 != null){
                    while (tran1.next()) {
                        tid = tran1.getInt(1);
                    }
                    tid = tid + 1;
                }else{
                    tid = 1;
                }

                String isql = """
                            INSERT INTO transaction(tid, pid, sid, tdate)
                            VALUES(""";

                //find today's date
                Date d = new Date();
                String date = null;
                Integer datey = d.getYear()+1900;
                Integer datem = d.getMonth()+1;
                Integer dated = d.getDate();
                if(dated < 10){
                    date = "'" + "0" + dated + "/" + datem + "/" + datey + "'";
                }else {
                    date = "'" + dated + "/" + datem + "/" + datey + "'";
                }
                String insert = isql + tid + ", " + search1 + ", " + search2 + ", " + date + ");";
                stmt.executeUpdate(insert);


            } else if (t <= 0){
                System.out.println("This part is sold out!");
            }






        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }


    public static void Salesperson() throws SQLException {

        String openning = """
                -----Operations for salesperson menu-----
                What kinds of operation would you like to perform?
                1. Search for parts
                2. Sell a part
                3. Return to the main menu
                Enter Your Choose:\s""";

        try {
            con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
            while (true) {
                System.out.print(openning);
                Scanner scan = new Scanner(System.in);
                int menu_input = scan.nextInt();

                //for different choices(1, 2, 3)
                if (menu_input == 1) {
                    search();
                } else if (menu_input == 2) {
                    sell();
                } else if (menu_input == 3) {
                    break;
                }
            }

        } catch(Exception ie){
            ie.printStackTrace();
        }
    }
}