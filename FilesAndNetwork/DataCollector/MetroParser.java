import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetroParser {

    public List<MetroLine> parseMetroData(String url) throws IOException {
        // Fetch the HTML content
        Document document = Jsoup.connect(url).get();

        // Select the div with id "metrodata"
        Element metroDataDiv = document.getElementById("metrodata");

        // Select all metro line elements
        Elements lineElements = metroDataDiv.select(".js-toggle-depend");

        List<MetroLine> metroLines = new ArrayList<>();

        // Loop through each line element and extract line name and stations
        for (Element lineElement : lineElements) {
            Element lineNameElement = lineElement.selectFirst(".js-metro-line");
            String lineName = lineNameElement.text().trim();
            String lineNumber = lineNameElement.attr("data-line");

            MetroLine metroLine = new MetroLine(lineName, lineNumber);

            // Get the corresponding stations container
            Element stationsContainer = lineElement.nextElementSibling();
            Elements stationElements = stationsContainer.select(".single-station");

            // Extract station names and numbers, and add to the line
            for (Element stationElement : stationElements) {
                String stationNumber = stationElement.selectFirst(".num").text().replace(".", "").trim();
                String stationName = stationElement.selectFirst(".name").text().trim();
                MetroStation metroStation = new MetroStation(stationName, stationNumber);
                metroLine.addStation(metroStation);
            }

            metroLines.add(metroLine);
        }

        return metroLines;
    }

    public static void main(String[] args) {
        MetroParser parser = new MetroParser();
        try {
            List<MetroLine> metroLines = parser.parseMetroData("https://skillbox-java.github.io/");

            // Print the extracted data
            for (MetroLine line : metroLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
