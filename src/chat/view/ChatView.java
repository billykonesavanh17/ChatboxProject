package chat.view;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ChatView
{
	private String windowMessage;
	private ImageIcon chatIcon;
	
	
	public ChatView()
	{
		windowMessage = "Hello from your friendly chatbot.";
		chatIcon = new ImageIcon(getClass().getResource("images/codybot.png"));
	}
	
	public void showResponse(String wordsFromSomewhere)
	{
		JOptionPane.showMessageDialog(null, wordsFromSomewhere);
	}

	
	public String grabAnswer(String popupText)
	{
		String answer = "";
		answer = JOptionPane.showInputDialog(null, popupText, windowMessage, JOptionPane.PLAIN_MESSAGE, chatIcon, null, "Answer here" ).toString();
		return answer;
	}
}
