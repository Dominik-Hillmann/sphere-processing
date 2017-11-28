package sphere;
import processing.core.*;
@SuppressWarnings({"static-access"})

public class Layer 
{
	private PApplet p;
	private Position left;
	private Position right;
	private int layerLen;
	private CirclePos[] points;	
	
	Layer(int x1, int x2, int y1, int y2, int numPoints, PApplet parent)
	{
		if(y1 != y2) System.out.println("Borders in layer do not have same height!");
		
		p = parent; // Processing-Funktion rufen über z.B. p.map()
		left = new Position(x1, y1);
		right = new Position(x2, y2);
		layerLen = x2- x1;
		points = new CirclePos[numPoints];
	}
	
	Layer(Position left, Position right, int numPoints, PApplet parent)
	{
		if(left.y != right.y)
			System.out.println("Borders in layer do not have same height!");
		
		this.left = left;
		this.right = right;
		layerLen = left.x - right.x;
		points = new CirclePos[numPoints];
	}
	
	private int distance(Position startPoint, Position endPoint)
	{
		return (int) Math.round(Math.pow(startPoint.x - endPoint.x, 2) + Math.pow(startPoint.y - endPoint.y, 2));
	}
	
	private void addPoints(int amount) // LATER PRIVATE??? and define it within class
	{
		PointArea area1Left = new PointArea(left, 0.1, layerLen);
		PointArea area2Left = new PointArea(left, 0.1, 0.25, layerLen);
		PointArea area3 = new PointArea(left, 0.25, 0.75, layerLen);
		PointArea area2Right = new PointArea(left, 0.75, 0.9, layerLen);
		PointArea area1Right = new PointArea(left, right, 0.9, layerLen);
		
		double odds = Math.random();
		PointArea[] selectedAreas;
		
		if(odds < 0.5)
		{
			selectedAreas = new PointArea[2];
			selectedAreas[0] = area1Left;
			selectedAreas[1] = area1Right;
		} 
		else if((odds > 0.5) && (odds < 0.8)) 
		{
			selectedAreas = new PointArea[2];
			selectedAreas[0] = area2Left;
			selectedAreas[1] = area2Right;
		} 
		else 
		{
			selectedAreas = new PointArea[1];
			selectedAreas[0] = area3;
		}
		
		PointArea iterationArea; // the chosen area in which the iterations to place the points take place
		if(selectedAreas.length == 2)
		{
			double randomPos;
			
			for(int i = 0; i < amount; i++)
			{
				randomPos = Math.random();
				if(randomPos <= 0.5 && selectedAreas.length == 2)
					iterationArea = selectedAreas[0];
				else
					iterationArea = selectedAreas[1];
				
				points[i] = new CirclePos
				(
					Math.round(p.map((float) Math.random(), (float) 0.0, (float) 1.0, (float) iterationArea.from, (float) iterationArea.to)),
					left.y,
					255,
					255,
					255
				);
			}
		}
		else // area 3, which has only one area
		{
			iterationArea = selectedAreas[0];
			for(int i = 0; i < amount; i++)
			{
				points[i] = new CirclePos
				(
					Math.round(p.map((float) Math.random(), (float) 0.0, (float) 1.0, (float) iterationArea.from, (float) iterationArea.to)),
					left.y,
					255,
					255,
					255
				);
			} // for
		} // else		
	} // addPoints
	
	
	
	private void colorize(Position start, ColorGradient cg, int radius)
	{
		for(int i = 0; i < points.length; i++)
		{
			CirclePos pos = points[i];			
			int dist = distance(pos, start);
			
			pos.color.r = Math.round(p.map(dist, 0, radius, cg.start.r, cg.end.r));
			pos.color.g = Math.round(p.map(dist, 0, radius, cg.start.g, cg.end.g));
			pos.color.b = Math.round(p.map(dist, 0, radius, cg.start.b, cg.end.b));
		}
	}
	
	
	public void movePoints(Cursor cursor)
	{
		for(int i = 0; i < points.length; i++)
		{
			CirclePos point = points[i];
			
			if(point.secondTier)
				point.x += layerLen * (-1 * cursor.relX);
			else
				point.x += layerLen * cursor.relX;
			
			if((point.x > right.x) || (point.x < left.x))
				point.secondTier =  !point.secondTier;
		}
	}
	
	
	public void drawPoints(int minSize, int maxSize, int numShadows, int width)
	{
		int midSize = (minSize + maxSize) / 2;
		
		
		for(int i = 0; i < points.length; i++)
		{
			CirclePos point = points[i];
			System.out.println(point.x);
			int x = point.x;
			int y = point.y;
			int r = point.color.r;
			int g = point.color.g;
			int b = point.color.b;
			int alpha = 255;
			int decAlpha = Math.round(255 / numShadows);
			
			double size = p.map
			(
				Math.abs((width / 2) - point.x),
				0,
				layerLen / 2,
				point.secondTier ? minSize : maxSize,
				midSize
			);
			
			for(int j = 0; j < (numShadows - 1); j++)
			{
				p.stroke(r, g, b, alpha);
				p.fill(r, g, b, alpha);
				p.ellipse((float) x, (float) y, (float) size, (float) size);
				
				x -= 3;
				y -= 3;
				alpha -= decAlpha;				
			} // for j
		} // for i
	} // drawPoints
	
	
	public void drawLine()
	{
		p.line((float) left.x, (float) left.y, (float) right.x, (float) right.y);
	}	
}












































