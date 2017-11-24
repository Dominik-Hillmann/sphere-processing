package sphere;

public class Layer 
{
	Position left;
	Position right;
	int layerLen;
	Position[] points;	
	
	Layer(int x1, int x2, int y1, int y2, int numPoints)
	{
		if(y1 != y2)
			System.out.println("Border in layer do not have same height!");
		
		left = new Position(x1, y1);
		right = new Position(x2, y2);
		layerLen = x2- x1;
		points = new Position[numPoints];
	}
	
	private void addPoints(int amount) // LATER PRIVATE??? and define it within class
	{
		PointArea area1Left  = new PointArea(left, 0.1, layerLen);
		PointArea area2Left  = new PointArea(left, 0.1, 0.25, layerLen);
		PointArea area3      = new PointArea(left, 0.25, 0.75, layerLen);
		PointArea area2Right = new PointArea(left, 0.75, 0.9, layerLen);
		PointArea area1Right = new PointArea(left, right, 0.9, layerLen);
		
		double odds = Math.random();
		PointArea selectedArea;
		
		
	}
}
