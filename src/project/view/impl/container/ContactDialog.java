package project.view.impl.container;

import project.model.Contact;
import project.utils.ValidatorUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactDialog extends JDialog implements ActionListener{

    private static final int PAD = 10;
    private static final int WIDTH = 300;
    private static final int HIGHT = 25;


    private final JTextPane textName = new JTextPane();
    private final JTextPane textPhone = new JTextPane();
    private final JTextPane textBirthday = new JTextPane();
    private final JTextPane textAge = new JTextPane();

    private MainForm parent;
    private Contact contact;
    public ContactDialog( MainForm parent, String title, Contact contact){
        this.parent = parent;
        this.contact = contact;
        setTitle(title);

        createFields();
        createButtons();
        if(contact != null) {
            feelFields(contact);
        }
        setLayout(null);
        setModal(true);
        setResizable(false);
        setBounds(300, 300, 450, 200);
        setVisible(true);

    }

    private void createFields(){

        int labelWidth = WIDTH/3;
        int textIndent = WIDTH/3+2*PAD;

        JLabel labelName = new JLabel("Name:");
        labelName.setHorizontalAlignment(SwingConstants.RIGHT);
        labelName.setBounds(new Rectangle(PAD, PAD, labelWidth, HIGHT));
        add(labelName);
        textName.setBounds(new Rectangle(textIndent, PAD, WIDTH, HIGHT));
        textName.setBorder(BorderFactory.createEtchedBorder());
        add(textName);

        JLabel labelBirthday = new JLabel("Phone number:");
        labelBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
        labelBirthday.setBounds(new Rectangle(PAD, HIGHT + PAD, labelWidth, HIGHT));
        add(labelBirthday);
        textPhone.setBounds(new Rectangle(textIndent, HIGHT + PAD, WIDTH, HIGHT));
        textPhone.setBorder(BorderFactory.createEtchedBorder());
        add(textBirthday);

        JLabel labelPhone = new JLabel("Birthday:");
        labelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        labelPhone.setBounds(new Rectangle(PAD, HIGHT + 3*PAD+5, labelWidth, HIGHT));
        add(labelPhone);
        textBirthday.setBounds(new Rectangle(textIndent, HIGHT + 3*PAD + 5, WIDTH, HIGHT));
        textBirthday.setBorder(BorderFactory.createEtchedBorder());
        add(textPhone);

        JLabel labelAge = new JLabel("Age:");
        labelAge.setHorizontalAlignment(SwingConstants.RIGHT);
        labelAge.setBounds(new Rectangle(PAD, 2*HIGHT + 3*PAD + 5, labelWidth, HIGHT));
        add(labelAge);
        textAge.setBounds(new Rectangle(textIndent, 2*HIGHT + 3*PAD + 5, WIDTH, HIGHT));
        textAge.setBorder(BorderFactory.createEtchedBorder());
        add(textAge);

    }

    private void feelFields(Contact contact) {
        textName.setText(contact.getName());
        textPhone.setText(contact.getPhoneNumber());
        textBirthday.setText(contact.getBirthday());
        textAge.setText(((Integer) contact.getAge()).toString());
    }
    private void createButtons() {
        int x = WIDTH/3+2*PAD;
        int y = 3*HIGHT + 3*PAD + 20;
        JButton btnSave = new JButton(Actions.SAVE.getAct());
        btnSave.setActionCommand(Actions.SAVE.toString());
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(x, y, WIDTH/3, HIGHT));
        add(btnSave);

        JButton btnCancel = new JButton(Actions.CANCEL.getAct());
        btnCancel.setActionCommand(Actions.CANCEL.toString());
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(x+WIDTH/3+50 , y, WIDTH/3, HIGHT));

        add(btnCancel);
    }
    private boolean validateFields(){
            if (!ValidatorUtils.readPhone(textPhone.getText())) {
                JOptionPane.showMessageDialog(null, "Please enter correct phone number");
                return false;
            }
            if (!ValidatorUtils.readDate(textBirthday.getText())) {
                JOptionPane.showMessageDialog(null, "Please enter correct date in DD.mm.YYYY format");
                return false;
            }
            if (!ValidatorUtils.readInt(textAge.getText())) {
                JOptionPane.showMessageDialog(null, "Please enter correct age(Number format)");
                return false;
            }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch (action) {
            case "SAVE":
                if(this.getTitle() == "Create"){
                    if (validateFields()){
                        parent.service.createContact(textName.getText(), textPhone.getText(), textBirthday.getText(), new Integer(textAge.getText()));
                        JOptionPane.showMessageDialog(null, "Contact was created");
                        setVisible(false);
                    }
                }
                if(this.getTitle() == "Edit"){
                    if (validateFields()){
                        parent.service.editContact(contact.getName(), textName.getText(), textPhone.getText(), textBirthday.getText());
                        JOptionPane.showMessageDialog(null, "Contact was changed");
                        setVisible(false);
                    }
                }

                break;
            case "CANCEL":
                setVisible(false);
                break;
        }
    }



    public Contact getContact() {
        Contact contact = new Contact( textName.getText(),
                textPhone.getText(), textBirthday.getText(), new Integer(textAge.getText()));
        return contact;
    }

}
