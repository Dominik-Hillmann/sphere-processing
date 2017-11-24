package sphere;
import processing.core.PApplet;



public class Sphere extends PApplet
{
	private int distance(Position startPoint, Position endPoint)
	{
		return (int) Math.round(Math.pow(startPoint.x - endPoint.x, 2) + Math.pow(startPoint.y - endPoint.y, 2));
	}
	
	
	public static void main(String[] args) 
	{
		PApplet.main("sphere.Sphere");
		for(int i = 0; i < 100; i++)
			System.out.println(Math.random());
	}
		
	public void settings()
	{
	}
		
	public void setup()
	{
			
	}
		
	public void draw()
	{
			
	}		
}
