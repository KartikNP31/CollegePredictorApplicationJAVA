
import java.sql.*;


public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            String name = "nishnat";
            String pass= "shinden";
            String email = "nishant@gmail.com";
////            User u = new User("shubham","letap_kn");
//            boolean r =u.register("shubham","shubham@gmail.com","letap_kn","male","obc",1,1);
////
////            boolean r = u.Login();
//            if(r)
//            {
//                System.out.println("yes");
//            }else {
//                System.out.println("no");
//            }
//            String [] column = {"username","password","e_mail"};
//            PreparedStatement statement = connection.prepareStatement("select * from admin_details where srno=3");
//            statement.execute("insert into user_details value('"+name+"', '"+pass +"', '"+email+"')");   where (username= ? or e_mail= ?) and password = ?
//            statement.setString(1,name);
//            statement.setString(3,pass);
//            statement.setString(2,email);

//            int update = statement.executeUpdate();
//            Statement st = connection.createStatement();
//            ResultSet resultSet = st.executeQuery("select * from admin_details");
//            while ( resultSet.next() ) {
//                System.out.println(resultSet.getString(2)+"     "+resultSet.getString(3)+"      "+resultSet.getString(4)  );
//            }
//            System.out.println(update);
//            if(resultSet.next() && resultSet.getInt(1)==1)
//            {
//                System.out.println("yes");
//            }else {
//                System.out.println("no");
//            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}