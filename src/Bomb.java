import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Bomb extends MovingThing {
	private int speed;
	private Image image;
	private Explosion explosion;
	private Boolean hasExploded = false;
	private long lastExplosion = 0;

	public Bomb()
	{
		this(0,0,10,10,3);
	}

	public Bomb(int x, int y)
	{
		this(x,y,10,10,3);
	}

	public Bomb(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed = s;

		try
		{
			URL url = getClass().getResource("bomb.png");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public long getLastExplosion(){
		return lastExplosion; 
		}

	public Boolean getExplode(){ 
		return hasExploded; 
		}

	public void setSpeed(int s)
	{
		speed = s;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public void explode(){
		speed = 0;
		lastExplosion = System.currentTimeMillis();
		hasExploded = true;
		explosion = new Explosion(getX(),getY(),getWidth(),getHeight());
		setX(StarFighter.WIDTH+getWidth()+1);
		setY(StarFighter.HEIGHT+getHeight()+1);
	}


	public void move( String direction )
	{
		//add code to move the ammo UP or DOWN
		//	if(direction.equals("______"))
		//		Call setX/Y like we have done before
		if(getY() > (StarFighter.HEIGHT/4)*3){
			explode();
		}
		if(direction.equals("UP"))
		{
			setY(getY()-speed);
		}
		if(direction.equals("DOWN"))
		{
			setY(getY()+speed);
		}
	}
	
}
