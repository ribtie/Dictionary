import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DigitDictionaryService extends DictionaryService {
    public DigitDictionaryService(String filepath) {
        super(filepath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean hasDigit = false;
            while ((line = reader.readLine()) != null) {
                if (line.matches(".*\\d.*")) {
                    hasDigit = true;
                    break;
                }
            }
            valid = hasDigit;
        } catch (IOException e) {
            valid = false;
        }
    }

    public boolean addEntry(String key, String value) {
        if (!valid) return false;
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
