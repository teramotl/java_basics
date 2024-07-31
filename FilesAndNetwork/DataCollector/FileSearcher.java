import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {

    public List<File> searchFiles(String directoryPath, String extension) {
        File directory = new File(directoryPath);
        List<File> resultList = new ArrayList<>();
        searchFilesRecursively(directory, extension, resultList);
        return resultList;
    }

    private void searchFilesRecursively(File directory, String extension, List<File> resultList) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        searchFilesRecursively(file, extension, resultList);
                    } else if (file.getName().endsWith(extension)) {
                        resultList.add(file);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FileSearcher searcher = new FileSearcher();
        String directoryPath = "C:\\Users\\Tera\\Desktop\\data";

        List<File> jsonFiles = searcher.searchFiles(directoryPath, ".json");
        List<File> csvFiles = searcher.searchFiles(directoryPath, ".csv");

        System.out.println("Found JSON files:");
        for (File file : jsonFiles) {
            System.out.println(file.getAbsolutePath());
        }

        System.out.println("Found CSV files:");
        for (File file : csvFiles) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
