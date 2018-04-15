package project.services.impl;

import project.dao.ContactDao;
import project.model.Contact;
import project.services.ContactService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactServiceImpl implements ContactService {

    private Map<Integer,Contact> contactList = new HashMap<>();
    private static int id = 0;

    private ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void createContact(String name,String phone, String birthday, int age) {
        Contact contact = new Contact(name, phone, birthday, age);
        contactDao.saveContact(contact);
    }

    @Override
    public List<Contact> findContact(String keyWord) {
        List<Contact> searchResult = new ArrayList<>();
        for (Map.Entry<Integer, Contact> contacts: contactList.entrySet()) {
            if((contacts.getValue().getName().contains(keyWord))||(contacts.getValue().getPhoneNumber().contains(keyWord))){
                searchResult.add(contacts.getValue());
            }
        }
        return  searchResult;
    }

    @Override
    public void editContact(String name, String newName, String newPhone, String newBirthday) {
        for (Map.Entry<Integer, Contact> contacts: contactList.entrySet()) {
            if(contacts.getValue().getName().equals(name)){
                if(! newName.isEmpty()){
                    contacts.getValue().setName(newName);
                }
                if(! newPhone.isEmpty()){
                    contacts.getValue().setPhoneNumber(newPhone);
                }
                if(! newBirthday.isEmpty()){
                    contacts.getValue().setBirthday(newBirthday);
                }
                System.out.println("Contact was changed");
                System.out.println(contacts.getValue().toString());
            }
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        for (Map.Entry<Integer, Contact> contacts: contactList.entrySet()) {
            if(contacts.getValue().equals(contact)){
                this.contactList.remove(contacts.getKey());
            }
        }
        System.out.println("Contact was deleted");
    }

    @Override
    public void showAllContacts() {
        contactDao.showAll();
    }

}
