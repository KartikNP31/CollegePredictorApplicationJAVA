import java.sql.*;
import java.util.*;

public class SearchInstitute extends User {
    private String instituteName;
    private String branch;
    private String quota;
    private int round;
    
    SearchInstitute() {
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
    
    public void topBorder() {
        for (int i = 0; i < 47; i++) {
            System.out.print("--------");
        }
        System.out.println();
    }
    
    public void tableHeadline() {
        topBorder();
        System.out.printf("| %-130s | %-144s | %-5s | %-11s | %-40s | %-12s | %-12s |\n", "Institute Name", "Academic Program Name", "Quota", "Seat-Type", "Gender", "Opening Rank", "Closing Rank");
        topBorder();
    }
    
    public void searchCollege() {
        Scanner scan = new Scanner(System.in);
        String r;
        boolean checkLoop = true;
        
        String gender = super.getGender();
        if (gender.toLowerCase().compareTo("male") == 0) {
            gender = "Gender-Neutral";
        } else {
            gender = "Female-only (including Supernumerary)";
        }
        
        System.out.println("Select: On What Basis you want to search?");
        System.out.println("1.Search among all Branches and Institutes\n2.Institute Name and Your Rank\n3.Branch Name and Your Rank\n4.Branch Name and Institute Name\n5.Exit");
        int select = scan.nextInt();
        scan.nextLine();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "#KAR331@tikNP");
            while (checkLoop) {
                switch (select) {
                    case 1 -> {
                        List<Institute> InstituteList1 = new ArrayList<>();
                        System.out.println("Select Josaa Round Numbar from (1-6)");
                        setRound(scan.nextInt());
                        scan.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        PreparedStatement statement1 = connection.prepareStatement("select * from " + r + " where gender= ? and category=? and Opening_Rank < ? and closing_rank > ?");
                        statement1.setString(1, gender);
                        statement1.setString(2, super.getCategory());
                        statement1.setInt(3, super.getCategoryRank());
                        statement1.setInt(4, super.getCategoryRank());
                        ResultSet resultSet = statement1.executeQuery();
                        tableHeadline();
                        while (resultSet.next()) {
                            Institute inst = new Institute(getRound(), resultSet.getString("Institute"), resultSet.getString("Program"), resultSet.getString("Quota"), resultSet.getString("Category"), resultSet.getString("Gender"), resultSet.getInt("Opening_rank"), resultSet.getInt("Closing_rank"));
                            InstituteList1.add(inst);
                        }
                        for (Institute i : InstituteList1) {
                            i.printInstitute();
                        }
                        topBorder();
                    }
                    case 2 -> {
                        List<Institute> InstituteList2 = new ArrayList<>();
                        System.out.println("Select Josaa Round Numbar from (1-6)");
                        setRound(scan.nextInt());
                        scan.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Institute Name: ");
                        this.setInstituteName(scan.nextLine());
                        PreparedStatement statement2 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
                        statement2.setString(1, "%" + getInstituteName() + "%");
                        statement2.setString(2, gender);
                        statement2.setString(3, super.getCategory());
                        statement2.setInt(4, super.getCategoryRank());
                        statement2.setInt(5, super.getCategoryRank());
                        ResultSet resultSet2 = statement2.executeQuery();
                        tableHeadline();
                        while (resultSet2.next()) {
                            Institute inst = new Institute(getRound(), resultSet2.getString("Institute"), resultSet2.getString("Program"), resultSet2.getString("Quota"), resultSet2.getString("Category"), resultSet2.getString("Gender"), resultSet2.getInt("Opening_rank"), resultSet2.getInt("Closing_rank"));
                            InstituteList2.add(inst);
                        }
                        for (Institute i : InstituteList2) {
                            i.printInstitute();
                        }
                        topBorder();
                    }
                    case 3 -> {
                        List<Institute> InstituteList3 = new ArrayList<>();
                        System.out.println("Select Josaa Round Numbar from (1-6)");
                        setRound(scan.nextInt());
                        scan.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Branch Name: ");
                        this.setBranch(scan.nextLine());
                        PreparedStatement statement3 = connection.prepareStatement("select * from " + r + " where Program LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
                        statement3.setString(1, "%" + getBranch() + "%");
                        statement3.setString(2, gender);
                        statement3.setString(3, super.getCategory());
                        statement3.setInt(4, super.getCategoryRank());
                        statement3.setInt(5, super.getCategoryRank());
                        ResultSet resultSet3 = statement3.executeQuery();
                        tableHeadline();
                        while (resultSet3.next()) {
                            Institute inst = new Institute(getRound(), resultSet3.getString("Institute"), resultSet3.getString("Program"), resultSet3.getString("Quota"), resultSet3.getString("Category"), resultSet3.getString("Gender"), resultSet3.getInt("Opening_rank"), resultSet3.getInt("Closing_rank"));
                            InstituteList3.add(inst);
                        }
                        for (Institute i : InstituteList3) {
                            i.printInstitute();
                        }
                        topBorder();
                    }
                    case 4 -> {
                        List<Institute> InstituteList4 = new ArrayList<>();
                        System.out.println("Select Josaa Round Numbar from (1-6)");
                        setRound(scan.nextInt());
                        scan.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Institute Name: ");
                        this.setInstituteName(scan.nextLine());
                        System.out.println("Enter Branch Name: ");
                        this.setBranch(scan.nextLine());
                        PreparedStatement statement4 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and  Program LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
                        statement4.setString(1, "%" + getInstituteName() + "%");
                        statement4.setString(2, "%" + getBranch() + "%");
                        statement4.setString(3, gender);
                        statement4.setString(4, super.getCategory());
                        statement4.setInt(5, super.getCategoryRank());
                        statement4.setInt(6, super.getCategoryRank());
                        ResultSet resultSet4 = statement4.executeQuery();
                        tableHeadline();
                        while (resultSet4.next()) {
                            Institute inst = new Institute(getRound(), resultSet4.getString("Institute"), resultSet4.getString("Program"), resultSet4.getString("Quota"), resultSet4.getString("Category"), resultSet4.getString("Gender"), resultSet4.getInt("Opening_rank"), resultSet4.getInt("Closing_rank"));
                            InstituteList4.add(inst);
                        }
                        for (Institute i : InstituteList4) {
                            i.printInstitute();
                        }
                        topBorder();
                    }
                    case 5 -> checkLoop = false;
                }
                if (checkLoop) {
                    System.out.println("Select: On What Basis you want to search?");
                    System.out.println("1.Search among all Branches and Institutes\n2.Institute Name and Your Rank\n3.Branch Name and Your Rank\n4.Branch Name and Institute Name\n5.Exit");
                    select = scan.nextInt();
                    scan.nextLine();
                }
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Application error : Database connectivity Problem");
        }
    }
}
