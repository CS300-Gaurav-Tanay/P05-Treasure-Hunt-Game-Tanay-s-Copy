public class DroppableObject extends DraggableObject {
  // Declaring data fields
  private InteractiveObject target; // target of this droppable object

  // Constructor
  public DroppableObject(String name, int x, int y, String message, InteractiveObject target,
      InteractiveObject nextClue) {
    super(name, x, y, message);
    // Setting the next clue
    this.setNextClue(nextClue);
    // Setting the target
    this.target = target;
  }

  // Implementing the isOver() method
  public boolean isOver(InteractiveObject other) {
    // Declaring data fields
    int x1 = this.getX();
    int y1 = this.getY() + this.image.height;

    int x2 = this.getX() + this.image.width;
    int y2 = this.getY();

    int x3 = other.getX();
    int y3 = other.getY() + other.image.height;

    int x4 = other.getX() + other.image.width;
    int y4 = other.getY();;

    //if condition to check if the rectangles are overlapping
    if (((x1 < x4) && (x3 < x2) && (y1 > y4) && (y3 > y2))) {
      return true;
    }
    else
      return false;
  }

  @Override
  public void mouseReleased() {
    this.stopDragging();

    if (isOver(target) && target.isActive()) {
      // Deactivating both this object and the target
      this.deactivate();
      target.deactivate();
      //Activating the next clue
      this.activateNextClue();
      //Printing the message of this draggable Object to the console
      System.out.println(this.message());
    }
  }
}
