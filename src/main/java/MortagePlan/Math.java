package MortagePlan;

public class Math {
	public static float pow(float value, int exponent)
	{
		float result = 1;
		for(int i = 0; i < exponent; ++i)
		{
			result *= value;
		}
		
		return result;
	}
}