import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ConnectionParser {

    /**
     * Checks if the given station element has connections.
     *
     * @param stationElement The Jsoup element representing the station.
     * @return true if the station has connections, false otherwise.
     */
    public boolean hasConnection(Element stationElement) {
        // Check if the span with class "t-icon-metroln" has a title attribute
        Elements connectionIcons = stationElement.select(".t-icon-metroln");
        for (Element icon : connectionIcons) {
            if (icon.hasAttr("title")) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ConnectionParser parser = new ConnectionParser();

        try {
            // Fetch the HTML content from the website
            Document document = Jsoup.connect("https://skillbox-java.github.io/").get();

            // Select the div with id "metrodata"
            Element metroDataDiv = document.getElementById("metrodata");

            // Select all station elements
            Elements stationElements = metroDataDiv.select(".single-station");

            // Check connections for each station
            for (Element stationElement : stationElements) {
                String stationName = stationElement.selectFirst(".name").text().trim();
                boolean hasConnection = parser.hasConnection(stationElement);
                System.out.println("Station: " + stationName + " | Has Connection: " + hasConnection);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
