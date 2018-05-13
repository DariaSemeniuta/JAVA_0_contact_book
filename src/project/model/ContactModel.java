package project.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContactModel extends AbstractTableModel{
    private static final String[] headers = {"Name", "Phone number", "Birthday", "Age"};
    private final List<Contact> contacts;

    public ContactModel(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Contact contact = contacts.get(row);
        switch (col) {
            case 0:
                return contact.getName();
            case 1:
                return contact.getPhoneNumber();
            case 2:
                return contact.getBirthday();
            case 3:
                return contact.getAge();
            default:
                return contact.getPhoneNumber();
        }
    }
}
