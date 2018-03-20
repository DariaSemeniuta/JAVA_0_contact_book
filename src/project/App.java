package project;

import project.view.ComandLineService;
import project.view.impl.ComandLineServiceImpl;

public class App {

    public static void main(String[] args) {
        // start view
        ComandLineService cmd = new ComandLineServiceImpl();
        cmd.showMenu();


    }

}
