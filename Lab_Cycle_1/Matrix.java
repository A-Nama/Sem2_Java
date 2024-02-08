import java.util.Scanner;

public class Matrix {
    private int rows,cols;
    private double data[][];
    private String name;

    public Matrix(int r, int c){
        rows = r;
        cols = c;
        data = new double[rows][cols];
    }

    public void setElement(int row, int cols, double value){
        data[row][cols] = value;
    }

    public double getElement(int row, int cols){
        return data[row][cols];
    }

    public Matrix add(Matrix matrix){
        if (this.rows!= matrix.rows || this.cols!= matrix.cols){
            System.out.println("Matrices can't be added.");
            return null;
        }
        Matrix result = new  Matrix(rows, cols);
        for(int i = 0; i<rows; i++){
            for(int j = 0 ; j<cols; j++){
                result.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j));
            }
        }
        return result;
    }

    public Matrix subtract(Matrix matrix){
        if(this.rows!= matrix.rows || this.cols!= matrix.cols){
            System.out.println("Matrices can't be subtracted.");
            return null;
        }
        Matrix result = new Matrix(rows,cols);
        for(int i=0; i<rows; i++){
            for (int j = 0; j<cols; j++){
                result.setElement(i, j, this.getElement(i, j) - matrix.getElement(i, j));
            }
        }
        return result;
    }

    public Matrix multiply(Matrix matrix){
        if(this.cols != matrix.rows){
            System.out.println("Matrices can't be multiplied.");
            return null;
        }
        Matrix result = new Matrix(this.rows, matrix.cols);
        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<matrix.cols; j++){
                double sum = 0;
                for(int k = 0; k<this.cols; k++){
                    sum+= this.getElement(i, k) * matrix.getElement(k, j);
                }
                result.setElement(i, j, sum);
            }
        }
        return result;
    }

    public Matrix transpose(){
        Matrix transposed = new Matrix(this.cols, this.rows);
        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.cols; j++){
                transposed.setElement(j, i, this.getElement(i, j));
            }
        }
        return transposed;
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                string.append(getElement(i, j)).append(" ");
        }
        string.append("\n");
    }
    return string.toString();
}

    public static void main(String[] args){
        Matrix matrix1 = new Matrix(3,3);
        Matrix matrix2 =  new Matrix(3,3);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter values for Matrix 1:");
        for (int i = 0; i<3;i++){
            for (int j = 0; j<3; j++){
                matrix1.setElement(i, j, scanner.nextDouble());
            }
        }

        System.out.println("Enter values for Matrix 2 : ");
        for (int i = 0; i<3;i++){
            for (int j = 0; j<3; j++){
                matrix2.setElement(i, j, scanner.nextDouble());
            }
        }      

        Matrix add_result = matrix1.add(matrix2);
        Matrix sub_result = matrix1.subtract(matrix2);
        Matrix product = matrix1.multiply(matrix2);
        Matrix transpose1 = matrix1.transpose();
        Matrix transpose2 = matrix2.transpose();

        System.out.println("");
        System.out.println("Matrix 1");
        System.out.println(matrix1.toString());

        System.out.println("Matrix 2");
        System.out.println(matrix2.toString());

        System.out.println("The sum of the two matrices are: ");
        System.out.println(add_result.toString());

        System.out.println("The difference between matrices are : ");
        System.out.println(sub_result.toString());

        System.out.println("The product of the two matrices are : ");
        System.out.println(product.toString());

        System.out.println("The transpose of matrix1 is: ");
        System.out.println(transpose1.toString());

        System.out.println(("The transpose of matrix2 is: "));
        System.out.println(transpose2.toString());
     
        
    }
    };
