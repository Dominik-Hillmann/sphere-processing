package sphere;

public class CirclePos extends Position
{
	public boolean secondTier;
	public Color color;
	
	CirclePos(int x, int y, int r, int g, int b)
	{
		super(x, y);
		secondTier = Math.random() < 0.5;		
		color = new Color(r, g, b);
	}
}
