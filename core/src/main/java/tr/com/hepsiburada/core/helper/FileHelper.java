package tr.com.hepsiburada.core.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileHelper {

    public Stream<String> readFileLineByLine(String fileName) {
        try {
            return Files.lines(Paths.get(fileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
