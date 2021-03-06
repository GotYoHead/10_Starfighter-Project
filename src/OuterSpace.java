//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private bombShip bs;
	private static superAlienHorde sHorde;
	private Ammo a;
	private int hSize = 10;
	private boolean[] keys;
	private BufferedImage back;
	private static HeartsHorde hearts;
	private BufferedImage lose;
	private BufferedImage background;
	private BufferedImage win;
	boolean canShotgun = false;
	int level = 1;

	/* Task 8: uncomment once you are ready for this part
	 * You might want to talk to me before you start this section, you really have to use your brain here.
	 */

	private static AlienHorde horde;
	private Bullets shots;

	public OuterSpace()
	{

		try
		{
			URL url = getClass().getResource("background.jpeg");
			background = ImageIO.read(url);
			URL u = getClass().getResource("wasted.jpeg");
			lose = ImageIO.read(u);
			URL u2 = getClass().getResource("win.jpeg");
			win = ImageIO.read(u2);

		}
		catch(Exception e)
		{
			//feel free to do something here
		}

		setBackground(Color.black);

		keys = new boolean[5];

		// Task 3: Instantiate Ship (Look at the fields at the top before you do this)
		ship = new Ship();
		// Task 5: Instantiate Two Aliens
		hearts = new HeartsHorde(5);

		// Task 6: Instantiate your Ammo
		// The Ammo should originate in the middle of the ship,
		// so try to make one with a speed of 0 till you get a good location 
		// This will make the game always start by firing a bullet, we will fix that later.

		
		a = new Ammo();

		shots = new Bullets();
		// Task 8: Make an Alien Horde
		// Make your Horde of whatever size you wish
		// Down below you will need to make sure they are drawn to graphToBack
		// You also need to make them move
		horde = new AlienHorde(hSize);
		sHorde = new superAlienHorde(0);
		bs = new bombShip(25, 25, 50);


		// You likely no longer need your alienOne or alienTwo but if you comment these out you need to make sure that you comment out the code that goes with it
		// Your draw, move and collision with the Ammo from Task 7 are all going to error if you're not careful

		// Task 9: Instantiate your "Bullets" object 

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);

		sHorde.add(new superAlien((int)(Math.random()*StarFighter.WIDTH),(int)(Math.random()*50),3));
	}

	public void update(Graphics window)

	{
		paint(window);
	}

	public void paint( Graphics window )
	{


		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		if(hearts.getList().size() <= 0)
		{
			ship.setPos(-900, -900);
			twoDGraph.drawImage(lose,null,0,0);
			return;
		}
		if(level == 6)
		{
			ship.setPos(-900, -900);
			twoDGraph.drawImage(win,null,0,0);
			return;
		}

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
			back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);

		graphToBack.drawImage(background,0,0,getFocusCycleRootAncestor());


		// Task 3: Add Keys to make the ship move, must use Capital Letters
		// Scroll down and look at Keypress to find which keys are mapped to what index
		if(keys[0] == true)
		{
			ship.move("LEFT");
		}
		if(keys[1] == true)
			ship.move("RIGHT");

		if(keys[2] == true)
			ship.move("UP");
		if(keys[3] == true)
			ship.move("DOWN");

		// Task 6: If they press space, make the field 'a' a new Ammo
		if(keys[4] == true)
		{
			a = new Ammo(ship.getX()+(ship.getWidth()/2),ship.getY() + 10,5,5,3);
			shots.add(a);
			
			if(canShotgun)
			{
				Ammo a2 = new Ammo(ship.getX()+(ship.getWidth()/2),ship.getY() + 10,5,5,3,1);
				shots.add(a2);
				Ammo a3 = new Ammo(ship.getX()+(ship.getWidth()/2),ship.getY() + 10,5,5,3,-1);
				shots.add(a3);
			}
			keys[4] = false; // This is to help with the animation and doesn't need to be changed
		}
		
	

		// TASK 3: add code to draw Ship

	
		
		ship.draw(graphToBack);  // Anything can be drawn like with this same technique

		// TASK 5: add code to draw Alien
		// call alien.move()

		horde.drawEmAll(graphToBack);


		horde.moveEmAll();

		horde.removeDeadOnes(shots.getList());


		bs.move("RIGHT");
		bs.draw(graphToBack);
		bs.shoot(graphToBack);

		if(bs.getBomb().getY()>(StarFighter.HEIGHT/4)*3)
		{
			bs.getBomb().explode();
		}

		hearts.drawEmAll(graphToBack);
		

		sHorde.drawEmAll(graphToBack);
		sHorde.moveEmAll();
		sHorde.removeDeadOnes(shots.getList());

		shots.drawEmAll(graphToBack);
		shots.moveEmAll();

		if(levelUp())
		{
			level += 1;
			hSize += 5;
			horde = new AlienHorde(hSize);
			sHorde.add(new superAlien((int)(Math.random()*StarFighter.WIDTH),(int)(Math.random()*50),3));
			horde.speedEmUp();
		}
		
		if(level == 4)
			canShotgun = true;


		for (superAlien sa : sHorde.getList())
			sa.shoot(graphToBack);



		twoDGraph.setColor(Color.yellow);

		//		a.draw(graphToBack);
		//		a.move("UP");

		for(int i = 0; i < horde.getList().size(); i++)
		{
			if(ship.beenhit(horde.getList().get(i), horde.getList().get(i).getWidth()))
			{
				hearts.removeHeart();
				horde.getList().remove(i);
				break;
			}
		}



		for(int i = 0; i < shots.getList().size(); i++)
		{
			if(ship.beenhit(shots.getList().get(i), shots.getList().get(i).getWidth()))
			{
				hearts.removeHeart();
				shots.getList().remove(i);
				break; 
			}
		}

		for(int i = 0; i < sHorde.getList().size(); i++)
		{
			if(ship.beenhit(sHorde.getList().get(i).getA(), sHorde.getList().get(i).getA().getWidth()))
			{
				hearts.removeHeart();
				sHorde.getList().get(i).getA().setY(StarFighter.HEIGHT);
				break;
			}
		}

		if(bs.getBomb().getExplosion() != null)
		{
			if(ship.beenhit(bs.getBomb().getExplosion(), bs.getBomb().getExplosion().getWidth()))
			{
				bs.getBomb().getExplosion().setY(-500);
				hearts.removeHeart();

			}
		}


		// TASK 7: Check if AlienOne has been Shot by your Ammo (look for a method in Alien)
		// If Shot Print Boom (Just for your testing)
		// I also made Shot Alien Move UP and off the screen just to test and look cool
		// We will do some more elegant stuff later




		twoDGraph.drawImage(back, null, 0, 0);

		twoDGraph.setColor(Color.WHITE);
		twoDGraph.drawString("Level: " + level, StarFighter.WIDTH-50,50);
	}

	public static boolean levelUp()
	{
		if(horde.getList().size() == 0)
			return true;
		return false;
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}

		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}

		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
		//no code needed here
	}

	public void run()
	{
		try
		{
			while(true)
			{
				Thread.currentThread().sleep(5);
				repaint();
			}
		}catch(Exception e)
		{
		}
	}
}

