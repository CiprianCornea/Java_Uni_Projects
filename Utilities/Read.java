package Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Read {
    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static long readNumber(String s) {
        long a = 0l;
        int i1;
        String s1;
        try{
            InputStreamReader stdin = new InputStreamReader(System.in);
            BufferedReader console = new BufferedReader(stdin);
            System.out.print(s);
            s1 = console.readLine();
            i1 = Integer.parseInt(s1);
            a = i1;
        } catch(IOException exc){
            System.out.print("Input error: ");
        }
        catch(NumberFormatException exc1){
            System.out.println("\"" +
                    exc1.getMessage() +
                    "\" It's not a natural number!!");
        }
        return a;
    }
}
