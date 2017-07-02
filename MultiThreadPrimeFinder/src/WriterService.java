//NOT YET IMPLEMENTED WILL WRITE OUTPUT TO TXT

import java.io.*;
import java.util.List;

public class WriterService {

    private BufferedWriter writer;

    public WriterService(String path, String name) {

        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path + "/" + name + ".txt")));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(List<Integer> listToWrite) {
        try {
            for (int i = 0; i < listToWrite.size(); i++) {
                writer.write(listToWrite.get(i) + " ");
                if ((i + 1) % 10 == 0){
                    writer.write("\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
