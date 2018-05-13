package project.view.impl;

import project.services.ContactService;
import project.view.ClientService;

public class ClientServiceImpl implements ClientService {
    private ContactService service ;


    public ClientServiceImpl(ContactService service) {
        this.service = service;
        showAll();
    }

    @Override
    public void showMenu() {

    }

    @Override
    public void getResponse() {

    }

    @Override
    public void createContact() {
       /* Contact contact;
        service.createContact(contact.getName(),contact.getPhoneNumber(),contact.getBirthday(), contact.getAge());
    */}

    @Override
    public void showAll() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void find() {

    }

    @Override
    public void edit() {

    }


}
