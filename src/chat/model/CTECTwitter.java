package chat.model;

import java.util.ArrayList;
import twitter4j.*;
import chat.controller.ChatController;

/**
 * @author Billy Konesavanh
 * @version 0.4 March 7, 2016 - Made a method that can send a tweet, made try/catch
 * Interact with Twitter
 */

public class CTECTwitter
{
	private ArrayList<Status> statusList;
	private ArrayList<String> wordList;
	private Twitter chatbotTwitter;
	private ChatController baseController;
	
	public CTECTwitter(ChatController baseController)
	{
		statusList = new ArrayList<Status>();
		wordList = new ArrayList<String>();
		chatbotTwitter = TwitterFactory.getSingleton();
		this.baseController = baseController;
	}
	
	/**
	 * Send the supplied message as a tweet.
	 * @param tweet the supplied String.
	 */
	public void sendTweet(String tweet)
	{
		try
		{
			chatbotTwitter.updateStatus("I just tweeted from my Java Chatbot program! #APCSRocks @CTECNow Thanks @cscheerleader & @codyhenrichsen! BillyBot");
		}
		catch (TwitterException error)
		{
			baseController.handleErrors(error.getErrorMessage());
		}
	}
}

