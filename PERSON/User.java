package PERSON;
import CSV.CSVFileHandle;

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
    
    public User(String userName, String email, String category, String gender, int generalRank, int categoryRank) {
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
            PreparedStatement stat = connection.prepareStatement("select count(*) from user_deleted where username = ? and email = ?" );
            stat.setString(1,this.getUsername());
            stat.setString(2,this.getEmail());
            ResultSet resultSetStat = stat.executeQuery();
            if(resultSetStat.next() && resultSetStat.getInt(1)!=0)
            {
                PreparedStatement deleteStat = connection.prepareStatement("delete from user_deleted where username = ? and email = ?");
                deleteStat.setString(1,this.getUsername());
                deleteStat.setString(2,this.getEmail());
                deleteStat.executeUpdate();
            }
            
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
                String[] data =new String[7];
                data[0]=getUsername();
                data[1]=getEmail();
                data[2]=getPassword();
                data[3]=getGender();
                data[4]=getCategory();
                data[5]=Integer.toString(getGeneralRank());
                data[6]=Integer.toString(getCategoryRank());
                CSVFileHandle.WritelineIntoCSV("./CSV/user_register.csv", data);
                CSVFileHandle.addCSVtoDatabase("./CSV/user_register.csv", connection);
                System.out.println("You have Registered Successfully . Here is Your Profile Information. ");
                this.userTableHeadline();
                this.printUser();
                topBorderUserTable();

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

    public  void UpdateUserData(Connection connection){
        CSVFileHandle.UpdateUserData_CSVtoDatabase("./CSV/user_updated.csv", connection);
        try{
            PreparedStatement stmt= connection.prepareStatement("select * from user_details where username = ? and email = ?");
            stmt.setString(1,this.getUsername());
            stmt.setString(2,this.getEmail());
            ResultSet rs= stmt.executeQuery();
            ArrayList<User> UserList = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(5),rs.getString(4),rs.getInt(6),rs.getInt(7));
                UserList.add(user);
            }
            User user1 = new User();
            user1.printUserList(UserList);
        }
        catch (Exception e){
            
            System.out.println("Application error : Database connectivity problem.");
        }
    }
    public void UpdateUserDetails(Connection connection,Scanner sc) {

        boolean check = true;
        String str, pass;
        int rank;
        try{
            System.out.println("Enter Password");
            pass = sc.next();
            PreparedStatement stmt = connection.prepareStatement("select count(*) from user_details where username = ? and email = ? and password = ?");
            stmt.setString(1, this.getUsername());
            stmt.setString(2, this.getEmail());
            stmt.setString(3, pass);
            ResultSet checkPass = stmt.executeQuery();
            if(checkPass.next() && checkPass.getInt(1)==1)
            {
                while (check) {
                    System.out.println("Choose What You Want to Update");
                    System.out.println("1.Update Gender\n2.Update Category\n3.Update GeneralRank\n4.Update CategoryRank\n5.Exit");
                    int select = sc.nextInt();
                    switch (select) {
                        case 1:
                        {
                            System.out.println("Enter New Gender");
                            str = sc.next();
                            String[] data =new String[4];
                            data[0]=getUsername();
                            data[1]=getEmail();
                            data[2]=str;
                            data[3] = "gender";
                            this.setGender(str);
                            CSVFileHandle.WriteLineIntoCSVForDeletionAndUpdation("./CSV/user_updated.csv", data);
                            break;
                        }

                        case 2:
                        {
                            System.out.println("Enter New Category");
                            str = sc.next();
                            String[] data =new String[4];
                            data[0]=getUsername();
                            data[1]=getEmail();
                            data[2]=str;
                            data[3] = "category";
                            this.setCategory(str);
                            CSVFileHandle.WriteLineIntoCSVForDeletionAndUpdation("./CSV/user_updated.csv", data);

                            break;
                        }

                        case 3:
                        {
                            System.out.println("Enter New GeneralRank");
                            rank = sc.nextInt();
                            String[] data =new String[4];
                            data[0]=getUsername();
                            data[1]=getEmail();
                            data[2]=Integer.toString(rank);
                            data[3] = "generalRank";
                            this.setGeneralRank(rank);
                            CSVFileHandle.WriteLineIntoCSVForDeletionAndUpdation("./CSV/user_updated.csv", data);
                            break;
                        }

                        case 4:{
                            System.out.println("Enter New CategoryRank");
                            rank = sc.nextInt();
                            String[] data =new String[4];
                            data[0]=getUsername();
                            data[1]=getEmail();
                            data[2]=Integer.toString(rank);
                            data[3] = "categoryRank";
                            this.setCategoryRank(rank);
                            CSVFileHandle.WriteLineIntoCSVForDeletionAndUpdation("./CSV/user_updated.csv", data);
                            break;
                        }
                        default:
                        {
                            check = false;
                            break;
                        }
                    }
                }

                System.out.println("Your profile has been updated as below.");
                UpdateUserData(connection);
//                CSVFileHandle.DeleteCSVFIle("./CSV/user_updated.csv");
            }
            else {
                System.out.println("Incorrect Password");
            }


        }
        catch (Exception e)
        {
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
                PreparedStatement stmt = connection.prepareStatement("update user_details SET password= ? where password= ? and username= ? ");
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
    
    
    public boolean deleteAccount(Connection connection, Scanner sc) {
        String oldPass;
        String uname;
        boolean checkStatus = false;
        System.out.println("NOTE:After Deletion of Account Your Data will be Erased and you will be log out \n Press 1:Continue\n Press 2:Stop Deletion");

        int checkVal = sc.nextInt();

        if (checkVal == 1) {
            System.out.println("Enter Username");
            uname = sc.next();
            System.out.println("Enter Your Password");
            oldPass = sc.next();
            
            if (oldPass.compareTo(getPassword()) == 0 && uname.compareTo(getUsername()) == 0) {
                try {
                    PreparedStatement stmt = connection.prepareStatement("delete from user_details where username = ? and password = ?");
                    stmt.setString(1, uname);
                    stmt.setString(2, oldPass);
                    int ct = stmt.executeUpdate();
                    if (ct != 0) {
                        checkStatus = true;
                        System.out.println("Account Deleted");
                        String[] data =new String[7];
                        data[0]=getUsername();
                        data[1]=getEmail();
                        data[2]=getPassword();
                        data[3]=getGender();
                        data[4]=getCategory();
                        data[5]= Integer.toString( getGeneralRank());
                        data[6]=Integer.toString( getCategoryRank());


                        CSVFileHandle.WriteLineIntoCSVForDeletionAndUpdation("./CSV/user_deleted.csv", data);
                        
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
       System.out.println("-------------+");
    }
    
    public void userTableHeadline() {
        topBorderUserTable();
        System.out.printf("| %-40s | %-65s | %-10s | %-39s | %-11s | %-13s | %-13s |\n","User Name","E-mail ID","Password","Gender","Category","General Rank","Category Rank");
        topBorderUserTable();
    }
    
    public void printUser() {
        topBorderUserTable();
        System.out.printf("| %-40s | %-65s | %-10s | %-39s | %-11s | %-13s | %-13s |\n", super.getUsername(), super.getEmail(), "********" ,getGender(),getCategory(),  getGeneralRank(), getCategoryRank());
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
    
    public void sortUserList(ArrayList<User> arrayList, Scanner scanner)
    {
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

    

