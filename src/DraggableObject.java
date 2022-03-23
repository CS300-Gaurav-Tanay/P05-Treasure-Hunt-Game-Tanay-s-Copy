public class DraggableObject extends InteractiveObject {
  // Declaring data fields
  private boolean isDragging;
  private int oldMouseX;
  private int oldMouseY;

  // Constructor 1
  public DraggableObject(String name, int x, int y) {
    super(name, x, y, "Drag Me!");
    //Making the new Draggable Object not dragging
    this.isDragging = false;
  }

  // Constructor 2
  public DraggableObject(java.lang.String name, int x, int y, String message) {
    super(name, x, y, message);
    //Making the new Draggable Object not dragging
    this.isDragging = false;
  }

  // Implementing Class Methods

  public boolean isDragging() {
    return isDragging;
  }

  public void startDragging() {
    this.isDragging = true;

    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }

  public void stopDragging() {
    this.isDragging = false;
  }

  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      startDragging();
    }
  }

  @Override
  public void mouseReleased() {
    stopDragging();
  }

  @Override
  public void draw() {
    if (isDragging) {
      this.move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      //Updating oldMouseX and oldMouseY to the current position of the mouse
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;

      // try using super and draw() here if that works
    }
    processing.image(image, getX(), getY());
  }
}


