package project.view.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import project.model.Contact;
import project.services.impl.ContactServiceImpl;
import project.view.CmdLineService;
import project.view.impl.ValidatorService;

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
                        this.edit();
                        break;
                    case "3" :
                        this.delete();
                        break;
                    case "4" :
                        this.find();
                        break;
                    case "5" :
                        this.showAll();
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
        String phone = ValidatorService.readPhone();
        System.out.print("Please enter birthday => ");
        String birthday = ValidatorService.readDate();
        System.out.print("Please enter age => ");
        int age = ValidatorService.readInt();
        this.service.createContact(name, phone, birthday, age);
        System.out.println("Contact was created");
        this.showMenu();
    }

    @Override
    public void showAll() {
        this.service.showAllContacts();
        this.showMenu();
    }

    @Override
    public void find() throws IOException {
        System.out.print("Please enter info(name or phone number) for search=>");
        String keyWord = input.readLine();
        List<Contact> searchResult = this.service.findContact(keyWord);
        if (searchResult.size() > 0){
            System.out.println("The following contacts were found:");
            for (Contact result:searchResult){
                System.out.println(result.toString());
            }
        }
        else{
            System.out.println("No contacts were found");
            this.showMenu();
        }

    }

    @Override
    public void edit() throws IOException {
        System.out.print("Please enter contact name, which you want to edit =>");
        String name = input.readLine();
        if (this.service.findContact(name).size() == 0){
            System.out.println("There is no "+name+" in contact book");
        } else {

            System.out.println("Please enter new info:");
            System.out.println("If you don't want to change value of field, please just press Enter");
            System.out.print("Name => ");
            String newName = input.readLine();
            System.out.print("Phone number => ");
            String newPhone = ValidatorService.readPhone();
            System.out.print("Birthday => ");
            String newBirthday = input.readLine();

            this.service.editContact(name, newName,newPhone,newBirthday);
        }
        this.showMenu();
    }

    @Override
    public void delete() throws IOException{
        System.out.print("Please enter contact name, which you want to delete =>");
        String name = input.readLine();
        List<Contact> searchResult = this.service.findContact(name);
        if (searchResult.size() > 0){
            System.out.println("The following contacts will be deleted:");
            for (Contact result:searchResult){
                System.out.println(result.toString());
                this.service.deleteContact(result);
            }
        }
        else{
            System.out.println("No contacts were found");
            this.showMenu();
        }

        }
}
