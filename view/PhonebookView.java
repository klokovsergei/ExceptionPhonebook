package finishException.view;

import finishException.model.Contact;
import finishException.service.ParseContactService;
import finishException.service.ReaderService;
import finishException.service.WriterService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class PhonebookView {
    Path currentDir = Paths.get(".");
    public void start(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Выберите команду:\n"+
                    "\t1. Создать новый контакт\n" +
                    "\t2. Показать имеющиеся контакты\n" +
                    "\t0. Выйти из программы\n" +
                    "Ваш выбор: ");
            switch (scanner.nextLine()){
                case "1" -> {
                    try {
                        Contact contact = new ParseContactService().parseStringToContact(getStringWithNewContact());
                        new WriterService().write(currentDir, contact);
                    } catch (Exception e) {
                        System.out.println("\nERROR: " + e.getMessage());
                    }
                }
                case "2" -> showPhonebook();
                case "0" -> System.exit(0);
                default -> System.out.println("Команда не поддерживается.");
            }
        }
    }

    private String getStringWithNewContact(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\nДля создания нового контакта введите:\n" +
                "Фамилия Имя Отчество датарождения номертелефона пол\n" +
                "- разделителем является ' '(пробел)\n" +
                "- фамилия, имя, отчество - строки\n" +
                "- номер_телефона - целое беззнаковое число без форматирования\n" +
                "- дата_рождения - строка формата dd.mm.yyyy\n" +
                "- пол - символ латиницей f или m\n" +
                "- последовательность блоков ввода любая\n" +
                "- в конце введите 'Enter'.\nВводить здесь: ");
        return scanner.nextLine();
    }
    private void showPhonebook(){
        Scanner scanner = new Scanner(System.in);
        List<Path> listContacts = new ReaderService().getPhonebookContact(currentDir);
        System.out.printf("\nНайдено записей в телефонной книге - %s\n", listContacts.size());
        if (listContacts.size() > 0) {
            System.out.println("Выберите файл для отображения:");
            for (int i = 0; i < listContacts.size(); i++) {
                System.out.printf("%s - %s\n", (i+1), listContacts.get(i).getFileName().toString().split("_")[0]);
            }
            System.out.print("Выберите файл для отображения или любую клавишу для возврата в предыдущее меню: ");
            int index = 0;
            try {
                index = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (Exception e) {
                System.out.println("Вы выбрали некорректный номер записи.");
                start();
            }
            System.out.println("\nИмеющиеся контакты:");
            List<String> listContact = new ReaderService().readPhonebookContact(listContacts.get(index));
            listContact.stream().forEach(System.out::println);
        }

    }
}
