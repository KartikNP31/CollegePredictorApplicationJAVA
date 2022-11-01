public class Institute {

    private String InstituteType;
    private String program;
    private String Quota;
    private String category;
    private String Gender;
    private int OpeningRank;
    private int ClosingRank;


    public String getInstituteType() {
        return InstituteType;
    }

    public void setInstituteType(String instituteType) {
        InstituteType = instituteType;
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

    

}