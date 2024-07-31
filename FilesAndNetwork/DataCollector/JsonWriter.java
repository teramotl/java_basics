import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    public static void writeStationsToJson(List<Station> stations, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Enable pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Create a wrapper class for the JSON structure
        StationsWrapper wrapper = new StationsWrapper(stations);
        mapper.writeValue(new File(filePath), wrapper);
    }

    public static void main(String[] args) {
        MetroParser parser = new MetroParser();
        try {
            List<Station> stations = parser.parseStationNames("https://skillbox-java.github.io/");
            writeStationsToJson(stations, "stations.json");
            System.out.println("JSON file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
