package project.dao.impl;

import project.dao.ContactDao;
import project.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBContactDao implements ContactDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/Prog1";
    private static final String DB_USER = "Test";
    private static final String DB_TABLE = "Contacts";

    public DBContactDao(){
        try{
            Class.forName("org.h2.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("System can't connect to DB");
        }
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            Statement statement = connection.createStatement())
        {
            statement.execute("CREATE TABLE IF NOT EXISTS " + DB_TABLE + "(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), PHONE VARCHAR(50), BIRTHDAY VARCHAR(20), AGE INT);");
        }catch (SQLException e){
            System.out.println("Can't create table!");
            e.printStackTrace();
        }
    }

    @Override
    public void saveContact(Contact contact) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "+ DB_TABLE +"(NAME, PHONE, BIRTHDAY, AGE)VALUES(?,?,?,?);"))
        {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhoneNumber());
            preparedStatement.setString(3, contact.getBirthday());
            preparedStatement.setInt(4, contact.getAge());

            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Can't insert contacts!");
            e.printStackTrace();
        }
    }

    @Override
    public void removeContact(String name) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "+ DB_TABLE + " WHERE NAME = ?;")){

            preparedStatement.setString(1, name);
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println("Can't remove contacts!");
            e.printStackTrace();
        }

    }

    @Override
    public List<Contact> showAll() {
        List<Contact> allContacts = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            Statement statement = connection.createStatement()){

            ResultSet result = statement.executeQuery("SELECT * from "+ DB_TABLE +";");
            while (result.next()){
                Contact contact = new Contact(result.getString("NAME"), result.getString("PHONE"), result.getString("BIRTHDAY"), result.getInt("AGE"));
                allContacts.add(contact);
                System.out.println(contact.toString());
            }
            result.close();


        }catch (SQLException e){
            System.out.println("Can't connect to DB");
            e.printStackTrace();
        }
        return allContacts;
    }

    @Override
    public List<Contact> findContact(String keyWord) {
        List<Contact> contacts = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM "+ DB_TABLE +" WHERE NAME LIKE ? OR PHONE LIKE ?;")){

            preparedStatement.setString(1, "%"+keyWord+"%");
            preparedStatement.setString(2,"%"+keyWord+"%");
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                contacts.add(new Contact(result.getString("NAME"), result.getString("PHONE"), result.getString("BIRTHDAY"), result.getInt("AGE")));
            }
            result.close();
        }catch (SQLException e){
            System.out.println("Can't connect to DB");
            e.printStackTrace();
        }

        return contacts;
    }

    @Override
    public void editContact(String name, Contact newContact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DB_TABLE + " SET NAME = ?, PHONE = ?, BIRTHDAY = ? WHERE NAME = ?;")) {

            preparedStatement.setString(1, newContact.getName());
            preparedStatement.setString(2, newContact.getPhoneNumber());
            preparedStatement.setString(3, newContact.getBirthday());
            preparedStatement.setString(4, name);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Can't connect to DB");
            e.printStackTrace();
        }
    }
}
