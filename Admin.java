import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

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
    public boolean addNewAdmin(Connection connection, String adminID, String email, String password)
    {
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "#KAR331@tikNP");
            PreparedStatement statement = connection.prepareStatement("insert into admin_details(adminID,email,Password) values(?,?,?)");
            statement.setString(1, adminID);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.execute();
            
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Above AdminID or e-mail ID is already a administrator.\nPlease Login with your Username/email ID and Password!");
            return false;
        }
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
                }
                
                return true;
            }else {
                
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean removeInstitute(Connection connection, String InstituteName){
        try {
            int count =0;
            for (int i = 1; i <= 6; i++) {
                PreparedStatement statement1 = connection.prepareStatement("DELETE FROM round"+i+" WHERE Institute = ?");
                statement1.setString(1,InstituteName);
                if(statement1.executeUpdate() >= 1)
                {
                    count++;
                }
            }
            if(count == 6)
            {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
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
        System.out.println("+");
    }
    
    public void adminTableHeadline() {
        topBorderAdminTable();
        System.out.printf("| %-68s | %-68s |\n", " Admin ID", " E-mail ID");
        topBorderAdminTable();
    }
    public void getAdmin(Connection connection){
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin_details order by adminID");
            
            adminTableHeadline();
            while(rs.next()){
                topBorderAdminTable();
                System.out.printf("| %-68s | %-68s |\n",rs.getString(1),rs.getString(2));
            }
            topBorderAdminTable();
            
        }
        catch(Exception e){
            System.out.println(e);
        }
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
    
    
    public void getUser(Connection connection){
        try{
            Scanner scanner  = new Scanner(System.in);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username,email,category,gender,generalRank,categoryRank FROM user_details order by username");
            
            ArrayList<User> UserList = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
                UserList.add(user);
            }
            
            userTableHeadline();
            for (User i : UserList)
            {
                i.printUser();
            }
            topBorderUserTable();
    
            System.out.println("Do you want to User List\n1.Yes     2.No    (Select option number 1 or 2");
            int opt = scanner.nextInt();
            
            switch (opt)
            {
                case 1:
                {
                    System.out.println("Select proper number for Parameter by which you want to sort User List\n1.User Name     2.Category      3.Category Rank     4.General Rank");
                    int para = scanner.nextInt();
                    switch (para)
                    {
//                        case 1:
//                        case 1:
//                        case 1:
//                        case 1:
//                        case 1:
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void removeadmin(Connection connection, String adminid){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM admin_details WHERE adminID ='" + adminid + "'");
            System.out.println("AdminID " + adminid + " was sucessfully removed");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void removeuser(Connection connection,String username){
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM user_details WHERE adminID ='" + username + "'");
            System.out.println("User with username " + username + " was sucessfully removed");
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    
    
}
