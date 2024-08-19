package com.skillbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebCrawler {
    private final Set<String> visited = new HashSet<>();
    private final String domain;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    public WebCrawler(String domain) {
        this.domain = domain;
    }

    public void start(String startUrl) {
        executor.schedule(() -> crawl(startUrl, 0), 0, TimeUnit.MILLISECONDS);
        executor.shutdown();
    }

    private void crawl(String url, int depth) {
        if (depth > 3) return;  // Limit depth for simplicity
        if (visited.contains(url)) return;

        try {
            Document doc = Jsoup.connect(url).get();
            visited.add(url);

            Elements links = doc.select("a[href]");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("sitemap.txt", true))) {
                for (Element link : links) {
                    String absUrl = link.attr("abs:href");
                    if (absUrl.startsWith(domain) && !absUrl.contains("#")) {
                        writeIndentedLink(writer, absUrl, depth);
                        executor.schedule(() -> crawl(absUrl, depth + 1), 100, TimeUnit.MILLISECONDS);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeIndentedLink(BufferedWriter writer, String url, int depth) throws IOException {
        writer.write(" ".repeat(depth * 4) + url);
        writer.newLine();
    }
}
