import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FailoBazineInformacija {
    String failoPavadinimas = "surikiuotiSkaiciai.txt";
    String filePath = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-18b\\src\\";
    String failoDydis;
    String path = filePath + failoPavadinimas;

    public String getFileSizeInKB(Path path) {
        try {
            long sizeInBytes = Files.size(path);
            long sizeInKB = sizeInBytes / 1024;
            return sizeInKB + " KB";
        } catch (IOException e) {
            System.err.println("Unable to determine file size: " + e.getMessage());
            return "Error";
        }
    }
}
