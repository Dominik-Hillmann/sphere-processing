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
		System.out.println(cursor);
		
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
		System.out.println(WIDTH * HEIGHT);
		
		
		// filtering out all positions not having a dist of SPHERE_RADIUS to middlePos
		int circleCounter = 0;
		Position[] newCirclePos;		
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
				if(circleDists[x][y] == SPHERE_RADIUS)
					circleCounter++;
				
			
		System.out.print("counter:  ");
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
		
		System.out.print("counter2: ");;
		System.out.println(circleCounter);
		
		
		
		Position[] leftPos = new Position[circleCounter / 2];
		int leftCounter = 0;
		Position[] rightPos = new Position[(circleCounter / 2)];
		int rightCounter = 0;
		
		System.out.print(leftPos.length); System.out.print("|"); System.out.println(rightPos.length);
		
		for(int i = 0; i < newCirclePos.length; i++)
		{
			if(newCirclePos[i].x < (WIDTH / 2))
			{
				leftPos[leftCounter] = newCirclePos[i];
				leftCounter++;
				if(leftCounter == leftPos.length - 1) break;
			}
			else
			{
				rightPos[rightCounter] = newCirclePos[i];
				rightCounter++;
				if(rightCounter == rightPos.length - 1) break;
			}
		}
		
		System.out.print("right: ");
		System.out.println(rightCounter);
		System.out.print("left: ");
		System.out.println(leftCounter);
		
		for(int i = 0; i < rightCounter; i++)
		{
			System.out.print(leftPos[i].x);
			System.out.print("|");
			System.out.println(leftPos[i].y);
			
			System.out.print(rightPos[i].x);
			System.out.print("|");
			System.out.println(rightPos[i].y);
		}
		
		
		int matchCounter = 0;
		
		for(int i = 0; i < rightCounter; i++)
			for(int j = 0; j < rightCounter; j++)
				if(leftPos[i].y == rightPos[j].y)
				{
					matchCounter++;
					j = rightCounter; // so that there will be no other Points on the same height found
				}
		
		layers = new Layer[matchCounter];
		matchCounter = 0;
		
		for(int i = 0; i < leftCounter; i++)
			for(int j = 0; j < rightCounter; j++)
				if(leftPos[i].y == rightPos[j].y)
				{
					layers[matchCounter] = new Layer(leftPos[i], rightPos[j], 3, this);
					matchCounter++;
					j = rightCounter;
				}
		
		for(int i = 0; i < layers.length; i++)
			System.out.println(layers[i]);
				
		System.out.println(matchCounter);
		//int layerCounter = 0;
		
		//if(leftCounter != rightCounter) System.out.println("Ungleiche Laenge der Arrays");
		
		
	}
		
	public void draw()
	{
		background(0, 0, 0);
		//cursor.update();
		
		for(int i = 0; i < layers.length; i++)
		{
			//layers[i].drawPoints(2, 5, 3, WIDTH);
			
			ellipse((float) layers[i].left.x, (float) layers[i].left.y, (float) 3.0, (float) 3.0);
		}
	}		
}
