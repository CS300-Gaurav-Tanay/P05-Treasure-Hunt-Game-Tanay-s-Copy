public class ScreenshotButton extends Button {

  // Constructor
  public ScreenshotButton(int x, int y) {
    super("Take a screenshot", x, y);
  }

  @Override
  public void mousePressed() {
    if (isMouseOver())
    processing.save("screenshot.png");
  }

}
