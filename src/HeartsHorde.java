import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class HeartsHorde {

	private ArrayList<Heart> hearts;


	public HeartsHorde(int size)
	{
		hearts = new ArrayList<Heart>();

		int hX = StarFighter.WIDTH-20;

		for(int i = 0; i < size; i++ )
		{
			hearts.add(new Heart(hX,50,20,20));
			hX -= 20;
		}

	}
	public void add(Heart h)
	{
		hearts.add(h);
	}
	public void removeHeart()
	{
		if(hearts.size() > 0)
			hearts.remove(hearts.size()-1);

	}
	public void drawEmAll( Graphics window )
	{
		for(Heart h : hearts)
		{
			h.draw(window);
		}
	}
	public List<Heart> getList()
	{
		return (List<Heart>) hearts;
	}

}
