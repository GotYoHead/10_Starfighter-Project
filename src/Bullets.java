//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets 
{
	
	protected Image image;
	
	private List<Ammo> ammo;

	public Bullets()
	{
		ammo = new ArrayList<Ammo>();		
		
	}

	public void add(Ammo al)
	{
		ammo.add(al);
	}

	//post - draw each Ammo
	public void drawEmAll( Graphics window )
	{
		for(Ammo a: ammo)
		{
			a.draw(window);
		}
	}

	public void moveEmAll()
	{
		for(Ammo a: ammo)
		{
			a.move("UP");
		}
	}

	public void cleanEmUp()
	{
		for(Ammo a: ammo)
		{
			if(a.getY() < -10)
			{
				ammo.remove(a);
			}
		}

	}

	public List<Ammo> getList()
	{
		return (List<Ammo>) ammo;
	}

	public String toString()
	{
		return "";
	}

	

}
