/**
 * This class models a draggable object. A draggable object is a clickable interactive object
 * which can follow the mouse moves when being dragged.
 */
public class DraggableObject extends InteractiveObject {
  // Declaring data fields
  //indicates whether this object is being dragged or not
  private boolean isDragging;
  //old x-position of the mouse
  private int oldMouseX;
  //old y-position of the mouse
  private int oldMouseY;

  // Constructor 1

  /**
   * Creates a new Draggable object with a given name, x and y position, and "Drag Me!" as a
   * default message. When created a new Draggable object is NOT dragging
   *
   * @param name name to be assigned to this draggable object
   * @param x    x-position of this draggable object in the display window
   * @param y    y-position of this draggable object in the display window
   */
  public DraggableObject(String name, int x, int y) {
    super(name, x, y, "Drag Me!");
    //Making the new Draggable Object not dragging
    this.isDragging = false;
  }

  // Constructor 2

  /**
   * Creates a new Draggable object with a given name, x and y position, and a specific message.
   * When created a new Draggable object is NOT dragging
   *
   * @param name    name to be assigned to this draggable object
   * @param x       x-position of this draggable object in the display window
   * @param y       y-position of this draggable object in the display window
   * @param message message to be displayed when this draggable object is clicked. We assume that
   *                message is VALID (meaning it is NOT null and NOT an empty string).
   */
  public DraggableObject(java.lang.String name, int x, int y, String message) {
    super(name, x, y, message);
    //Making the new Draggable Object not dragging
    this.isDragging = false;
  }

  // Implementing Class Methods

  /**
   * Checks whether this draggable object is being dragged.
   *
   * @return true if this object is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Starts dragging this draggable object and updates the oldMouseX and oldMouseY positions to
   * the current position of the mouse
   */
  public void startDragging() {
    this.isDragging = true;

    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }

  /**
   * Stops dragging this draggable object
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Starts dragging this object when it is clicked (meaning when the mouse is over it).
   */
  @Override public void mousePressed() {
    if (isMouseOver()) {
      startDragging();
    }
  }

  /**
   * Stops dragging this object if this mouse is released
   */
  @Override public void mouseReleased() {
    stopDragging();
  }

  /**
   * Draws this draggable object to the display window. If this object is dragging, this method
   * sets its position to follow the mouse moves. Then, it draws its image to the its current
   * position.
   */
  @Override public void draw() {
    if (isDragging) {
      this.move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      //Updating oldMouseX and oldMouseY to the current position of the mouse
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;

      // try using super and draw() here if that works
    }
    processing.image(image, getX(), getY());
  }
} // class ends


