package project.services.impl;

import project.model.Contact;
import project.services.ContactService;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    //public static final String FILE = "contacts.txt";
    private List<Contact> contactList = new ArrayList<>();
    @Override
    public void createContact(String name,String phone, String birthday, int age) {

        Contact contact = new Contact(name, phone, birthday, age);
        contactList.add(contact);

    }

    @Override
    public List<Contact> findContact(String keyWord) {
        List<Contact> searchResult = new ArrayList<>();
        for (Contact contact:this.contactList) {
            if((contact.getName().contains(keyWord))||(contact.getPhoneNumber().contains(keyWord))){
                searchResult.add(contact);
            }
        }
        return  searchResult;
    }

    @Override
    public void editContact(String name, String newName, String newPhone, String newBirthday) {
        for (Contact contact:contactList){
            if (contact.getName().equals(name)) {
                if(! newName.isEmpty()){
                    contact.setName(newName);
                }
                if(! newPhone.isEmpty()){
                    contact.setPhoneNumber(newPhone);
                }
                if(! newBirthday.isEmpty()){
                    contact.setBirthday(newBirthday);
                }
                System.out.println("Contact was changed");
                System.out.println(contact.toString());
            }
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        contactList.remove(contact);
        System.out.println("Contact was deleted");

    }

    @Override
    public void showAllContacts() {
        for (Contact contact: this.contactList) {
            System.out.println(contact.toString());
        }
    }


}
