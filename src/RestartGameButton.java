public class RestartGameButton extends Button{

  // Constructor
  public RestartGameButton(int x, int y) {
    super("Restart Game", x, y);
  }

@Override
public void mousePressed() {
    if (isMouseOver())
  ((TreasureHunt) processing).initGame();
}

}
