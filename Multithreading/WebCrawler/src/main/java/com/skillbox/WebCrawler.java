package com.skillbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WebCrawler {
    private final Set<String> visited = new HashSet<>();
    private final String domain;
    private final BufferedWriter writer;
    private final int maxDepth;
    private final int delayMillis;

    public WebCrawler(String domain, int maxDepth, int delayMillis) throws IOException {
        this.domain = normalizeDomain(domain);
        this.maxDepth = maxDepth;
        this.delayMillis = delayMillis;
        this.writer = new BufferedWriter(new FileWriter("sitemap.txt"));
    }

    public void start(String startUrl) {
        Queue<UrlDepthPair> queue = new LinkedList<>();
        queue.add(new UrlDepthPair(startUrl, 0));

        while (!queue.isEmpty()) {
            UrlDepthPair current = queue.poll();
            if (current.depth > maxDepth || visited.contains(current.url)) {
                continue;
            }

            crawl(queue, current.url, current.depth);
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crawl(Queue<UrlDepthPair> queue, String url, int depth) {
        try {
            System.out.println("Crawling URL: " + url);
            Document doc = Jsoup.connect(url).get();
            visited.add(url);
            writeIndentedLink(url, depth);

            Elements links = doc.select("a[href]");
            System.out.println("Found " + links.size() + " links on " + url);

            for (Element link : links) {
                String absUrl = link.attr("abs:href");

                if (isValidUrl(absUrl) && !visited.contains(absUrl)) {
                    queue.add(new UrlDepthPair(absUrl, depth + 1));
                }
            }

            // Pause between requests to avoid overwhelming the server
            Thread.sleep(delayMillis);
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to fetch URL: " + url);
            e.printStackTrace();
        }
    }

    private String normalizeDomain(String domain) {
        if (domain.startsWith("http://") || domain.startsWith("https://")) {
            domain = domain.substring(domain.indexOf("://") + 3);
        }
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    private boolean isValidUrl(String url) {
        try {
            URL u = new URL(url);
            return u.getHost() != null && u.getHost().endsWith(domain) && !url.contains("#");
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private void writeIndentedLink(String url, int depth) {
        try {
            writer.write(" ".repeat(depth * 4) + url);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class UrlDepthPair {
        String url;
        int depth;

        UrlDepthPair(String url, int depth) {
            this.url = url;
            this.depth = depth;
        }
    }
}
