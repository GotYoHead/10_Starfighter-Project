import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Heart extends MovingThing  {

	protected Image image;
	
	
	
	public Heart(int x, int y, int w, int h)
	{
		super(x, y, w,h);
		try
		{
			URL url = getClass().getResource("heart.png");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here
			System.out.println(" NO <3 PICTURE");
		}
	}


	public void setSpeed(int s) {
		// TODO Auto-generated method stub
		
	}



	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}



	public void move(String direction) {
		// TODO Auto-generated method stub
		
	}



	public void draw(Graphics window) {
	   	window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);		
	}


}
