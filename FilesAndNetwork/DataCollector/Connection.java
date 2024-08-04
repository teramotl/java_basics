public class Connection {
    private int line;
    private String station;

    public Connection(int line, String station) {
        this.line = line;
        this.station = station;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "line=" + line +
                ", station='" + station + '\'' +
                '}';
    }
}
