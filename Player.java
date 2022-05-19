package com.FirstJavaProject;




import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player extends Entity {

  public GamePanel gp;
  public KeyHandler keyH;
  
public final int screenX;
public final int screenY;
public int hasKey = 0;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;
    
    screenX = gp.screenWidth/2 - (gp.tileSize/2);
    screenY = gp.screenHeight/2 - (gp.tileSize/2);
    
    solidArea = new Rectangle();
    solidArea.x = 8;
    solidArea.y = 8 ;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    solidArea.width = 32;
    solidArea.height = 32;
    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = gp.tileSize * 23;
    worldY = gp.tileSize * 21;
    speed = 4;
    speed = gp.worldWidth/600;
    direction = "down";
  }

  public void getPlayerImage() { 
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("up1.png"));

      up2 = ImageIO.read(getClass().getResourceAsStream("up2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("down1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("down2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("left1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("left2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("right1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("right2.png"));

    } catch (IOException e) {
    	System.out.println("not working");
    	//e.printStackTrace();
    }
  }

  public void update() {
    if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
      if (keyH.upPressed == true) {
        direction = "up";

      } else if (keyH.downPressed == true) {
        direction = "down";
      
      } else if (keyH.rightPressed == true) {
        direction = "right";
   
      }

      else if (keyH.leftPressed == true) {
        direction = "left";
       
      }
      //CHECK TILE COLLISION
collisionOn = false;
gp.cChecker.checkTile(this);

int objIndex = gp.cChecker.checkObject(this, true);
      pickUpObject(objIndex);
//IF COLLLISION IS FLASE PLAYER CAN MOVE
if(collisionOn == false) {
	switch(direction) {
	case "up" :
		worldY -= speed;
		break;
	case "down": 
		worldY += speed;
		break;
	case"left":
		worldX -= speed;
		break;
	case "right":
	       worldX += speed;
		break;
	}
}
      spriteCounter++;
      if (spriteCounter > 12) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 1;
        }
        spriteCounter = 0;
      }
    }

  }
  public void pickUpObject(int i){
    if(i != 999){
      String objectName = gp.obj[i].name;

      switch(objectName){
        case "Key": 
          gp.playSE(1);
          hasKey++;
          gp.obj[i] = null;
          gp.ui.showMessage("You got a key!");
          break;
           case "Door": 
            gp.playSE(3);
          if(hasKey > 0){
             gp.obj[i] = null;
            hasKey--;
            gp.ui.showMessage("You opened the door!");
          } else {
        	  gp.ui.showMessage("You need a key!");
          }
       break;
           case "Chest": 
        	  gp.ui.gameFinished = true;
        	  gp.stopMusic();
        	  gp.playSE(4);
        	  
       break;
        case "Boots":
          gp.playSE(2);
          speed+=1;
           gp.obj[i] = null;
           gp.ui.showMessage("Speed up!");
          break;
      }
    }
  }

  public void draw(Graphics2D g2D) {
    // g2D.setColor(Color.white);

    

    BufferedImage image = null;

    switch (direction) {
      case "up":
        if (spriteNum == 1) {
          image = up1;
          
        }
        if (spriteNum == 2) {
          image = up2;
        }

        break;

      case "down":
        if (spriteNum == 1) {
          image = down1;

        }
        if (spriteNum == 2) {
          image = down2;
        }
        break;

      case "left":
        if (spriteNum == 1) {
          image = left1;

        }
        if (spriteNum == 2) {
          image = left2;
        }
        break;

      case "right":
        if (spriteNum == 1) {
          image = right1;

        }
        if (spriteNum == 2) {
          image = right2;
        }
        break;
    }

    g2D.drawImage(image, screenX, screenY , gp.tileSize, gp.tileSize, null);
   // g2D.fillRect(screenX, screenY, (int)solidArea.getWidth(),(int) solidArea.getHeight());
  }

}