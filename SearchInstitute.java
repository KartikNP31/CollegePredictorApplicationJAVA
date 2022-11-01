import java.sql.*;
import java.util.Scanner;

public class SearchInstitute extends User {
  private String instituteName;
  private String branch;
  private int rank;
  private String quota;
  private int round;

  SearchInstitute() {}

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
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

  // public void PredictCollegeForUser(String round) {
  // }

  public void searchCollege() {
    Scanner scan = new Scanner(System.in);
    String r, instituteName;
    boolean checkLoop = true;
    SearchInstitute searchObject = new SearchInstitute();

    String gender = super.getGender();
    if (gender.toLowerCase().compareTo("male") == 0) {
      gender = "Gender-Neutral";
    } else {
      gender = "Female-only (including Supernumerary)";
    }

    String category = searchObject.getCategory();
    int GeneralRank = searchObject.getGeneralRank();
    int CategoryRank = searchObject.getCategoryRank();

    System.out.println("Select: On What Basis you want to search?");
    System.out.println(
      "1.Search among all Branches and Institutes\n2.Institute Name and Your Rank\n3.Branch Name and Your Rank\n4.Branch Name and Institute Name\n5.Exit"
    );
    int select = scan.nextInt();
    scan.nextLine();

    try {
      Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/java_proj_college_predictor",
        "root",
        "mh153599"
      );

      while (checkLoop) {
        switch (select) {
          case 1:
            System.out.println("Select Josaa Round Numbar from (1-6)");
            setRank(scan.nextInt());
            scan.nextLine();
            r = Integer.toString(getRound());
            r = "round".concat(r);

            PreparedStatement statement1 = connection.prepareStatement(
              "select * from " +
              r +
              " where gender= ? and category=? and Opening_Rank < ? and closing_rank > ?"
            );

            statement1.setString(1, gender);
            statement1.setString(2, super.getCategory());
            statement1.setInt(3, super.getCategoryRank());
            statement1.setInt(4, super.getCategoryRank());
            ResultSet resultSet = statement1.executeQuery();
            System.out.printf(
              "%-130s | %-144s | %-5s | %-11s | %-40s | %-12s | %-12s\n",
              "Institute Name",
              "Academic Program Name",
              "Quota",
              "Seat-Type",
              "Gender",
              "Opening Rank",
              "Closing_Rank"
            );
            while (resultSet.next()) {
              String a = resultSet.getString(1);
              String b = resultSet.getString(2);
              String c = resultSet.getString(3);
              String d = resultSet.getString(4);
              String e = resultSet.getString(5);
              int f = resultSet.getInt(6);
              int g = resultSet.getInt(7);
              System.out.printf(
                "%-130s | %-144s | %-5s | %-11s | %-40s | %-12d | %-12d\n",
                a,
                b,
                c,
                d,
                e,
                f,
                g
              );
            }

            break;
          case 2:
            System.out.println("Select Josaa Round Numbar from (1-6)");
            setRank(scan.nextInt());
            scan.nextLine();
            r = Integer.toString(getRound());
            r = "round".concat(r);

            System.out.println("Enter Institute Name: ");
            this.setInstituteName(scan.nextLine());

            PreparedStatement statement2 = connection.prepareStatement(
              "select * from " +
              r +
              " where Institute LIKE %?% and gender=? and category=? and Opening_Rank=? and Closing_Rank=?"
            );

            statement2.setString(1, getInstituteName());
            statement2.setString(2, gender);
            statement2.setString(3, super.getCategory());
            statement2.setInt(4, super.getCategoryRank());
            statement2.setInt(5, super.getCategoryRank());

            ResultSet resultSet2 = statement2.executeQuery();
            System.out.printf(
              "%-130s | %-144s | %-5s | %-11s | %-40s | %-12s | %-12s\n",
              "Institute Name",
              "Academic Program Name",
              "Quota",
              "Seat-Type",
              "Gender",
              "Opening Rank",
              "Closing_Rank"
            );

            while (resultSet2.next()) {
              String a = resultSet2.getString(1);
              String b = resultSet2.getString(2);
              String c = resultSet2.getString(3);
              String d = resultSet2.getString(4);
              String e = resultSet2.getString(5);
              int f = resultSet2.getInt(6);
              int g = resultSet2.getInt(7);
              System.out.printf(
                "%-130s | %-144s | %-5s | %-11s | %-40s | %-12d | %-12d\n",
                a,
                b,
                c,
                d,
                e,
                f,
                g
              );
            }

            break;
          case 3:
            System.out.println("Select Josaa Round Numbar from (1-6)");
            setRank(scan.nextInt());
            scan.nextLine();
            r = Integer.toString(getRound());
            r = "round".concat(r);

            System.out.println("Enter Branch Name: ");
            this.setBranch(scan.nextLine());

            PreparedStatement statement3 = connection.prepareStatement(
              "select * from " +
              r +
              " where Program LIKE %?% and gender=? and category=? and Opening_Rank=? and Closing_Rank=?"
            );

            statement3.setString(1, getBranch());
            statement3.setString(2, gender);
            statement3.setString(3, super.getCategory());
            statement3.setInt(4, super.getCategoryRank());
            statement3.setInt(5, super.getCategoryRank());

            ResultSet resultSet3 = statement3.executeQuery();

            System.out.printf(
              "%-130s | %-144s | %-5s | %-11s | %-40s | %-12s | %-12s\n",
              "Institute Name",
              "Academic Program Name",
              "Quota",
              "Seat-Type",
              "Gender",
              "Opening Rank",
              "Closing_Rank"
            );

            while (resultSet3.next()) {
              String a = resultSet3.getString(1);
              String b = resultSet3.getString(2);
              String c = resultSet3.getString(3);
              String d = resultSet3.getString(4);
              String e = resultSet3.getString(5);
              int f = resultSet3.getInt(6);
              int g = resultSet3.getInt(7);
              System.out.printf(
                "%-130s | %-144s | %-5s | %-11s | %-40s | %-12d | %-12d\n",
                a,
                b,
                c,
                d,
                e,
                f,
                g
              );
            }
            break;
          case 4:
            System.out.println("Select Josaa Round Numbar from (1-6)");
            setRank(scan.nextInt());
            scan.nextLine();
            r = Integer.toString(getRound());
            r = "round".concat(r);

            System.out.println("Enter Institute Name: ");
            this.setInstituteName(scan.nextLine());

            System.out.println("Enter Branch Name: ");
            this.setBranch(scan.nextLine());

            PreparedStatement statement4 = connection.prepareStatement(
              "select * from " +
              r +
              " where Institute LIKE %?% and  Program LIKE %?% and gender=? and category=? and Opening_Rank=? and Closing_Rank=?"
            );

            statement4.setString(1, getInstituteName());
            statement4.setString(2, getBranch());
            statement4.setString(3, gender);
            statement4.setString(4, super.getCategory());
            statement4.setInt(5, super.getCategoryRank());
            statement4.setInt(6, super.getCategoryRank());

            ResultSet resultSet4 = statement4.executeQuery();

            System.out.printf(
              "%-130s | %-144s | %-5s | %-11s | %-40s | %-12s | %-12s\n",
              "Institute Name",
              "Academic Program Name",
              "Quota",
              "Seat-Type",
              "Gender",
              "Opening Rank",
              "Closing_Rank"
            );
            while (resultSet4.next()) {
              String a = resultSet4.getString(1);
              String b = resultSet4.getString(2);
              String c = resultSet4.getString(3);
              String d = resultSet4.getString(4);
              String e = resultSet4.getString(5);
              int f = resultSet4.getInt(6);
              int g = resultSet4.getInt(7);
              System.out.printf(
                "%-130s | %-144s | %-5s | %-11s | %-40s | %-12d | %-12d\n",
                a,
                b,
                c,
                d,
                e,
                f,
                g
              );
            }
            break;
          case 5:
            checkLoop = false;
            break;
        }
      }
      connection.close();
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("Application error : Database connectivity Problem");
    }
  }
}
