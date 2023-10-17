public class Arithmetic {
    public int num1;
    public int num2;

    public Arithmetic(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public int summary() {
        return num1 + num2;
    }
    public int multiplication() {
        return num1 * num2;
    }
    public int minNumber() {
        return Math.min(num1, num2);
    }
    public int maxNumber() {
        return Math.max(num1, num2);
    }
}
