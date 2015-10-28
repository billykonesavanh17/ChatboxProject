package chat.controller;

import chat.view.ChatView;
import chat.model.Chatbot;

/**
 * Application controller for the Chatbot project.
 * @author Billy Konesavanh
 * @version 1.11 10/21/15 fixed error :)
 */
public class ChatController
{
	private Chatbot myBot;
	private ChatView myDisplay;
	
	public ChatController()
	{
		myDisplay = new ChatView();
		String userName = myDisplay.grabAnswer("What is your name?");
		myBot = new Chatbot(userName);
	}
	
	public void start()
	{
		myDisplay.showResponse("Hello " + myBot.getUserName());
		chat();
	}
	
	private void chat()
	{
		String conversation = myDisplay.grabAnswer("What would you like to talk about today?");
		while(myBot.lengthChecker(conversation))
		
		if(myBot.contentChecker(conversation))
		{
			myDisplay.showResponse("wow, I had no idea you were interested in " + myBot.getContent());
		}
	
		conversation = myDisplay.grabAnswer(conversation);
	}
	
	private void shutDown()
	{
		
	}

}
