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
	
	/*
	 * Creates the Twitter model object with a reference to the controller to enable MVC message passing.
	 */
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
	
	/*
	 * Loads 2000 tweets from the supplied Twitter user to a List<Status> and a list<String>.
	 * @param twitterHandle The Twitter user being searched.
	 * @throws TwitterException any associated TwitterExceptions.
	 */
	public void loadTweets(String twitterHandle) throws TwitterException
	{
		Paging statusPage = new Paging(1,200);
		int page = 1;
		while (page <= 10)
		{
			statusPage.setPage(page);
			statuses.addAll(chatbotTwitter.getUserTimeline(twitterHandle, statusPage));
			page++;
		}
		for (Status currentStatus : statuses)
		{
			String[] tweetText = currentStatus.getText().split("");
			for(String word : tweetText)
			{
				tweetTexts.ad(removePunctuation(word).toLowerCase());
			}
		}
		removeCommonEnglishWords(tweetTexts);
		removeEmptyText();
	}
}

