package project.services.impl;

import project.model.Contact;
import project.services.ContactService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    //public static final String FILE = "contacts.txt";
    private List<Contact> contactList = new ArrayList<>();
    @Override
    public void createContact(String name,String phone, String birthday) {

        Contact contact = new Contact(name, phone,birthday);
        contactList.add(contact);


    }

    @Override
    public void findContact() {
        System.out.println("Find contact");
    }

    @Override
    public void editContact() {
        System.out.println("Edit contact");
    }

    @Override
    public void deleteContact() {
        System.out.println("delete contact");
    }

    @Override
    public void showAllContacts() {
        for (Contact contact1: this.contactList) {
            System.out.println(contact1.toString());
        }
    }


}
