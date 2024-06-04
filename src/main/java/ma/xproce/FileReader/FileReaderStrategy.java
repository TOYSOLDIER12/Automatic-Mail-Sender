package ma.xproce.FileReader;

import java.util.List;

public interface FileReaderStrategy {
    List<String> readFile(String filePath);
}
