public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        for (int i = 1040; i < 1105; i++) {
            char c = (char) i;
            System.out.println(i + " - " + c);
        }
    }
}
