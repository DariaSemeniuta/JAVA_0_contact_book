package project.view.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import project.services.impl.ContactServiceImpl;
import project.view.CmdLineService;

public class CmdLineServiceImpl implements CmdLineService {

    private String[] menuItems = {"1. Create contact", "2. Edit contact", "3. Delete contact", "4. Find contact", "5. Show all contacts", "0. Exit"};
    private  ContactServiceImpl service = new ContactServiceImpl();
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

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

        String response;

        System.out.print("Please enter number of menu item => ");

        try{
            while (!(response=input.readLine()).equals("0")){
                switch (response){
                    case "1" :
                        this.createContact();
                        break;
                    case "2" :
                        service.editContact();
                        break;
                    case "3" :
                        service.deleteContact();
                        break;
                    case "4" :
                        service.findContact();
                        break;
                    case "5" :
                        service.showAllContacts();
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

    @Override
    public void createContact() throws IOException {

        System.out.print("Please enter name => ");
        String name = input.readLine();
        System.out.print("Please enter phone number => ");
        String phone = input.readLine();
        System.out.print("Please enter birthday number => ");
        String birthday = input.readLine();
        this.service.createContact(name,phone,birthday);
        System.out.println("Contact was created");

    }
}
