package project.services.impl;

import project.dao.ContactDao;
import project.model.Contact;
import project.services.ContactService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactServiceImpl implements ContactService {

   
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
        return  contactDao.findContact(keyWord);
    }

    @Override
    public void editContact(String name, String newName, String newPhone, String newBirthday) {
        Contact newContact = contactDao.findContact(name).get(0);

        if(! newName.isEmpty()){
            newContact.setName(newName);
        }
        if(! newPhone.isEmpty()){
            newContact.setPhoneNumber(newPhone);
        }
        if(! newBirthday.isEmpty()){
            newContact.setBirthday(newBirthday);
        }

        contactDao.editContact(name, newContact);

        System.out.println("Contact was changed");
        System.out.println(newContact.toString());
    }


    @Override
    public void deleteContact(Contact contact) {
        contactDao.removeContact(contact.getName());
        System.out.println("Contact was deleted");
    }

    @Override
    public void showAllContacts() {
        contactDao.showAll();
    }

}
