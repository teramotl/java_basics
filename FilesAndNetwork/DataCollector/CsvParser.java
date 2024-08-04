import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvParser {
    public List<StationDate> parseCsvFile(String filePath) throws IOException {
        return new CsvToBeanBuilder<StationDate>(new FileReader(filePath))
                .withType(StationDate.class)
                .build()
                .parse();
    }

    public static void main(String[] args) {
        CsvParser parser = new CsvParser();
        try {
            List<StationDate> dates = parser.parseCsvFile("C:\\Users\\Tera\\Desktop\\newData\\dates-1.csv");
            dates.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
