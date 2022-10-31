import java.sql.*;
import java.util.*;


public class database{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAPROJECT", "root", "ace@mysql325");
            Statement stmt = con.createStatement();

            System.out.printf("1-Admin Login \n2-Student Login \n4-Exit\n");
            System.out.printf("Enter the choice: ");
            int choice = sc.nextInt();

            while(choice != 4){
            switch (choice) {
                case 1:

                    sc.nextLine();
                    System.out.printf("Enter the admin user-name: ");
                    String admin_name = sc.nextLine();
                    System.out.printf("Enter the admin password: ");
                    String admin_pass = sc.nextLine();
                    ResultSet rs = stmt.executeQuery("select COUNT(NAME) from admin where NAME = '" + admin_name + "' AND PASSWORD = '" + admin_pass + "'" );
                    while(rs.next()){
                        if(rs.getInt("COUNT(NAME)") == 1){
                            System.out.println("Logged in as Administrator!");
                            System.out.println("1-Create New Institue \n2-Update the details \n3-Delete the details \n4-Exit");
                            System.out.println("Enter the operation you want to perform: ");
                            int choice2 = sc.nextInt();
                            while(choice2 != 4){
                                switch(choice2){
                                    case 1:
                                        System.out.println("Enter the CSAB round: ");
                                        int round = sc.nextInt();

                                }

                            }
                            choice = 3;
                        }
                        else if(rs.getInt("COUNT(NAME)") == 0){
                            System.out.println("Log in failed");
                            choice = 3;
                        }
                    }
                    break;

                case 2:
                    choice = 3;
                    break;

                case 3:
                    System.out.printf("1-Admin Login \n2-Student Login \n4-Exit\n");
                    System.out.printf("Enter the choice: ");
                    choice = sc.nextInt();
                    break;
            }
        }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}