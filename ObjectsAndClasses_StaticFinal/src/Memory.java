public class Memory {
    private final String type;
    private final int capacity;
    private final double weight;

    public Memory(String type, int capacity, double weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }
    public String toString() {
        return type + " " + capacity + "gb";
    }
    public double getWeight() {
        return weight;
    }
}
