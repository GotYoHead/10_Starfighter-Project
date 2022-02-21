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
			URL url = getClass().getResource("bombship.png");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			//feel free to do something here
		}
		bomb = new Bomb(getX() + getWidth()/2,getY()+getHeight()/2,20,20,3);
	}

	public void move(String direction){
		if (direction.equals("RIGHT")){
			setX(getX()+1);
			if(getX() > StarFighter.WIDTH){
				setX(0);
			}
		}
	}

	public void draw(Graphics w){
		w.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}


	public void shoot(Graphics w)
	{
		long lastExplo = bomb.getLastExplosion();
		if(lastExplo != 0 && System.currentTimeMillis()-lastExplo > 2000)
			bomb = new Bomb(getX() + getWidth()/2,getY()+getHeight()/2,20,20,3);
		bomb.draw(w);
		bomb.move("DOWN");
	}
}
