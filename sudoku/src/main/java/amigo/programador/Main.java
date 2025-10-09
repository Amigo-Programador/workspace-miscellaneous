package amigo.programador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    // 64MB memory max.
    List<Integer> line0 = new ArrayList<>(List.of(8, 2, 4, 9, 5, 3, 6, 7, 1));
    List<Integer> line1 = new ArrayList<>(List.of(6, 3, 5, 8, 1, 7, 9, 2, 4));
    List<Integer> line2 = new ArrayList<>(List.of(7, 1, 9, 6, 2, 4, 8, 5, 3));
    List<Integer> line3 = new ArrayList<>(List.of(5, 8, 7, 2, 9, 1, 3, 4, 6));
    List<Integer> line4 = new ArrayList<>(List.of(1, 4, 2, 7, 3, 6, 5, 8, 9));
    List<Integer> line5 = new ArrayList<>(List.of(3, 9, 6, 4, 8, 5, 2, 1, 7));
    List<Integer> line6 = new ArrayList<>(List.of(2, 6, 1, 5, 4, 9, 7, 3, 8));
    List<Integer> line7 = new ArrayList<>(List.of(4, 7, 8, 3, 6, 2, 1, 9, 5));
    List<Integer> line8 = new ArrayList<>(List.of(9, 5, 3, 1, 7, 8, 4, 6, 2));

    List<List<Integer>> sudoku = Arrays.asList(line0, line1, line2, line3, line4, line5, line6, line7, line8);

    boolean isValid = validateSudokuImproved(sudoku);
    System.out.println("Sudoku is " + (isValid ? "valid!" : "wrong!"));
  }


  private static boolean validateSudoku(List<List<Integer>> sudoku) {

    for(int x=0; x<sudoku.size(); x++) {
      boolean[] rowX = new boolean[10]; // default is false
      boolean[] rowY = new boolean[10]; // default is false
      for(int y=0; y<sudoku.get(x).size(); y++) {
        int valueX = sudoku.get(x).get(y);
        int valueY = sudoku.get(y).get(x);

        if(valueX < 1 || valueX > 9) {
          return false;
        }

        if(rowX[valueX] || rowY[valueY]) {
          return false;
        }
        rowX[valueX] = true;
      }
    }

    for (int x=0; x<sudoku.size(); x=x+3) {
      for (int y=0; y<sudoku.get(x).size(); y=y+3) {
        boolean[] block = new boolean[10];
        // i=3; i<6
        // j=6; i<9
        for (int i=x; i<x+3; i++) {
          for (int j=y; j<y+3; j++) {
            int value = sudoku.get(i).get(j);
            if(block[value]) {
              return false;
            }
            block[value] = true;
          }
        }
      }
    }

    return true;
  }

  private static boolean validateSudokuImproved(List<List<Integer>> sudoku) {
    boolean[][] row = new boolean[9][10];
    boolean[][] column = new boolean[9][10];
    boolean[][] block = new boolean[9][10];

    for(int x=0; x<sudoku.size(); x++) {
      for(int y=0; y<sudoku.get(x).size(); y++) {
        int value = sudoku.get(x).get(y);
        int blockIndex = (x/3)*3 + y/3;

        if(row[x][value] || column[y][value] || block[blockIndex][value]) {
          return false;
        }

        row[x][value] = true;
        column[y][value] = true;
        block[blockIndex][value] = true;
      }
    }
    return true;
  }



}