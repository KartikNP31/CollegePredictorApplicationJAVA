import java.sql.*;
import java.util.*;

public class SearchInstitute extends User {
    private String instituteName;
    private String branch;
    private int round;
    
    SearchInstitute() {
    }
    SearchInstitute(String username, String password){
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
    
    public String defineGender(String str) {
        String gender;
        if (str.toLowerCase().contains("f") && str.toLowerCase().compareTo("female") == 0) {
            gender = "Female-only (including Supernumerary)";
        } else {
            gender = "Gender-Neutral";
        }
        return gender;
    }
    
    public void printInstituteList(ArrayList<Institute> arrayList)
    {
        tableHeadline();
        for (Institute i : arrayList) {
            i.printInstitute();
        }
        topBorder();
    }
    public void sortInstituteList(ArrayList<Institute> arrayList)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to Sort Institute List\n1.Yes     2.No    (Select option number 1 or 2)");
        int opt = scanner.nextInt();
        while (opt == 1)
        {
            System.out.println("Select proper number (1-7) for Parameter by which you want to sort Institute List\n1.Institute Name     2.Academic Program Name      3.Seat - type      4.Gender     5.Opening Rank     6.Closing Rank      7.Exit");
            int para = scanner.nextInt();
            switch (para) {
                case 1: {
                    arrayList.sort(Institute::compareTo2);
                    System.out.println("Institute List is Sorted by Institute Name");
                    printInstituteList(arrayList);
                    break;
                }
                case 2: {
                    arrayList.sort(Institute::compareTo3);
                    System.out.println("Institute List is Sorted by Academic Program Name");
                    printInstituteList(arrayList);
                    break;
                }
                case 3: {
                    arrayList.sort(Institute::compareTo4);
                    System.out.println("Institute List is Sorted by Category");
                    printInstituteList(arrayList);
                    break;
                }
                case 4: {
                    arrayList.sort(Institute::compareTo5);
                    System.out.println("Institute List is Sorted by Gender");
                    printInstituteList(arrayList);
                    break;
                }
                case 5: {
                    arrayList.sort(Institute::compareTo);
                    System.out.println("Institute List is Sorted by Opening Rank");
                    printInstituteList(arrayList);
                    break;
                }
                case 6: {
                    arrayList.sort(Institute::compareTo1);
                    System.out.println("Institute List is Sorted by Closing Rank");
                    printInstituteList(arrayList);
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
    
    public void IncognitoSearch(Connection connection){
        String r;
        boolean checkLoop = true;
        int round, rank;
        Scanner sc= new Scanner(System.in);
        SearchInstitute guest = new SearchInstitute();
        System.out.println("Enter JOSAA Round i.e(1-6)");
        round=sc.nextInt();
        sc.nextLine();
        r = Integer.toString(round);
        r = "round".concat(r);

        System.out.println("Enter Gender");
        guest.setGender(sc.nextLine());
        String gender = defineGender(guest.getGender());

        System.out.println("Enter: On What Preference you want to search?\nThose field you don't want to give Preference press <Enter> without typing anything and for Rank Enter 0");
        System.out.println("Enter Institute Name");
        setInstituteName(sc.nextLine());
        System.out.println("Enter Branch Name");
        setBranch(sc.nextLine());
        System.out.println("Enter Category");
        guest.setCategory(sc.nextLine());
        System.out.println("Enter Category Rank");
        rank= sc.nextInt();
        sc.nextLine();
        try{

            List<Institute> InstituteList1 = new ArrayList<>();
            PreparedStatement statement1= connection.prepareStatement("select * from "+r+" where Institute LIKE ? and Program LIKE ? and Category LIKE ? and gender= ? and Opening_Rank <= ? and Closing_Rank >= ?" );
            statement1.setString(1,"%"+ getInstituteName() +"%");
            statement1.setString(2,"%"+ getBranch()+"%");
            statement1.setString(3,"%"+ guest.getCategory()+"%");
            statement1.setString(4,gender);
            statement1.setInt(5,rank);
            statement1.setInt(6, rank);

            ResultSet resultSet1 = statement1.executeQuery();
            tableHeadline();
            while (resultSet1.next()) {
                Institute inst = new Institute(getRound(), resultSet1.getString("Institute"), resultSet1.getString("Program"), resultSet1.getString("Quota"), resultSet1.getString("Category"), resultSet1.getString("Gender"), resultSet1.getInt("Opening_rank"), resultSet1.getInt("Closing_rank"));
                InstituteList1.add(inst);
            }
          Collections.sort(InstituteList1);
            for (Institute i : InstituteList1) {
                i.printInstitute();
            }
            topBorder();


        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }
    sc.close();

    }

    
    public void searchCollege(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        String r;
        boolean checkLoop = true;
    
        String gender = defineGender(super.getGender());
    
        System.out.println("Select: On What Basis you want to search?");
        System.out.println("1.Search among all Branches and Institutes\n2.Institute Name and Your Rank\n3.Branch Name and Your Rank\n4.Branch Name and Institute Name\n5.Exit");
        int select = scanner.nextInt();
        scanner.nextLine();
        try {
            while (checkLoop) {
                switch (select) {
                    case 1 -> {
                        ArrayList<Institute> InstituteList1 = new ArrayList<>();
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        scanner.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        PreparedStatement statement1 = connection.prepareStatement("select * from " + r + " where gender= ? and category=? and Opening_Rank < ? and closing_rank > ?");
                        statement1.setString(1, gender);
                        statement1.setString(2, this.getCategory());
                        statement1.setInt(3, this.getCategoryRank());
                        statement1.setInt(4, this.getCategoryRank());
                        ResultSet resultSet = statement1.executeQuery();
                        while (resultSet.next()) {
                            Institute inst = new Institute(getRound(), resultSet.getString("Institute"), resultSet.getString("Program"), resultSet.getString("Quota"), resultSet.getString("Category"), resultSet.getString("Gender"), resultSet.getInt("Opening_rank"), resultSet.getInt("Closing_rank"));
                            InstituteList1.add(inst);
                        }
                        
                        printInstituteList(InstituteList1);
                        sortInstituteList(InstituteList1);
                    }
                    case 2 -> {
                        ArrayList<Institute> InstituteList2 = new ArrayList<>();
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        scanner.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Institute Name: ");
                        this.setInstituteName(scanner.nextLine());
                        PreparedStatement statement2 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
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
                        
                        printInstituteList(InstituteList2);
                        sortInstituteList(InstituteList2);
                    }
                    case 3 -> {
                        ArrayList<Institute> InstituteList3 = new ArrayList<>();
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        scanner.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Branch Name: ");
                        this.setBranch(scanner.nextLine());
                        PreparedStatement statement3 = connection.prepareStatement("select * from " + r + " where Program LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
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
                        
                        printInstituteList(InstituteList3);
                        sortInstituteList(InstituteList3);
                    
                    }
                    case 4 -> {
                        ArrayList<Institute> InstituteList4 = new ArrayList<>();
                        System.out.println("Select Josaa Round Number from (1-6)");
                        setRound(scanner.nextInt());
                        scanner.nextLine();
                        r = Integer.toString(getRound());
                        r = "round".concat(r);
                        System.out.println("Enter Institute Name: ");
                        this.setInstituteName(scanner.nextLine());
                        System.out.println("Enter Branch Name: ");
                        this.setBranch(scanner.nextLine());
                        PreparedStatement statement4 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and  Program LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
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
                        printInstituteList(InstituteList4);
                        sortInstituteList(InstituteList4);
                    }
                    case 5 -> checkLoop = false;
                }
                if (checkLoop) {
                    System.out.println("Select: On What Basis you want to search?");
                    System.out.println("1.Search among all Branches and Institutes\n2.Institute Name and Your Rank\n3.Branch Name and Your Rank\n4.Branch Name and Institute Name\n5.Exit");
                    select = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
        }
        scanner.close();
    }
}
