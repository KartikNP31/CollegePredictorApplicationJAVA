abstract class Institute {

    private String name;
    private String city;
    private String state;
    private int openingRank;
    private int closingRank;
    private int ranking;
    private int capacity;

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
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
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
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