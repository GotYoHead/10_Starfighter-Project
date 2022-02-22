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

public class AlienHorde
{
	private List<Alien> aliens;

	public AlienHorde(int size)
	{

		// Initialize aliens
		// Don't use size here, you want aliens to have a size of 0 and then add the right number of aliens beneath this comment

		aliens = new ArrayList<Alien>();

		// After you initialize aliens, you need to make rows of Aliens until you have the appropriate number


		// I started at 25,50 - Had a Gap of 75 between each Alien, 
		// To Move to the next row you can compare X to StarFighter.WIDTH

		int x = 25;
		int y = 50;
		for(int i = 0; i < size; i++)
		{

			aliens.add(new Alien(x,y,3));

			x += 75;

			if(x > StarFighter.WIDTH-50)
			{
				x = 50;
				y += 25;
			}

		}
	}

	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for(Alien a : aliens)
		{
			a.draw(window);
		}
	}

	// Move them each DOWN
	public void moveEmAll()
	{
		for(Alien a : aliens)
		{
			a.move("DOWN");
		}
	}
	public List<Alien> getList()
	{
		return (List<Alien>) aliens;
	}
	// Task 10
	public void removeDeadOnes(List<Ammo> shots)
	{
		for(int i = 0; i < aliens.size(); i++)
		{
			for(int j = 0; j < shots.size(); j++)
			{
				if(aliens.get(i).beenShot(shots.get(j))) 
				{
					aliens.remove(i);
					shots.remove(j);
					i--;
					j--;
					break;
				}
			}
		}
	}
	
	public void speedEmUp()
	{
		for(Alien a : aliens)
		{
			a.setSpeed(a.getSpeed()+1);
		}
	}
	

	public String toString()
	{
		return "";
	}
}
