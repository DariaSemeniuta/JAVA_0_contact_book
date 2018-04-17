package project.dao;

import project.model.Contact;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ContactDao {

    void saveContact(Contact contact);
    void removeContact(String name);
    void showAll();
    List<Contact> findContact(String keyWord);
    void editContact(String name, Contact newContact);


}
