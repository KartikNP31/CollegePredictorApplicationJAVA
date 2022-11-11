import java.sql.*;
import java.util.Scanner;

public class User extends Person implements Comparable {
    private String gender;
    private String category;
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
    
    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return this.getUsername().compareTo(user.getUsername());
    }
    public int compareTo1(Object o) {
        User user = (User) o;
        return this.getCategory().compareTo(user.getCategory());
    }
    public int compareTo2(Object o) {
        User user = (User) o;
        return this.getGender().compareTo(user.getGender());
    }
    public int compareTo3(Object o) {
        User user = (User) o;
        return this.getGeneralRank() - user.getGeneralRank();
    }
    public int compareTo4(Object o) {
        User user = (User) o;
        return this.getCategoryRank() - user.getCategoryRank();
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
            System.out.println("\n1.Update Gender\n2.Update Category\n3.Update GeneralRank\n4.Update CategoryRank");
            int select = sc.nextInt();
            switch (select) {
                case 1:
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

                case 2:
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

                case 3:
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

                case 4:
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

                case 5:
                    check = false;
                    break;
            }

        }
        sc.close();
    }

    public boolean resetPassword(Connection connection){
        Scanner sc = new Scanner(System.in);
        String oldpass,newpass;
        boolean checkpass=false;
        boolean checkStatus=false;
        System.out.println("Enter Old Password");
        oldpass=sc.nextLine();

        if(getPassword().compareTo(oldpass)==0 ) {
            checkpass = true;
            System.out.println("Enter New Password");
            newpass = sc.nextLine();

            try {
                PreparedStatement stmt = connection.prepareStatement("update user_details SET password= ? where password= ? and username= ? ");
                stmt.setString(1, newpass);
                stmt.setString(2, newpass);
                stmt.setString(3, getUsername());

                if (stmt.execute()) {
                    System.out.println("Password Updated");
                    checkStatus = true;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
            sc.close();
            return checkStatus;
        }


    public boolean deleteAccount(Connection connection){
        Scanner sc = new Scanner(System.in);
        String oldpass;
        String uname;
        boolean checkStatus=false;
        int checkval;

        System.out.println("NOTE::After Deletion of Account Your Data will be Erased and you will be log out \n Press 1:Continue\n Press 2:Stop Deletion");
        checkval=sc.nextInt();
        sc.nextLine();
        if(checkval == 1){
            System.out.println("Enter Username");
            uname=sc.nextLine();
            System.out.println("Enter Your Password");
            oldpass=sc.nextLine();

            if (oldpass.compareTo(getPassword()) == 0 && uname.compareTo(getUsername()) ==0){
                try {
                    PreparedStatement stmt= connection.prepareStatement("delete from user_details where username = ? and password = ?");
                    stmt.setString(1, uname);
                    stmt.setString(2, oldpass);
                    int ct=stmt.executeUpdate();
                    if (ct !=0){
                        checkStatus=true;
                        System.out.println("Account Deleted");
                        return checkStatus;
                    }
                    else {
                        System.out.println("Can't Delete Account");
                        return checkStatus;
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        }
            return checkStatus;
        }




}

    

