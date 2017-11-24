package sphere;

public class PointArea 
{
	int from;
	int to;
	
	private int partialDist(int start, int length, double percent)
	{
		return (int) (start + Math.round(length * percent));
	}
	
	// outer left area or outer right area
	PointArea(Position leftPos, double percent, int layerLen)
	{
		int x = leftPos.x;
		from = leftPos.x;
		to = partialDist(x, layerLen, percent);
	}
	
	// where area are not at the edge of the layer
	PointArea(Position leftPos, double startPc, double endPc, int layerLen)
	{
		int x =  leftPos.x;
		from = partialDist(x, layerLen, startPc);
		to = partialDist(x, layerLen, endPc);
	}
	
	// area at the right edge
	PointArea(Position leftPos, Position rightPos, double percent, int layerLen)
	{
		from = partialDist(leftPos.x, layerLen, percent);
		to = rightPos.x;
	}
}
