public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("chtoto",5000,1,32);
        basket.add("ewe chtoto",2000, 1, 100);
        System.out.println("общая стоимость " + Basket.getTotalItemCost());
        System.out.println("средняя стоимость " + Basket.getAverageItemPrice());
        System.out.println("кол-во товаров " + Basket.getTotalItems());
    }
}
