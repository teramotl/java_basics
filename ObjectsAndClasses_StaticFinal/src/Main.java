public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor("amd",2.4,16,200);
        Memory memory = new Memory("DDR5",16,400);
        Storage storage = new Storage("SSD", 512, 800);
        Monitor monitor = new Monitor(24, "IPS",2700);
        Keyboard keyboard = new Keyboard("mechanical", true, 900);

        Computer computer = new Computer("Alienware","NLO", processor, memory, storage, monitor, keyboard);

        System.out.println(computer);
    }
}