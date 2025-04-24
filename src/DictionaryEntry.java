public class DictionaryEntry {
    public String key;
    public String value;

    public DictionaryEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return key + " - " + value;
    }
}
