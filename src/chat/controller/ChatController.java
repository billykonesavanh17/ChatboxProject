package chat.controller;

import chat.view.ChatView;
import chat.model.Chatbot;
import chat.view.ChatFrame;

/**
 * Application controller for the Chatbot project.
 * @author Billy Konesavanh
 * @version 1.2 12/3/15 Added methods and documentation.
 */
public class ChatController
{
	private Chatbot myBot;
	private ChatView myDisplay;
	private ChatFrame baseFrame;
	
	//Creates an instances
	public ChatController()
	{
		myDisplay = new ChatView();
		String userName = myDisplay.grabAnswer("What is your name?");
		myBot = new Chatbot(userName);
		baseFrame = new ChatFrame(this);
	}
	
	//Displays GUI and says hello and the start.
	public void start()
	{
		myDisplay.showResponse("Hello " + myBot.getUserName());
		chat();
	}
	
	//Uses chatbot and allows chat.
	private void chat()
	{
	//	String conversation = myDisplay.grabAnswer("What would you like to talk about today?");
	//	while(myBot.lengthChecker(conversation))
	//	{
		//	conversation = myDisplay.grabAnswer(myBot.processConversation(conversation));
	//		conversation = myBot.processConversation(conversation);
	//	}
		
	
	}
	
	//Shuts down program if passes quitChecker and goes to chatbot to chat.
	public String userToChatbot(String conversation)
	{
		String response = "";
		if(myBot.quitChecker(conversation))
		{
			shutDown();
		}
		response = myBot.processConversation(conversation);
		return response;
	}
	
	//Method to shut program down and also displays a goodbye before shutdown.
	private void shutDown()
	{
		myDisplay.showResponse("Goodbye " + myBot.getUserName() + " it has been a pleasure to talk with you.");
		System.exit(0);
	}
	
	//Getters
	public Chatbot getChatbot()
	{
		return myBot;
	}
	
	public ChatView getChatView()
	{
		return myDisplay;
	}
	
	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}

}
