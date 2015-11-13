
public abstract class ResponseData
{

	private String text;

	public String getText() {
		return text;
	}
	public ResponseData()
	{

	}
	public ResponseData(String text)
	{
		this.setText(text);
	}
	public void setText(String text) {
		this.text = text;
	}
	public abstract boolean isLie();
	public boolean equals(Object object)
	{
		if (object instanceof ResponseData)
		{
			ResponseData response = (ResponseData) object;
			if(response.getText().equalsIgnoreCase(this.getText()))
				return true;
		}
		return false;
		
	}
	@Override
	public String toString() {
		return "ResponseData [text=" + text + ", isLie()=" + isLie() + "]";
	}
	
}
