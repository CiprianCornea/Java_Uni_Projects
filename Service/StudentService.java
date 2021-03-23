package Service;

import Domain.Student;
import Repository.RepoInFile;
import Utilities.Sort;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Vector;

public class StudentService {
    private final RepoInFile<Student> repo;

    public StudentService(RepoInFile<Student> repo) {
        this.repo = repo;
    }

    public void add(String uniqueCode, String name, String firstName, String address, String email, LocalDate birthDate) {
        Student student = new Student(uniqueCode, name, firstName, address, email, birthDate);
        this.repo.add(student);
    }

    public void add(String student) {
        Student s = new Student(student);
        this.repo.add(s);
    }

    public void delete(String uniqueCode) {
        Student student = this.repo.getById(uniqueCode);
        this.repo.delete(student);
    }

    public void update(String uniqueCode, String newName, String newFirstName, String newAddress, String newEmail, LocalDate newBirthDate, boolean integrally) {
        Student toUpdate = this.repo.getById(uniqueCode);
        Student toReplace = new Student(uniqueCode, newName, newFirstName, newAddress, newEmail, newBirthDate);
        toReplace.setIntegrally(integrally);
        this.repo.update(toUpdate, toReplace);
    }

    public Collection<Student> getAll() {
        return this.repo.getAll();
    }

    public Object[][] getAllStudentsGUI() {
        Vector<Student> students = new Vector<>(this.getAll());
        Object[][] obj = new Object[students.size()][8];
        int size = students.size();
        for (int i = 0; i < size; i++) {
            Student student = students.elementAt(i);
            obj[i][0] = student.getUniqueCod();
            obj[i][1] = student.getName();
            obj[i][2] = student.getFirstName();
            obj[i][3] = student.getAddress();
            obj[i][4] = student.getEmail();
            obj[i][5] = student.getBirthDate();
            obj[i][6] = student.getIntegrally();
            obj[i][7] = student.getAverage();
        }
        return  obj;
    }

    public Student getById(String uniqueCode) {
        return this.repo.getById(uniqueCode);
    }

    public String getStudentToShow(Student student) {
        LocalDate birthDate = student.getBirthDate();
        int month = birthDate.getMonthValue();
        String str = "" + birthDate.getYear() + "-" + month + "-" + birthDate.getDayOfMonth();
        return String.format("|%-15s|%-10s|%-15s|%-20s|%-30s|%-15s|%-11s|%10.2f|", student.getUniqueCod(),
                student.getName(),
                student.getFirstName(),
                student.getAddress(),
                student.getEmail(),
                str,
                student.getIntegrally(),
                student.getAverage());
    }

    public Collection<String> getStudentsByAverageInDescOrder() {
        Vector<Student> sort = new Vector<>(Sort.sortForStudentsByAverageDesc(this.repo.getAll()));
        Vector<String> sortForReturn = new Vector<>();
        for (Student s : sort) {
            sortForReturn.add(this.getStudentToShow(s));
        }
        return sortForReturn;
    }

    public Object[][] getStudentsByAverageInDescOrderGUI() {
        Vector<Student> sort = new Vector<>(Sort.sortForStudentsByAverageDesc(this.repo.getAll()));
        Object[][] obj = new Object[sort.size()][8];
        int size = sort.size();
        for (int i = 0; i < size; i++) {
            Student student = sort.elementAt(i);
            obj[i][0] = student.getUniqueCod();
            obj[i][1] = student.getName();
            obj[i][2] = student.getFirstName();
            obj[i][3] = student.getAddress();
            obj[i][4] = student.getEmail();
            obj[i][5] = student.getBirthDate();
            obj[i][6] = student.getIntegrally();
            obj[i][7] = student.getAverage();
        }
        return  obj;
    }

    public Collection<String> getIntegrallyStudents() {
        Vector<String> sort = new Vector<>();
        Vector<Student> sortDesc = new Vector<>(Sort.sortForStudentsByAverageDesc(this.repo.getAll()));
        for (Student s : sortDesc) {
            if (s.getIntegrally()) {
                sort.add(this.getStudentToShow(s));
            }
        }
        return sort;
    }

    public Object[][] getIntegrallyStudentsGUI() {
        Vector<String> sort = new Vector<>();
        Vector<Student> sortDesc = new Vector<>(Sort.sortForStudentsByAverageDesc(this.repo.getAll()));
        for (Student s : sortDesc) {
            if (s.getIntegrally()) {
                sort.add(s.getUniqueCod());
            }
        }
        Object[][] obj = new Object[sort.size()][8];
        int size = sort.size();
        for (int i = 0; i < size; i++) {
            Student student = this.repo.getById(sort.elementAt(i));
            obj[i][0] = student.getUniqueCod();
            obj[i][1] = student.getName();
            obj[i][2] = student.getFirstName();
            obj[i][3] = student.getAddress();
            obj[i][4] = student.getEmail();
            obj[i][5] = student.getBirthDate();
            obj[i][6] = student.getIntegrally();
            obj[i][7] = student.getAverage();
        }
        return  obj;
    }

    public Collection<String> getStudentsByAgeInAscOrder() {
        Vector<Student> sort = new Vector<>(Sort.sortForStudentsByAgeAsc(this.repo.getAll()));
        Vector<String> sortForReturn = new Vector<>();
        for (Student s : sort) {
            sortForReturn.add(this.getStudentToShow(s));
        }
        return sortForReturn;
    }

    public Object[][] getStudentsByAgeInAscOrderGUI() {
        Vector<Student> sort = new Vector<>(Sort.sortForStudentsByAgeAsc(this.repo.getAll()));
        Object[][] obj = new Object[sort.size()][8];
        int size = sort.size();
        for (int i = 0; i < size; i++) {
            Student student = sort.elementAt(i);
            obj[i][0] = student.getUniqueCod();
            obj[i][1] = student.getName();
            obj[i][2] = student.getFirstName();
            obj[i][3] = student.getAddress();
            obj[i][4] = student.getEmail();
            obj[i][5] = student.getBirthDate();
            obj[i][6] = student.getIntegrally();
            obj[i][7] = student.getAverage();
        }
        return  obj;
    }

    public void save() {
        this.repo.saveToFile();
    }
}
