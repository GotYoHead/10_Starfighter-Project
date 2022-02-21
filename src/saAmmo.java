import java.awt.Color;
import java.awt.Graphics;

public class saAmmo extends Ammo {
	
	// Give Ammo an Image Field
	// use try catch
	// Steal Alien Draw
	
	public saAmmo(int i, int j)
	{
		super(i,j);
	}
	
	public void draw( Graphics window )
	{
		window.setColor(Color.GREEN);
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	
}
