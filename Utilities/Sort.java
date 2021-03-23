package Utilities;

import Domain.Student;

import java.util.Collection;
import java.util.Vector;

public class Sort {
    public static Vector<Student> sortForStudentsByAverageDesc(Collection<Student> v) {
        boolean flag;
        int i, poz, pozInter;
        poz = pozInter = v.size() - 1;
        Vector<Student> values = new Vector<>(v);
        do {
            flag = true;
            for (i = 0; i < poz; i++)
                if (!values.elementAt(i).greaterByAverage(values.elementAt(i + 1))) {
                    Student aux = values.elementAt(i);
                    Student aux1 = values.elementAt(i + 1);
                    values.remove(i);
                    values.remove(i);
                    values.add(i, aux);
                    values.add(i, aux1);
                    pozInter = i;
                    flag = false;
                }
            poz = pozInter;
        }
        while (!flag);
        return values;
    }

    public static Vector<Student> sortForStudentsByAgeAsc(Collection<Student> v) {
        boolean flag;
        int i, poz, pozInter;
        poz = pozInter = v.size() - 1;
        Vector<Student> values = new Vector<>(v);
        do {
            flag = true;
            for (i = 0; i < poz; i++)
                if (values.elementAt(i).greaterByAge(values.elementAt(i + 1))) {
                    Student aux2 = values.elementAt(i);
                    Student aux1 = values.elementAt(i + 1);
                    values.remove(i);
                    values.remove(i);
                    values.add(i, aux2);
                    values.add(i, aux1);
                    pozInter = i;
                    flag = false;
                }
            poz = pozInter;
        }
        while (!flag);
        return values;
    }
}
