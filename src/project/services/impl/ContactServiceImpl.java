package project.services.impl;

import project.model.Contact;
import project.services.ContactService;

public class ContactServiceImpl implements ContactService {

    @Override
    public void createContact() {
        System.out.println("Contact is created");
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
        System.out.println("show all contacts");
    }
}
