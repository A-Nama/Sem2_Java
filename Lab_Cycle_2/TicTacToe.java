import java.util.Scanner;

public class TicTacToe {
    private SquareMatrix board;

    public TicTacToe() {
        board = new SquareMatrix(3);
    }
        
    public boolean makeMove(int row, int col, int turn) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board.getElement(row, col) != 0) {
            return false;
        }
        board.setElement(row, col, turn);
        return true;
    }

    public boolean isGameOver() {     
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getElement(i, j) == 0) {
                    return false; 
                }
            }
        }
        return true; 
    }

    public boolean checkWinner(int row, int col, int turn) {
        int sumrow = 0;
        int sumcol = 0;
        int sumdiagonal1 = 0;
        int sumdiagonal2 = 0;

        for(int i = 0; i < 3; i++) {
            sumrow += board.getElement(row, i);
            sumcol += board.getElement(i, col);
        }

        if (row == col) {
            for(int i = 0; i < 3; i++) {
                sumdiagonal1 += board.getElement(i, i);
            }
        }
        if (row + col == 2) {
            for(int i = 0; i < 3; i++) {
                sumdiagonal2 += board.getElement(i, 2 - i);
            }
        }

        if (sumrow == 3 || sumrow == -3 || sumcol == 3 || sumcol == -3 
            || sumdiagonal1 == 3 || sumdiagonal1 == -3 || sumdiagonal2 == 3 || sumdiagonal2 == -3) {
            return true; 
        }
        return false; 
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getElement(i, j) == -1) {
                    System.out.print("X ");
                } else if (board.getElement(i, j) == 1) {
                    System.out.print("O ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        int currentPlayer = -1; 
        boolean gameEnd = false;

        while (!gameEnd) {
            System.out.println((currentPlayer == -1 ? "X" : "O") + "'s turn.");
            System.out.print("Enter row & column numbers (0-2): \n");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!game.makeMove(row, col, currentPlayer)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            game.printBoard();

            if (game.checkWinner(row, col, currentPlayer)) {
                System.out.println("Player " + (currentPlayer == -1 ? "X" : "O") + " wins!");
                gameEnd = true;
            } else if (game.isGameOver()) {
                System.out.println("It's a draw!");
                gameEnd = true;
            }

            currentPlayer *= -1; 
        }
        scanner.close();
    }
}
