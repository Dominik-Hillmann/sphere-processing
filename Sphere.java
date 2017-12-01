package sphere;
import processing.core.PApplet;



public class Sphere extends PApplet
{
	final int WIDTH = 801;
	final int HEIGHT = 801;
	final int SPHERE_RADIUS = 255;
	
	Cursor cursor;
	Position startPoint;
	Position middlePos = new Position(WIDTH / 2, HEIGHT / 2);
	Layer[] layers;	
	ColorGradient[] cgs = 
	{
		new ColorGradient(243, 29, 52, 247, 159, 39),
		new ColorGradient(14, 255, 255, 204, 255, 19),
		new ColorGradient(10, 48, 255, 16, 216, 255)
	};
	
	
	private int distance(Position startPoint, Position endPoint)
	{
		return (int) Math.round(Math.sqrt(Math.pow(startPoint.x - endPoint.x, 2) + Math.pow(startPoint.y - endPoint.y, 2)));
	}	
	
	public static void main(String[] args) 
	{
		PApplet.main("sphere.Sphere");
	}
		
	public void settings()
	{
		size(WIDTH, HEIGHT);
	}
		
	public void setup()
	{
		// setting up the variables from above that will be worked with everywhere		
		cursor = new Cursor(new Position(mouseX, mouseY), WIDTH, this);
		
		// array for all possible positions at first
		Position[][] circlePositions = new Position[WIDTH][HEIGHT];
		int[][] circleDists = new int[WIDTH][HEIGHT];
		// Werte initialisiert
		for(int x = 0; x < WIDTH; x++)
		{
			for(int y = 0; y < HEIGHT; y++)
			{
				circlePositions[x][y] = new Position(x, y);
				circleDists[x][y] = distance(circlePositions[x][y], middlePos);
			}
		}
				
		// How many positions are there which have exactly a distannce of SPHERE_RADIUS to middlePos?
		int counter = 0;
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
				if(distance(middlePos, circlePositions[x][y]) == SPHERE_RADIUS)
				{
					// System.out.println(distance(middlePos, circlePositions[x][y]));
					counter++;
				}
		
		// now we know how large the array has to be and we can sort the right positions into this array
		Position[] filtCirclePos = new Position[counter];
			
		
		
		
	}
		
	public void draw()
	{
		background(0, 0, 0);		
		cursor.update();
		
		/*System.out.print(cursor.current.x);
		System.out.print("|");
		System.out.println(cursor.current.y);*/
	}		
}
