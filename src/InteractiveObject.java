import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class models a clickable interactive object used in cs300 Spring 2022 p05 Treasure Hunt
 * application.
 *
 * @author tanaynagar
 * @author gauravchopra
 * @version 1.0
 */
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

  /**
   * Creates a new interactive object with no next clue (nextClue == null) and sets its image,
   * name, x and y positions, its message, and activation status. When created, an interactive
   * object must be active, and not clicked yet. [Details on how to set the image of this
   * interactive object are provided in the write-up.]
   *
   * @param name    name to be assigned to this interactive object. We assume that name is VALID
   *                (not null and not equal to an empty string)
   * @param x       x-position to be assigned to this interactive object
   * @param y       y-position to be assigned to this interactive object
   * @param message message to be displayed on the console each time this interactive object is
   *                clicked. We assume that message is VALID (not null and not equal to an empty
   *                string)
   */
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
    //this.isActive = true;
    this.activate();
    //Setting if the object was clicked to false
    this.wasClicked = false; // though not needed since false is the default boolean value
    // Setting nextClue as null
    this.nextClue = null;
  }

  //Constructor 2

  /**
   * Creates a new interactive object with a next clue (nextClue != null) to be activated when
   * this interactive object is clicked for the first time. This constructor sets the image of the
   * newly created interactive object, its name, x and y positions, its message, and activation
   * status. When created, an interactive object must be active, and not clicked yet . Also, this
   * constructor deactivates the next clue of this interactive object.
   *
   * @param name     name to be assigned to this interactive object. We assume that name is VALID
   *                 (not null and not equal to an empty string)
   * @param x        x-position to be assigned to this interactive object
   * @param y        y-position to be assigned to this interactive object
   * @param message  message to be displayed on the console each time this interactive object is
   *                 clicked. We assume that message is VALID (not null and not equal to an empty
   *                 string)
   * @param nextClue a reference to a non-null InteractiveObject which represents the next clue
   *                 associated to this interactive object.
   */
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

  /**
   * Sets the next clue associated to this interactive object
   *
   * @param nextClue the next clue to be assigned to this interactive object
   */
  public void setNextClue(InteractiveObject nextClue) {
    // Sets the next clue associated to this interactive object
    this.nextClue = nextClue;
  }

  /**
   * Sets the PApplet object of the treasure hunt application where all the interactive objects
   * will be drawn. Note that a graphic application has ONLY one display window of type PApplet
   * where all graphic objects interact. In p05, the TreasureHunt class is of type PApplet.
   *
   * @param processing represents the reference to the TreasureHunt PApplet object where all the
   *                   interactive objects will be drawn.
   */
  public static void setProcessing(TreasureHunt processing) {
    //processing is static so referring to it using the reference of the class
    InteractiveObject.processing = processing;
  }

  // Implementing getter methods

  /**
   * Gets the x-position of this interactive object
   *
   * @return the x-position of this interactive object
   */
  public int getX() {
    // Gets the x-position of this interactive object
    return this.x;
  }

  /**
   * Gets the y-position of this interactive object
   *
   * @return the y-position of this interactive object
   */
  public int getY() {
    // Gets the y-position of this interactive object
    return this.y;
  }

  /**
   * Checks whether this interactive object is active. This should be a getter of the isActive
   * data field.
   *
   * @return true if this interactive object is active and false otherwise.
   */
  public boolean isActive() {
    return this.isActive;
  }


  /**
   * Gets the message of this interactive object.
   *
   * @return the message to be displayed each time this interactive object is clicked.
   */
  public String message() {
    return this.message;
  }

  //Implementing overridden method

  /**
   * Draws this interactive object (meaning drawing its image) to the display window at positions
   * x and y.
   */
  @Override public void draw() {
    /*
    Draws this interactive object (meaning drawing its image) to the display window at
    positions x and y.
     */
    processing.image(this.image, getX(), getY());
  }


  /**
   * Checks whether the mouse is over the image of this interactive object
   *
   * @return true if the mouse is over the image of this interactive object, and false otherwise
   */
  @Override public boolean isMouseOver() {
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
    isInXRange = ((mouseXPos >= xStart) && (mouseXPos <= xEnd));
    //Checking if the mouse is in y-range
    isInYRange = ((mouseYPos >= yStart) && (mouseYPos <= yEnd));
    //If mouse is in both the x and y range, then returning true
    return (isInXRange && isInYRange);
  }

  /**
   * This method operates each time the mouse is pressed. This interactive object responds to the
   * mouse clicks as follows. If the mouse is clicked (meaning the mouse is over it) two
   * operations will be performed as follows: (1) The message of this interactive object must be
   * displayed to the console. (2) If this interactive object has a next clue and was not clicked,
   * its next clue will be activated and its wasClicked setting will be updated.
   */
  @Override public void mousePressed() {
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

  /**
   * This method operates each time the mouse is released. It implements a default behavior for an
   * interactive object which is DO NOTHING (meaning that the InteractiveObject.mouseReleased has
   * a blank implementation).
   */
  @Override public void mouseReleased() {
    //DO NOTHING here
  }

  // Implementing other methods

  /**
   * Activates this interactive object, meaning setting its isActive data field to true.
   */
  public void activate() {
    // Activates this interactive object, meaning setting its isActive data field to true.
    this.isActive = true;
  }

  /**
   * Activates the next clue of this interactive object and adds it to the treasure hunt
   * application
   *
   * @throws NoSuchElementException with a descriptive error message if the nextClue of this
   *                                interactive object is null
   */
  protected void activateNextClue() throws NoSuchElementException {
    //Checking if nextClue of the interactive Object is null
    if (this.nextClue == null) {
      throw new NoSuchElementException(
          "The next clue of the " + NAME + " interactive object " + "does not exist");
    }
    // Activating next clue of the interactive application
    this.nextClue.isActive = true;
    //Adding the nextClue to the treasure hunt application
    processing.add(nextClue);
  }

  /**
   * Deactivates this interactive object, meaning setting its isActive data field to false.
   */
  public void deactivate() {
    this.isActive = false;
  }


  /**
   * Checks whether the name of this interactive object equals to the name passed as input
   * parameter. We consider a case-sensitive comparison.
   *
   * @param name name to compare to
   * @return true if the name of this interactive object equals the provided name, false
   * otherwise.
   */
  public boolean hasName(java.lang.String name) {
    return (this.NAME.equals(name));
  }

  /**
   * Moves the current x and y positions of this interactive object with dx and dy, respectively
   *
   * @param dx move to be added to the x position of this interactive object
   * @param dy move to be added to the y position of this interactive object
   */
  public void move(int dx, int dy) {
    //Changing x and y positions
    this.x = this.x + dx;
    this.y = this.y + dy;
  }
}
