import java.util.Scanner;

class matrix { 
    private int rows;
    private int cols;
    private int[][] data;

    public matrix(int rows, int cols, int[] Data) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = Data[k++];
            }
        }
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int ColumnSum(int col) {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            if (col >= 0 && col < cols) {
                sum += data[i][col];
            }
        }
        return sum;
    }

    public int RowSum(int row) {
        int sum = 0;
        if (row >= 0 && row < rows) {
            for (int j = 0; j < cols; j++) {
                sum += data[row][j];
            }
        }
        return sum;
    }

    public double Average() {
        double sum = 0;
        for (int i = 0; i<rows; i++) {
            for (int j = 0 ; j<cols; j++) {
                sum += data[i][j];
            }
        }
        return sum / (rows * cols);
    }

    public boolean isDiagonal() {
        if (rows != cols) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i != j && data[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class MatrixMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static matrix matx;

    public static void main(String[] args) {
        char choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("a. Create/Update matrix");
            System.out.println("b. Print matrix");
            System.out.println("c. Print Column Sum");
            System.out.println("d. Print Row Sum");
            System.out.println("e. Print Average of matrix");
            System.out.println("f. Check if matrix is Diagonal");
            System.out.println("g. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.next().charAt(0);

            switch (choice) {
                case 'a':
                    createOrUpdateMatrix();
                    break;
                case 'b':
                    printMatrix();
                    break;
                case 'c':
                    printColumnSum();
                    break;
                case 'd':
                    printRowSum();
                    break;
                case 'e':
                    printAverage();
                    break;
                case 'f':
                    checkDiagonal();
                    break;
                case 'g':
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'g');
    }

    public static void createOrUpdateMatrix() {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();
        System.out.println("Enter matrix elements row-wise:");
        int[] elements = new int[rows * cols];
        for (int i = 0; i < rows * cols; i++) {
            elements[i] = scanner.nextInt();
        }
        matx = new matrix(rows, cols, elements); 
        System.out.println("Matrix created/updated successfully.");
    }

    public static void printMatrix() {
        if (matx != null) {
            System.out.println("Matrix:");
            matx.display();
        } 
        else {
            System.out.println("No matrix exists. Please create one first.");
        }
    }

    public static void printColumnSum() {
        if (matx != null) {
            System.out.print("Enter column number: ");
            int col = scanner.nextInt();
            int sum = matx.ColumnSum(col);
            System.out.println("Sum of column " + col + ": " + sum);
        } else {
            System.out.println("No matrix exists. Please create one first.");
        }
    }

    public static void printRowSum() {
        if (matx != null) {
            System.out.print("Enter row number: ");
            int row = scanner.nextInt();
            int sum = matx.RowSum(row);
            System.out.println("Sum of row " + row + ": " + sum);
        } else {
            System.out.println("No matrix exists. Please create one first.");
        }
    }

    public static void printAverage() {
        if (matx != null) {
            double average = matx.Average();
            System.out.println("Average of matrix elements: " + average);
        } else {
            System.out.println("No matrix exists. Please create one first.");
        }
    }

    public static void checkDiagonal() {
        if (matx != null) {
            String result = matx.isDiagonal() ? "It is a diagonal matrix" : "It is not a diagonal matrix";
            System.out.println(result);
        } else {
            System.out.println("No matrix exists. Please create one first.");
        }
    }
}

