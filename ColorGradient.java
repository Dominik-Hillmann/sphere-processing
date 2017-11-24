package sphere;

public class ColorGradient 
{
	public Color start;
	public Color end;
	
	ColorGradient(int startR, int startG, int startB, int endR, int endG, int endB)
	{
		start = new Color(startR, startG, startB);
		end = new Color(endR, endG, endB);
	}
	
	ColorGradient(Color startColor, Color endColor)
	{
		start = startColor;
		end = endColor;
	}
}
