import java.sql.*;

public class User extends Person {
    private String gender;
    private String category;
    // private String examType;
    private int GeneralRank;
    private int CategoryRank;
    
    User() {
    
    }
    
    User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    // public void setExamType(String examType) {
    //     this.examType = examType;
    // }
    
    public void setGeneralRank(int GeneralRank) {
        this.GeneralRank = GeneralRank;
    }
    
    public void setCategoryRank(int categoryRank) {
        CategoryRank = categoryRank;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getGender() {
        return gender;
    }
    
    // public String getExamType() {
    //     return examType;
    // }
    
    public int getGeneralRank() {
        return GeneralRank;
    }
    
    public int getCategoryRank() {
        return CategoryRank;
    }
    
    
    public boolean register(String userName, String email, String password, String gender, String category, int generalRank, int categoryRank) {
        super.setUsername(userName);
        super.setEmail(email);
        super.setPassword(password);
        setCategory(category);
        setCategoryRank(categoryRank);
        setGender(gender);
        setGeneralRank(generalRank);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("insert into user_details(username,email,password,gender,category,generalRank,categoryRank) values(?,?,?,?,?,?,?)");
            statement.setString(1, getUsername());
            statement.setString(2, getEmail());
            statement.setString(3, getPassword());
            statement.setString(4, getGender());
            statement.setString(5, getCategory());
            statement.setInt(6, getGeneralRank());
            statement.setInt(7, getCategoryRank());
            statement.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Above username or e-mail ID is already registered.\nPlease Login with your Username/email ID and Password!");
            return false;
        }
    }
    
    public boolean Login() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("select count(*) from user_details where (username= ? or email= ?) and password = ? ");
            
            statement.setString(1, getUsername());
            statement.setString(2, getUsername());
            statement.setString(3, getPassword());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next() && resultSet.getInt(1) == 1) {
                PreparedStatement retriveUser = connection.prepareStatement("select * from user_details where (username= ? or email= ?) and password = ? ");
                retriveUser.setString(1, getUsername());
                retriveUser.setString(2, getUsername());
                retriveUser.setString(3, getPassword());
                ResultSet resultSet1 = retriveUser.executeQuery();
                while (resultSet1.next()) {
                    setUsername(resultSet1.getString(1));
                    setEmail(resultSet1.getString(2));
                    setPassword(resultSet1.getString(3));
                    setGender(resultSet1.getString(4));
                    setCategory(resultSet1.getString(5));
                    setGeneralRank(resultSet1.getInt(6));
                    setCategoryRank(resultSet1.getInt(7));
                }
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
