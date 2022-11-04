import java.sql.*;
import java.util.Scanner;

public class User extends Person {
    private String gender;
    private String category;
    // private String examType;
    private int GeneralRank;
    private int CategoryRank;
    
    User() {
    
    }
    
    User(String userName, String password)
    {
        setUsername(userName);
        setPassword(password);
    }
    User(String userName,String email, String password, String category,String gender,int categoryRank,int generalRank) {
        super.setUsername(userName);
        super.setEmail(email);
        super.setPassword(password);
        setCategory(category);
        setCategoryRank(categoryRank);
        setGender(gender);
        setGeneralRank(generalRank);
    }
    User(String userName,String email, String category,String gender,int categoryRank,int generalRank) {
        super.setUsername(userName);
        super.setEmail(email);
        setCategory(category);
        setCategoryRank(categoryRank);
        setGender(gender);
        setGeneralRank(generalRank);
    }
    
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
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
    public int getGeneralRank() {
        return GeneralRank;
    }
    
    public int getCategoryRank() {
        return CategoryRank;
    }
    
    public void topBorderUserTable() {
        System.out.print("+");
        for (int i = 0; i < 22; i++) {
            System.out.print("---------");
        }
        System.out.println("+");
    }
    
    public void printUser(){
        topBorderUserTable();
        System.out.printf("| %-40s | %-65s | %-11s | %-39s | %-13s | %-13s |\n", super.getUsername(),super.getEmail(),getCategory(),getGender(),getGeneralRank(),getCategoryRank());
    }
    public boolean Register(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into user_details(username,email,password,gender,category,generalRank,categoryRank) values(?,?,?,?,?,?,?)");
            statement.setString(1, getUsername());
            statement.setString(2, getEmail());
            statement.setString(3, getPassword());
            statement.setString(4, getGender());
            statement.setString(5, getCategory());
            statement.setInt(6, getGeneralRank());
            statement.setInt(7, getCategoryRank());
            statement.execute();
            System.out.println("You have Registered Successfully ! ");
            return true;
        } catch (Exception e) {
            System.out.println("Above username or e-mail ID is already registered.\nPlease Login with your Username/email ID and Password!");
            return false;
        }
    }
    
    public boolean Login(Connection connection) {
        try {
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
                    setUsername(resultSet1.getString("username"));
                    setEmail(resultSet1.getString("email"));
                    setPassword(resultSet1.getString("password"));
                    setGender(resultSet1.getString("gender"));
                    setCategory(resultSet1.getString("category"));
                    setGeneralRank(resultSet1.getInt("generalRank"));
                    setCategoryRank(resultSet1.getInt("categoryRank"));
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void UpdateUserDetails(Connection connection) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        String str, pass;
        int rank;

        while (check) {
            System.out.println("Choose What You Want to Update");
            System.out.println("\n1.Update Username\n2.Update Password\n3.Update Gender\n4.Update Category\n5.Update GeneralRank\n6.Update CategoryRank");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("Enter Password");
                    pass = sc.nextLine();
                    System.out.println("Enter New Username");
                    str = sc.nextLine();
                    try {
                        PreparedStatement stmt2 = connection.prepareStatement("update user_details SET username=? where username=? and password=?");
                        stmt2.setString(1, str);
                        stmt2.setString(2, getUsername());
                        stmt2.setString(3, pass);
                        if (stmt2.executeUpdate() == 1) {
                            System.out.println("Username Updated");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 2:
                    System.out.println("Enter Old Password");
                    pass = sc.nextLine();
                    System.out.println("Enter New Password");
                    str = sc.nextLine();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET password=? where username=? and password=?");
                        stmt3.setString(1, str);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.execute()) {
                            System.out.println("Password Updated");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    System.out.println("Enter Password");
                    pass = sc.nextLine();
                    System.out.println("Enter New Gender");
                    str = sc.nextLine();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET gender=? where username=? and password=?");
                        stmt3.setString(1, str);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.execute()) {
                            System.out.println("Gender Updated");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 4:
                    System.out.println("Enter Password");
                    pass = sc.nextLine();
                    System.out.println("Enter New Category");
                    str = sc.nextLine();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET category=? where username=? and password=?");
                        stmt3.setString(1, str);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.execute()) {
                            System.out.println("Category Updated");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.out.println("Enter Password");
                    pass = sc.nextLine();
                    System.out.println("Enter New GeneralRank");
                    rank = sc.nextInt();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET generalRank=? where username=? and password=?");
                        stmt3.setInt(1, rank);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.execute()) {
                            System.out.println("General Rank Updated");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 6:
                    System.out.println("Enter Password");
                    pass = sc.nextLine();
                    System.out.println("Enter New CategoryRank");
                    rank = sc.nextInt();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET categoryRank=? where username=? and password=?");
                        stmt3.setInt(1, rank);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.execute()) {
                            System.out.println("Category Rank Updated");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 7:
                    check = false;
                    break;
            }

        }
    }
}
