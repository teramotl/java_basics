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

    public List<List<Connection>> parseConnections(String url) throws IOException {
        // Fetch the HTML content
        Document document = Jsoup.connect(url).get();

        // Select all connection elements
        Elements connectionElements = document.select(".t-icon-metroln");

        List<List<Connection>> connections = new ArrayList<>();

        for (Element connectionElement : connectionElements) {
            String title = connectionElement.attr("title");
            if (title.startsWith("переход на станцию")) {
                String connectedStation = title.replace("переход на станцию «", "").replace("»", "");
                String lineClass = connectionElement.className().replaceAll("[^0-9]", "");
                int line = Integer.parseInt(lineClass);

                Connection connection = new Connection(line, connectedStation);
                List<Connection> connectionList = new ArrayList<>();
                connectionList.add(connection);
                connections.add(connectionList);
            }
        }

        return connections;
    }

    public static void main(String[] args) {
        MetroParser parser = new MetroParser();
        try {
            List<MetroStation> metroStations = parser.parseMetroData("https://skillbox-java.github.io/");
            List<List<Connection>> connections = parser.parseConnections("https://skillbox-java.github.io/");

            // Print the extracted data
            for (MetroStation station : metroStations) {
                System.out.println(station);
            }

            // Print the connections
            for (List<Connection> connectionList : connections) {
                for (Connection connection : connectionList) {
                    System.out.println(connection);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
