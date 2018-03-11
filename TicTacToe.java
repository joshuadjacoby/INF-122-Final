
public class TicTacToe extends GridGame {
  public void initializeButtons() {
    for (int i = 0; i <= 8; i++) {
      environment.gameBoard[i] = new JButton();
      environment.gameBoard[i].setText("");
      environment.gameBoard[i].addActionListener(new ticTacToeListener());

      add(gameBoard[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
      //because this whole class is a JPanel already
    }
  }

  public void processLogic() {
  }
}
