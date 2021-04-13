import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.deepToString(tasks()));
    }


    public static String[][] tasks() throws IOException {
        String line;
        String[] singleTask = new String[0];
        String[][] tasks = new String[0][];
        File file = new File("tasks.csv");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                singleTask = line.split(",");
                tasks = addNewItemToArray(tasks, singleTask);

            }
        }
        return tasks;
    }
    public static String[][] addNewItemToArray(String[][] items, String[] addedArr) {
        String[][] temp = Arrays.copyOf(items, items.length + 1);

        temp[temp.length - 1] = addedArr;
        return temp;
    }


}

