package chat.controller;

import twitter4j.TwitterException;
import chat.view.ChatView;
import chat.model.Chatbot;
import chat.view.ChatFrame;
import chat.model.CTECTwitter;

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
	private CTECTwitter chatTwitter;
	
	//Creates an instances
	public ChatController()
	{
		myDisplay = new ChatView();
		String userName = myDisplay.grabAnswer("What is your name?");
		myBot = new Chatbot(userName);
		baseFrame = new ChatFrame(this);
		chatTwitter = new CTECTwitter(this);
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
	
	public void sendTweet(String tweetText)
	{
		chatTwitter.sendTweet(tweetText);
	}
	
	public String analyze(String userName)
	{
	
			String userAnalysis = "The Twitter user " + userName + " has many tweets.  ";
			try
			{
				chatTwitter.loadTweets(userName);
			}
			catch(TwitterException error)
			{
				handleErrors(error.getErrorMessage());
			}
			userAnalysis += chatTwitter.topResults();
			
			return userAnalysis;
	}
	
	public String doInvestigation()
	{
		String twitterResults = "";
		twitterResults += chatTwitter.sampleInvestigation();
		return twitterResults;
	}
		
	
	public void handleErrors(String errorMessage)
	{
		myDisplay.showResponse(errorMessage);
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
