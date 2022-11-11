import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
        System.out.println("Do you want to Sort User List\n1.Yes     2.No    (Select option number 1 or 2");
        int opt = scanner.nextInt();
        while (opt == 1) {
            switch (opt)
            {
                case 1: {
                    System.out.println("Select proper number (1-5) for Parameter by which you want to sort User List\n1.Username     2.Category      3.Gender      4.Category Rank     5.General Rank");
                    int para = scanner.nextInt();
                    switch (para)
                    {
                        case 1 :
                        {
                            Collections.sort(arrayList);
                            System.out.println("User List is Sorted by Username");
                            printUserList(arrayList);
                            break;
                        }
                        case 2 :
                        {
                            arrayList.sort(User::compareTo1);
                            System.out.println("User List is Sorted by Category");
                            printUserList(arrayList);
                            break;
                        }
                        case 3 :
                        {
                            arrayList.sort(User::compareTo2);
                            System.out.println("User List is Sorted by Gender");
                            printUserList(arrayList);
                            break;
                        }
                        case 4 :
                        {
                            arrayList.sort(User::compareTo3);
                            System.out.println("User List is Sorted by General Rank");
                            printUserList(arrayList);
                            break;
                        }
                        case 5 :
                        {
                            arrayList.sort(User::compareTo4);
                            System.out.println("User List is Sorted by Category Rank");
                            printUserList(arrayList);
                            break;
                        }
                        default:{
                            System.out.println("Error : Invalid option is Selected !");
                            break;
                        }
                    }
                    System.out.println("Do you want to Sort it again\n1.Yes     2.No    (Select option number 1 or 2)");
                    opt = scanner.nextInt();
                    break;
                }
                default: {
                    opt =2;
                    break;
                }
            }
        }
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
            printUserList(UserList);
            sortUserList(UserList);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void removeAdmin(Connection connection, String adminID){
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM admin_details WHERE adminID = ? ");
            stmt.setString(1,adminID);
            if(stmt.executeUpdate()==1)
            {
                System.out.println("AdminID " + adminID + " was successfully removed");
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void removeUser(Connection connection,String username){
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM user_details WHERE adminID ='" + username + "'");
            System.out.println("User with username " + username + " was successfully removed");
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    
    
}
