package Utilities;

public class Tables {
    public static String tableForStudents() {
        String head = "|Student code   |Name      |First name     |Address             |Email                         |Birth date     |Integrally |Average   |";
        String lines = "_______________________________________________________________________________________________________________________________________";
        System.out.println(lines);
        System.out.println(head);
        System.out.println(lines);
        return  lines;
    }

    public static String tableForDisciplines() {
        String head = "|Code discipline     |Name                |Number of credits|";
        String lines = "_____________________________________________________________";
        System.out.println(lines);
        System.out.println(head);
        System.out.println(lines);
        return  lines;
    }

    public static String tableForNote() {
        String head = "|Note code                          |Value |Student code             |Discipline code     |Note date       |";
        String lines = "------------------------------------------------------------------------------------------------------------";
        System.out.println(lines);
        System.out.println(head);
        System.out.println(lines);
        return  lines;
    }
}
