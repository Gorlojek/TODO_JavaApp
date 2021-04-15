import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String[][] tasks = taskArr();

        String[] programMethods = {"add", "list", "remove", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (String s : programMethods) {
            System.out.println(s);
        }
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("add") && !command.equals("remove") && !command.equals("list") && !command.equals("exit")) {
            System.out.println("Podaj poprawną komendę do wykonania");
            command = scanner.nextLine();

        }
        while (!command.equals("exit")) {
            if (command.equals("add")) {
                tasks = addTask(tasks);

            }
            if (command.equals("remove")) {
                tasks = removeTask(tasks);

            }
            if (command.equals("list")) {
                tasksReader(tasks);

            }
            System.out.println(ConsoleColors.BLUE + "Wpisz kolejne polecenie do wykonania" + ConsoleColors.RESET);
            command = scanner.nextLine();
        }
        saveArr(tasks);

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


    public static void tasksReader(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + 1 + ": ");
            for (int j = 0; j < tasks[i].length; j++) {
                if (j < tasks[i].length - 1) {
                    System.out.print(tasks[i][j] + " ");
                } else {
                    if (tasks[i][j].equals("true")){
                        System.out.println(ConsoleColors.RED + tasks[i][j] + ConsoleColors.RESET);
                    }
                    else{
                        System.out.println(ConsoleColors.GREEN+ tasks[i][j] + ConsoleColors.RESET);
                    }

                }
            }

        }
    }

    // metoda add DO DOPISANIA metody poprawności daty
    public static String[][] addTask(String[][] tasks) {
        System.out.println("Proszę o podanie nowego zadnia do dodania");
        Scanner sc = new Scanner(System.in);
        String[] newTask = new String[3];
        System.out.println(ConsoleColors.CYAN_BOLD +"Podaj opis nowego zadania: ");

        newTask[0] = sc.nextLine();
        System.out.println("Podaj termin wykonania zadania: ");
        newTask[1] = sc.nextLine();
        System.out.println("Podaj czy zadanie jest pilne: ");
        while (!sc.hasNextBoolean()) {
            sc.next();
            System.out.println(ConsoleColors.RED_BOLD +"Podaj poprawną wartość:" + ConsoleColors.RESET +"\ntrue - jeżeli zadanie jest pilne\nfalse - jeżeli zadanie nie jest pilne");
        }
        boolean priority = sc.nextBoolean();
        newTask[2] = Boolean.toString(priority);


        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = newTask;
        return tasks;
    }


    //metoda remove
    public static String[][] removeTask(String[][] tasks) {
        System.out.println("Podaj numer zadania, który chcesz usunąć");
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                int i = sc.nextInt() - 1;
                if (i >= 0 && i < tasks.length) {
                    tasks = ArrayUtils.remove(tasks, i);
                    return tasks;
                }
            } else {
                sc.next();
            }
            System.out.println("Podaj prawidłową dodatnią wartość do zmiany");
        }

    }

    //metoda exit
    public static void saveArr(String[][] tasks) {
        File file = new File("tasks.csv");
        System.out.println(ConsoleColors.RED + "Bye,bye!");
        try (PrintWriter pw = new PrintWriter(file)) {
            for (String[] singleTask : tasks) {
                for (int j = 0; j < singleTask.length; j++) {
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

