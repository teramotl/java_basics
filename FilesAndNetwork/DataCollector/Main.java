import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MetroParser metroParser = new MetroParser();
        CsvParser csvParser = new CsvParser();
        JsonParser jsonParser = new JsonParser();
        FileSearcher fileSearcher = new FileSearcher();
        JsonWriter jsonWriter = new JsonWriter();

        try {

            // Search for all JSON files in the specified directory
            List<File> jsonFiles = fileSearcher.searchFiles("C:\\Users\\Tera\\Desktop\\data", ".json");
            // Search for all CSV files in the specified directory
            List<File> csvFiles = fileSearcher.searchFiles("C:\\Users\\Tera\\Desktop\\data", ".csv");
            // Parse metro data from specified url
            List<MetroStation> metroStations = metroParser.parseMetroData("https://skillbox-java.github.io/");




            // Parse all CSV files and aggregate the data
            List<StationDate> allStationDates = new ArrayList<>();
            for (File csvFile : csvFiles) {
                List<StationDate> stationDates = csvParser.parseCsvFile(csvFile.getAbsolutePath());
                allStationDates.addAll(stationDates);
            }


            // Parse all JSON files and aggregate the data
            List<StationDepth> allStationDepths = new ArrayList<>();
            for (File jsonFile : jsonFiles) {
                List<StationDepth> stationDepths = jsonParser.parseJsonFile(jsonFile.getAbsolutePath());
                allStationDepths.addAll(stationDepths);
            }

            // Write to JSON file
            jsonWriter.writeStationsToJson(metroStations, allStationDates, allStationDepths, "C:\\Users\\Tera\\Desktop\\stations.json");

            // Write the map.json file
            jsonWriter.writeMapToJson("https://skillbox-java.github.io/", "C:\\Users\\Tera\\Desktop\\map.json");

            System.out.println("Data written to JSON files successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
