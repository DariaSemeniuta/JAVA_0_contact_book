package project.view.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import project.services.impl.ContactServiceImpl;
import project.view.CmdLineService;

public class CmdLineServiceImpl implements CmdLineService {

    private String[] menuItems = {"1. Create contact", "2. Edit contact", "3. Delete contact", "4. Find contact", "5. Show all contacts", "0. Exit"};

    @Override
    public void showMenu(){
        System.out.println(" Menu:");
        for(int i=0; i< menuItems.length; ++i){
            System.out.println("|----------------------|");
            System.out.println(" "+menuItems[i]);
        }
        System.out.println("|______________________|");
    }

    @Override
    public void getResponse() {
        ContactServiceImpl service = new ContactServiceImpl();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String response;

        System.out.print("Please enter number of menu item => ");

        try{
            while (!(response=reader.readLine()).equals("0")){
                switch (response){
                    case "1" : service.createContact();
                    break;
                    case "2" : service.editContact();
                    break;
                    case "3" : service.deleteContact();
                    break;
                    case "4" : service.findContact();
                    break;
                    case "5" : service.showAllContacts();
                    break;
                    default:
                        System.out.println("Please enter correct number");
                        break;
                }
                System.out.print("Please enter number of menu item => ");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.exit(0);

    }
}
