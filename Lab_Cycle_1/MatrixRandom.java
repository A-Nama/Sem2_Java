import java.util.Random;

public class MatrixRandom {
    private int[][] matrix;

    public MatrixRandom() {
        Random random = new Random();
        int rows = random.nextInt(11) ; 
        int cols = random.nextInt(11) ; 
        matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(1000) + 1;
            }
        }
    }

    public void displayMatrix() {
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j<matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MatrixRandom randomMatrix = new MatrixRandom();
        System.out.println("Random Matrix:");
        randomMatrix.displayMatrix();
    }
}

