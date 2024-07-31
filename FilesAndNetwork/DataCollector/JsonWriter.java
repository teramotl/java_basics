import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    public void writeStationsToJson(List<MetroStation> stations, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Create a wrapper class for the stations list
        class StationsWrapper {
            public List<MetroStation> stations;

            public StationsWrapper(List<MetroStation> stations) {
                this.stations = stations;
            }
        }

        StationsWrapper wrapper = new StationsWrapper(stations);

        // Write the JSON file
        mapper.writeValue(new File(filePath), wrapper);
    }

    public static void main(String[] args) {
        MetroParser parser = new MetroParser();
        try {
            List<MetroStation> metroStations = parser.parseMetroData("https://skillbox-java.github.io/");

            JsonWriter writer = new JsonWriter();
            writer.writeStationsToJson(metroStations, "C:\\Users\\Tera\\Desktop\\stations.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
