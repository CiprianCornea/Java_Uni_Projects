package UserInterface;

import Service.DisciplineService;
import Service.NoteService;
import Service.StudentService;

import javax.swing.*;

public class ConsoleGUI {
    StudentService studentService;
    DisciplineService disciplineService;
    NoteService noteService;

    public ConsoleGUI(StudentService studentService,
                      DisciplineService disciplineService,
                      NoteService noteService) {
        this.studentService = studentService;
        this.disciplineService = disciplineService;
        this.noteService = noteService;
    }

    public void runConsole(int option) {
        String[] studentColumnNames = {"Student code", "Name", "First name", "Address", "Email", "Birth date", "Integrally", "Average"};
        String[] disciplineColumnNames = {"Discipline code", "Name", "Number of credits"};
        String[] noteColumnNames = {"Note code", "Value", "Student code", "Discipline code", "Note date"};

        JFrame frame = new JFrame();
        switch (option) {
            case 1 -> {
                JTable table = new JTable(this.studentService.getAllStudentsGUI(), studentColumnNames);
                frame.add(new JScrollPane(table));
            }
            case 2 -> {
                JTable table2 = new JTable(this.disciplineService.getAlDisciplinesGUI(), disciplineColumnNames);
                frame.add(new JScrollPane(table2));
            }
            case 3 -> {
                JTable table3 = new JTable(this.noteService.getAlNotesGUI(), noteColumnNames);
                frame.add(new JScrollPane(table3));
            }
            case 4 -> {
                JTable table4 = new JTable(this.studentService.getStudentsByAverageInDescOrderGUI(), studentColumnNames);
                frame.add(new JScrollPane(table4));
            }
            case 5 -> {
                JTable table5 = new JTable(this.studentService.getIntegrallyStudentsGUI(), studentColumnNames);
                frame.add(new JScrollPane(table5));
            }
            case 6 -> {
                JTable table6 = new JTable(this.studentService.getStudentsByAgeInAscOrderGUI(), studentColumnNames);
                frame.add(new JScrollPane(table6));
            }
        }
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1000, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
