import java.util.*;
import java.io.*;

public abstract class DictionaryService {
    protected String filepath;
    protected boolean valid = true;

    public DictionaryService(String filepath) {
        this.filepath = filepath;
    }


    public boolean isValid() {
        return valid;
    }

    public List<DictionaryEntry> readAll() {
        if (!valid) return new ArrayList<>();
        List<DictionaryEntry> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    list.add(new DictionaryEntry(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return list;
    }

    public DictionaryEntry findByKey(String key) {
        if (!valid) return null;
        for (DictionaryEntry e : readAll()) {
            if (e.key.equals(key)) return e;
        }
        return null;
    }

    public boolean deleteEntry(String key) {
        if (!valid) return false;
        List<DictionaryEntry> entries = readAll();
        boolean removed = entries.removeIf(e -> e.key.equals(key));
        if (!removed) return false;

        try (PrintWriter writer = new PrintWriter(filepath)) {
            for (DictionaryEntry e : entries) {
                writer.println(e.key + " - " + e.value);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка удаления: " + e.getMessage());
            return false;
        }
    }

    public abstract boolean addEntry(String key, String value);
}
