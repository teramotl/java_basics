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
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class WebCrawler {
    private final Set<String> visited = new HashSet<>();
    private final String domain;
    private final BufferedWriter writer;
    private final int maxDepth;
    private final int delayMillis;
    private final ForkJoinPool forkJoinPool;

    public WebCrawler(String domain, int maxDepth, int delayMillis) throws IOException {
        this.domain = normalizeDomain(domain);
        this.maxDepth = maxDepth;
        this.delayMillis = delayMillis;
        this.writer = new BufferedWriter(new FileWriter("sitemap.txt"));
        this.forkJoinPool = new ForkJoinPool();
    }

    public void start(String startUrl) {
        try {
            forkJoinPool.invoke(new CrawlTask(startUrl, 0));
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            forkJoinPool.shutdown();
        }
    }

    private class CrawlTask extends RecursiveAction {
        private final String url;
        private final int depth;

        public CrawlTask(String url, int depth) {
            this.url = url;
            this.depth = depth;
        }

        @Override
        protected void compute() {
            if (depth > maxDepth || visited.contains(url)) {
                return;
            }

            try {
                synchronized (visited) {
                    if (!visited.add(url)) {
                        return;
                    }
                }

                System.out.println("Crawling URL: " + url);
                Document doc = Jsoup.connect(url).get();
                writeIndentedLink(url, depth);

                Elements links = doc.select("a[href]");
                System.out.println("Found " + links.size() + " links on " + url);

                Set<CrawlTask> tasks = new HashSet<>();

                for (Element link : links) {
                    String absUrl = link.attr("abs:href");

                    if (isValidUrl(absUrl)) {
                        tasks.add(new CrawlTask(absUrl, depth + 1));
                    }
                }

                invokeAll(tasks);

                // Pause between requests to avoid overwhelming the server
                Thread.sleep(delayMillis);
            } catch (IOException | InterruptedException e) {
                System.err.println("Failed to fetch URL: " + url);
                e.printStackTrace();
            }
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
            synchronized (writer) {
                writer.write(" ".repeat(depth * 4) + url);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
