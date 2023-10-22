public class Storage {
    private final String type;
    private final int capacity;
    private final double weight;

    public Storage(String type, int capacity, double weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    public String toString() {
        return type + " " + capacity + "gb";
    }
}
