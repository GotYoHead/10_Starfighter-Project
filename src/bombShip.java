import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class bombShip extends Alien {

	
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
	
}
