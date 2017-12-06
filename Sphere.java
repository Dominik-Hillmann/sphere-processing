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
		// initialize values
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
			{
				circlePositions[x][y] = new Position(x, y);
				circleDists[x][y] = distance(circlePositions[x][y], middlePos);
			}
				
		// How many positions are there which have exactly a distance of SPHERE_RADIUS to middlePos?
		int counter = 0;
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
				if(distance(middlePos, circlePositions[x][y]) == SPHERE_RADIUS)
					counter++;
		
		// now we know how large the array has to be and we can sort the right positions into this array
		Position[] filtCirclePos = new Position[counter];
		counter = 0;
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < HEIGHT; y++)
				if(distance(middlePos, circlePositions[x][y]) == SPHERE_RADIUS)
				{
					filtCirclePos[counter] = circlePositions[x][y]; // now if the two points are SPHERE_RADIUS apart from each other, the other is added
					counter++;
				}
		
		// now the layers have to be constructed, the following way
		// we take the filtered array and divide into two arrays: one for the left part with x < WIDTH / 2, the right one with x > WIDTH / 2
		// left array: first count, than add the positions
		counter = 0;
		for(int i = 0; i < filtCirclePos.length; i++)
			if(filtCirclePos[i].x < (WIDTH / 2))
				counter++;
		
		Position[] leftPos = new Position[counter];
		counter = 0;
		for(int i = 0; i < filtCirclePos.length; i++)
			if(filtCirclePos[i].x < (WIDTH / 2))
			{
				leftPos[counter] = filtCirclePos[i];
				counter++;
			}
		
		// now we do the same for all the positions that are on the right: x >= WIDTH / 2
		counter = 0;
		for(int i = 0; i < filtCirclePos.length; i++)
			if(filtCirclePos[i].x < (WIDTH / 2))
				counter++;
		
		Position[] rightPos = new Position[counter];
		counter = 0;
		for(int i = 0; i < filtCirclePos.length; i++)
			if(filtCirclePos[i].x < (WIDTH / 2))
			{
				rightPos[counter] = filtCirclePos[i];
				counter++;
			}
		
		
		// now that we have these distinct groups, we can start constructing the layer from a left position and right position on the same height (y)
		
		// test whether all y positions are ordered
		for(int i = 0; i < counter; i++)
			for(int j = 0; j < counter; j++)
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// now we create the layers because we know how many there are
		for(int i = 0; i < leftPos.length; i++)
		{
			System.out.print(leftPos[i].x);
			System.out.print("|");
			System.out.println(leftPos[i].y);
		}
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
