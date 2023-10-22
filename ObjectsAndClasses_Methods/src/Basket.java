public class Basket {
    private String items = "";
    private int price = 0;
    private int limit;
    private double totalWeight = 0;
    private static int totalItems = 0;
    private static int totalItemCost = 0;
    private static int totalCount = 0;
    private static int basketCount = 0;


    public Basket() {
        basketCount += 1;
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.price = totalPrice;
    }

    public void add(String name, int price, int count, double totalWeight) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (this.price + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + "шт. - " + price + "руб. - " + totalWeight + "кг";
        this.price = this.price + count * price;
        this.totalWeight += totalWeight * count;
        totalItemCost += count * price;
        totalCount += count;
        totalItems += count;
    }
    public static int getTotalBaketPrice() {
        return totalItemCost;
    }
    public static int getTotalItems() {
        return totalItems;
    }
    public static int getAverageItemPrice() {
        return totalItemCost / totalCount;
    }
    public static int getBasketCount() {
        return basketCount;
    }
    public static int getAverageBasketPrice() {
        return totalItemCost / basketCount;
    }
    public void clear() {
        items = "";
        price = 0;
        totalWeight = 0;
    }

    public int getPrice() {
        return price;
    }
    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
