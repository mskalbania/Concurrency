import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader implements Reader {

    private Path path;

    public FileReader(String path, String filename) {
        this.path = Paths.get(path, filename);
    }

    public List<String> readWords() {
        List<String> output = null;
        try {
            output = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }
}
