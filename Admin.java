 import java.sql.*;
 import java.util.ArrayList;

 public class Admin extends Person{

    Admin()
    {
        this(null,null);
    }
    Admin(String username,String password)
    {
        setUsername(username);
        setPassword(password);
    }
    public boolean addNewAdmin(String adminID, String email, String password)
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("insert into admin_details(adminID,email,Password) values(?,?,?)");
            statement.setString(1, adminID);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.execute();
            connection.close();
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Above AdminID or e-mail ID is already a administrator.\nPlease Login with your Username/email ID and Password!");
            return false;
        }
    }

    public boolean Login()
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("select count(*) from admin_details where (adminID= ? or email= ?) and password = ?");

            statement.setString(1,getUsername());
            statement.setString(2,getUsername());
            statement.setString(3,getPassword());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next() && resultSet.getInt(1)==1)
            {

                PreparedStatement retriveUser = connection.prepareStatement("select * from admin_details where (adminID= ? or email= ?) and password = ?");
                retriveUser.setString(1,getUsername());
                retriveUser.setString(2,getUsername());
                retriveUser.setString(3,getPassword());
                ResultSet resultSet1 = retriveUser.executeQuery();
                while (resultSet1.next())
                {
                    setUsername(resultSet1.getString("adminID"));
                    setEmail(resultSet1.getString("email"));
                    setPassword(resultSet1.getString("password"));
                }
                connection.close();
                return true;
            }else {
                connection.close();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void deletecollegeallrounds(String InstituteName){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "#KAR331@tikNP");
            Statement stmt = connection.createStatement();

            ResultSet rs1 = stmt.executeQuery("DELETE FROM round1 WHERE Institute = '" + InstituteName + "'");
            ResultSet rs2 = stmt.executeQuery("DELETE FROM round2 WHERE Institute = '" + InstituteName + "'");
            ResultSet rs3 = stmt.executeQuery("DELETE FROM round3 WHERE Institute = '" + InstituteName + "'");
            ResultSet rs4 = stmt.executeQuery("DELETE FROM round4 WHERE Institute = '" + InstituteName + "'");
            ResultSet rs5 = stmt.executeQuery("DELETE FROM round5 WHERE Institute = '" + InstituteName + "'");
            ResultSet rs6 = stmt.executeQuery("DELETE FROM round6 WHERE Institute = '" + InstituteName + "'");

            System.out.println("Institute "+ InstituteName +" is sucessfully removed");

            connection.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

     public void getadmin(){
         try{
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "ace@mysql325");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM admin_details");
             while(rs.next()){
                 System.out.println("AdminID: " + rs.getString(1));
                 System.out.println("E-maiL: " + rs.getString(2));
                 System.out.println("Password: " + rs.getString(3));
                 System.out.printf("\n");
             }
         }
         catch(Exception e){
             System.out.println(e);
         }
     }

     public void getuser(){
         try{
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "ace@mysql325");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM user_details");
             while(rs.next()){
                 System.out.println("Username: " + rs.getString(1));
                 System.out.println("E-maiL: " + rs.getString(2));
                 System.out.println("Password: " + rs.getString(3));
                 System.out.println("Gender: " + rs.getString(4));
                 System.out.println("Category: " + rs.getString(5));
                 System.out.println("General Rank: " + rs.getString(6));
                 System.out.println("Category Rank: " + rs.getString(7));

                 System.out.printf("\n");
             }
         }
         catch(Exception e){
             System.out.println(e);
         }
     }

     public void removeadmin(String adminid){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "ace@mysql325");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM admin_details WHERE adminID ='" + adminid + "'");
            System.out.println("AdminID " + adminid + " was sucessfully removed");
        }
        catch(Exception e){
            System.out.println(e);
        }
     }

     public void removeuser(String username){
         try {
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "ace@mysql325");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("DELETE FROM user_details WHERE adminID ='" + username + "'");
             System.out.println("User with username " + username + " was sucessfully removed");
         }
         catch(Exception e){
             System.out.println(e);
         }

     }


}
