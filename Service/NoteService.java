package Service;

import Domain.Discipline;
import Domain.Note;
import Domain.Student;
import Exceptions.InvalidDisciplineException;
import Exceptions.InvalidStudentException;
import Repository.RepoInFile;

import java.time.LocalDate;
import java.util.*;


public class NoteService {
    private final RepoInFile<Note> repo;
    private final StudentService studentService;
    private final DisciplineService disciplineService;

    public NoteService(RepoInFile<Note> repo, StudentService studentService, DisciplineService disciplineService) {
        this.repo = repo;
        this.studentService = studentService;
        this.disciplineService = disciplineService;

        for (Note note : this.repo.getAll()) {
            Student student = this.studentService.getById(note.getStudentCode());
            student.setAverage(note.getValue());
            if(note.getValue() < 5){
                student.setIntegrally(false);
            }
            this.studentService.save();
        }
    }

    public void add(int value, String studentCode, String disciplineCode, LocalDate noteDate) {
        try {
            Student student = this.studentService.getById(studentCode);
        } catch (InvalidStudentException exc) {
            throw new InvalidStudentException(String.format("This student does not exist...%3s", studentCode));
        }
        try {
            Discipline discipline = this.disciplineService.getById(disciplineCode);
        } catch (InvalidDisciplineException exc) {
            throw new InvalidDisciplineException(String.format("This discipline does not exist...%3s", disciplineCode));
        }
        if (value < 5) {
            Student studentToReplace = this.studentService.getById(studentCode);
            this.studentService.update(studentToReplace.getUniqueCod(),
                    studentToReplace.getName(),
                    studentToReplace.getFirstName(),
                    studentToReplace.getAddress(),
                    studentToReplace.getEmail(),
                    studentToReplace.getBirthDate(),
                    false);
        }
        Student student = this.studentService.getById(studentCode);
        student.setAverage(value);
        this.studentService.save();
        Note note = new Note(value, studentCode, disciplineCode, noteDate);
        this.repo.add(note);
    }

    public void add(String note) {
        String[] splitNote = note.split(",");
        int value = Integer.parseInt(splitNote[0]);
        LocalDate noteDate = LocalDate.parse(splitNote[3]);
        this.add(value, splitNote[1], splitNote[2], noteDate);
    }

    public void delete(String noteCode) {
        Note note = this.repo.getById(noteCode);
        this.repo.delete(note);
    }

    public void update(String noteCode, int newValue, LocalDate newNoteDate) {
        Note note = this.repo.getById(noteCode);
        this.delete(noteCode);
        this.add(newValue, note.getStudentCode(), note.getDisciplineCode(), newNoteDate);
    }

    public Collection<Note> getAll() {
        return this.repo.getAll();
    }

    public Object[][] getAlNotesGUI() {
        Vector<Note> notes = new Vector<>(this.getAll());
        Object[][] obj = new Object[notes.size()][5];
        int size = notes.size();
        for (int i = 0; i < size; i++) {
            Note note= notes.elementAt(i);
            obj[i][0] = note.getDisciplineCode();
            obj[i][1] = note.getValue();
            obj[i][2] = note.getStudentCode();
            obj[i][3] = note.getDisciplineCode();
            obj[i][4] = note.getNoteDate();
        }
        return  obj;
    }

    public String getNoteToShow(Note note) {
        LocalDate noteDate = note.getNoteDate();
        String str = "" + noteDate.getYear() + "-" + noteDate.getMonthValue() + "-" + noteDate.getDayOfMonth();
        return String.format("|%-35s|%6s|%-25s|%-20s|%-16s|", note.getNoteCode(),
                note.getValue(),
                note.getStudentCode(),
                note.getDisciplineCode(),
                str);
    }

    public int getAppearanceOfAStudent(String studentCode) {
        int k = 0;
        for (Note note : this.repo.getAll()) {
            String code = note.getStudentCode();
            if (studentCode.equals(code))
                k++;
        }
        return k;
    }

    private Vector<String> getFailedExams(String studentCode) {
        Vector<String> disciplines = new Vector<>();
        for (Note n : this.repo.getAll()) {
            if (n.getValue() < 5 && n.getStudentCode().equals(studentCode)) {
                disciplines.add(n.getDisciplineCode());
            }
        }
        return disciplines;
    }

    public Map<String, Vector<String>> getFailedStudents() {
        Hashtable<String, Vector<String>> students = new Hashtable<>();
        for (Student s : this.studentService.getAll()) {
            if (!s.getIntegrally()) {
                students.put(s.getUniqueCod(), this.getFailedExams(s.getUniqueCod()));
            }
        }
        return students;
    }

    private Vector<String> getFirstThree(String disciplineCode) {
        String[] students = {"", "", ""};
        Integer[] sN = {0, 0, 0};
        for (Note note : this.repo.getAll()) {
            if (disciplineCode.equals(note.getDisciplineCode())) {
                int v = note.getValue();
                if (v > sN[0]) {
                    sN[2] = sN[1];
                    sN[1] = sN[0];
                    sN[0] = v;
                    students[2] = students[1];
                    students[1] = students[0];
                    students[0] = note.getStudentCode();
                }
                else if (v > sN[1]) {
                    sN[2] = sN[1];
                    sN[1] = v;
                    students[2] = students[1];
                    students[1] = note.getStudentCode();
                }
                else if (v > sN[2]) {
                    sN[2] = v;
                    students[2] = note.getStudentCode();
                }
            }
        }
        return new Vector<>(Arrays.asList(students));
    }

    private boolean checkDisciplines(String disciplineCode) {
        for (Note note : this.repo.getAll()) {
            if (disciplineCode.equals(note.getDisciplineCode()))
                return true;
        }
        return false;
    }

    public Hashtable<String, Vector<String>> getToppers() {
        Hashtable<String, Vector<String>> disciplines = new Hashtable<>();
        for (Discipline discipline : this.disciplineService.getAll()) {
            if (this.checkDisciplines(discipline.getDisciplineCode())) {
                String code = discipline.getDisciplineCode();
                disciplines.put(code, this.getFirstThree(code));
            }
        }
        return disciplines;
    }
}
