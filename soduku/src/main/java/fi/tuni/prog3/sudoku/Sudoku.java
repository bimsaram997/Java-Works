/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fi.tuni.prog3.sudoku;

public class Sudoku {
    private char[][] grid;

    public Sudoku() {
        grid = new char[9][9];
        // Initialize the grid with empty cells
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void set(int i, int j, char c) {
        if (!isValidIndex(i, j)) {
            System.out.println("Trying to access illegal cell (" + i + ", " + j + ")!");
            return;
        }

        if (!isValidCharacter(c)) {
            System.out.println("Trying to set illegal character " + c + " to (" + i + ", " + j + ")!");
            return;
        }

        grid[i][j] = c;
    }

    public boolean check() {
        // Check rows
        for (int i = 0; i < 9; i++) {
            if (!isRowValid(i)) {
                System.out.println("Row " + i + " has multiple " + findInvalidCharacterInRow(i) + "'s!");
                return false;
            }
        }

        // Check columns
        for (int j = 0; j < 9; j++) {
            if (!isColumnValid(j)) {
                System.out.println("Column " + j + " has multiple " + findInvalidCharacterInColumn(j) + "'s!");
                return false;
            }
        }

        // Check sub-blocks
        for (int x = 0; x < 9; x += 3) {
            for (int y = 0; y < 9; y += 3) {
                if (!isSubBlockValid(x, y)) {
                    char invalidChar = findInvalidCharacterInSubBlock(x, y);
                    System.out.println("Block at (" + x + ", " + y + ") has multiple " + invalidChar + "'s!");
                    return false;
                }
            }
        }

        return true;
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            // Print the top border of each row
            if (i % 3 == 0) {
                System.out.println("#####################################");
            } else {
                System.out.println("#---+---+---#---+---+---#---+---+---#");
            }
    
            for (int j = 0; j < 9; j++) {
                // Print the left border of each cell
                if (j % 3 == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print("| ");
                }
    
                // Print the cell value (space for empty cells)
                System.out.print(grid[i][j] + " ");
            }
    
            // Print the right border of the last cell in each row
            System.out.println("#");
    
            // Print the bottom border of the Sudoku grid
            if (i == 8) {
                System.out.println("#####################################");
            }
        }
    }

private String generateHeaderLine(int rowLength) {
    StringBuilder headerLine = new StringBuilder("#");
    for (int i = 0; i < rowLength - 2; i++) {
        headerLine.append(" ");
    }
    headerLine.append("#");
    return headerLine.toString();
}
    
    private boolean isValidIndex(int i, int j) {
        return i >= 0 && i < 9 && j >= 0 && j < 9;
    }

    private boolean isValidCharacter(char c) {
        return c == ' ' || (c >= '1' && c <= '9');
    }

    private boolean isRowValid(int row) {
        boolean[] seen = new boolean[10];
        for (int j = 0; j < 9; j++) {
            char c = grid[row][j];
            if (c != ' ') {
                int num = c - '0';
                if (seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        return true;
    }

    private boolean isColumnValid(int col) {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 9; i++) {
            char c = grid[i][col];
            if (c != ' ') {
                int num = c - '0';
                if (seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        return true;
    }

    private boolean isSubBlockValid(int x, int y) {
        boolean[] seen = new boolean[10];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                char c = grid[i][j];
                if (c != ' ') {
                    int num = c - '0';
                    if (seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
        }
        return true;
    }

    private char findInvalidCharacterInRow(int row) {
        boolean[] seen = new boolean[10];
        for (int j = 0; j < 9; j++) {
            char c = grid[row][j];
            if (c != ' ') {
                int num = c - '0';
                if (seen[num]) {
                    return c;
                }
                seen[num] = true;
            }
        }
        return ' '; // Should never happen
    }

    private char findInvalidCharacterInColumn(int col) {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 9; i++) {
            char c = grid[i][col];
            if (c != ' ') {
                int num = c - '0';
                if (seen[num]) {
                    return c;
                }
                seen[num] = true;
            }
        }
        return ' '; // Should never happen
    }

    private char findInvalidCharacterInSubBlock(int x, int y) {
        boolean[] seen = new boolean[10];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                char c = grid[i][j];
                if (c != ' ') {
                    int num = c - '0';
                    if (seen[num]) {
                        return c;
                    }
                    seen[num] = true;
                }
            }
        }
        return ' '; // Should never happen
    }
}
