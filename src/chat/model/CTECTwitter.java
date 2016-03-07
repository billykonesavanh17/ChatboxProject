package chat.model;

import java.util.ArrayList;
import twitter4j.*;

/**
 * @author Billy Konesavanh
 * @version 0.2 Made a method that can send a tweet
 * Interact with Twitter
 */

public class CTECTwitter
{
	private ArrayList<Status> statusList;
	private ArrayList<String> wordList;
	private Twitter chatbotTwitter;
	
	public CTECTwitter()
	{
		statusList = new ArrayList<Status>();
		wordList = new ArrayList<String>();
	}
	
	public void sendTweet(String tweet)
	{
		chatbotTwitter.updateStatus("I just tweeted from my Java Chatbot program! #APCSRocks @CTECNow Thanks @cscheerleader & @codyhenrichsen!");
	}
}

