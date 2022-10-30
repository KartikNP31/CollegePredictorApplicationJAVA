
import java.sql.*;


public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            String name = "damin";
            String pass= "ace";
            String email = "daminkhan@gmail.com";
//            String [] column = {"username","password","e_mail"};
            Statement statement = connnection.createStatement();
//            statement.execute("insert into user_details value('"+name+"', '"+pass +"', '"+email+"')");

            ResultSet resultSet = statement.executeQuery("select * from user_details where username='"+name+"' and password='"+pass+"'");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3));
            }
            connnection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}