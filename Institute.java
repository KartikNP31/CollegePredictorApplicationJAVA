abstract class Institute {

    private String instituteName;
    private String city;
    private String programName;
    private int openingRank;
    private int closingRank;
    private int ranking;
    private int capacity;

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setOpeningRank(int openingRank) {
        this.openingRank = openingRank;
    }

    public void setClosingRank(int closingRank) {
        this.closingRank = closingRank;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return instituteName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return programName;
    }

    public int getOpeningRank() {
        return openingRank;
    }

    public int getClosingRank() {
        return closingRank;
    }

    public int getRanking() {
        return ranking;
    }

    public int getCapacity() {
        return capacity;
    }

}

class AdvInstitute extends Institute {



}

class MainsInstitute extends Institute {



}