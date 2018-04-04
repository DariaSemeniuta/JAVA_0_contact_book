package project.view;

import java.io.IOException;

public interface CmdLineService {
    /**
     *
     */
    void showMenu();
    void getResponse();
    void createContact() throws IOException;
    void showAll();
    void delete()throws IOException;
    void find() throws IOException;
    void edit()throws IOException;


}
