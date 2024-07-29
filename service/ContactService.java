package finishException.service;

import finishException.model.Contact;

public class ContactService {
    public String getLastname(Contact contact){
        return contact.getLastName();
    }
    public String toString(Contact contact){
        return contact.toString();
    }
}
