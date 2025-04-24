import java.io.FileWriter;
import java.io.IOException;

public class LatinDictionaryService extends DictionaryService {
    public LatinDictionaryService(String filepath) {
        super(filepath);
    }

    public boolean addEntry(String key, String value) {
        if (!key.matches("[a-zA-Z]{4}")) return false;
        if (findByKey(key) != null) return false;
        try (FileWriter writer = new FileWriter(filepath, true)) {
            writer.write(key + " - " + value + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
            return false;
        }
    }
}
