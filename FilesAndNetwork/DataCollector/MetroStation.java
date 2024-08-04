public class MetroStation {
    private String name;
    private String line;

    public MetroStation(String name, String line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "MetroStation{" +
                "name='" + name + '\'' +
                ", line='" + line + '\'' +
                '}';
    }
}
