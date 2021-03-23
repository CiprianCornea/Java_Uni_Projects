package UserInterface;

import Domain.Student;
import Domain.Discipline;
import Domain.Note;
import Exceptions.InvalidDataException;
import Exceptions.InvalidStudentException;
import Exceptions.InvalidDisciplineException;
import Exceptions.InvalidNoteException;
import Service.StudentService;
import Service.DisciplineService;
import Service.NoteService;
import Utilities.Read;
import Utilities.Tables;

import java.nio.file.InvalidPathException;
import java.util.Hashtable;
import java.util.Vector;

public class Console {
    StudentService studentService;
    DisciplineService disciplineService;
    NoteService noteService;

    public Console(StudentService studentService,
                   DisciplineService disciplineService,
                   NoteService noteService) {
        this.studentService = studentService;
        this.disciplineService = disciplineService;
        this.noteService = noteService;
    }

    private void mainMenu() {
        System.out.println("\n----------");
        System.out.println("|MAIN MENU|");
        System.out.println("----------\n");
        System.out.println(" (1) |Add");
        System.out.println(" (2) |Show all");
        System.out.println(" (3) |Show a table with students and the general average, descending after the general average");
        System.out.println(" (4) |Show a table with non-promoted students and subjects they did not pass");
        System.out.println(" (5) |Show a table with the first 3 (three) students in descending order of averages in each subject");
        System.out.println(" (6) |Show a table for awarding scholarships, a list of integrally students, descending according to the general average");
        System.out.println(" (7) |Show a list of students in order of age");
        System.out.println(" (8) |EXIT|\n");
    }

    private void addMenu() {
        System.out.println("\n---------");
        System.out.println("|ADD MENU|");
        System.out.println("---------\n");
        System.out.println(" (1) |Add student");
        System.out.println(" (2) |Add discipline");
        System.out.println(" (3) |Add note");
        System.out.println(" (4) |EXIT|\n");
    }

    private void showAllMenu() {
        System.out.println("\n--------------");
        System.out.println("|SHOW ALL MENU|");
        System.out.println("--------------\n");
        System.out.println(" (1) |Show all students");
        System.out.println(" (2) |Show all disciplines");
        System.out.println(" (3) |Show all notes");
        System.out.println(" (4) |EXIT|\n");
    }

    private void addStudent() {
        try {
            System.out.println("\nUSE THE IN LINE FORMAT FOR A STUDENT: ");
            System.out.println("studentCode,name,firstName,address,email,birthDate\n");
            System.out.print("Your input: ");
            String student = Read.readString();
            student += ",true,0.0";
            this.studentService.add(student);
            System.out.println("Student added!");
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private void addDiscipline() {
        try {
            System.out.println("\nUSE THE IN LINE FORMAT FOR A DISCIPLINE: ");
            System.out.println("disciplineCode,name,numberOfCredits\n");
            System.out.print("Your input: ");
            String discipline = Read.readString();
            this.disciplineService.add(discipline);
            System.out.println("Discipline added!");
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private void addNote() {
        try {
            System.out.println("\nUSE THE IN LINE FORMAT FOR A NOTE: ");
            System.out.println("value,studentCode,disciplineCode,noteDate\n");
            System.out.print("Your input: ");
            String note = Read.readString();
            this.noteService.add(note);
            System.out.println("Note added!");
        } catch (RuntimeException exc1) {
            System.out.println(exc1.getMessage());
        }
    }

    private void showAllStudents() {
        String str = Tables.tableForStudents();
        for (Student student : this.studentService.getAll()) {
            System.out.println(this.studentService.getStudentToShow(student));
        }
        System.out.println(str);
    }

    private void showAllDisciplines() {
        String str = Tables.tableForDisciplines();
        for (Discipline discipline : this.disciplineService.getAll()) {
            System.out.println(this.disciplineService.getDisciplineToShow(discipline));
        }
        System.out.println(str);
    }

    private void showAllNote() {
        String str = Tables.tableForNote();
        for (Note note : this.noteService.getAll()) {
            System.out.println(this.noteService.getNoteToShow(note));
        }
        System.out.println(str);
    }

    private void showStudentsDescByAverage() {
        String str = Tables.tableForStudents();
        for(String studentToShow:this.studentService.getStudentsByAverageInDescOrder()){
            System.out.println(studentToShow);
        }
        System.out.println(str);
    }

    private void showFailedStudentsAndDisciplines(){
        Hashtable<String,Vector<String>> result = new Hashtable<>(this.noteService.getFailedStudents());
        for(String key: result.keySet()){
            Student student = this.studentService.getById(key);
            String str = "";
            str += student.getName() + " " + student.getFirstName();
            System.out.println();
            System.out.println(str);
            System.out.println();
            String s = Tables.tableForDisciplines();
            for(String discipline:result.get(key)){
                System.out.println(this.disciplineService.getDisciplineToShow(this.disciplineService.getById(discipline)));
            }
            System.out.println(s);
        }
    }

    private void showFirstThreeByDisciplines(){
        Hashtable<String,Vector<String>> result = new Hashtable<>(this.noteService.getToppers());
        for(String key: result.keySet()){
            Discipline discipline= this.disciplineService.getById(key);
            String str = "";
            str += discipline.getName();
            System.out.println();
            System.out.println(str);
            System.out.println();
            String s = Tables.tableForStudents();
            for(String student:result.get(key)){
                if(!student.equals("")) {
                    System.out.println(this.studentService.getStudentToShow(this.studentService.getById(student)));
                }
            }
            System.out.println(s);
        }
    }

    private void showIntegrallyDesc(){
        String str = Tables.tableForStudents();
        for(String studentToShow:this.studentService.getIntegrallyStudents()){
            System.out.println(studentToShow);
        }
        System.out.println(str);
    }

    private void showStudentsByAge(){
        String str = Tables.tableForStudents();
        for(String studentToShow:this.studentService.getStudentsByAgeInAscOrder()){
            System.out.println(studentToShow);
        }
        System.out.println(str);
    }

    private void runAddConsole() {
        int option;
        boolean flag = true;
        while (flag) {
            this.addMenu();
            option = (int) Read.readNumber("Choose an option: ");
            if (option == 1) this.addStudent();
            else if (option == 2) this.addDiscipline();
            else if (option == 3) this.addNote();
            else flag = false;
        }
    }

    private void runShowAllConsole() {
        int option;
        boolean flag = true;
        while (flag) {
            this.showAllMenu();
            option = (int) Read.readNumber("Choose an option: ");
            if (option == 1) this.showAllStudents();
            else if (option == 2) this.showAllDisciplines();
            else if (option == 3) this.showAllNote();
            else flag = false;
        }
    }


    public void runConsole() {
        int option;
        boolean flag = true;
        while (flag) {
            this.mainMenu();
            option = (int) Read.readNumber("Choose an option: ");
            if (option == 1) this.runAddConsole();
            else if (option == 2) this.runShowAllConsole();
            else if (option == 3) this.showStudentsDescByAverage();
            else if (option == 4) this.showFailedStudentsAndDisciplines();
            else if (option == 5) this.showFirstThreeByDisciplines();
            else if (option == 6) this.showIntegrallyDesc();
            else if (option == 7) this.showStudentsByAge();
            else flag = false;
        }
    }
}
