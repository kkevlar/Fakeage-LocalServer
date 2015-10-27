
public class Question 
{
	private String questionText;
	private String truth;
	private Lie[] lies;
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getTruth() {
		return truth;
	}
	public void setTruth(String truth) {
		this.truth = truth;
	}
	public Lie[] getLies() {
		return lies;
	}
	public void setLies(Lie[] lies) {
		this.lies = lies;
	}
	
}
