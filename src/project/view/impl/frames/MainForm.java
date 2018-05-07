package project.view.impl.frames;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    public JTable contactTable = new JTable();

    public MainForm (){
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JFrame mainFrame = new JFrame();

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel buttonPannel = new JPanel();
        buttonPannel.setLayout(gridbag);

        buttonPannel.add(createButton(gridbag, gbc, Actions.REFRESH.getAct(),Actions.REFRESH.toString()));
        buttonPannel.add(createButton(gridbag, gbc, Actions.CREATE.getAct(),Actions.CREATE.toString()));
        buttonPannel.add(createButton(gridbag, gbc, Actions.EDIT.getAct(),Actions.EDIT.toString()));
        buttonPannel.add(createButton(gridbag, gbc, Actions.DELETE.getAct(),Actions.DELETE.toString()));
        buttonPannel.add(createButton(gridbag, gbc, Actions.FIND.getAct(),Actions.FIND.toString()));
        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());
        right.add(buttonPannel, BorderLayout.NORTH);
        add(right, BorderLayout.EAST);

        add(new JScrollPane(contactTable), BorderLayout.CENTER);

        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        JButton button = new JButton(title);
        button.setActionCommand(action);
        gridbag.setConstraints(button, gbc);

        return button;
    }

}
