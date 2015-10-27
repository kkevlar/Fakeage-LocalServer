
public class Truth extends ResponseData
{
	public Truth(String truth) 
	{
		super(truth);
	}

	@Override
	public boolean isLie() 
	{
		return false;
	}

}
