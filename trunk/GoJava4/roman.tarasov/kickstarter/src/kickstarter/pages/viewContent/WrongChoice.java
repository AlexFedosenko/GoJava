package kickstarter.pages.viewContent;

public class WrongChoice extends PageView {

	@Override
	public String getHeader() {
		StringBuilder header = new StringBuilder();
		header.append("\n----- Wrong Choice ----------");
		header.append("\n-----------------------------");
		header.append("\nOptions:  <p> - previous page");
		return header.toString();
	}
}
