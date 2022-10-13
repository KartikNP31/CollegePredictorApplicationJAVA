public abstract  class User {

    private String UserName;
    private String Password;
    private String email;
    private String gender;
    private String category;
    private String examType;
    private int GeneralRank;
    private int CategoryRank;


    User() {
        
    }

    User(String userName,String password, String email,String gender,String category,String examType,int generalRank,int categoryRank)


    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public void setGeneralRank(int GeneralRank) {
        this.GeneralRank = GeneralRank;
    }

    public void setCategoryRank(int categoryRank) {
        CategoryRank = categoryRank;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getCategory() {
        return category;
    }

    public String getGender() {
        return gender;
    }

    public String getExamType() {
        return examType;
    }

    public int getGeneralRank() {
        return GeneralRank;
    }

    public int getCategoryRank() {
        return CategoryRank;
    }

    public String getEmail() {
        return email;
    }

}



