import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class bombShip extends Alien {

	public Bomb bomb;
	protected Image image;

	public bombShip(int x, int y, int s)
	{
		super(x, y, 50,50,s);
		
		try
		{
			URL url = getClass().getResource("bombShip.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here
		}
	}
	
	public void shoot(Graphics w)
	{
		if(bomb.getY()>StarFighter.HEIGHT)
			bomb = new Bomb(getX() + getWidth()/2,getY()+getHeight()/2,20,20,3);
		bomb.draw(w);
		bomb.move("DOWN");
	}
}
