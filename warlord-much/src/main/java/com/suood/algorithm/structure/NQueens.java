//package com.suood.algorithm.structure;
//
//public class NQueens {
//  int hv = 0;
//  private boolean[][] chessboard;
//
//  /**
//   * N-Queens 在小于4的时候无解
//   *
//   * @param p
//   * @throws Exception
//   */
//  public NQueens(int p) throws Exception {
//    if (p < 4) {
//      throw new Exception("invalid number ");
//    }
//    chessboard = new boolean[p][p];
//    int hv = p;
//    for (int i = 0; i < p; i++) {
//      for (int j = 0; j < p; j++) {
//        chessboard[i][j] = false;
//      }
//    }
//  }
//  public void getS(){
//    for (int i = 0; i < ; i++) {
//
//    }
//  }
//  private void solutionPrint(boolean[][] chessboard, int p) {
//    for (int i = 0; i < p; i++) {
//      for (int j = 0; j < p; j++) {
//        if (chessboard[i][p]) {
//          System.out.print("Q");
//        } else {
//          System.out.print("X");
//        }
//      }
//      System.out.println();
//    }
//  }
//
//  /**
//   * @param h 横向迭代次数
//   * @param v 和纵向迭代次数
//   * @return
//   */
//  private boolean isPlaceQueenValid(int h, int v) {
//
//  }
//
//}
