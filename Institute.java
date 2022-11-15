import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Institute implements Comparable{
    
    private int round;
    private String InstituteName;
    private String program;
    private String Quota;
    private String category;
    private String Gender;
    private int OpeningRank;
    private int ClosingRank;
    
    Institute() {
    
    }
    
    Institute(int round, String instituteName, String program, String quota, String category, String gender, int openingRank, int closingRank) {
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
    
    public void topBorderSearchCollege() {
        for (int i = 0; i < 47; i++) {
            System.out.print("--------");
        }
        System.out.println();
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
    
    
    
    public void borderGetInstituteOrBranch()
    {
        System.out.print("+");
        for (int i=0;i<10;i++)
        {
            System.out.print("-------------");
        }
        System.out.println("--+");
    }
    public void getAllInstitute(Connection connection) {
        try {
            ArrayList<Institute> InstituteList = new ArrayList<>();
            PreparedStatement statement2 = connection.prepareStatement("select distinct institute from round1 order by Institute");
            ResultSet resultSet = statement2.executeQuery();
            borderGetInstituteOrBranch();
            System.out.printf("| %-130s |\n","Institute Name");
            borderGetInstituteOrBranch();
            while (resultSet.next()) {
                Institute inst = new Institute(1, resultSet.getString("Institute"), null, null, null, null, 0, 0);
                InstituteList.add(inst);
            }
            for (Institute i : InstituteList) {
                borderGetInstituteOrBranch();
                System.out.printf("| %-130s |\n",i.getInstituteName());
            }
            borderGetInstituteOrBranch();
            
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    public void getAllBranch(Connection connection) {
        try {
            ArrayList<Institute> InstituteList = new ArrayList<>();
            PreparedStatement statement2 = connection.prepareStatement("select distinct program from round1 order by program");
            ResultSet resultSet = statement2.executeQuery();
            borderGetInstituteOrBranch();
            System.out.printf("| %-130s |\n","Academic Program Name");
            borderGetInstituteOrBranch();
            while (resultSet.next()) {
                Institute branch = new Institute(1,null, resultSet.getString("Program"), null, null, null, 0, 0);
                InstituteList.add(branch);
            }
            for (Institute i : InstituteList) {
                borderGetInstituteOrBranch();
                System.out.printf("| %-130s |\n",i.getProgram());
            }
            borderGetInstituteOrBranch();
            
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
        }
    }
    
}