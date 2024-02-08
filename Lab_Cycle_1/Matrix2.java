public class Matrix2 {
    private int rows;
    private int cols;
    private int[][] data;

    public Matrix2(int rows, int cols, int[] elements) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = elements[index++];
            }
        }
    }

    public void display() {
        System.out.println("MATRIX:");
        for (int[] row : data) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try{
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            int[] elements = new int[rows * cols];
            for (int i = 0; i < elements.length; i++) {
                elements[i] = Integer.parseInt(args[i + 2]);
            }
            Matrix2 Matrix2 = new Matrix2(rows, cols, elements);
            Matrix2.display();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide integers only.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Insufficient arguments provided.");
        } 
    }
}

