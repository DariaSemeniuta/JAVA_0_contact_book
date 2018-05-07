package project.dao;

import project.model.Contact;

import java.util.List;

public interface ContactDao {

    void saveContact(Contact contact);
    void removeContact(String name);
    List<Contact> showAll();
    List<Contact> findContact(String keyWord);
    void editContact(String name, Contact newContact);


}
