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
	private JTextArea chatArea;
	private SpringLayout baseLayout;
	private JLabel promptLabel;
	private JScrollPane textPane;
	private JScrollPane textPane_1;
	private JButton tweetButton;
	private JButton saveButton;
	private JButton loadButton;
	
	//Declare 
	public ChatPanel(ChatController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		firstButton = new JButton("Enter");
		tweetButton = new JButton("Tweet");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		colorButton = new JButton("Change Colors");
		firstTextField = new JTextField(25);
		promptLabel = new JLabel("Chat with Chatbot");
		chatArea = new JTextArea(10, 15);
		
		setupChatPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	
	private void setupChatPane()
	{
		textPane = new JScrollPane(chatArea);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEnabled(false);
		chatArea.setEditable(false);
		textPane_1 = new JScrollPane(chatArea);
		textPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	//Setup panel and added a tool tip 
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(firstButton);
		this.add(textPane_1);
		this.add(tweetButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(colorButton);
		this.add(firstTextField);
		this.add(promptLabel);
		firstTextField.setToolTipText("Type here for the chatbot");
		chatArea.setEnabled(false);
	}
	
	//Where the Layout is.
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, firstTextField, 40, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, firstTextField, -40, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, promptLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatArea, 6, SpringLayout.SOUTH, promptLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatArea, -112, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatArea, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatArea, 440, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstTextField, 17, SpringLayout.SOUTH, chatArea);
		baseLayout.putConstraint(SpringLayout.WEST, promptLabel, 165, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, promptLabel, -165, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, textPane_1, 35, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, textPane_1, 20, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, textPane_1, 200, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, textPane_1, -20, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, firstButton, 6, SpringLayout.SOUTH, firstTextField);
		baseLayout.putConstraint(SpringLayout.NORTH, colorButton, 6, SpringLayout.SOUTH, firstTextField);
		baseLayout.putConstraint(SpringLayout.WEST, tweetButton, 2, SpringLayout.EAST, colorButton);
		baseLayout.putConstraint(SpringLayout.WEST, colorButton, 6, SpringLayout.EAST, firstButton);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, firstButton);
		baseLayout.putConstraint(SpringLayout.EAST, loadButton, 0, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, firstButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, tweetButton);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, -78, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, tweetButton, 0, SpringLayout.NORTH, firstButton);
		baseLayout.putConstraint(SpringLayout.WEST, firstButton, 0, SpringLayout.WEST, this);
	}
	
	//Method for my change color button, just changes color of background randomly.
	private void changeColor()
	{
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		setBackground(new Color(red, green, blue));
	}
	
	//Just setups all of my buttons
	private void setupListeners()
	{
		firstButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{			
				String userText = firstTextField.getText(); 	//Grab user typed answer
				chatArea.append("\nUser:  " + userText); 	//display typed answer
				firstTextField.setText("");
				String response = baseController.userToChatbot(userText); //send the text to chatbot 	//chatbot will process
				chatArea.append("\nChatbot:  " + response); 			//display the response
			}
		});
		
		tweetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.sendTweet("no text to send");
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
	
	//Just returns JTextField
	public JTextField getTextField()
	{
		return firstTextField;
	}
	

}
