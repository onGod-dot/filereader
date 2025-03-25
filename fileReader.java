import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class fileReader {
    private static final String FILE_PATH = "/Users/user/Desktop/Student.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Show All Data");
            System.out.println("2. Add Data");
            System.out.println("3. Update Data");
            System.out.println("4. Delete Data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAllData();
                    break;
                case 2:
                    System.out.print("Enter data to add: ");
                    String dataToAdd = scanner.nextLine();
                    addData(dataToAdd);
                    break;
                case 3:
                    System.out.print("Enter the data to update: ");
                    String oldData = scanner.nextLine();
                    System.out.print("Enter the new data: ");
                    String newData = scanner.nextLine();
                    updateData(oldData, newData);
                    break;
                case 4:
                    System.out.print("Enter the data to delete: ");
                    String dataToDelete = scanner.nextLine();
                    deleteData(dataToDelete);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void showAllData() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner fileScanner = new Scanner(file);
        System.out.println("Contents of the file:");
        while (fileScanner.hasNextLine()) {
            System.out.println(fileScanner.nextLine());
        }
        fileScanner.close();
    }

    private static void addData(String data) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(data);
            System.out.println("Data added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding data.");
            e.printStackTrace();
        }
    }

    private static void updateData(String oldData, String newData) {
        try {
            File file = new File(FILE_PATH);
            List<String> fileContent = new ArrayList<>();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.equals(oldData)) {
                    fileContent.add(newData);
                } else {
                    fileContent.add(line);
                }
            }
            fileScanner.close();

            // Write updated content back to the file
            try (PrintWriter out = new PrintWriter(file)) {
                for (String line : fileContent) {
                    out.println(line);
                }
            }
            if (fileContent.size() == 0) {
                System.out.println("No matching data found to update.");
            } else {
                System.out.println("Data updated successfully.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    private static void deleteData(String data) {
        try {
            File file = new File(FILE_PATH);
            List<String> fileContent = new ArrayList<>();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.equals(data)) {
                    fileContent.add(line);
                }
            }
            fileScanner.close();

            // Write updated content back to the file
            try (PrintWriter out = new PrintWriter(file)) {
                for (String line : fileContent) {

                    out.println(line);
                }
            }
            if (fileContent.size() == 0) {
                System.out.println("No matching data found to delete.");
            } else {
                System.out.println("Data deleted successfully.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
