package leetcode.DP.minimax;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author manoji on 2/8/20.
 */
public class TicTacToe {

  private int[][] generateBoard() {
    return new int[1][1];
  }

  private void draw() {
    JFrame frame = new JFrame();
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void main(String args[]) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new TicTacToe().draw();
      }
    });

  }

}
