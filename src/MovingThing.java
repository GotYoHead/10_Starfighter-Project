//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingThing implements Moveable
{
	private int xPos;
	private int yPos;
	private int width;
	private int height;

	public MovingThing()
	{
		xPos = 10;
		yPos = 10;
		width = 10;
		height = 10;
	}

	public MovingThing(int x, int y)
	{
		xPos = x;
		yPos = y;
		width = 10;
		height = 10;
	}

	public MovingThing(int x, int y, int w, int h)
	{
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}

	public void setPos( int x, int y)
	{
		xPos =x;
		yPos =y;
	}

	public void setX(int x)
	{
		xPos = x;
	}

	public void setY(int y)
	{
		yPos = y;
	}

	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}

	public void setWidth(int w)
	{
		width = w;
	}

	public void setHeight(int h)
	{
		height = h;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	// Did I collide with another MovingThing? Eric Cui
	// if(alien.beenHit(ship, ship.getWidth())
	public boolean beenhit(MovingThing thing, int distance) {

		int x = thing.getX();
		int y = thing.getY();

		int w = thing.getWidth();
		int h = thing.getHeight();

		double thisx = getX() + getWidth()/2;
		double thisy = getY() + getHeight()/2;

		x += w/2; // Shift to the middle rather than top left
		y += h/2;

		// Distance Formula
		double magnitude = Math.pow((Math.pow(thisx - x, 2)) + (Math.pow(thisy - y, 2)), 0.5);

		if(magnitude < distance) {	
			return true;
		}

		return false;

	}



	public abstract void move(String direction);
	public abstract void draw(Graphics window);

	public String toString()
	{
		return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
	}
}