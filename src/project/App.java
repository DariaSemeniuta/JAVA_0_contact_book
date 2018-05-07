package project;

import project.dao.ContactDao;
import project.dao.impl.DBContactDao;
import project.services.ContactService;
import project.services.impl.ContactServiceImpl;
import project.view.ClientService;
import project.view.impl.ClientServiceImpl;

public class App {

    public static void main(String[] args) {


        ContactDao contactDao = new DBContactDao();//new FileSystemContactDaoImpl();
        ContactService contactService = new ContactServiceImpl(contactDao);
        ClientService cmd = new ClientServiceImpl(contactService);//new CmdLineServiceImpl(contactService);

        cmd.showMenu();
        cmd.getResponse();
    }

}
