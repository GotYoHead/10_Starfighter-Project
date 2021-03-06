import java.awt.*;
import java.net.URL;
import java.time.Instant;

import javax.imageio.ImageIO;

public class Explosion extends MovingThing {

	protected Image image;

	public Explosion(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		try
		{
			URL url = getClass().getResource("explosion.png");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here
			System.out.println(" NO Explosion PICTURE");
		}
	}

	public void expand() {
		if(getHeight() < 200)
		{
		setX(getX()-5);
		setWidth(getWidth()+10);
		setY(getY()-5);
		setHeight(getHeight()+10);
		}
	}

	@Override
	public void move(String direction) {}
	@Override
	public void draw(Graphics window) { window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null); }
	@Override
	public void setSpeed(int s) { }
	@Override
	public int getSpeed() { return 0;}
}
