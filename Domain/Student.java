package Domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Student implements Entity {
    private final String uniqueCode;
    private final String name;
    private final String firstName;
    private final String address;
    private final String email;
    private final LocalDate birthDate;
    private boolean integrally;
    private int sum;
    private int noteNumber;
    private float average;

    public Student() {
        this.uniqueCode = "";
        this.name = "";
        this.firstName = "";
        this.address = "";
        this.email = "";
        this.birthDate = LocalDate.now();
        this.integrally = true;
        this.sum = 0;
        this.noteNumber = 0;
        this.average = 0;
    }

    public Student(String uniqueCode, String name, String firstName, String address, String email, LocalDate birthDate) {
        this.uniqueCode = uniqueCode;
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.integrally = true;
        this.sum = 0;
        this.noteNumber = 0;
        this.average = 0;
    }

    public Student(String student) {
//        try {
        String[] studentSplit = student.split(",");
        this.uniqueCode = studentSplit[0];
        this.name = studentSplit[1];
        this.firstName = studentSplit[2];
        this.address = studentSplit[3];
        this.email = studentSplit[4];
        LocalDate date = LocalDate.parse(studentSplit[5]);
        this.birthDate = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        String integrally = studentSplit[6];
        this.integrally = integrally.charAt(0) == 't';
        this.sum = 0;
        this.noteNumber = 0;
        this.average = Float.parseFloat(studentSplit[7]);
//        } catch (RuntimeException exc) {
//            System.out.println((exc.getMessage()));
//        }
    }

    @Override
    public Object getId() {
        return this.uniqueCode;
    }

    public String getUniqueCod() {
        return this.uniqueCode;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public boolean getIntegrally() {
        return this.integrally;
    }

    public float getAverage() {
        return this.average;
    }

    public void setIntegrally(boolean newIntegrally) {
        this.integrally = newIntegrally;
    }

    public void setAverage(int note) {
        this.sum += note;
        this.noteNumber ++;
        this.average = (float)this.sum/this.noteNumber;
    }

    public String toString() {
        String string = "";
        string += this.uniqueCode;
        string += ",";
        string += this.name;
        string += ",";
        string += this.firstName;
        string += ",";
        string += this.address;
        string += ",";
        string += this.email;
        string += ",";
        string += this.birthDate;
        string += ",";
        string += this.integrally;
        string += ",";
        string += this.average;
        return string;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Student && ((Student) other).getId().equals(this.uniqueCode);
    }

    public boolean greaterByAverage(Student student){
        return this.average > student.average;
    }

    public boolean greaterByAge(Student student){
        return this.birthDate.isAfter(student.getBirthDate());
    }
}
