import java.sql.*;


public class User extends Person{

    private String gender;
    private String category;
    private String examType;
    private int GeneralRank;
    private int CategoryRank;
    private int rollNumber;
    private final String userRole = "user";

    User() {
        
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public void setGeneralRank(int GeneralRank) {
        this.GeneralRank = GeneralRank;
    }

    public void setCategoryRank(int categoryRank) {
        CategoryRank = categoryRank;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getGender() {
        return gender;
    }

    public String getExamType() {
        return examType;
    }

    public int getGeneralRank() {
        return GeneralRank;
    }

    public int getCategoryRank() {
        return CategoryRank;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void register(String userName, String email,String password) {
        super.setUsername(userName);
        super.setEmail(email);
        super.setPassword(password);
        super.setRole(userRole);
    }

    public boolean login(String userName, String password) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            
            Statement statement = connnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from user_details where username == " + userName + " and password == " + password);
            if(resultSet.getInt(1)==1)
            {
                connnection.close();
                return true;
            }
            else{
                connnection.close();
                return false;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}



