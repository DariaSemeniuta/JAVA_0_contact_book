package project.view.impl;

import javax.swing.*;
import java.awt.*;

public class ContactFrame extends JFrame{
    private final JTable contactTable = new JTable();

    private static final String LOAD = "LOAD";
    private static final String CREATE = "CREATE";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    public ContactFrame (){
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JFrame mainFrame = new JFrame();
        // Используем layout manager
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel buttonPannel = new JPanel();
        buttonPannel.setLayout(gridbag);

        buttonPannel.add(createButton(gridbag, gbc, "Show all", LOAD));
        buttonPannel.add(createButton(gridbag, gbc, "Create", CREATE));
        buttonPannel.add(createButton(gridbag, gbc, "Edit", EDIT));
        buttonPannel.add(createButton(gridbag, gbc, "Delete", DELETE));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(buttonPannel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(contactTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //loadContact();





    }
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп роверяться в обработчике и мы будем знать, какую
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
       // button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

}
