package project.services;

import project.model.Contact;

import java.util.List;

public interface ContactService {

void createContact(String name,String phone, String birthday, int age);
List<Contact> findContact(String keyWord);
void editContact(String name, String newName, String newPhone, String newBirthday);
void deleteContact(Contact contact);
void showAllContacts();

}
