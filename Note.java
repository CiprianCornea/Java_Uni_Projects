package Domain;

import java.time.LocalDate;

public class Note implements Entity {
    private final String noteCode;
    private final int value;
    private final String studentCode;
    private final String disciplineCode;
    private final LocalDate noteDate;

    public Note() {
        this.noteCode = "";
        this.value = 0;
        this.studentCode = "";
        this.disciplineCode = "";
        this.noteDate = LocalDate.now();
    }

    public Note(int value, String studentCode, String disciplineCode, LocalDate noteDate) {
        this.noteCode = studentCode + disciplineCode;
        this.value = value;
        this.studentCode = studentCode;
        this.disciplineCode = disciplineCode;
        this.noteDate = noteDate;
    }

    public Note(String note) {
        String[] noteSplit = note.split(",");
        int value = Integer.parseInt(noteSplit[0]);
        this.noteCode = noteSplit[1] + noteSplit[2];
        this.value = value;
        this.studentCode = noteSplit[1];
        this.disciplineCode = noteSplit[2];
        this.noteDate = LocalDate.parse(noteSplit[3]);
    }

    @Override
    public Object getId() {
        return this.noteCode;
    }

    public String getNoteCode() {
        return this.noteCode;
    }

    public int getValue() {
        return this.value;
    }

    public String getStudentCode() {
        return this.studentCode;
    }

    public String getDisciplineCode() {
        return this.disciplineCode;
    }

    public LocalDate getNoteDate() {
        return this.noteDate;
    }

    public String toString() {
        String string = "";
        string += this.value;
        string += ",";
        string += this.studentCode;
        string += ",";
        string += this.disciplineCode;
        string += ",";
        string += this.noteDate;
        return string;
    }
}
