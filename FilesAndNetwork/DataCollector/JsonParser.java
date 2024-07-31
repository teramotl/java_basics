import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser {
    public List<StationDepth> parseJsonFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), mapper.getTypeFactory().constructCollectionType(List.class, StationDepth.class));
    }

    public static void main(String[] args) {
        JsonParser parser = new JsonParser();
        try {
            List<StationDepth> depths = parser.parseJsonFile("C:\\Users\\Tera\\Desktop\\depths\\depths-1.json");
            depths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
