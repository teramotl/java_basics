public class MetroStation {
    private String name;
    private String number;

    public MetroStation(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "MetroStation{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
