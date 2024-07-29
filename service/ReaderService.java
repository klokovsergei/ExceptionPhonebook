package finishException.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReaderService {
    public List<Path> getPhonebookContact(Path path) {
        List<Path> phonebookContacts = new ArrayList<>();
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (var p : files) {
                if (Files.isRegularFile(p) && p.getFileName().toString().contains("_phonebook.txt")) {
                    phonebookContacts.add(p.getFileName());
                }
            }

        } catch (Exception e) {
            System.out.println("Путь к директории не корректен.");
        }
        return phonebookContacts;
    }

    public List<String> readPhonebookContact(Path path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {

            while (reader.ready()) {
                list.add(reader.readLine());
            }

        } catch (Exception e) {
            System.out.println("Файл не удалось прочитать.");
        }
        return list;
    }
}
