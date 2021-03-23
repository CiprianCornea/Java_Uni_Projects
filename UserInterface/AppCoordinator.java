package UserInterface;

import Domain.Student;
import Domain.Discipline;
import Domain.Note;
import Repository.RepoInFile;
import Service.StudentService;
import Service.DisciplineService;
import Service.NoteService;

public class AppCoordinator {
    public static void run(){
        RepoInFile<Student> studentRepo = new RepoInFile<>("students.txt",Student::new);
        RepoInFile<Discipline> disciplineRepo = new RepoInFile<>("disciplines.txt",Discipline::new);
        RepoInFile<Note> noteRepo = new RepoInFile<>("notes.txt",Note::new);
        StudentService studentService = new StudentService(studentRepo);
        DisciplineService disciplineService = new DisciplineService(disciplineRepo);
        NoteService noteService = new NoteService(
                noteRepo,
                studentService,
                disciplineService);
        Console console = new Console(
                studentService,
                disciplineService,
                noteService
        );
        ConsoleGUI consoleGUI = new ConsoleGUI(studentService,
                disciplineService,
                noteService);
        GUI gui = new GUI(consoleGUI);

        //console.runConsole();
        gui.doGUI();
    }
}
