package PERSON;
import INSTITUTE.Institute;
import java.sql.*;
import java.util.*;

public class Admin extends Person {
    private String Position;
    
    public void setPosition(String position) {
        Position = position;
    }
    
    public Admin()
    {
    
    }
    public Admin(String username, String password)
    {
        this();
        setUsername(username);
        setPassword(password);
    }
    public Admin(String username,String email, String password)
    {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }
    
    
    public void addNewAdmin(Connection connection, String adminID, String email, String password)
    {
        try {
            PreparedStatement statement1 = connection.prepareStatement("select count(adminID) from admin_details where adminID = ?");
            statement1.setString(1,adminID);
            ResultSet rs1 = statement1.executeQuery();
            int count_username =0, count_email =0;
            if(rs1.next())
            {
                count_username = rs1.getInt(1);
            }
            PreparedStatement statement2 = connection.prepareStatement("select count(email) from admin_details where email = ?");
            statement2.setString(1,email);
            ResultSet rs2 = statement2.executeQuery();
            if(rs2.next())
            {
                count_email = rs2.getInt(1);
            }
            
            if(count_username >= 1 || count_email >= 1)
            {
                if(count_username>=1 && count_email >=1)
                {
                    System.out.println("Above AdminID and e-mail ID is already a administrator.\nPlease Login with your Admin ID / E-mail ID and Password!");
                }else if(count_email >= 1)
                {
                    System.out.println("Given E-mail is already registered.");
                }else {
                    System.out.println("Given Admin ID is not available, try with Different Admin ID.");
                }
                
            }
            else {
                PreparedStatement statement = connection.prepareStatement("insert into admin_details(adminID,email,Password,position) values(?,?,?,'admin')");
                statement.setString(1, adminID);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.execute();
                System.out.println(adminID+", is successfully Registered as  a new Admin");
                
            }
        }
        catch (Exception e)
        {
            System.out.println("Application error : Database connectivity problem.");
            
        }
    }
    
    
    public void removeAdmin(Connection connection, String adminID,String email){
        try {
            PreparedStatement statement = connection.prepareStatement("select count(*) from admin_details where adminID = ? and email = ?");
            statement.setString(1,adminID);
            statement.setString(2,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() && resultSet.getInt(1)==1)
            {
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM admin_details WHERE adminID = ? and email = ? and position not like 'owner'");
                stmt.setString(1,adminID);
                stmt.setString(2,email);
                if(stmt.executeUpdate()==1)
                {
                    System.out.println(" You Successfully removed Admin : '" + adminID + "' with E-mail ID : '"+email+"'");
                }else {
                    System.out.println("You can't remove the owner.");
                    
                }
            }else {
                System.out.println("Given Admin ID and E-mail not exists");
            }
        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }
    }

    
    
    
    public boolean resetPassword(Connection connection, Scanner sc) {
        String oldPass, newPass;
        boolean checkStatus = false;
        System.out.println("Enter Old Password");
        oldPass = sc.next();

        if (getPassword().compareTo(oldPass) == 0) {
            System.out.println("Enter New Password");
            newPass = sc.next();

            try {
                PreparedStatement stmt = connection.prepareStatement("update admin_details SET password= ? where password= ? and adminID= ? ");
                stmt.setString(1, newPass);
                stmt.setString(2, getPassword());
                stmt.setString(3, getUsername());

                if (stmt.executeUpdate()==1) {
                    System.out.println("Password Updated");
                    setPassword(newPass);
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

        return checkStatus;
    }
    
    public boolean Login(Connection connection)
    {
        try {
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
                    setPosition(resultSet1.getString("position"));
                }
                System.out.println("Login Successful");
                return true;
            }else {
                System.out.println("Login Failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
            return false;
        }
    }
    
    public boolean removeInstitute(Connection connection, String InstituteName){
        try {
            int count =0;
            for (int i = 1; i <= 6; i++) {
                PreparedStatement statement1 = connection.prepareStatement("DELETE FROM round"+i+" WHERE Institute = ?");
                statement1.setString(1,InstituteName);
                if(statement1.executeUpdate() ==0)
                {
                    count++;
                }
            }
            return count != 6;
        }
        catch (Exception e)
        {
            System.out.println("Application error : Database connectivity Problem");
            return false;
        }
    }
    
    public void topBorderAdminTable() {
        System.out.print("+");
        for (int i = 0; i < 7; i++) {
            System.out.print("----------");
        }
        System.out.print("+");
        for (int i = 0; i < 7; i++) {
            System.out.print("----------");
        }
        System.out.println("+------------+");
    }
    
    public void adminTableHeadline() {
        topBorderAdminTable();
        System.out.printf("| %-68s | %-68s | %-10s |\n", "Admin ID", " E-mail ID","Position");
        topBorderAdminTable();
    }
    public void getAdminList(Connection connection){
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin_details order by adminID");
            
            adminTableHeadline();
            while(rs.next()){
                topBorderAdminTable();
                System.out.printf("| %-68s | %-68s | %-10s |\n",rs.getString(1),rs.getString(2),rs.getString(4));
            }
            topBorderAdminTable();
            
        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }
    }

    
    
    
    
    public void getUserList(Connection connection, Scanner scanner){
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username,email,category,gender,generalRank,categoryRank FROM user_details order by username");
            
            ArrayList<User> UserList = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
                UserList.add(user);
            }
            User user1 = new User();
            user1.printUserList(UserList);
            user1.sortUserList(UserList, scanner);
        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    
    //Search via ID/Primary-Key => Single Record
    public void getUser(Connection connection,String username, String email){
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT username,email,category,gender,generalRank,categoryRank ,count(*) FROM user_details where username = ? and email = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1,username);
            stmt.setString(2,email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() && rs.getInt(7) ==0)
            {
                System.out.println("User not exist / Invalid Username or E-mail");
            }
            rs.previous();
            ArrayList<User> UserList = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
                UserList.add(user);
            }
            User user1 = new User();
            user1.printUserList(UserList);
        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    
    
    
    
    public void removeUser(Connection connection,String username,String email){
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM user_details WHERE username = ? and email= ?");
            stmt.setString(1,username);
            stmt.setString(2,email);
            int count =stmt.executeUpdate();
            if(count ==1)
            {
                System.out.println("You removed User : '" + username + "' with E-mail ID : '"+email+"', Successfully");
                
            }else {
                System.out.println("Given Username and E-mail not found in database, so '"+username+"' can't be removed");
                
            }
        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    
    
    
    public void UploadDeletedUserCSV(Connection connection){
        
        try{
            CSVFileHandle.addUser_deletedCsvToDatabasesUser_deleted("./PERSON/user_deleted.csv", connection);
            PreparedStatement stmt= connection.prepareStatement("select * from user_deleted");
            ResultSet rs= stmt.executeQuery();
            ArrayList<User> UserList = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
                UserList.add(user);
            }
            User user1 = new User();
            user1.printUserList(UserList);
        }
        catch (Exception e){
            System.out.println("Application error : Database connectivity problem.");
        }
    }
    
    public  void UpdateUserData(Connection connection){
        CSVFileHandle.UpdateCategoryData_CSVtoDatabase("./PERSON/user_updatedCategory.csv", connection);
        CSVFileHandle.UpdateGenderData_CSVtoDatabase("./PERSON/user_updatedGender.csv", connection);
        CSVFileHandle.UpdateGeneralRankData_CSVtoDatabase("./PERSON/user_updatedGeneralRank.csv", connection);
        CSVFileHandle.UpdateCategoryRankData_CSVtoDatabase("./PERSON/user_updatedCategoryRank.csv", connection);
        try{
            PreparedStatement stmt= connection.prepareStatement("select * from user_details where username = ? and email = ?");
            stmt.setString(1,this.getUsername());
            stmt.setString(2,this.getEmail());
            ResultSet rs= stmt.executeQuery();
            ArrayList<User> UserList = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
                UserList.add(user);
            }
            User user1 = new User();
            user1.printUserList(UserList);
        }
        catch (Exception e){
            System.out.println("Application error : Database connectivity problem.");
        }
    }
    
    
    
}
