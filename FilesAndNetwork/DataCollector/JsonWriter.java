import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonWriter {

    // Function to write stations to JSON with dates and connection status included
    public void writeStationsToJson(List<MetroStation> stations, List<StationDate> stationDates, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Create a map to store the date of each station by its name
        Map<String, String> stationDateMap = new HashMap<>();
        for (StationDate stationDate : stationDates) {
            stationDateMap.put(stationDate.getName(), stationDate.getDate());
        }

        // Initialize the ConnectionParser
        ConnectionParser connectionParser = new ConnectionParser();

        // Fetch the HTML content from the website
        Document document = Jsoup.connect("https://skillbox-java.github.io/").get();

        // Select the div with id "metrodata"
        Element metroDataDiv = document.getElementById("metrodata");

        // List to store station information
        List<Map<String, Object>> stationsList = new ArrayList<>();
        for (MetroStation station : stations) {
            Map<String, Object> stationInfo = new HashMap<>();
            stationInfo.put("name", station.getName());
            stationInfo.put("line", station.getLine());

            // Add the date if available
            String date = stationDateMap.get(station.getName());
            if (date != null) {
                stationInfo.put("date", date);
            }

            // Check if the station has connections
            Element stationElement = metroDataDiv.selectFirst(".single-station:contains(" + station.getName() + ")");
            if (stationElement != null) {
                boolean hasConnection = connectionParser.hasConnection(stationElement);
                stationInfo.put("hasConnection", hasConnection);
            } else {
                stationInfo.put("hasConnection", false);
            }

            stationsList.add(stationInfo);
        }

        // Create the final JSON structure
        Map<String, Object> stationsMap = new HashMap<>();
        stationsMap.put("stations", stationsList);

        // Write the JSON file
        mapper.writeValue(new File(filePath), stationsMap);
    }

    public static void main(String[] args) {
        MetroParser metroParser = new MetroParser();
        CsvParser csvParser = new CsvParser();
        FileSearcher fileSearcher = new FileSearcher();
        JsonWriter jsonWriter = new JsonWriter();

        try {
            // Parse metro data
            List<MetroStation> metroStations = metroParser.parseMetroData("https://skillbox-java.github.io/");

            // Search for all CSV files in the specified directory
            List<File> csvFiles = fileSearcher.searchFiles("C:\\Users\\Tera\\Desktop\\newData", ".csv");

            // Parse all CSV files and aggregate the data
            List<StationDate> allStationDates = new ArrayList<>();
            for (File csvFile : csvFiles) {
                List<StationDate> stationDates = csvParser.parseCsvFile(csvFile.getAbsolutePath());
                allStationDates.addAll(stationDates);
            }

            // Write to JSON file
            jsonWriter.writeStationsToJson(metroStations, allStationDates, "C:\\Users\\Tera\\Desktop\\stations.json");

            System.out.println("Data aggregation and JSON writing completed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
