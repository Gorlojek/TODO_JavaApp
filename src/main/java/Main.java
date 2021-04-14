import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
       String[][] tasks = taskArr();
       String[] programMethods = {"add","list","remove","quit"};
       addTask(tasks);
       tasksReader(tasks);

       /*Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input){
            case "add":
                addTask();
                break;
        }

*/

    }


// metody działania na tablicach
    //metoda wczytująca plik do programu
    //zaczytasnie pliku do tablicy na ktorej pracuje program
    public static String[][] taskArr() throws IOException {
        File file = new File("tasks.csv");
        String[][] tasks = new String[0][];
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
                tasks = addNewItemToArray(tasks, scanner.nextLine().split(","));
        }
        System.out.println(Arrays.deepToString(tasks));
        return tasks;
    }

    //odczyt z pliku do tablicy
    public static void tasksReader(String[][] tasks) throws IOException {

        System.out.println(Arrays.deepToString(tasks));
    }

    //wymiana elementow w tablicy
/*    public static String[][] tasksUpdater(){
        File file = new File("tasks.csv");
    }*/

    //metoda list

    // metoda add
    public static String[][] addTask(String[][] tasks) throws IOException {
        String[][] arrForProgram = taskArr();
        System.out.println("Proszę o podanie nowego zadnia do dodania");
        Scanner sc = new Scanner(System.in);
        String[] newTask = new String[3];
        System.out.println("Podaj opis nowego zadania: ");

        newTask[0] = sc.nextLine();
        System.out.println("Podaj termin wykonania zadania: ");
        newTask[1] = sc.nextLine();
        System.out.println("Podaj czy zadanie jest pilne: ");
        newTask[2] = sc.nextLine();

        arrForProgram = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = newTask;
        System.out.println(Arrays.deepToString(tasks));
        return tasks;
    }
    //metoda remove
    //metoda exit

    // dodawanie dynamiczne do tablicy
    public static String[][] addNewItemToArray(String[][] items, String[] addedArr) {
        String[][] temp = Arrays.copyOf(items, items.length + 1);

        temp[temp.length - 1] = addedArr;
        return temp;
    }
}

