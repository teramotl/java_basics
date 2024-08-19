package com.skillbox;

public class Main {
    public static void main(String[] args) {
        String domain = "http://www.lenta.ru/";  // Replace with your desired domain
        WebCrawler crawler = new WebCrawler(domain);
        crawler.start(domain);
    }
}
