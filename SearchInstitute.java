import java.util.Scanner;

public class SearchInstitute extends User {

  SearchInstitute() {}

  private String instituteName;
  private String branch;
  private int rank;
  private String quota;

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
