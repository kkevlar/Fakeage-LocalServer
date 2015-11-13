
public interface Player 
{
	public void sayTo(String s);
	public String getChoice();
	public boolean hasChoice();
	public void resetChoice();
	public void setId(int i);
	public int getId();
	public void setPoints(int i);
	public int getPoints();
	public void truth(String s);
	public void holdResponseData(ResponseData choice);
	public ResponseData getResponseData();
	public String getName();
	public void setName(String nm);
	public void setTellTruth(boolean b);
	public boolean shouldTellTruth();
}
