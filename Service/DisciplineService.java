package Service;

import Domain.Discipline;
import Domain.Note;
import Domain.Student;
import Repository.RepoInFile;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Vector;

public class DisciplineService {
    private final RepoInFile<Discipline> repo;

    public DisciplineService(RepoInFile<Discipline> repo) {
        this.repo = repo;
    }

    public void add(String disciplineCode, String name, int numberOfCredits) {
        Discipline discipline = new Discipline(disciplineCode, name, numberOfCredits);
        this.repo.add(discipline);
    }

    public void add(String discipline) {
        Discipline d = new Discipline(discipline);
        this.repo.add(d);
    }

    public void delete(String disciplineCode) {
        Discipline discipline = this.repo.getById(disciplineCode);
        this.repo.delete(discipline);
    }

    public void update(String disciplineCode, String newName, int newNumberOfCredits) {
        Discipline toUpdate = this.repo.getById(disciplineCode);
        Discipline toReplace = new Discipline(disciplineCode, newName, newNumberOfCredits);
        this.repo.update(toUpdate, toReplace);
    }

    public Collection<Discipline> getAll() {
        return this.repo.getAll();
    }

    public Object[][] getAlDisciplinesGUI() {
        Vector<Discipline> disciplines = new Vector<>(this.getAll());
        Object[][] obj = new Object[disciplines.size()][3];
        int size = disciplines.size();
        for (int i = 0; i < size; i++) {
            Discipline discipline = disciplines.elementAt(i);
            obj[i][0] = discipline.getDisciplineCode();
            obj[i][1] = discipline.getName();
            obj[i][2] = discipline.getNumberOfCredits();
        }
        return  obj;
    }

    public Discipline getById(String disciplineCode) {
        return this.repo.getById(disciplineCode);
    }

    public String getDisciplineToShow(Discipline discipline){
        return String.format("|%-20s|%-20s|%17s|", discipline.getDisciplineCode(),
                discipline.getName(),
                discipline.getNumberOfCredits());
    }

}
