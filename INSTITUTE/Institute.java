package INSTITUTE;
import java.sql.*;
import java.util.*;

public class Institute implements Comparable{
    
    private int round;
    private String InstituteName;
    private String program;
    private String Quota;
    private String category;
    private String Gender;
    private int OpeningRank;
    private int ClosingRank;
    
    public Institute() {
    
    }
    
    public Institute(int round, String instituteName, String program, String quota, String category, String gender, int openingRank, int closingRank) {
        setRound(round);
        setInstituteName(instituteName);
        setProgram(program);
        setQuota(quota);
        setCategory(category);
        setGender(gender);
        setOpeningRank(openingRank);
        setClosingRank(closingRank);
    }
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public String getInstituteName() {
        return InstituteName;
    }
    
    public void setInstituteName(String instituteType) {
        InstituteName = instituteType;
    }
    
    public String getProgram() {
        return program;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }
    
    public String getQuota() {
        return Quota;
    }
    
    public void setQuota(String quota) {
        Quota = quota;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getGender() {
        return Gender;
    }
    
    public void setGender(String gender) {
        Gender = gender;
    }
    
    public int getOpeningRank() {
        return OpeningRank;
    }
    
    public void setOpeningRank(int openingRank) {
        OpeningRank = openingRank;
    }
    
    public int getClosingRank() {
        return ClosingRank;
    }
    
    public void setClosingRank(int closingRank) {
        ClosingRank = closingRank;
    }
    
    public int getMinimumOpeningRank(Connection connection)
    {
        try{
            PreparedStatement statement = connection.prepareStatement("select min(opening_rank) from round1");
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e)
        {
            System.out.println("Application error : Database connectivity Problem");
            return 1;
        }
    }
    public int getMaxCLosingRank(Connection connection)
    {
        try{
            PreparedStatement statement = connection.prepareStatement("select max(Closing_Rank) from round6");
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e)
        {
            System.out.println("Application error : Database connectivity Problem");
            return 1;
        }
    }
    
    public void topBorderSearchCollege() {
        for (int i = 0; i < 47; i++) {
            System.out.print("--------");
        }
        System.out.println();
    }
    
    public void topBorder() {
        for (int i = 0; i < 47; i++) {
            System.out.print("--------");
        }
        System.out.println();
    }
    
    public void tableHeadline() {
        topBorder();
        System.out.printf("| %-130s | %-144s | %-5s | %-11s | %-40s | %-12s | %-12s |\n", "Institute Name","Academic Program Name","Quota","Seat - type","Gender","Opening Rank","Closing Rank");
        topBorder();
    }
    
    public void printSearchCollege() {
        String a = getInstituteName();
        String b = getProgram();
        String c = getQuota();
        String d = getCategory();
        String e = getGender();
        int f = getOpeningRank();
        int g = getClosingRank();
        topBorderSearchCollege();
        System.out.printf("| %-130s | %-144s | %-5s | %-11s | %-40s | %-12d | %-12d |\n", a, b, c, d, e, f, g);
    }
    public void printInstituteList(ArrayList<Institute> arrayList) {
        tableHeadline();
        for (Institute i : arrayList) {
            i.printSearchCollege();
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
                    break;
                }
            }
            System.out.println("Do you want to Sort it again\n1.Yes     2.No    (Select option number 1 or 2)");
            opt = scanner.nextInt();
            
        }
    }

    
    
    
    @Override
    public int compareTo(Object o) {
        Institute inst = (Institute) o;
        return this.getOpeningRank()-inst.getOpeningRank();
    }
    public int compareTo1(Object o) {
        Institute inst = (Institute) o;
        return this.getClosingRank()-inst.getClosingRank();
    }
    public int compareTo2(Object o) {
        Institute inst = (Institute) o;
        return this.getInstituteName().compareTo(inst.getInstituteName());
    }
    public int compareTo3(Object o) {
        Institute inst = (Institute) o;
        return this.getProgram().compareTo(inst.getProgram());
    }
    public int compareTo4(Object o) {
        Institute inst = (Institute) o;
        return this.getCategory().compareTo(inst.getCategory());
    }
    public int compareTo5(Object o) {
        Institute inst = (Institute) o;
        return this.getGender().compareTo(inst.getGender());
    }
    
    
    
    public void borderGetInstitute()
    {
        System.out.print("+");
        for (int i=0;i<10;i++)
        {
            System.out.print("-------------");
        }
        System.out.println("--+");
    }
    public void borderGetInstituteAsterisk()
    {
        System.out.print("+");
        for (int i=0;i<10;i++)
        {
            System.out.print("*************");
        }
        System.out.println("**+");
    }
    public void borderGetBranch()
    {
        System.out.print("+");
        for (int i=0;i<10;i++)
        {
            System.out.print("--------------");
        }
        System.out.println("-------+");
    }
    public void borderGetBranchAsterisk ()
    {
        System.out.print("+");
        for (int i=0;i<10;i++)
        {
            System.out.print("**************");
        }
        System.out.println("*******+");
    }
    public void getAllInstitute(Connection connection) {
        try {
            ArrayList<Institute> InstituteList = new ArrayList<>();
            PreparedStatement statement2 = connection.prepareStatement("select distinct institute from round1 order by Institute");
            ResultSet resultSet = statement2.executeQuery();
            borderGetInstituteAsterisk();
            System.out.printf("| %-130s |\n","Institute Name");
            borderGetInstituteAsterisk();
            while (resultSet.next()) {
                Institute inst = new Institute(1, resultSet.getString("Institute"), null, null, null, null, 0, 0);
                InstituteList.add(inst);
            }
            for (Institute i : InstituteList) {
                System.out.printf("| %-130s |\n",i.getInstituteName());
                borderGetInstitute();
            }
            
            
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    public void getAllBranch(Connection connection) {
        try {
            ArrayList<Institute> InstituteList = new ArrayList<>();
            PreparedStatement statement2 = connection.prepareStatement("select distinct program from round1 order by program");
            ResultSet resultSet = statement2.executeQuery();
            borderGetBranchAsterisk();
            System.out.printf("| %-145s |\n","Academic Program Name");
            borderGetBranchAsterisk();
            while (resultSet.next()) {
                Institute branch = new Institute(1,null, resultSet.getString("Program"), null, null, null, 0, 0);
                InstituteList.add(branch);
            }
            for (Institute i : InstituteList) {
                System.out.printf("| %-145s |\n",i.getProgram());
                borderGetBranch();
            }
            
            
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    
}