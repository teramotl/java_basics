public class Keyboard {
    private final String type;
    private final boolean backlit;
    private final double weight;

    public Keyboard(String type, boolean backlit, double weight) {
        this.type = type;
        this.backlit = backlit;
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    public String toString() {
        return type + (backlit ? " (Backlit)" : "");
    }
}
