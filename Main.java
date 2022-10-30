// public class Driver {
//     public static void main (String [] args) {

//         System.out.println("College Predictor");
//         System.out.println("Hello college World");


//     }
// }

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAPROJECT", "root",
                    "ace@mysql325");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from dummy");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}