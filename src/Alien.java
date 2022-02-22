//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{
	private int speed;
	protected Image image;

	public Alien()
	{
		this(0,0,45,45,4);
	}

	public Alien(int x, int y)
	{
		this(x,y,45,45,4);
	}

	public Alien(int x, int y, int s)
	{
		this(x,y,45,45,s);
	}

	public Alien(int x, int y, int w, int h, int s)
	{
		super(x, y, w,h);
		speed=s;
		try
		{
			URL url = getClass().getResource("goomba.png");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here
		}
	}

	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

   public void move(String direction)
	{
	   if(direction.equals("DOWN"))
		{
		   // If I go off the right OR off the left side of the screen
		   if(getX() > StarFighter.WIDTH  ) // YOU MUST ADD LEFT HERE
		   {
			   speed *= -1;
			   // The Y moves down 25 pixels - use SetY(number)  
			   setY(getY()+25);
			   setX(getX()+speed);
		   }
		   if(getX() < 0) 
		   {
			   speed *= -1;
			   // The Y moves down 25 pixels - use SetY(number)  
			   setY(getY()+25);
			   setX(getX()+speed);

		   }
		   // Move the X position - use SetX(_____)
		   setX(getX() + speed);
			
		}	
	   if(direction.equals("LEFT"))
	   {

		   if(getX() > StarFighter.WIDTH  ) // YOU MUST ADD LEFT HERE
		   {
			   speed *= -1;
		   }
		   if(getX() < 0) 
		   {
			   speed *= -1;

		   }
		   setX(getX()+speed);

	   }
	   if(direction.equals("RIGHT"))
	   {

		   if(getX() > StarFighter.WIDTH  ) // YOU MUST ADD LEFT HERE
		   {
			   speed *= -1;
		   }
		   if(getX() < 0) 
		   {
			   speed *= -1;

		   }
		   setX(getX()-speed);

	   }
	}
   

	public void draw( Graphics window )
	{
   	window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return "";
	}
	
	public boolean beenShot(Ammo am) {


		if ((am.getX() >= this.getX() && am.getX() <= this.getX()+60) ||
				(am.getX()+10 >= this.getX() && am.getX()+10 <= this.getX()+60))
			if ((am.getY() >= this.getY() && am.getY() <= this.getY()+60) ||
					(am.getY()+10 >= this.getY() && am.getY()+10 <= this.getY()+60))
			{
				return true;

			}
		return false;
	}
}
