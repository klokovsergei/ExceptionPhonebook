package finishException.service;

import finishException.model.Contact;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriterService {

    public void write(Path path, Contact contact) throws Exception {
        ContactService service = new ContactService();
        String nameFile = service.getLastname(contact) + "_phonebook.txt";
        Path pathForWrite = Path.of(path.toString(), "/", nameFile);
        if (!Files.exists(pathForWrite))
            try {
                Files.createFile(pathForWrite);
            } catch (Exception e) { throw new Exception("Ну удалось создать файл, проверьте права доступа у программы."); }
        try (FileWriter out = new FileWriter(pathForWrite.toFile(), true);
            BufferedWriter writer = new BufferedWriter(out)){
            writer.write(service.toString(contact));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw new Exception("Не удалось записать новый контакт в файл:" + pathForWrite);
        }
    }

}
