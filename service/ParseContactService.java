package finishException.service;

import finishException.model.Contact;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParseContactService {
    public Contact parseStringToContact(String text) throws Exception{
        String lastName;
        String firstName;
        String middleName;
        char gender;
        LocalDate birthday;
        long phoneNumber;

        List<String> list = new ArrayList<>(List.of(text.split(" ")));
        if (list.size() != 6)
            throw new Exception("Кол-во введенных элементов недостаточно для создания корректного контакта.");

        String result = findGender(list);
        if (result == null)
            throw new Exception("В введенном контакте не указан пол согласно требованию.");
        else {
            gender = result.toLowerCase().toCharArray()[0];
        }
        list.remove(result);

        birthday = findBirthday(list);
        list.remove(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(birthday));

        phoneNumber = findPhoneNumber(list);
        list.remove(String.valueOf(phoneNumber));

        lastName = list.get(0);
        firstName = list.get(1);
        middleName = list.get(2);

        return new Contact(lastName, firstName, middleName, gender, birthday, phoneNumber);
    }
    private String findGender(List<String> list) throws Exception{
        return list.stream()
                .filter(x -> (x.equalsIgnoreCase("m") || x.equalsIgnoreCase("f")))
                .findFirst()
                .orElseThrow(() -> new Exception("В введенном контакте не указан пол согласно требованию."));

    }
    private LocalDate findBirthday(List<String> list) throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return list.stream()
                .map(s -> {
                    try {
                        return LocalDate.parse(s, dtf);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new Exception("В введенном контакте не указана дата рождения согласно требованию."));

    }
    private long findPhoneNumber(List<String> list) throws Exception {
        return list.stream()
                .map(s -> {
                    try {
                        return Long.parseLong(s);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new Exception("В введенном контакте не указан номер телефона согласно требованию."));
    }

}
