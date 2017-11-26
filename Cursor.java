package sphere;
import processing.core.*;

public class Cursor 
{
	public Position last;
	public Position current;
	private int width;
	public double relX;
	private PApplet p;
	
	Cursor(Position position, int width, PApplet parent)
	{
		last = position;
		current = position;
        p = parent;
		
		relX = ((current.x - width / 2) / width) * 0.05;
	}
	
	public void update()
	{
		last.x = current.x;
		last.y = current.y;
		
		current.x = p.mouseX;
		current.y = p.mouseY;
		
		relX = ((current.x - width / 2) / width) * 0.05;
	}
}
