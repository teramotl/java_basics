import java.util.List;

public class StationsWrapper {
    private List<Station> stations;

    public StationsWrapper(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
