package Main;


import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{

   private static char last = ' ';							
   private static stopWatchX sw = new stopWatchX(250); 	
   private static stopWatchX timer = new stopWatchX(85); 	
   private static int i = 1;								
	
   public static void processKey(char key){
   	/*Case: if player would like to exit the game*/
      switch(key){
         case '%':								
            if(key == ' ')				
               return;
            if(key == last)
               if(sw.isTimeUp() == false)			
                  return;
            last = key;
            sw.resetWatch();
         
            System.exit(0);
            break;
      
      /*Case: optional mouse coordinations*/
         case 'm':
            if(key == ' ')				
               return;
            if(key == last)
               if(sw.isTimeUp() == false)			
                  return;
            last = key;
            sw.resetWatch();
         
            Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
            break;
      /*Case: if player would like to move up*/
         case 'w':
            Main.trigger = "";
            Main.TextBox.setTag("blank");
            if (timer.isTimeUp()){
               Main.LPosition.setCoords(Main.CharacterSprite.getCoords().getX(), Main.CharacterSprite.getCoords().getY());
               Main.CharacterSprite.getCoords().adjustY(-16);
               Main.CharacterSprite.setTag("u"+i); 
               i++;
            
               if (i >= 5){
                  i = 1;
               }
               timer.resetWatch();
            }
         
            break;
      /*Case: if player would like to move left*/
         case 'a':
            Main.trigger = ""; 										
            Main.TextBox.setTag("blank");						
            if (timer.isTimeUp()){									
               Main.LPosition.setCoords(Main.CharacterSprite.getCoords().getX(), Main.CharacterSprite.getCoords().getY());
               Main.CharacterSprite.getCoords().adjustX(-16);
               Main.CharacterSprite.setTag("l"+i);
               i++;
               if (i >= 5){
                  i = 1;
               }
               timer.resetWatch();
            }
            break;
      	/*Case: if player would like to move down*/
         case 's':
            Main.trigger = "";
            Main.TextBox.setTag("blank");
            if (timer.isTimeUp()){
               Main.LPosition.setCoords(Main.CharacterSprite.getCoords().getX(), Main.CharacterSprite.getCoords().getY());
               Main.CharacterSprite.getCoords().adjustY(16);
               Main.CharacterSprite.setTag("d"+i);
               i++;
               if (i >= 5){
                  i = 1;
               }
               timer.resetWatch();
            }
            break;
      /*Case: if player would like to move right*/
         case 'd':
            Main.trigger = "";
            Main.TextBox.setTag("blank");
            if (timer.isTimeUp()){
               Main.LPosition.setCoords(Main.CharacterSprite.getCoords().getX(), Main.CharacterSprite.getCoords().getY());
               Main.CharacterSprite.getCoords().adjustX(16);
               Main.CharacterSprite.setTag("r"+i);
               i++;
               if (i >= 5){
                  i = 1;
               }
               timer.resetWatch();
            }
            break;
      	/*Case: if player would like to interact with a close by subject*/
         case '$':								
         
            if(key == ' ')				
               return;
            if(key == last)
               if(sw.isTimeUp() == false)			
                  return;
            last = key;
            sw.resetWatch();
         /*Checks for which subject is in collision with player when spacebar is activated and triggers the text box and a message for user*/
            if(Main.checkCollision(Main.playerBox, Main.house)){
               Main.trigger = "Welcome Home!";
               Main.TextBox.setTag("textBox");
            }
            if(Main.checkCollision(Main.playerBox, Main.goat)){
               Main.trigger = "BA BA BA, Its a goat!";
               Main.TextBox.setTag("textBox");
            }
            if(Main.checkCollision(Main.playerBox, Main.chicken)){
               Main.trigger = "Cluck Cluck, Its a chicken!";
               Main.TextBox.setTag("textBox"); 
            }
            break;
      }
   }
}