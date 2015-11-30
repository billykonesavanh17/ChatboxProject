package chat.view;

import javax.swing.*;
import chat.controller.ChatController;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private JButton firstButton;
	private JTextField firstTextField;
	private JTextArea firstTextArea;
	private SpringLayout baseLayout;
	
	public ChatPanel(ChatController baseController)
	{
		this.baseController = baseController;
		
		baseLayout = new SpringLayout();
		firstButton = new JButton("Enter");
		firstTextField = new JTextField("Answer Here");
		firstTextArea = new JTextArea("Hello, I'm Chatbot");
	
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(firstButton);
		this.add(firstTextField);
		this.add(firstTextArea);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, firstButton, 178, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, firstButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstTextField, 42, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, firstTextField, -38, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextField, 21, SpringLayout.SOUTH, firstTextArea);
		baseLayout.putConstraint(SpringLayout.EAST, firstTextArea, 429, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstTextArea, 21, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextArea, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, firstTextArea, 179, SpringLayout.NORTH, this);
		
	}
	
	private void setupListeners()
	{
		
	}
	

}
