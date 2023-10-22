public class Monitor {
    private final double sizeInch;
    private final String type;
    private final double weight;

    public Monitor(double sizeInch, String type, double weight) {
        this.sizeInch = sizeInch;
        this.type = type;
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    public String toString() {
        return type + " (" + sizeInch + " inches)";
    }
}
