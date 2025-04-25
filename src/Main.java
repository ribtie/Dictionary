
   import java.io.BufferedReader;
   import java.io.FileNotFoundException;
   import java.io.FileReader;
   import java.io.IOException;
   import java.util.List;
import java.util.Scanner;

    public class Main {
        public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);

            DictionaryService service = null;

            System.out.println("Выберите язык словаря:");
            System.out.println("1. Латинский (4 буквы)");
            System.out.println("2. Цифровой (5 цифр)");
            BufferedReader reader;
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("Введите путь 1 словаря");
                String lang1 = scanner.nextLine();
                reader  = new BufferedReader(new FileReader(lang1));
                if(!reader.readLine().matches(".*\\d.*"))
                service = new LatinDictionaryService(lang1);
                else
                {
                    System.out.println("Неверный выбор.");
                    return;
                }
            } else if (choice.equals("2")) {
                System.out.println("Введите путь 2 словаря");
                String lang2 = scanner.nextLine();
                reader  = new BufferedReader(new FileReader(lang2));
                if(reader.readLine().matches(".*\\d.*"))
                service = new DigitDictionaryService(lang2);
                else
                {
                    System.out.println("Неверный выбор.");
                    return;
                }
            } else {
                System.out.println("Неверный выбор.");
                return;
            }

            while (true) {
                System.out.println("\n1. Просмотр\n2. Поиск\n3. Добавление\n4. Удаление\n5. Выход");
                String action = scanner.nextLine();

                switch (action) {
                    case "1":
                        List<DictionaryEntry> entries = service.readAll();
                        for (DictionaryEntry e : entries) {
                            System.out.println(e);
                        }
                        break;
                    case "2":
                        System.out.print("Введите ключ: ");
                        String searchKey = scanner.nextLine();
                        DictionaryEntry found = service.findByKey(searchKey);
                        System.out.println(found != null ? found : "Не найдено");
                        break;
                    case "3":
                        System.out.print("Введите ключ: ");
                        String newKey = scanner.nextLine();
                        System.out.print("Введите перевод: ");
                        String newValue = scanner.nextLine();
                        boolean added = service.addEntry(newKey, newValue);
                        System.out.println(added ? "Добавлено" : "Ошибка добавления");
                        break;
                    case "4":
                        System.out.print("Введите ключ: ");
                        String delKey = scanner.nextLine();
                        boolean deleted = service.deleteEntry(delKey);
                        System.out.println(deleted ? "Удалено" : "Не найдено");
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Неверный выбор");
                }
            }
        }
    }

