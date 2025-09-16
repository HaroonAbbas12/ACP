/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import java.util.Scanner;

public class StudentManagement {

    static String[] names = new String[50];
    static int[] rollNums = new int[50];
    static int[][] marks = new int[50][3];
    static int studentCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n Student Management Menu ");
            System.out.println("1. Add Student");
            System.out.println("2. Update Marks");
            System.out.println("3. Remove Student");
            System.out.println("4. View All Students");
            System.out.println("5. Search Student");
            System.out.println("6. Highest Scorer");
            System.out.println("7. Class Average");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: updateMarks(sc); break;
                case 3: removeStudent(sc); break;
                case 4: viewAll(); break;
                case 5: searchStudent(sc); break;
                case 6: highestScorer(); break;
                case 7: classAverage(); break;
                case 8:
                    System.out.println("Exiting... Total students: " + studentCount);
                    classAverage();
                    break;
                default:
                    System.out.println("Invalid choice! try again...");
            }
        } while (choice != 8);
    }

    static void addStudent(Scanner sc) {
        if (studentCount >= 50) {
            System.out.println("Max limit reached! cant add more.");
            return;
        }
        System.out.print("Enter roll no: ");
        int roll = sc.nextInt();

        for (int i = 0; i < studentCount; i++) {
            if (rollNums[i] == roll) {
                System.out.println("Roll no already exists!!");
                return;
            }
        }

        sc.nextLine(); // consume newline
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        int[] m = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter marks in subject " + (i + 1) + ": ");
            m[i] = sc.nextInt();
            if (m[i] < 0 || m[i] > 100) {
                System.out.println("Invalid marks! must be 0-100.");
                return;
            }
        }

        rollNums[studentCount] = roll;
        names[studentCount] = name;
        marks[studentCount] = m;
        studentCount++;
        System.out.println("Student added successfully!");
    }

    static void updateMarks(Scanner sc) {
        System.out.print("Enter roll no to update: ");
        int roll = sc.nextInt();
        int index = findIndex(roll);

        if (index == -1) {
            System.out.println("Roll not found!");
            return;
        }

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter new marks in subject " + (i + 1) + ": ");
            marks[index][i] = sc.nextInt();
        }
        System.out.println("Marks updated successfully!");
    }

    static void removeStudent(Scanner sc) {
        System.out.print("Enter roll no to remove: ");
        int roll = sc.nextInt();
        int index = findIndex(roll);

        if (index == -1) {
            System.out.println("Roll not found!");
            return;
        }

        for (int i = index; i < studentCount - 1; i++) {
            rollNums[i] = rollNums[i + 1];
            names[i] = names[i + 1];
            marks[i] = marks[i + 1];
        }
        studentCount--;
        System.out.println("Student removed.");
    }

    static void viewAll() {
        if (studentCount == 0) {
            System.out.println("No students to show.");
            return;
        }
        System.out.println("Roll\tName\tSub1\tSub2\tSub3\tTotal\tAvg");
        for (int i = 0; i < studentCount; i++) {
            int total = marks[i][0] + marks[i][1] + marks[i][2];
            double avg = total / 3.0;
            System.out.println(rollNums[i] + "\t" + names[i] + "\t" +
                    marks[i][0] + "\t" + marks[i][1] + "\t" + marks[i][2] + "\t" +
                    total + "\t" + String.format("%.2f", avg));
        }
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter roll no to search: ");
        int roll = sc.nextInt();
        int index = findIndex(roll);

        if (index == -1) {
            System.out.println("Student not found!");
            return;
        }

        int total = marks[index][0] + marks[index][1] + marks[index][2];
        double avg = total / 3.0;
        System.out.println("Found student: " + names[index] +
                " | Roll: " + rollNums[index] +
                " | Marks: " + marks[index][0] + ", " + marks[index][1] + ", " + marks[index][2] +
                " | Total: " + total +
                " | Avg: " + String.format("%.2f", avg));
    }

    static void highestScorer() {
        if (studentCount == 0) {
            System.out.println("No students yet.");
            return;
        }

        int highIndex = 0;
        int highTotal = marks[0][0] + marks[0][1] + marks[0][2];

        for (int i = 1; i < studentCount; i++) {
            int total = marks[i][0] + marks[i][1] + marks[i][2];
            if (total > highTotal) {
                highTotal = total;
                highIndex = i;
            }
        }

        System.out.println("Highest scorer is: " + names[highIndex] +
                " (Roll " + rollNums[highIndex] + ") with total " + highTotal);
    }

    static void classAverage() {
        if (studentCount == 0) {
            System.out.println("Class average = 0 (no students).");
            return;
        }

        int sum = 0;
        for (int i = 0; i < studentCount; i++) {
            sum += marks[i][0] + marks[i][1] + marks[i][2];
        }
        double avg = sum / (studentCount * 3.0);
        System.out.println("Class average marks: " + String.format("%.2f", avg));
    }

    static int findIndex(int roll) {
        for (int i = 0; i < studentCount; i++) {
            if (rollNums[i] == roll) return i;
        }
        return -1;
    }
}

