package project.view.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import project.model.Contact;
import project.services.ContactService;
import project.view.CmdLineService;
import project.utils.ValidatorUtils;

public class CmdLineServiceImpl implements CmdLineService {

    private String[] menuItems = {"1. Create contact", "2. Edit contact", "3. Delete contact", "4. Find contact", "5. Show all contacts", "0. Exit"};
    private ContactService service ;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private ContactFrame gui = new ContactFrame();

    public CmdLineServiceImpl(ContactService service) {
        this.service = service;
    }

    @Override
    public void showMenu(){

        System.out.println(" Menu:");

        for (String item: menuItems) {
            System.out.println("|----------------------|");
            System.out.println(" "+item);
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
                        createContact();
                        break;
                    case "2" :
                        edit();
                        break;
                    case "3" :
                        delete();
                        break;
                    case "4" :
                        find();
                        break;
                    case "5" :
                        showAll();
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
        String phone;
        while (! ValidatorUtils.readPhone(phone = input.readLine())){
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct phone number => ");
        }

        System.out.print("Please enter birthday => ");
        String birthday;
        while (! ValidatorUtils.readDate(birthday = input.readLine())){
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct date of birthday => ");
        }
        System.out.print("Please enter age => ");
        String inputAge;
        while (! ValidatorUtils.readInt(inputAge = input.readLine())){
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct (Integer)age => ");
        }
        int age = new Integer(inputAge);
        service.createContact(name, phone, birthday, age);
        System.out.println("Contact was created");
        showMenu();
    }

    @Override
    public void showAll() {
        service.showAllContacts();
        showMenu();
    }

    @Override
    public void find() throws IOException {
        System.out.print("Please enter info(name or phone number) for search=>");
        String keyWord = input.readLine();
        List<Contact> searchResult = service.findContact(keyWord);
        if (searchResult.size() > 0){
            System.out.println("The following contacts were found:");
            for (Contact result:searchResult){
                System.out.println(result.toString());
            }
        }
        else{
            System.out.println("No contacts were found");
        }
        showMenu();
    }

    @Override
    public void edit() throws IOException {
        System.out.print("Please enter contact name, which you want to edit =>");
        String name = input.readLine();
        if (service.findContact(name).size() == 0){
            System.out.println("There is no "+name+" in contact book");
        } else {

            System.out.println("Please enter new info:");
            System.out.println("If you don't want to change value of field, please just press Enter");
            System.out.print("Name => ");
            String newName = input.readLine();
            System.out.print("Phone number => ");
            String newPhone;
            while (! ValidatorUtils.readPhone(newPhone = input.readLine())){
                if(newPhone.isEmpty()){
                    break;
                }
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct phone number => ");
            }
            System.out.print("Birthday => ");
            String newBirthday;
            while (! ValidatorUtils.readDate(newBirthday = input.readLine())){
                if(newBirthday.isEmpty()){
                    break;
                }
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct date of birthday => ");
            }

            service.editContact(name, newName,newPhone,newBirthday);
        }
        showMenu();
    }

    @Override
    public void delete() throws IOException{
        System.out.print("Please enter contact name, which you want to delete =>");
        String name = input.readLine();
        List<Contact> searchResult = service.findContact(name);
        if (searchResult.size() > 0){
            System.out.println("The following contacts will be deleted:");
            for (Contact result:searchResult){
                System.out.println(result.toString());
                service.deleteContact(result);
            }
        }
        else{
            System.out.println("No contacts were found");
        }
        showMenu();
    }
}
