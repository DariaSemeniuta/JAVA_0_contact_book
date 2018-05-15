package project.view.impl.container;

import project.model.Contact;
import project.model.ContactModel;
import project.services.ContactService;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame implements ActionListener {
    private JTable contactTable = new JTable();
    private  JTextPane findText = new JTextPane();
    ContactService service ;

    public MainForm (ContactService contactService){
        this.service = contactService;

        setTitle("Contact Book");

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel buttonPannel = new JPanel();
        buttonPannel.setLayout(gridbag);

        buttonPannel.add(createButton(gridbag, gbc, Actions.FIND.getAct(),Actions.FIND.toString()));
        buttonPannel.add(createButton(gridbag, gbc, Actions.CREATE.getAct(),Actions.CREATE.toString()));
        buttonPannel.add(createButton(gridbag, gbc, Actions.EDIT.getAct(),Actions.EDIT.toString()));
        buttonPannel.add(createButton(gridbag, gbc, Actions.DELETE.getAct(),Actions.DELETE.toString()));
        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());
        right.add(buttonPannel, BorderLayout.NORTH);
        add(right, BorderLayout.EAST);

        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        add(new JScrollPane(contactTable), BorderLayout.CENTER);
        loadContacts();

        JLabel label = new JLabel("Find");

        label.setFont(new Font("Verdana", Font.BOLD, 15));
        JPanel findPanel = new JPanel();
        findPanel.setLayout(new BorderLayout());
        findPanel.add(label, BorderLayout.LINE_START);
        findPanel.add(findText, BorderLayout.AFTER_LAST_LINE);
        findPanel.setBackground(Color.lightGray);
        add(findPanel, BorderLayout.NORTH);

        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }
    private void loadContacts(){
        List<Contact> allContacts = service.showAllContacts();
        ContactModel contactModel = new ContactModel(allContacts);
        contactTable.setModel(contactModel);
    }
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        JButton button = new JButton(title);
        button.setActionCommand(action);
        button.addActionListener(this);
        gridbag.setConstraints(button, gbc);

        return button;
    }

    Contact getContact(){

        int row = contactTable.getSelectedRow();
        if(row!=-1) {
            String name = contactTable.getModel().getValueAt(row, 0).toString();
            String phone = contactTable.getModel().getValueAt(row, 1).toString();
            String birthday = contactTable.getModel().getValueAt(row, 2).toString();
            int age = new Integer(contactTable.getModel().getValueAt(row, 3).toString());

            return new Contact(name, phone, birthday, age);
        }
        else {
            return null;
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        Contact contact  = getContact();

        switch (action) {
            case "CREATE":
                new ContactDialog(this, Actions.CREATE.getAct(), null);
                loadContacts();
                break;
            case "EDIT":
                new ContactDialog(this, Actions.EDIT.getAct(), contact);
                loadContacts();
                break;
            case "DELETE":
                service.deleteContact(contact);
                JOptionPane.showMessageDialog(null,"Contact was deleted");
                loadContacts();
                break;
            case "FIND":
                List<Contact> result = service.findContact(findText.getText());
                ContactModel contactModel = new ContactModel(result);
                contactTable.setModel(contactModel);
                break;
        }

    }

}
