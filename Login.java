import java.sql.*;

public class Login {
    private String usernameOrEmail;
    private String password;

    Login(){
        this(null,null);
    }
    Login(String usernameOrEmail, String password)
    {
        setUsernameOrEmail(usernameOrEmail);
        setPassword(password);
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String username) {
        this.usernameOrEmail = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean administratorLogin()
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("select count(*) from user_details where (username= ? or e_mail= ?) and password = ?");

            statement.setString(1,getUsernameOrEmail());
            statement.setString(2,getUsernameOrEmail());
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
    public boolean userLogin()
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("select count(*) from user_details where (username= ? or e_mail= ?) and password = ? ");

            statement.setString(1,getUsernameOrEmail());
            statement.setString(2,getUsernameOrEmail());
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