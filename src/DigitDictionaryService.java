import java.io.FileWriter;
import java.io.IOException;

public class DigitDictionaryService extends DictionaryService {
    public DigitDictionaryService(String filepath) {
        super(filepath);
    }

    public boolean addEntry(String key, String value) {
        if (!key.matches("\\d{5}")) return false;
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
