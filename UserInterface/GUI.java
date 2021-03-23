package UserInterface;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private final ConsoleGUI consoleGUI;

    public GUI(ConsoleGUI consoleGUI) {
        this.consoleGUI = consoleGUI;
    }

    public void doGUI() {
        JFrame frame = new JFrame("Academic Info");

        ImageIcon image1 = new ImageIcon("C:\\Users\\cipri\\IdeaProjects\\laborator04\\src\\UserInterface\\01.png");
        ImageIcon image2 = new ImageIcon("C:\\Users\\cipri\\IdeaProjects\\laborator04\\src\\UserInterface\\02.png");
        ImageIcon image3 = new ImageIcon("C:\\Users\\cipri\\IdeaProjects\\laborator04\\src\\UserInterface\\03.png");
        ImageIcon image4 = new ImageIcon("C:\\Users\\cipri\\IdeaProjects\\laborator04\\src\\UserInterface\\04.png");

        JButton button1 = new JButton("Show Students");
        button1.addActionListener(e -> consoleGUI.runConsole(1));

        JButton button2 = new JButton("Show Disciplines");
        button2.addActionListener(e -> consoleGUI.runConsole(2));

        JButton button3 = new JButton("Show Notes");
        button3.addActionListener(e -> consoleGUI.runConsole(3));

        JButton button4 = new JButton("Show Students Desc By Average");
        button4.addActionListener(e -> consoleGUI.runConsole(4));

        JButton button5 = new JButton("Show Integrally Students Desc");
        button5.addActionListener(e -> consoleGUI.runConsole(5));

        JButton button6 = new JButton("Show Students Asc By Age");
        button6.addActionListener(e -> consoleGUI.runConsole(6));

        button1.setAlignmentX(Component.LEFT_ALIGNMENT);
        button2.setAlignmentX(Component.LEFT_ALIGNMENT);
        button3.setAlignmentX(Component.LEFT_ALIGNMENT);
        button4.setAlignmentX(Component.LEFT_ALIGNMENT);
        button5.setAlignmentX(Component.LEFT_ALIGNMENT);
        button6.setAlignmentX(Component.LEFT_ALIGNMENT);

        doButtons(button1, image1);
        doButtons(button2, image1);
        doButtons(button3, image1);
        doButtons(button4, image1);
        doButtons(button5, image1);
        doButtons(button6, image1);

        Box box = Box.createVerticalBox();
        box.add(Box.createRigidArea(new Dimension(5, 5)));
        box.add(button1);
        box.add(Box.createRigidArea(new Dimension(5, 5)));
        box.add(button2);
        box.add(Box.createRigidArea(new Dimension(5, 5)));
        box.add(button3);
        box.add(Box.createRigidArea(new Dimension(5, 5)));
        box.add(button4);
        box.add(Box.createRigidArea(new Dimension(5, 5)));
        box.add(button5);
        box.add(Box.createRigidArea(new Dimension(5, 5)));
        box.add(button6);

        Box b2 = Box.createHorizontalBox();
        b2.add(Box.createRigidArea(new Dimension(5, 5)));
        b2.add(new JLabel(image2));
        b2.add(Box.createRigidArea(new Dimension(5, 5)));
        b2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        b2.add(box);
        b2.setBorder(BorderFactory.createMatteBorder(
                45, 0, 35, 0, image3));
        //b2.setAlignmentX(50);

        JLabel label = new JLabel("Academic Info");
        label.setFont(new Font("Column", Font.BOLD, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box b4 = Box.createHorizontalBox();
        b4.add(new JLabel(image4));
        b4.add(label);
        b4.add(new JLabel(image2));
        b4.setAlignmentX(-200);

        Box b3 = Box.createVerticalBox();
        b3.add(b4, BorderLayout.NORTH);
        b3.add(b2, BorderLayout.SOUTH);
        frame.add(b3);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
//        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private static void doButtons(JButton button, ImageIcon icons) {
        button.setIcon(icons);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
}
