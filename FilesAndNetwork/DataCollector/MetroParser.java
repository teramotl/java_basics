import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetroParser {

    public List<Station> parseStationNames(String url) throws IOException {
        // Fetch the HTML content
        Document document = Jsoup.connect(url).get();

        // Select the div with id "metrodata"
        Element metroDataDiv = document.getElementById("metrodata");

        // Select all metro line elements
        Elements lineElements = metroDataDiv.select(".js-toggle-depend");

        List<Station> stations = new ArrayList<>();

        // Loop through each line element and extract station names
        for (Element lineElement : lineElements) {
            // Get the corresponding stations container
            Element stationsContainer = lineElement.nextElementSibling();
            Elements stationElements = stationsContainer.select(".single-station");

            // Extract station names and add to the list
            for (Element stationElement : stationElements) {
                String stationName = stationElement.selectFirst(".name").text().trim();
                stations.add(new Station(stationName));
            }
        }

        return stations;
    }

    public static void main(String[] args) {
        MetroParser parser = new MetroParser();
        try {
            List<Station> stations = parser.parseStationNames("https://skillbox-java.github.io/");

            // Print the extracted data
            for (Station station : stations) {
                System.out.println(station.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
