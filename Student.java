abstract class Student {

    private String name;
    private String category;
    private String gender;
    private int rank;
    private int rollNumber;
    private String preferredState;
    private String email;

    Student() {
        
    }

    public void checkValidGender(String gender) {
        if (gender.toLowerCase() == "male") {

        }
        else if (gender.toLowerCase() == "female") {

        }
        else {

        }
    }

    public void checkValidRollNumber(int rollNumber) {

    }

    public void checkValidRank(int rank) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setPreferredState(String preferredState) {
        this.preferredState = preferredState;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getGender() {
        return gender;
    }

    public int getRank() {
        return rank;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getPreferredState() {
        return preferredState;
    }

    public String getEmail() {
        return email;
    }

    abstract public void printCollegeList();

}

class MainsAspirant extends Student {

    

}