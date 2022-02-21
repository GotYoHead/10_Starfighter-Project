import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Explosion extends Alien {

	protected Image image;
	
	public Explosion(int x, int y, int w, int h)
	{
		super(x, y, w,h,0);
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
	public void expand()
	{
		
	}
}
