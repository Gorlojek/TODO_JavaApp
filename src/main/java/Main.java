import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String[][] tasks = taskArr();
        String[] programMethods = {"add", "list", "remove", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (String s: programMethods){
            System.out.println(s);
        }

        Scanner scanner = new Scanner(System.in);
        while(!scanner.nextLine().equals("exit")){
            String command = scanner.nextLine();
        if (command.equals("add")) {
            tasks = addTask(tasks);
            scanner.next();
        } else if (command.equals("list")) {
            tasksReader(tasks);
            scanner.next();
        }
        else if (command.equals("remove")){
            tasks =removeTask(tasks);
            scanner.next();
        }
        }
        if (scanner.nextLine().equals("exit")) {
            saveArr(tasks);
        }

       /*Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input){
            case "add":
                addTask();
                break;
        }

*/

    }





    public static String[][] taskArr() throws IOException {
        File file = new File("tasks.csv");
        String[][] tasks = new String[0][];
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            tasks = addNewItemToArray(tasks, scanner.nextLine().split(", "));
        }
        return tasks;
    }


    public static void tasksReader(String[][] tasks) throws IOException {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < tasks[i].length; j++) { //this equals to the column in each row.
                if (j < tasks[i].length - 1) {
                    System.out.print(tasks[i][j] + " ");
                } else {
                    System.out.println(tasks[i][j]);
                }
            }

        }
    }

    // metoda add DO DOPISANIA metody poprawności daty i płci
    public static String[][] addTask(String[][] tasks) throws IOException {
        System.out.println("Proszę o podanie nowego zadnia do dodania");
        Scanner sc = new Scanner(System.in);
        String[] newTask = new String[3];
        System.out.println("Podaj opis nowego zadania: ");

        newTask[0] = sc.nextLine();
        System.out.println("Podaj termin wykonania zadania: ");
        newTask[1] = sc.nextLine();
        System.out.println("Podaj czy zadanie jest pilne: ");
        newTask[2] = sc.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = newTask;
        return tasks;
    }

    //metoda remove
    public static String[][] removeTask(String[][] tasks){
        System.out.println("Podaj numer zadania, który chcesz usunąć");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        tasks = ArrayUtils.remove(tasks,i);
        return tasks;
    }
    //metoda exit
    public static void saveArr(String[][] tasks) {
        File file = new File("tasks.csv");
        try (PrintWriter pw = new PrintWriter(file);) {
            for (String[] singleTask : tasks) {
                for (int j = 0; j < singleTask.length; j++) { //this equals to the column in each row.
                    if (j < singleTask.length - 1) {
                        pw.print(singleTask[j]);
                        pw.print(", ");
                    } else {
                        pw.print(singleTask[j]);
                    }
                }
                pw.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // dodawanie dynamiczne do tablicy
    public static String[][] addNewItemToArray(String[][] items, String[] addedArr) {
        String[][] temp = Arrays.copyOf(items, items.length + 1);

        temp[temp.length - 1] = addedArr;
        return temp;
    }
}

