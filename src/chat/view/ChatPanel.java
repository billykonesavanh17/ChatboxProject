package chat.view;

import javax.swing.*;

import chat.controller.ChatController;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private JButton firstButton;
	private JButton colorButton;
	private JTextField firstTextField;
	private JTextArea firstTextArea;
	private SpringLayout baseLayout;
	private JLabel promptLabel;
	
	public ChatPanel(ChatController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		firstButton = new JButton("Enter");
		colorButton = new JButton("Change Colors");
		firstTextField = new JTextField("Answer Here");
		promptLabel = new JLabel("Chat with Chatbot");
		firstTextArea = new JTextArea("Hello, I'm Chatbot");
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(firstButton);
		this.add(colorButton);
		this.add(firstTextField);
		this.add(firstTextArea);
		this.add(promptLabel);
		firstTextField.setToolTipText("Type here for the chatbot");
		firstTextArea.setEnabled(false);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, firstButton, 77, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstButton, 0, SpringLayout.NORTH, colorButton);
		baseLayout.putConstraint(SpringLayout.EAST, colorButton, -84, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, colorButton, 14, SpringLayout.SOUTH, firstTextField);
		baseLayout.putConstraint(SpringLayout.WEST, firstTextField, 40, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, firstTextField, -40, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, promptLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, promptLabel, 153, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextArea, 6, SpringLayout.SOUTH, promptLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, firstTextArea, -112, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstTextArea, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, firstTextArea, 440, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextField, 17, SpringLayout.SOUTH, firstTextArea);
	}
	
	private void changeColor()
	{
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		setBackground(new Color(red, green, blue));
	}
	
	private void setupListeners()
	{
		firstButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{			
				String userText = firstTextField.getText(); 	//Grab user typed answer
				firstTextArea.append("\nUnser:  " + userText); 	//display typed answer
				firstTextField.setText("");
				String response = baseController.userToChatbot(userText); //send the text to chatbot 	//chatbot will process
				firstTextArea.append("\nChatbot:  " + response); 			//display the response
			}
		});
		
		colorButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				changeColor();
			}
		});
		
	}
	
	public JTextField getTextField()
	{
		return firstTextField;
	}
	

}
