package project.services;

import project.model.Contact;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ContactService {

    void createContact(String name,String phone, String birthday);
    void findContact();
    void editContact();
    void deleteContact();
    void showAllContacts();
}
