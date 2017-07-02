import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter implements Writer {

    private Path path;

    public FileWriter(String path, String fileName) {
        this.path = Paths.get(path + "/" + fileName + ".txt");
    }

    public BufferedWriter getWriter() throws IOException {
        return Files.newBufferedWriter(path);
    }

    @Override
    public void writeLine(String string) {

    }
}
