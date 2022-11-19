package PERSON;

import INSTITUTE.Institute;

import java.sql.*;
import java.util.*;

public class User extends Person implements Comparable {
    private String gender;
    private String category;
    private int GeneralRank;
    private int CategoryRank;
    
    public User() {
    
    }
    
    public User(String userName, String password) {
        setUsername(userName);
        setPassword(password);
    }
    
    public User(String userName, String email, String password, String category, String gender, int categoryRank, int generalRank) {
        super.setUsername(userName);
        super.setEmail(email);
        super.setPassword(password);
        setCategory(category);
        setCategoryRank(categoryRank);
        setGender(gender);
        setGeneralRank(generalRank);
    }
    
    public User(String userName, String email, String category, String gender, int categoryRank, int generalRank) {
        super.setUsername(userName);
        super.setEmail(email);
        setCategory(category);
        setCategoryRank(categoryRank);
        setGender(gender);
        setGeneralRank(generalRank);
    }
    
    public String defineGender(String str) {
        String gender;
        if (str.toLowerCase().contains("f") ) {
            gender = "Female-only (including Supernumerary)";
        } else {
            gender = "Gender-Neutral";
        }
        return gender;
    }
    public boolean Register(Connection connection) {
        try {
            PreparedStatement statement1 = connection.prepareStatement("select count(username) from user_details where username = ?");
            statement1.setString(1,getUsername());
            ResultSet rs1 = statement1.executeQuery();
            int count_username =0, count_email =0;
            if(rs1.next())
            {
                count_username = rs1.getInt(1);
            }
            PreparedStatement statement2 = connection.prepareStatement("select count(email) from user_details where email = ?");
            statement2.setString(1,getEmail());
            ResultSet rs2 = statement2.executeQuery();
            if(rs2.next())
            {
                count_email = rs2.getInt(1);
            }
    
            if(count_username >= 1 || count_email >= 1)
            {
                if(count_username>=1 && count_email >=1)
                {
                    System.out.println("Above Username and e-mail ID is already Registered.\nPlease Login with your Username/email ID and Password!");
                }else if(count_email >= 1)
                {
                    System.out.println("Given E-mail is already registered.");
                }else {
                    System.out.println("Given Username is not available, try with Different Username.");
                }
                return false;
            }
            else
            {
                PreparedStatement statement = connection.prepareStatement("insert into user_details(username,email,password,gender,category,generalRank,categoryRank) values(?,?,?,?,?,?,?)");
                statement.setString(1, getUsername());
                statement.setString(2, getEmail());
                statement.setString(3, getPassword());
                statement.setString(4, defineGender(getGender()));
                statement.setString(5, getCategory());
                statement.setInt(6, getGeneralRank());
                statement.setInt(7, getCategoryRank());
                statement.execute();
                System.out.println("You have Registered Successfully ! ");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity problem.");
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
                System.out.println("Login Successful");
                return true;
            } else {
                System.out.println("Login Failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
            return false;
        }
    }
    
    public void UpdateUserDetails(Connection connection) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        String str, pass;
        System.out.println("Enter Password");
        pass = sc.nextLine();
        int rank;
        
        while (check) {
            System.out.println("Choose What You Want to Update");
            System.out.println("1.Update Gender\n2.Update Category\n3.Update GeneralRank\n4.Update CategoryRank\n5.Exit");
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    System.out.println("Enter New Gender");
                    str = sc.nextLine();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET gender=? where username=? and password=?");
                        stmt3.setString(1, str);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.executeUpdate() == 1) {
                            System.out.println("Gender Updated");
                        }else {
                            System.out.println("Incorrect Password");
                        }
                    } catch (Exception e) {
                        System.out.println("Application error : Database connectivity Problem");
                    }
                    break;
                
                case 2:
                    System.out.println("Enter New Category");
                    str = sc.nextLine();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET category=? where username=? and password=?");
                        stmt3.setString(1, str);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.executeUpdate() == 1) {
                            System.out.println("Category Updated");
                        }else {
                            System.out.println("Incorrect Password");
                        }
                    } catch (Exception e) {
                        System.out.println("Application error : Database connectivity Problem");
                    }
                    break;
                
                case 3:
                    System.out.println("Enter New GeneralRank");
                    rank = sc.nextInt();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET generalRank=? where username=? and password=?");
                        stmt3.setInt(1, rank);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.executeUpdate() == 1) {
                            System.out.println("General Rank Updated");
                        }else {
                            System.out.println("Incorrect Password");
                        }
                    } catch (Exception e) {
                        System.out.println("Application error : Database connectivity Problem");
                    }
                    break;
                
                case 4:
                    System.out.println("Enter New CategoryRank");
                    rank = sc.nextInt();
                    try {
                        PreparedStatement stmt3 = connection.prepareStatement("update user_details SET categoryRank=? where username=? and password=?");
                        stmt3.setInt(1, rank);
                        stmt3.setString(2, getUsername());
                        stmt3.setString(3, pass);
                        if (stmt3.executeUpdate() == 1) {
                            System.out.println("Category Rank Updated");
                        }else {
                            System.out.println("Incorrect Password");
                        }
                    } catch (Exception e) {
                        System.out.println("Application error : Database connectivity Problem");
                    }
                    break;
                
                case 5:
                    check = false;
                    break;
            }
            
        }
        sc.close();
    }
    
    public boolean resetPassword(Connection connection) {
        Scanner sc = new Scanner(System.in);
        String oldPass, newPass;
        boolean checkStatus = false;
        System.out.println("Enter Old Password");
        oldPass = sc.nextLine();
        
        if (getPassword().compareTo(oldPass) == 0) {
            System.out.println("Enter New Password");
            newPass = sc.nextLine();
            
            try {
                PreparedStatement stmt = connection.prepareStatement("update user_details SET password= ? where password= ? and username= ? ");
                stmt.setString(1, newPass);
                stmt.setString(2, getPassword());
                stmt.setString(3, getUsername());
                
                if (stmt.executeUpdate()==1) {
                    System.out.println("Password Updated");
                    checkStatus = true;
                }else {
                    System.out.println("Password not Updated.");
                }
                
            } catch (Exception e) {
                System.out.println("Application error : Database connectivity Problem");
            }
        }else {
            System.out.println("Incorrect old Password, Please enter correct Password.");
        }
        sc.close();
        return checkStatus;
    }
    
    
    public boolean deleteAccount(Connection connection) {
        Scanner sc = new Scanner(System.in);
        String oldPass;
        String uname;
        boolean checkStatus = false;
        int checkVal;
        
        System.out.println("NOTE:After Deletion of Account Your Data will be Erased and you will be log out \n Press 1:Continue\n Press 2:Stop Deletion");
        checkVal = sc.nextInt();
        sc.nextLine();
        if (checkVal == 1) {
            System.out.println("Enter Username");
            uname = sc.nextLine();
            System.out.println("Enter Your Password");
            oldPass = sc.nextLine();
            
            if (oldPass.compareTo(getPassword()) == 0 && uname.compareTo(getUsername()) == 0) {
                try {
                    PreparedStatement stmt = connection.prepareStatement("delete from user_details where username = ? and password = ?");
                    stmt.setString(1, uname);
                    stmt.setString(2, oldPass);
                    int ct = stmt.executeUpdate();
                    if (ct != 0) {
                        checkStatus = true;
                        System.out.println("Account Deleted");
                        
                    } else {
                        System.out.println("Can't Delete Account");
                    }
                    return checkStatus;
                } catch (Exception e) {
                    System.out.println("Application error : Database connectivity Problem");
                }
            }
            System.out.println("Invalid Username and Password. Account can't be deleted");
        }
        return checkStatus;
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
    
    public void userTableHeadline() {
        topBorderUserTable();
        System.out.printf("| %-40s | %-65s | %-11s | %-39s | %-13s | %-13s |\n","User Name","E-mail ID","Category","Gender","General Rank","Category Rank");
        topBorderUserTable();
    }
    
    public void printUser() {
        topBorderUserTable();
        System.out.printf("| %-40s | %-65s | %-11s | %-39s | %-13s | %-13s |\n", super.getUsername(), super.getEmail(), getCategory(), getGender(), getGeneralRank(), getCategoryRank());
    }
    
    public void printUserList(ArrayList<User> arrayList)
    {
        userTableHeadline();
        for (User i : arrayList)
        {
            i.printUser();
        }
        topBorderUserTable();
    }
    
    public void sortUserList(ArrayList<User> arrayList)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to Sort User List\n1.Yes     2.No    (Select option number 1 or 2)");
        int opt = scanner.nextInt();
        while (opt == 1) {
            System.out.println("Select proper number (1-5) for Parameter by which you want to sort User List\n1.Username     2.Category      3.Gender      4.Category Rank     5.General Rank");
            int para = scanner.nextInt();
            switch (para) {
                case 1: {
                    arrayList.sort(User::compareTo);
                    System.out.println("User List is Sorted by Username");
                    printUserList(arrayList);
                    break;
                }
                case 2: {
                    arrayList.sort(User::compareTo1);
                    System.out.println("User List is Sorted by Category");
                    printUserList(arrayList);
                    break;
                }
                case 3: {
                    arrayList.sort(User::compareTo2);
                    System.out.println("User List is Sorted by Gender");
                    printUserList(arrayList);
                    break;
                }
                case 4: {
                    arrayList.sort(User::compareTo3);
                    System.out.println("User List is Sorted by General Rank");
                    printUserList(arrayList);
                    break;
                }
                case 5: {
                    arrayList.sort(User::compareTo4);
                    System.out.println("User List is Sorted by Category Rank");
                    printUserList(arrayList);
                    break;
                }
                default: {
                    System.out.println("Error : Invalid option is Selected !");
                    break;
                }
            }
            System.out.println("Do you want to Sort it again\n1.Yes     2.No    (Select option number 1 or 2)");
            opt = scanner.nextInt();
        }
    }
    
}

    

