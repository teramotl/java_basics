public class Computer {
    private final String vendor;
    private final String name;
    private final Processor processor;
    private final Memory memory;
    private final Storage storage;
    private final Monitor monitor;
    private final Keyboard keyboard;

    public Computer(String manufacturer, String name, Processor processor, Memory memory, Storage storage, Monitor monitor, Keyboard keyboard) {
        this.vendor = manufacturer;
        this.name = name;
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.monitor = monitor;
        this.keyboard = keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public Storage getStorage() {
        return storage;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public double calculateTotalWeight() {
        return processor.getWeight() + memory.getWeight() + storage.getWeight() + monitor.getWeight() + keyboard.getWeight();
    }

    public String toString() {
        return "Computer: " + name + " by " + vendor + "\n" +
                "Processor: " + processor + "\n" +
                "Memory: " + memory + "\n" +
                "Storage: " + storage + "\n" +
                "Monitor: " + monitor + "\n" +
                "Keyboard: " + keyboard + "\n" +
                "Total Weight: " + calculateTotalWeight() + " kg";
    }
}
