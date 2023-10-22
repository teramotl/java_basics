public class Processor {
    private final double GHz;
    private final int cores;
    private final String manufacturer;
    private final double weight;

    public Processor(String manufacturer, double GHz, int cores, double weight) {
        this.GHz = GHz;
        this.cores = cores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }
    public String toString() {
        return  manufacturer + " (" + cores + " cores - " + GHz + "GHz";
    }
    public double getWeight() {
        return weight;
    }
}
