package project;

import project.view.CmdLineService;
import project.view.impl.CmdLineServiceImpl;

public class App {

    public static void main(String[] args) {
        CmdLineService cmd = new CmdLineServiceImpl();
        cmd.showMenu();
        cmd.getResponse();
    }

}
