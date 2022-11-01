
import java.util.Scanner;
import java.sql.*;

public class SearchInstitute extends User {
  private String instituteName;
  private String branch;
  private int rank;
  private String quota;

  SearchInstitute()
  {

  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
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

  public void PredictCollegeForUser(String round)
  {
    try{
      String r  = "round1";
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root", "#KAR331@tikNP");
      PreparedStatement statement = connection.prepareStatement("select * from "+r+" where gender= ? and category=? and Opening_Rank < ? and closing_rank > ?");


//      statement.setString(1,r);
//      String category = super.getCategory();
//      if(category=="male")
//      {
//        statement.setString(2,"Gender-Neutral");
//      }else {
//        statement.setString(2,"Female-only (including Supernumerary)");
//      }
//
//      statement.setString(3,super.getCategory());
//      statement.setInt(4,super.getCategoryRank());
//      statement.setInt(5,super.getCategoryRank());


      String category = super.getCategory();
      if(category=="male")
      {
        statement.setString(1,"Gender-Neutral");
      }else {
        statement.setString(1,"Female-only (including Supernumerary)");
      }

      statement.setString(2,super.getCategory());
      statement.setInt(3,super.getCategoryRank());
      statement.setInt(4,super.getCategoryRank());
      ResultSet resultSet = statement.executeQuery();
      System.out.printf("%-130s | %-144s | %-5s | %-11s | %-40s | %-12s | %-12s\n","Institute Name","Academic Program Name","Quota","Gender","Seat-Type","Opening Rank","Closing_Rank");
      while (resultSet.next())
      {
        String a = resultSet.getString(1);
        String b = resultSet.getString(2);
        String c  = resultSet.getString(3);
        String d = resultSet.getString(4);
        String e = resultSet.getString(5);
        int f = resultSet.getInt(6);
        int g = resultSet.getInt(7);
        System.out.printf("%-130s | %-144s | %-5s | %-11s | %-40s | %-12d | %-12d\n",a,b,c,d,e,f,g);
      }
      connection.close();
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println("Application error : Database connectivity Problem");
    }
  }
  public void searchCollege() {
    Scanner scan = new Scanner(System.in);
    SearchInstitute searchObject = new SearchInstitute();
    String gender = searchObject.getGender();
    String category = searchObject.getCategory();
    int GeneralRank = searchObject.getGeneralRank();
    int CategoryRank = searchObject.getCategoryRank();

    System.out.println("Select: On What Basis you want to search?");
    System.out.println(
      "1.Institute Name and Your Rank\n2.Branch Name and Your Rank"
    );
    int select = scan.nextInt();
    scan.nextLine();
    switch (select) {
      case 1:
        setInstituteName(scan.nextLine());
        if (category.toLowerCase().compareTo("general") == 0) {
          setRank(GeneralRank);
        } else {
          setRank(CategoryRank);
        }
        break;
      case 2:
        break;
    }
  }
}
