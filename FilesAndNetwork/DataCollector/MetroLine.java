import java.util.ArrayList;
import java.util.List;

public class MetroLine {
    private String name;
    private String number;
    private List<MetroStation> stations;

    public MetroLine(String name, String number) {
        this.name = name;
        this.number = number;
        this.stations = new ArrayList<>();
    }

    public void addStation(MetroStation station) {
        stations.add(station);
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public List<MetroStation> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "MetroLine{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", stations=" + stations +
                '}';
    }
}
