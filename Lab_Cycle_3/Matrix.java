import java.util.Scanner;

public class Matrix { //hiii
    private int rows, cols;
    private double data[][];

    public Matrix(int r, int c) {
        rows = r;
        cols = c;
        data = new double[rows][cols];
    }

    public void setElement(int row, int col, double value) {
        data[row][col] = value;
    }

    public double getElement(int row, int col) {
        return data[row][col];
    }

    public Matrix add(Matrix matrix) {
        if (this.rows != matrix.rows || this.cols != matrix.cols) {
            System.out.println("Matrices can't be added.");
            return null;
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j));
            }
        }
        return result;
    }

    public Matrix subtract(Matrix matrix) {
        if (this.rows != matrix.rows || this.cols != matrix.cols) {
            System.out.println("Matrices can't be subtracted.");
            return null;
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.getElement(i, j) - matrix.getElement(i, j));
            }
        }
        return result;
    }

    public Matrix multiply(Matrix matrix) {
        if (this.cols != matrix.rows) {
            System.out.println("Matrices can't be multiplied.");
            return null;
        }
        Matrix result = new Matrix(this.rows, matrix.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.getElement(i, k) * matrix.getElement(k, j);
                }
                result.setElement(i, j, sum);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix transposed = new Matrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                transposed.setElement(j, i, this.getElement(i, j));
            }
        }
        return transposed;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                string.append(getElement(i, j)).append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of rows and columns for Matrix 1:");
        int rows1 = scanner.nextInt();
        int cols1 = scanner.nextInt();
        Matrix matrix1 = new Matrix(rows1, cols1);

        System.out.println("Enter elements for Matrix 1:");
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                matrix1.setElement(i, j, scanner.nextDouble());
            }
        }

        System.out.println("Enter number of rows and columns for Matrix 2:");
        int rows2 = scanner.nextInt();
        int cols2 = scanner.nextInt();
        Matrix matrix2 = new Matrix(rows2, cols2);

        System.out.println("Enter elements for Matrix 2:");
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++) {
                matrix2.setElement(i, j, scanner.nextDouble());
            }
        }

        System.out.println("Matrix 1:");
        System.out.println(matrix1);
        System.out.println("Matrix 2:");
        System.out.println(matrix2);

        Matrix matrix3 = matrix1.add(matrix2);
        System.out.println("Matrix 1 + Matrix 2:");
        System.out.println(matrix3);

        Matrix matrix4 = matrix1.subtract(matrix2);
        System.out.println("Matrix 1 - Matrix 2:");
        System.out.println(matrix4);

        Matrix matrix5 = matrix1.multiply(matrix2);
        System.out.println("Matrix 1 * Matrix 2:");
        System.out.println(matrix5);

        Matrix matrix1Transposed = matrix1.transpose();
        System.out.println("Transpose of Matrix 1:");
        System.out.println(matrix1Transposed);

        System.out.println("Enter order of square matrix:");
        int order = scanner.nextInt();
        SquareMatrix squareMatrix = new SquareMatrix(order);

        System.out.println("Enter elements for Square Matrix:");
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                squareMatrix.setElement(i, j, scanner.nextDouble());
            }
        }

        System.out.println("\nSquare Matrix: ");
        System.out.println(squareMatrix);

        System.out.println("Determinant of Square Matrix:");
        System.out.println(squareMatrix.determinant());

        System.out.println("Is Square Matrix symmetric?");
        System.out.println(squareMatrix.isSymmetric());

        System.out.println("Is Square Matrix singular?");
        System.out.println(squareMatrix.isSingular());

        System.out.println("Trace of Square Matrix:");
        System.out.println(squareMatrix.trace());


        System.out.println("Enter order of diagonal matrix:");
        int diagOrder = scanner.nextInt();
        double[] diagValues = new double[diagOrder];

        System.out.println("Enter diagonal elements:");
        for (int i = 0; i < diagOrder; i++) {
            diagValues[i] = scanner.nextDouble();
        }

        DiagonalMatrix diagonalMatrix = new DiagonalMatrix(diagOrder, diagValues);

        System.out.println("Diagonal Matrix:");
        System.out.println(diagonalMatrix);
    }
}

class SquareMatrix extends Matrix {
    public int order;

    public SquareMatrix(int order) {
        super(order, order);
        this.order = order;
    }

    public double determinant() {
        if (this.order == 1) {
            return this.getElement(0, 0);
        } else if (this.order == 2) {
            return (this.getElement(0, 0) * this.getElement(1, 1)) - (this.getElement(0, 1) * this.getElement(1, 0));
        } else {
            double det = 0;
            for (int i = 0; i < this.order; i++) {
                det += Math.pow(-1, i) * this.getElement(0, i) * this.minor(0, i).determinant();
            }
            return det;
        }
    }

    private SquareMatrix minor(int row, int col) {
        SquareMatrix minor = new SquareMatrix(this.order - 1);
        int m = 0;
        for (int i = 0; i < this.order; i++) {
            if (i == row) continue;
            int n = 0;
            for (int j = 0; j < this.order; j++) {
                if (j == col) continue;
                minor.setElement(m, n, this.getElement(i, j));
                n++;
            }
            m++;
        }
        return minor;
    }

    public boolean isSymmetric() {
        return this.equals(this.transpose());
    }

    public boolean isSingular() {
        return this.determinant() == 0;
    }

    public double trace() {
        double trace = 0;
        for (int i = 0; i < this.order; i++) {
            trace += this.getElement(i, i);
        }
        return trace;
    }
}

class DiagonalMatrix extends SquareMatrix {
    public DiagonalMatrix(int size, double values[]) {
        super(size);
        for (int i = 0; i < size; i++) {
            this.setElement(i, i, values[i]);
        }
    }
}
