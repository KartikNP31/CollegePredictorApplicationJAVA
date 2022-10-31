 import java.sql.*;
public class Admin extends Person{

    Admin()
    {
        this(null,null,null);
    }
    Admin(String username,String email,String password)
    {
        super(username,email,password);
    }
    public void addNewAdmin(String adminID, String email, String password) throws SQLException
    {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("insert into admin_details(adminID,email,Password) values(?,?,?)");

            statement.setString(1,adminID);
            statement.setString(2,email);
            statement.setString(3,password);

            statement.executeUpdate();


            connection.close();

    }

    public boolean Login()
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("select count(*) from user_details where (username= ? or e_mail= ?) and password = ?");

            statement.setString(1,getUsername());
            statement.setString(2,getEmail());
            statement.setString(3,getPassword());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next() && resultSet.getInt(1)==1)
            {
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

}
