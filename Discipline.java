package Domain;

public class Discipline implements Entity {
    private final String disciplineCode;
    private final String name;
    private final int numberOfCredits;

    public Discipline() {
        this.disciplineCode = "";
        this.name = "";
        this.numberOfCredits = 0;
    }

    public Discipline(String disciplineCode, String name, int numberOfCredits) {
        this.disciplineCode = disciplineCode;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
    }

    public Discipline(String discipline) {
//        try {
        String[] disciplineSplit = discipline.split(",");
        int numberOfCredits = Integer.parseInt(disciplineSplit[2]);
        this.disciplineCode = disciplineSplit[0];
        this.name = disciplineSplit[1];
        this.numberOfCredits = numberOfCredits;
//        } catch (RuntimeException exc) {
//            System.out.println(exc.getMessage());
//        }
    }

    @Override
    public Object getId() {
        return this.disciplineCode;
    }

    public String getDisciplineCode() {
        return this.disciplineCode;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfCredits() {
        return this.numberOfCredits;
    }

    public String toString() {
        String string = "";
        string += this.disciplineCode;
        string += ",";
        string += this.name;
        string += ",";
        string += this.numberOfCredits;
        return string;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Discipline && ((Discipline) other).getId().equals(this.disciplineCode);

    }
}
