public class StationDate {
    private String name;
    private String date;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "StationDate{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
