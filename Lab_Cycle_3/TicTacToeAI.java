import java.util.Random;
import java.util.Scanner;

interface Player {
    void play(TicTacToeAI game, int currentPlayer) throws TicTacToeAI.InvalidMoveException;
}

class HumanPlayer implements Player {
    int row;
    int col;
    private Scanner scanner;

    public HumanPlayer() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void play(TicTacToeAI game, int currentPlayer) throws TicTacToeAI.InvalidMoveException {
        game.printBoard();
        System.out.println((currentPlayer == -1 ? "X" : "O") + "'s turn.");
        System.out.print("Enter row & column numbers (0-2): \n");
        this.row = scanner.nextInt();
        this.col = scanner.nextInt();
        
        game.makeMove(row, col, currentPlayer);
    }
}

class ComputerPlayer implements Player {
    private Random random;
    int row;
    int col;

    public ComputerPlayer() {
        random = new Random();
    }

    @Override
    public void play(TicTacToeAI game, int currentPlayer) {
        if (game.isGameOver()) {
            return;
        }
        
        int[] winningMove = findWinningMove(game, currentPlayer);
        if (winningMove != null) {
            game.makeMove(winningMove[0], winningMove[1], currentPlayer);
            return;
        }
        
        int[] blockingMove = findWinningMove(game, -1 * currentPlayer);
        if (blockingMove != null) {
            game.makeMove(blockingMove[0], blockingMove[1], currentPlayer);
            return;
        }
        
        if (game.isMoveValid(1, 1)) {
            game.makeMove(1, 1, currentPlayer);
            return;
        }
        
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (game.isMoveValid(corner[0], corner[1])) {
                game.makeMove(corner[0], corner[1], currentPlayer);
                return;
            }
        }
        
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!game.isMoveValid(row, col));
        game.makeMove(row, col, currentPlayer);
    }

    private int[] findWinningMove(TicTacToeAI game, int player) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (game.isMoveValid(row, col)) {
                    game.makeMove(row, col, player);
                    if (game.checkWinner(row,col,player)) {
                        game.undoMove(row, col);
                        return new int[]{row, col};
                    }
                    game.undoMove(row, col);
                }
            }
        }
        return null;
    }
}

public class TicTacToeAI {
    private int lastRow;
    private int lastCol;

    private static SquareMatrix board;

    public static class InvalidMoveException extends Exception {
        public InvalidMoveException(String message) {
            super(message);
        }
    }

    public TicTacToeAI() {
        board = new SquareMatrix(3);
    }

    public boolean isMoveValid(int row, int col) {
        return board.getElement(row, col) == 0;
    }

    public void undoMove(int row, int col) {
        board.setElement(row, col, 0);
    }
    
    public boolean makeMove(int row, int col, int turn) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board.getElement(row, col) != 0) {
            System.out.println("Invalid Move. Please try again.");
            return false;
        } else {
            board.setElement(row, col, turn);
            lastRow = row; 
            lastCol = col;
            return true;
        }
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

        return (sumrow == 3 || sumrow == -3 || sumcol == 3 || sumcol == -3 
            || sumdiagonal1 == 3 || sumdiagonal1 == -3 || sumdiagonal2 == 3 || sumdiagonal2 == -3) ;
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
        TicTacToeAI game = new TicTacToeAI();
        Player player1 = new HumanPlayer();
        Player player2 = new ComputerPlayer();
        int currentPlayer = -1;
        boolean gameEnd = false;

        while (!gameEnd) {
            Player currentPlayerObj = (currentPlayer == -1) ? player1 : player2;
            try {
                currentPlayerObj.play(game, currentPlayer);
            } catch (InvalidMoveException e) {
                System.out.println("Error: " + e.getMessage());
                continue; 
            }

            if (game.checkWinner(game.lastRow, game.lastCol, currentPlayer)) {
                game.printBoard();
                System.out.println("Player " + (currentPlayer == -1 ? "X" : "O") + " wins!");
                gameEnd = true;
            } else if (game.isGameOver()) {
                System.out.println("It's a draw!");
                gameEnd = true;
            }

            currentPlayer *= -1;
        }
    }
}


/*class ComputerPlayer implements Player {
    private Random random;

    public ComputerPlayer() {
        random = new Random();
    }

    @Override
    public void play(TicTacToeAI game, int currentPlayer) {
        int row, col;
        boolean moveMade = false; 
        do {
            try {
                row = random.nextInt(2);
                col = random.nextInt(2);
                moveMade = game.makeMove(row, col, currentPlayer);
            } catch (TicTacToeAI.InvalidMoveException e) {
                continue;
            }
        } while (!moveMade);
    }
}*/
