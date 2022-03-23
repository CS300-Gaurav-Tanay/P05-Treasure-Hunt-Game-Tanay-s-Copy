import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InteractiveObject implements Clickable {
  // TODO: This class should not have a main method

  // reference to the PApplet where this object will be drawn
  protected static TreasureHunt processing;
  private final String NAME; // name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this interactive object in the screen
  private int y; // y-position of this interactive object in the screen
  // Note that the position (x,y) of the interactive object is the position
  // of the upper-left corner of its image (and NOT the center of the image).
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue; // Object to be activated when this object is
  // clicked the first time, if any

  //Constructor 1
  public InteractiveObject(String name, int x, int y, String message) {
    //TODO: Things to set in this constructor
    /*
    * set no next clue
    * image
    * name
    * x and y position
    * message
    * activation status (should be active and not clicked yet)
    *
     */

    //Setting image
    this.image = processing.loadImage("images" + File.separator + name + ".png");
    //setting name of the Object
    this.NAME = name;
    //Setting x and y positions
    this.x = x;
    this.y = y;
    //Setting the message
    this.message = message;
    //Setting activation status
    this.isActive = true;
    //Setting if the object was clicked to false
    this.wasClicked = false; // though not needed since false is the default boolean value
    // Setting nextClue as null
    this.nextClue = null;
  }

  //Constructor 2
  public InteractiveObject(String name, int x, int y, String message,
      InteractiveObject nextClue) {
    this(name, x, y, message);
    //Setting nextClue to the parameter nextClue
    this.nextClue = nextClue;
    //Deactivating nextClue
    this.nextClue.deactivate();
    //todo: try using deactivate() here
  }

  // Implementing setter methods

  public void setNextClue(InteractiveObject nextClue) {
    // Sets the next clue associated to this interactive object
    this.nextClue = nextClue;
  }

  public static void setProcessing(TreasureHunt processing) {
    //processing is static so referring to it using the reference of the class
    InteractiveObject.processing = processing;
  }

  // Implementing getter methods

  public int getX() {
    // Gets the x-position of this interactive object
    return this.x;
  }

  public int getY() {
    // Gets the y-position of this interactive object
    return this.y;
  }

  public boolean isActive() {
    return this.isActive;
  }

  public String message() {
    return this.message;
  }

  //Implementing overridden method

  @Override
  public void draw() {
    /*
    Draws this interactive object (meaning drawing its image) to the display window at
    positions x and y.
     */
    processing.image(this.image, getX(), getY());
  }

  @Override
  public boolean isMouseOver() {
    /*
    Checks whether the mouse is over the image of this interactive object
     */

    //declaring local variables
    int xStart = getX();
    int yStart = getY();
    int xEnd = xStart + this.image.width;
    int yEnd = yStart + this.image.height;
    int mouseXPos = processing.mouseX;
    int mouseYPos = processing.mouseY;
    boolean isInXRange;
    boolean isInYRange;


    //Checking if the mouse is x-range
    isInXRange = ((mouseXPos >= xStart)&&(mouseXPos <= xEnd));
    //Checking if the mouse is in y-range
    isInYRange = ((mouseYPos >= yStart)&&(mouseYPos <= yEnd));
    //If mouse is in both the x and y range, then returning true
    return (isInXRange && isInYRange);
  }

  @Override
  public void mousePressed() {
    //if condition for if the mouse is clicked (i.e. if the mouse is pressed and the mouse is
    // over it)
    if (isMouseOver()) {
      //When mouse is clicked

      //Displaying message of the interactive object to the console
      System.out.println(this.message());
      // if condition for if the interactive object has a next clue and was not clicked
      if ((this.nextClue != null) && !wasClicked) {
        //Activating next clue
        this.activateNextClue();
        //Updating wasClicked setting to true
        this.wasClicked = true;
      }
    }
  }

  @Override
  public void mouseReleased() {
    //DO NOTHING here
  }

  // Implementing other methods

  public void activate() {
    // Activates this interactive object, meaning setting its isActive data field to true.
    this.isActive = true;
  }

  protected void activateNextClue() throws NoSuchElementException {
    //Checking if nextClue of the interactive Object is null
    if (this.nextClue == null) {
      throw new NoSuchElementException("The next clue of the " + NAME + " interactive object "
          + "does not exist");
    }
    // Activating next clue of the interactive application
    this.nextClue.isActive = true;
    //Adding the nextClue to the treasure hunt application
    processing.add(nextClue);
  }

  public void deactivate() {
    this.isActive = false;
  }

  public boolean hasName(java.lang.String name) {
    return (this.NAME.equals(name));
  }

  public void move(int dx, int dy) {
    //Changing x and y positions
    this.x = this.x + dx;
    this.y = this.y + dy;
  }
}
