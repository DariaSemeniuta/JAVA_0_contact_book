package project;

import project.dao.ContactDao;
import project.dao.impl.FileSystemContactDaoImpl;
import project.services.ContactService;
import project.services.impl.ContactServiceImpl;
import project.view.CmdLineService;
import project.view.impl.CmdLineServiceImpl;

public class App {

    public static void main(String[] args) {
        ContactDao contactDao = new FileSystemContactDaoImpl();
        ContactService contactService = new ContactServiceImpl(contactDao);
        CmdLineService cmd = new CmdLineServiceImpl(contactService);

        cmd.showMenu();
        cmd.getResponse();
    }

}
