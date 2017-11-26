package sphere;
import processing.core.PApplet;



public class Sphere extends PApplet
{
	final int WIDTH = 400;
	final int HEIGHT = 400;
	final int SPHERE_RADIUS = 255;
	
	Cursor cursor;
	Position startPoint;
	Position middlePos = new Position(WIDTH / 2, HEIGHT / 2);
	Layer[] layers;	
	ColorGradient[] cgs;
	
	
	private int distance(Position startPoint, Position endPoint)
	{
		return (int) Math.round(Math.pow(startPoint.x - endPoint.x, 2) + Math.pow(startPoint.y - endPoint.y, 2));
	}	
	
	public static void main(String[] args) 
	{
		PApplet.main("sphere.Sphere");
		for(int i = 0; i < 100; i++)
			System.out.println(Math.random());
		
		//Layer test = new Layer();
	}
		
	public void settings()
	{
		size(WIDTH, HEIGHT);
	}
		
	public void setup()
	{
		// setting up the variables from above that will be worked with everywhere
		cursor = new Cursor(new Position(mouseX, mouseY), WIDTH, this);
		cgs = new ColorGradient[3];
		for(int i = 0; i < cgs.length; i++)
		{
			switch(i)
			{
				case 0:
					cgs[i] = new ColorGradient(243, 29, 52, 247, 159, 39);
					break;
				case 1:
					cgs[i] = new ColorGradient(14, 255, 255, 204, 255, 19);
					break;
				case 2:
					cgs[i] = new ColorGradient(10, 48, 255, 16, 216, 255);
					break;
				default:
					break;
			}
		}
		
		
		Position[][] circlePositions = new Position[WIDTH][HEIGHT];
		int[][] circleDists = new int[WIDTH][HEIGHT];
		
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
			{
				circlePositions[x][y] = new Position(x, y);
				circleDists[x][y] = distance(circlePositions[x][y], middlePos);
			}
		
		int circleCounter = 0;
		Position[] newCirclePos;
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
				if(circleDists[x][y] == SPHERE_RADIUS)
					circleCounter++;
					
		newCirclePos = new Position[circleCounter];
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
				if(circleDists[x][y] == SPHERE_RADIUS)
					;//newCirlcePos[i] = 
				
	
		
		// final int WIDTH;
	}
		
	public void draw()
	{
			
	}		
}
