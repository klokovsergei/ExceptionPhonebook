package finishException.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contact {
    private String lastName;
    private String firstName;
    private String middleName;
    private char gender;
    private LocalDate birthday;
    private long phoneNumber;

    public Contact(String lastName, String firstName, String middleName, char gender, LocalDate birthday, long phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return '<' + lastName + '>' +
                '<' + firstName + '>' +
                '<' + middleName + '>' +
                '<' + dtf.format(birthday) + '>' +
                '<' + phoneNumber + '>' +
                '<' + gender + '>' + "\n";
    }

}
