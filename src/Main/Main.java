package Main;
/*
 * Qasim Ali
 * CSC 130
 * Fall 2023
 * Professor Phillips
 * 
 * */
import java.awt.Color;
import Data.Vector2D;
import Data.boundingBox;
import Data.spriteInfo;
import logic.Control;
import java.util.*;

public class Main{
    // Fields (Static) below...
   public static Vector2D currentVec = new Vector2D(99, 295);
   public static Vector2D LVec = new Vector2D(0, 0);
   public static String trigger = "";
   public static int frameCounter = 1;
   public static String textTrigger = "blank";
   public static ArrayList<boundingBox> box = new ArrayList<>(); 
   public static ArrayList<spriteInfo> sprites = new ArrayList<>();
   public static boundingBox playerBox;
   public static boundingBox house = new boundingBox(540, 680, 460, 485);
   public static boundingBox goat = new boundingBox(1000, 1100, 550, 575);
   public static boundingBox chicken = new boundingBox(100, 150, 297, 330);
   public static spriteInfo CharacterSprite = new spriteInfo(currentVec, "r"+frameCounter);
   public static spriteInfo TextBox = new spriteInfo(new Vector2D(410, 500), textTrigger);
   public static spriteInfo LPosition = new spriteInfo (LVec, CharacterSprite.getTag());
	
	// End Static fields...
	
   public static void main(String[] args) {
      Control ctrl = new Control();				// Do NOT remove!
      ctrl.gameLoop();	                        // Do NOT remove!
      
   }
	
   public static void start() {
       /*Entire game frame boundary*/
      box.add(new boundingBox(-128, 1408, -128, 75));
      box.add(new boundingBox(-128, 1408, 740, 848));	
      box.add(new boundingBox(-128, -40, -128, 848));	
      box.add(new boundingBox(1250, 1408, -128, 848));
      box.add(new boundingBox(540, 690, 285, 460));       // House Boundary
      box.add(new boundingBox(990, 1080, 210, 280));      // Hay Boundary
      box.add(new boundingBox(1000, 1080, 450, 550));     // goat Boundary
      box.add(new boundingBox(250, 390, 440, 490));       //Bush Boundary
      box.add(new boundingBox(100, 165, 240, 300));       //Chicken Boundary
   	/*Populating the array based on which image about appear first*/
      sprites.add(new spriteInfo(new Vector2D(0, 0), "background"));
      sprites.add(new spriteInfo(new Vector2D(540, 260), "house"));
      sprites.add(new spriteInfo(new Vector2D(1000, 200), "hay"));
      sprites.add(new spriteInfo(new Vector2D(1000, 400), "goat"));
      sprites.add(new spriteInfo(new Vector2D(250, 400), "bush"));
      sprites.add(new spriteInfo(new Vector2D(100, 200), "chicken"));
      sprites.add(CharacterSprite);
      sprites.add(TextBox);
   	
     
   }
   public static void update(Control ctrl) {
      /*Boundary box in which the player moves and it updates as the character walks around.*/
      playerBox = new boundingBox(CharacterSprite, 20, 55, 108, 120);
      /*Checks for collision and bounces the character where it was based on LastPosition*/
      for (int i = 0; i < box.size(); i++)
         if (checkCollision(playerBox, box.get(i)))
            bouncePlayer();
   	/*Prints the character sprite based on movement*/
      for (int i = 0; i < sprites.size(); i++)
         ctrl.addSpriteToFrontBuffer(sprites.get(i).getCoords().getX(), sprites.get(i).getCoords().getY(), sprites.get(i).getTag());
   	
      ctrl.drawString(450, 600, trigger, Color.BLACK); /*Display the messages in a text box*/
   }
   /*checks for collision by comparing the boundary boxes, comparing code from canvas class modules*/
   public static boolean checkCollision(boundingBox box1, boundingBox box2){
      if (((box1.getX1() > box2.getX2()) || (box1.getX2() < box2.getX1()) || (box1.getY1() > box2.getY2()) || (box1.getY2() < box2.getY1())))
         return false;
      else 
         return true;
   }
   /*Bounces the player back to its last position based on which direction it was coming from. if collision detected*/
   private static void bouncePlayer() {
      if ((CharacterSprite.getCoords().getY() - LPosition.getCoords().getY()) != 0){
         if ((CharacterSprite.getCoords().getY() - LPosition.getCoords().getY()) > 0) {	// If moving up to down.
            CharacterSprite.getCoords().adjustY(-16);
         }
         if ((CharacterSprite.getCoords().getY() - LPosition.getCoords().getY()) < 0) {	// If moving down to up.
            CharacterSprite.getCoords().adjustY(+16);
         }
      }
      if ((CharacterSprite.getCoords().getX() - LPosition.getCoords().getX()) != 0){
         if ((CharacterSprite.getCoords().getX() - LPosition.getCoords().getX()) > 0)	// If moving from right to left.
            CharacterSprite.getCoords().adjustX(-16);								
         if ((CharacterSprite.getCoords().getX() - LPosition.getCoords().getX()) < 0)  	// If moving left to right.
            CharacterSprite.getCoords().adjustX(+16);
      }
   		
   }
}