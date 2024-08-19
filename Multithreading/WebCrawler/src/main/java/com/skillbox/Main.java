package com.skillbox;

public class Main {
    public static void main(String[] args) {
        String domain = "lenta.ru";  // Use the domain without the scheme
        String startUrl = "http://www.lenta.ru/";  // Starting URL with scheme

        try {
            WebCrawler crawler = new WebCrawler(domain, 3, 150);  // Domain, max depth, and delay in milliseconds
            crawler.start(startUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
