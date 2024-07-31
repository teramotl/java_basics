import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetroParser {

    public List<MetroStation> parseMetroData(String url) throws IOException {
        // Fetch the HTML content
        Document document = Jsoup.connect(url).get();

        // Select the div with id "metrodata"
        Element metroDataDiv = document.getElementById("metrodata");

        // Select all metro line elements
        Elements lineElements = metroDataDiv.select(".js-toggle-depend");

        List<MetroStation> metroStations = new ArrayList<>();

        // Loop through each line element and extract line name and stations
        for (Element lineElement : lineElements) {
            Element lineNameElement = lineElement.selectFirst(".js-metro-line");
            String lineName = lineNameElement.text().trim();

            // Get the corresponding stations container
            Element stationsContainer = lineElement.nextElementSibling();
            Elements stationElements = stationsContainer.select(".single-station");

            // Extract station names and add to the list
            for (Element stationElement : stationElements) {
                String stationName = stationElement.selectFirst(".name").text().trim();
                MetroStation metroStation = new MetroStation(stationName, lineName);
                metroStations.add(metroStation);
            }
        }

        return metroStations;
    }

    public static void main(String[] args) {
        MetroParser parser = new MetroParser();
        try {
            List<MetroStation> metroStations = parser.parseMetroData("https://skillbox-java.github.io/");

            // Print the extracted data
            for (MetroStation station : metroStations) {
                System.out.println(station);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
