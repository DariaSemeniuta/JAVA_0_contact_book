package project.dao.impl;

import project.dao.ContactDao;
import project.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemContactDaoImpl implements ContactDao {

    private static final File FILE = new File("data");

    @Override
    public void showAll() {
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ( (line = reader.readLine()) != null ){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Error appears during writing to file");
        }
    }

    @Override
    public void saveContact(Contact contact){
        String existData = readFile();

        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE)))){
            writer.print(existData);
            writer.println(contact);
        }catch (IOException e){
            System.out.println("Error appears during writing to file");
        }
    }

    @Override
    public void removeContact(String name) {
        String newData = "";

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ( (line = reader.readLine()) != null ){
                if(! (line.contains(name))){
                    newData = newData + (line + "\n");
                }
            }
        }catch (IOException e){
            System.out.println("Error appears during reading of file");
        }
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE)))){
            writer.print(newData);
        }catch (IOException e){
            System.out.println("Error appears during writing to file");
        }
    }

    @Override
    public List<Contact> findContact(String keyWord) {
        List<Contact> contacts = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains(keyWord)){
                    contacts.add(parseFile(line));
                }
            }
        }catch (IOException e){
            System.out.println("Error during reading of file");
        }
        return contacts;
    }

    @Override
    public void editContact(String name, Contact newContact) {
        String newData = "";

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ( (line = reader.readLine()) != null ){
                if(line.contains(name)){
                    line = newContact.toString();
                }
                newData = newData + (line + "\n");
            }
        }catch (IOException e){
            System.out.println("Error appears during reading of file");
        }
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE)))){
            writer.print(newData);
        }catch (IOException e){
            System.out.println("Error appears during writing to file");
        }
    }

    private Contact parseFile(String line){
        String[] data = new String[4];

        for(int i=0; i<data.length; i++){
            int startIndx = line.indexOf("=");
            int stopIndx = line.indexOf(",");
            if (stopIndx == -1) {
                stopIndx = line.indexOf("}");
            }
            data[i] = line.substring(startIndx+1, stopIndx);
            int indx = stopIndx+1;
            line = line.substring(indx);
        }

        return new Contact(data[0],data[1], data[2], new Integer(data[3]));
    }

    private String readFile() {
        String existData ="";
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ( (line = reader.readLine()) != null ){
                existData = existData + (line + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return existData;
    }

}
