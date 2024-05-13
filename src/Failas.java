import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Failas extends  FailoBazineInformacija{

    int[] masyvas = new int[10000];
    String path = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-18b\\src\\";
    private Connection connection;
    public Failas(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void sort(int[] array) {
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
    public void spausdintIFaila(int[] masyvas) throws IOException {
        FileWriter fileWriter = new FileWriter(path + failoPavadinimas, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < masyvas.length; i++){
            if(masyvas[i] == 0){
                continue;
            }
            System.out.println(masyvas[i]);
            bufferedWriter.write(String.valueOf(masyvas[i]));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }
    public int failuKiekis(String directoryPath){
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.{txt,csv}")) {
            int c = 0;
            for (Path entry : stream) {
                c = c + 1;
            }
            return c;
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
            return 0;
        }
    }
    public String[] listTextAndCsvFiles(String directoryPath) {
        String[] fileList = new String[failuKiekis(path)];
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.{txt,csv}")) {
            int b = 0;
            for (Path entry : stream) {
                fileList[b] = String.valueOf(entry);
                b = b + 1;
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
        }
        return fileList;
    }

    public void spausdintRusiuotusFailuPavadinimus(String directoryPath) {
        String[] fileList1 = new String[failuKiekis(path)];
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.{txt,csv}")) {
            int b = 0;
            for (Path entry : stream) {
                fileList1[b] = entry.getFileName().toString();
                b = b + 1;
            }
            klaseStringMasyvas.sort(fileList1);
            for(String s : fileList1){
                System.out.println(s);
            }

        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
        }
    }
    public void isFailoIMasyva() throws IOException {
        String[] listas = listTextAndCsvFiles(path);
        for(int i = 0; i < listas.length; i++){
            FileReader fileReader = new FileReader(String.valueOf(listas[i]));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int a = 0;
            while ((line = bufferedReader.readLine()) != null) {
                masyvas[a] = Integer.parseInt(line);
                a = a + 1;
            }
            fileReader.close();
            bufferedReader.close();
        }

    }
    public void kurtiLentele() throws SQLException {
        String sql = "CREATE TABLE " + failoPavadinimas + "(skaiciai INT )";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    public void idetiSkaiciusIDB() throws SQLException {
        for(Integer i : masyvas){
            String sql1 = "INSERT INTO " + failoPavadinimas + " (skaiciai) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql1);
            statement.setInt(1, i);
        }
    }

}
