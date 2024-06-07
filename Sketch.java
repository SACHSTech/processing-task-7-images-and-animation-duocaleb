import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	

  PImage imgBackground;
  PImage imgCircle;
  boolean boolCnBounceVert = false;
  boolean boolCnBounceHorz = false;
  boolean boolDoneVert = false;

  // All variables from here on are editable!
  int[] intCircleSize = {30,30};
  int intScreenSize = 800;
  double[] dblCircleLoc = {intScreenSize/2-intCircleSize[0]/2,0};
  double[] dblCircleVel = {30,15};
  double dblGravity = 0.2;
  double dblBouncines = 0.5;
  double dblFloorFriction = 0.99;
  
  public void settings() {
	// put your size call here
    size(intScreenSize, intScreenSize);
  }
  public void setup() {
    background(210, 255, 173);
    imgCircle = loadImage("pngimg.com - circle_PNG50.png");
    imgCircle.resize(imgCircle.width/imgCircle.width*intCircleSize[0],imgCircle.height/imgCircle.height*intCircleSize[1]);
    imgBackground = loadImage("pexels-pixabay-531880.png");
    imgBackground.resize(imgBackground.width/imgBackground.width*intScreenSize,imgBackground.height/imgBackground.height*intScreenSize);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    image(imgBackground, 0, 0);
    // Falling
    if(dblCircleLoc[1] < intScreenSize-imgCircle.height && boolDoneVert == false){
      dblCircleVel[1] += dblGravity;
      boolCnBounceVert = true;
    }
    // Verticle bouncing top
    if(dblCircleLoc[1] < 0 && boolCnBounceVert){
      boolCnBounceVert = false;
      dblCircleVel[1] *= -dblBouncines;
    }
    // Verticle bouncing bottom
    if(dblCircleLoc[1] >= intScreenSize-imgCircle.height && boolCnBounceVert){
      boolCnBounceVert = false;
      dblCircleVel[1] *= -dblBouncines;
      if(dblCircleVel[1] < 1.1 && dblCircleVel[1] > -1.1){
        dblCircleVel[1] = 0;
        boolDoneVert = true;
      }
    }
    // Horizontal bouncing
    if((dblCircleLoc[0] >= intScreenSize-imgCircle.width || dblCircleLoc[0] <= 0) && boolCnBounceHorz){
      dblCircleVel[0] *= -dblBouncines;
      boolCnBounceHorz = false;
    }
    else{
      boolCnBounceHorz = true;
    }

    // Friction
    if(dblCircleLoc[1] >= intScreenSize-imgCircle.height){
      dblCircleVel[0] *= dblFloorFriction;
      if(dblCircleVel[0] < 0.17 && dblCircleVel[0] > -0.17){
        dblCircleVel[0] = 0;
      }
    }

    // Adding
    dblCircleLoc[0] += dblCircleVel[0];
    dblCircleLoc[1] += dblCircleVel[1];

    // Putting on screen
    image(imgCircle,(int)dblCircleLoc[0],(int)dblCircleLoc[1]);
  }  
}