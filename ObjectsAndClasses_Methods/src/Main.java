public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("chtoto",5000,1,32);
        basket.add("ewe chtoto",2000, 1, 100);

        Basket basket1 = new Basket();
        basket.add("drugoy",1010,1,50);
        basket.add("da",5000,1,20);

        System.out.println("общая стоимость всех корзин " + Basket.getTotalBaketPrice());
        System.out.println("кол-во товаров " + Basket.getTotalItems());
        System.out.println("средняя стоимость товара " + Basket.getAverageItemPrice());
        System.out.println("кол-во корзин " + Basket.getBasketCount());
        System.out.println("средняя стоимость корзины " + Basket.getAverageBasketPrice());
    }
}
