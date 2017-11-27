package sphere;
import processing.core.PApplet;



public class Sphere extends PApplet
{
	final int WIDTH = 401;
	final int HEIGHT = 401;
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
			{
				System.out.println(circleDists[x][y]);
				if(circleDists[x][y] == SPHERE_RADIUS)
				{
					circleCounter++;
					System.out.println(circleDists[x][y] == SPHERE_RADIUS);
				}
			}
		System.out.print("counter: ");
		System.out.println(circleCounter);
					
		newCirclePos = new Position[circleCounter];
		circleCounter = 0;
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
				if(circleDists[x][y] == SPHERE_RADIUS)
				{
					newCirclePos[circleCounter] = new Position(x, y);
					circleCounter++;
				}
		System.out.print("counter: ");
		System.out.println(circleCounter);
		
		
		
		Position[] leftPos = new Position[circleCounter / 2];
		int leftCounter = 0;
		Position[] rightPos = new Position[circleCounter / 2];
		int rightCounter = 0;
		
		for(int i = 0; i < newCirclePos.length; i++)
			if(newCirclePos[i].x < (WIDTH / 2))
			{
				leftPos[leftCounter] = newCirclePos[i];
				leftCounter++;
			}
			else
			{
				rightPos[rightCounter] = newCirclePos[i];
				rightCounter++;
			}
		System.out.println(rightCounter);
		System.out.println(leftCounter);
		
		layers = new Layer[rightCounter];
		int layerCounter = 0;
		
		if(leftCounter != rightCounter) System.out.println("Ungleiche Laenger der Arrays");
		
		for(int y = 0; y < HEIGHT; y++)
		{
			Position found;
			for(int i = 0; i < leftCounter; i++)
			{
				Position found2;
				if(leftPos[i].y == y)
				{
					found = leftPos[i];
				
					for(int j = 0; j < leftCounter; j++)
						if(rightPos[j].y == y)
						{
							found2 = rightPos[j];
							layers[layerCounter] = new Layer(found, found2, 5, this);
							layerCounter++;
							continue;
						}
				}
			}
		}
		System.out.println(layers.length);
	}
		
	public void draw()
	{
		background(0, 0, 0);
		for(int i = 0; i < layers.length; i++)
		{
			layers[i].movePoints(cursor);
			layers[i].drawPoints(1, 5, 5, WIDTH);
		}
	}		
}
