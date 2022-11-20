package PERSON.USER;
import INSTITUTE.Institute;
import PERSON.User;
import java.sql.*;
import java.util.*;

public class SearchInstitute extends User {
    private String instituteName;
    private String branch;
    private int round;
    
    public SearchInstitute() {
    }
    public SearchInstitute(String username, String password){
        super(username,password);
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public int getRound() {
        return round;
    }
    
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
    
    public String getInstituteName() {
        return instituteName;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public String getBranch() {
        return branch;
    }
    
    public void IncognitoSearch(Connection connection, Scanner sc){
        String r;
        int round, rank;
        SearchInstitute guest = new SearchInstitute();
        System.out.println("Enter JOSAA Round i.e(1-6)");
        round=sc.nextInt();
        r = Integer.toString(round);
        r = "round".concat(r);

        System.out.println("Enter Gender");
        guest.setGender(sc.next());
        String gender = defineGender(guest.getGender());

        System.out.println("Enter: On What Preference you want to search? (Required fields are marked with (*)\nThose field you don't want to give Preference press <Enter> without typing anything ");
        System.out.println("Enter Institute Name");
        sc.nextLine();
        setInstituteName(sc.nextLine());
        System.out.println("Enter Branch Name");
        setBranch(sc.nextLine());
        System.out.println("Enter Category");
        guest.setCategory(sc.next());
        System.out.println("Enter valid Category Rank (*)");
        rank= sc.nextInt();
        while (rank == 0)
        {
            System.out.println("Enter valid non-zero Rank\nNote : Rank can't be non-positive");
            rank= sc.nextInt();
        }
        try{
    
            PreparedStatement stat= connection.prepareStatement("select count(*) from "+r+" where Institute LIKE ? and Program LIKE ? and Category LIKE ? and gender= ? and Opening_Rank <= ? and Closing_Rank >= ?" );
            stat.setString(1,"%"+ getInstituteName() +"%");
            stat.setString(2,"%"+ getBranch()+"%");
            stat.setString(3,"%"+ guest.getCategory()+"%");
            stat.setString(4,gender);
            stat.setInt(5,rank);
            stat.setInt(6, rank);
            ResultSet rs = stat.executeQuery();
            if(rs.next() && rs.getInt(1) == 0)
            {
                System.out.println("No college found : Your criteria are not suitable for any college.");
            }
            else{
                ArrayList<Institute> InstituteList1 = new ArrayList<>();
                PreparedStatement statement1= connection.prepareStatement("select * from "+r+" where Institute LIKE ? and Program LIKE ? and Category LIKE ? and gender= ? and Opening_Rank <= ? and Closing_Rank >= ?" );
                statement1.setString(1,"%"+ getInstituteName() +"%");
                statement1.setString(2,"%"+ getBranch()+"%");
                statement1.setString(3,"%"+ guest.getCategory()+"%");
                statement1.setString(4,gender);
                statement1.setInt(5,rank);
                statement1.setInt(6, rank);
                ResultSet resultSet1 = statement1.executeQuery();
                while (resultSet1.next()) {
                    Institute inst = new Institute(getRound(), resultSet1.getString("Institute"), resultSet1.getString("Program"), resultSet1.getString("Quota"), resultSet1.getString("Category"), resultSet1.getString("Gender"), resultSet1.getInt("Opening_rank"), resultSet1.getInt("Closing_rank"));
                    InstituteList1.add(inst);
                }
                Institute institute = new Institute();
                institute.printInstituteList(InstituteList1);
                institute.sortInstituteList(InstituteList1);
            }
        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }

    }

    
    public void searchCollege(Connection connection, Scanner scanner) {
        String r;
        boolean checkLoop = true;
        String gender = defineGender(super.getGender());
    
        System.out.println("Select: On What Basis you want to search?");
        System.out.println("1.Search among all Branches and Institutes\n2.Institute Name and Your Rank\n3.Branch Name and Your Rank\n4.Branch Name and Institute Name\n5.Exit");
        int select = scanner.nextInt();
        try {
            while (checkLoop) {
                switch (select) {
                    case 1 -> {
                        
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
    
                        PreparedStatement stat = connection.prepareStatement("select count(*) from " + r + " where gender= ? and category=? and Opening_Rank <= ? and closing_rank >= ?");
                        stat.setString(1, gender);
                        stat.setString(2, this.getCategory());
                        stat.setInt(3, this.getCategoryRank());
                        stat.setInt(4, this.getCategoryRank());
                        ResultSet rs1 = stat.executeQuery();
                        if(rs1.next() && rs1.getInt(1) == 0)
                        {
                            System.out.println("No college found : Your criteria are not suitable for any college.");
                        }
                        else {
                            ArrayList<Institute> InstituteList1 = new ArrayList<>();
                            PreparedStatement statement1 = connection.prepareStatement("select * from " + r + " where gender= ? and category=? and Opening_Rank <= ? and closing_rank >= ?");
                            statement1.setString(1, gender);
                            statement1.setString(2, this.getCategory());
                            statement1.setInt(3, this.getCategoryRank());
                            statement1.setInt(4, this.getCategoryRank());
                            ResultSet resultSet = statement1.executeQuery();
                            while (resultSet.next()) {
                                Institute inst = new Institute(getRound(), resultSet.getString("Institute"), resultSet.getString("Program"), resultSet.getString("Quota"), resultSet.getString("Category"), resultSet.getString("Gender"), resultSet.getInt("Opening_rank"), resultSet.getInt("Closing_rank"));
                                InstituteList1.add(inst);
                            }
                            Institute institute = new Institute();
                            institute.printInstituteList(InstituteList1);
                            institute.sortInstituteList(InstituteList1);
                        }
                    }
                    case 2 -> {
                        ArrayList<Institute> InstituteList2 = new ArrayList<>();
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        scanner.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Institute Name: ");
                        String instName = scanner.nextLine();
                        this.setInstituteName(instName);
                        
                        PreparedStatement stat = connection.prepareStatement( "select count(*) from " + r + " where Institute LIKE ? and gender=? and category=? and Opening_Rank <= ? and Closing_Rank >= ?");
                        stat.setString(1, "%" + getInstituteName() + "%");
                        stat.setString(2, gender);
                        stat.setString(3, this.getCategory());
                        stat.setInt(4, this.getCategoryRank());
                        stat.setInt(5, this.getCategoryRank());
                        ResultSet rs2 = stat.executeQuery();
                        if(rs2.next() && rs2.getInt(1) == 0)
                        {
                            System.out.println("No college found : Your criteria are not suitable for any college.");
                        }else {
                            PreparedStatement statement2 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and gender=? and category=? and Opening_Rank <= ? and Closing_Rank >= ?");
                            statement2.setString(1, "%" + getInstituteName() + "%");
                            statement2.setString(2, gender);
                            statement2.setString(3, this.getCategory());
                            statement2.setInt(4, this.getCategoryRank());
                            statement2.setInt(5, this.getCategoryRank());
                            ResultSet resultSet2 = statement2.executeQuery();
                            while (resultSet2.next()) {
                                Institute inst = new Institute(getRound(), resultSet2.getString("Institute"), resultSet2.getString("Program"), resultSet2.getString("Quota"), resultSet2.getString("Category"), resultSet2.getString("Gender"), resultSet2.getInt("Opening_rank"), resultSet2.getInt("Closing_rank"));
                                InstituteList2.add(inst);
                            }
                            Institute institute = new Institute();
                            institute.printInstituteList(InstituteList2);
                            institute.sortInstituteList(InstituteList2);
                        }
                        
                    }
                    case 3 -> {
                        ArrayList<Institute> InstituteList3 = new ArrayList<>();
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        scanner.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Branch Name: ");
                        String branchName = scanner.nextLine();
                        this.setBranch(branchName);
    
                        PreparedStatement stat = connection.prepareStatement("select count(*) from " + r + " where Program LIKE ? and gender=? and category=? and Opening_Rank <= ? and Closing_Rank >= ?");
                        stat.setString(1, "%" + getBranch() + "%");
                        stat.setString(2, gender);
                        stat.setString(3, this.getCategory());
                        stat.setInt(4, this.getCategoryRank());
                        stat.setInt(5, this.getCategoryRank());
                        ResultSet rs3 = stat.executeQuery();
                        if(rs3.next() && rs3.getInt(1) == 0)
                        {
                            System.out.println("No college found : Your criteria are not suitable for any college.");
                        }else {
                            PreparedStatement statement3 = connection.prepareStatement("select * from " + r + " where Program LIKE ? and gender=? and category=? and Opening_Rank <= ? and Closing_Rank >= ?");
                            statement3.setString(1, "%" + getBranch() + "%");
                            statement3.setString(2, gender);
                            statement3.setString(3, this.getCategory());
                            statement3.setInt(4, this.getCategoryRank());
                            statement3.setInt(5, this.getCategoryRank());
                            ResultSet resultSet3 = statement3.executeQuery();
                            while (resultSet3.next()) {
                                Institute inst = new Institute(getRound(), resultSet3.getString("Institute"), resultSet3.getString("Program"), resultSet3.getString("Quota"), resultSet3.getString("Category"), resultSet3.getString("Gender"), resultSet3.getInt("Opening_rank"), resultSet3.getInt("Closing_rank"));
                                InstituteList3.add(inst);
                            }
                            Institute institute = new Institute();
                            institute.printInstituteList(InstituteList3);
                            institute.sortInstituteList(InstituteList3);
                        }
                    
                    }
                    case 4 -> {
                        ArrayList<Institute> InstituteList4 = new ArrayList<>();
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        scanner.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Institute Name: ");
                        String instName = scanner.nextLine();
                        this.setInstituteName(instName);
                        System.out.println("Enter Branch Name: ");
                        String branchName = scanner.nextLine();
                        this.setBranch(branchName);
    
                        PreparedStatement stat = connection.prepareStatement("select count(*) from " + r + " where Institute LIKE ? and  Program LIKE ? and gender=? and category=? and Opening_Rank <= ? and Closing_Rank >= ?");
                        stat.setString(1, "%" + getInstituteName() + "%");
                        stat.setString(2, "%" + getBranch() + "%");
                        stat.setString(3, gender);
                        stat.setString(4, this.getCategory());
                        stat.setInt(5, this.getCategoryRank());
                        stat.setInt(6, this.getCategoryRank());
                        ResultSet rs4 = stat.executeQuery();
                        if(rs4.next() && rs4.getInt(1) == 0)
                        {
                            System.out.println("No college found : Your criteria are not suitable for any college.");
                        }else{
                            PreparedStatement statement4 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and  Program LIKE ? and gender=? and category=? and Opening_Rank <= ? and Closing_Rank >= ?");
                            statement4.setString(1, "%" + getInstituteName() + "%");
                            statement4.setString(2, "%" + getBranch() + "%");
                            statement4.setString(3, gender);
                            statement4.setString(4, this.getCategory());
                            statement4.setInt(5, this.getCategoryRank());
                            statement4.setInt(6, this.getCategoryRank());
                            ResultSet resultSet4 = statement4.executeQuery();
                            while (resultSet4.next()) {
                                Institute inst = new Institute(getRound(), resultSet4.getString("Institute"), resultSet4.getString("Program"), resultSet4.getString("Quota"), resultSet4.getString("Category"), resultSet4.getString("Gender"), resultSet4.getInt("Opening_rank"), resultSet4.getInt("Closing_rank"));
                                InstituteList4.add(inst);
                            }
                            Institute institute = new Institute();
                            institute.printInstituteList(InstituteList4);
                            institute.sortInstituteList(InstituteList4);
                        }
                        
                    }
                    case 5 -> checkLoop = false;
                }
                if (checkLoop) {
                    System.out.println("Select: On What Basis you want to search?");
                    System.out.println("1.Search among all Branches and Institutes\n2.Institute Name and Your Rank\n3.Branch Name and Your Rank\n4.Branch Name and Institute Name\n5.Exit");
                    select = scanner.nextInt();
                }
            }
        } catch (Exception e) {
            
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    
    
    
}
