import PERSON.*;
import INSTITUTE.*;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter your JDBC Password");
//            String jdbcPassword;
//            jdbcPassword =scanner.next();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            System.out.println("Welcome To JAVA College Predictor Application");
            
            User u  = new SearchInstitute("katrina","kk143vk");
            if(u.Login(connection))
            {
                SearchInstitute searchInstitute = (SearchInstitute) u;
                searchInstitute.searchCollege(connection);
            }
            
//            SearchInstitute s = new SearchInstitute();
//            s.IncognitoSearch(connection);
            
            

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}