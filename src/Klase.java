import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Klase {
    String failoPavadinimas;
    int[] masyvas;
    String path = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-18b\\src\\";

    public static void sort(int[] array) {
        boolean swapped;
        int n = array.length;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }
    public int[] rusiuok(){
        sort(masyvas);
        return masyvas;
    }
    public void spausdintIFaila(int[] masyvas){
        for (int i = 0; i < masyvas.length; i++){
            System.out.println(masyvas[i]);
        }
    }
    public static List<Path> listTextAndCsvFiles(String directoryPath) {
        List<Path> fileList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.{txt,csv}")) {
            for (Path entry : stream) {
                fileList.add(entry);
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
        }
        return fileList;
    }
}
