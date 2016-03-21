package chat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import twitter4j.*;

import java.io.*;

import chat.controller.ChatController;
import chat.view.ChatView;

/**
 * @author Billy Konesavanh
 * @version 0.5 March 11, 2016 - Added methods
 * Interact with Twitter
 */

public class CTECTwitter
{
	private ArrayList<Status> statuses;
	private ArrayList<String> tweetTexts;
	private Twitter chatbotTwitter;
	private ChatView myDisplay;
	private Chatbot myBot;
	private ChatController baseController;
	
	/*
	 * Creates the Twitter model object with a reference to the controller to enable MVC message passing.
	 */
	public CTECTwitter(ChatController baseController)
	{
		statuses = new ArrayList<Status>();
		tweetTexts = new ArrayList<String>();
		chatbotTwitter = TwitterFactory.getSingleton();
		myDisplay = new ChatView();
		String userName = myDisplay.grabAnswer("What is your name?");
		myBot = new Chatbot(userName);
		this.baseController = baseController;
	}
	
	private void shutDown()
	{
		myDisplay.showResponse("Goodbye, " + myBot.getUserName() + " it has been my pleasure to talk with you.");
		System.exit(0);
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
	
	public String analyze(String userName)
	{
		String userAnalysis = "The Twitter user " + userName + " has many tweets.  ";
		try
		{
			chatbotTwitter.loadTweets(userName);
		}
		catch(TwitterException error)
		{
			handleErrors(error.getErrorMessage());
		}
		userAnalysis =+ chatbotTwitter.topResults();
		
		return userAnalysis;
	}
	
	public void handleErrors(String errorMessage)
	{
		myDisplay.showResponse(errorMessage);
	}
	
	public void topResults(ArrayList<String> wordList)
	{
		
	}
	
	/*
	 * Loads 2000 tweets from the supplied Twitter user to a List<Status> and a list<String>.
	 * @param twitterHandle The Twitter user being searched.
	 * @throws TwitterException any associated TwitterExceptions.
	 */
	public void loadTweets(String twitterHandle) throws TwitterException
	{
		statuses.clear();
		tweetTexts.clear();
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
				tweetTexts.add(removePunctuation(word).toLowerCase());
			}
		}
		removeCommonEnglishWords(tweetTexts);
		removeEmptyText();
	}
	
	public String topResults()
	{
		String tweetResults = "";
		int topWordLocation = 0;
		int topCount = 0;
		
		for(int index = 0; index < tweetTexts.size(); index++)
		{
			int wordUseCount = 1;
			for(int spot = index +1; spot < tweetTexts.size(); spot++)
			{
				if(tweetTexts.get(index).equals(tweetTexts.get(spot)))
				{
					wordUseCount++;
				}
				if(wordUseCount > topCount)
				{
					topCount = wordUseCount;
					topWordLocation = index;
				}
			}
		}
		
		tweetResults = "The top word in the tweets was " + tweetTexts.get(topWordLocation) + " and t was used " + topCount + " times!";
		return tweetResults;
	}
	
	private String removePunctuation(String currentString)
	{	
		String punctuation = ".,;?:;\"(){}^[]<>-";	//think about adding hashtag and @.
		
		String scrubbedString = "";
		//Checks each letter of a word and if it is in the punctuation list then it is not added.
		for(int i = 0; i < currentString.length(); i++)
		{
			if(punctuation.indexOf(currentString.charAt(i)) == -1)	
			{
				scrubbedString +=currentString.charAt(i);	//adds the letter at index
			}
		}
		
		return scrubbedString;
	}
	
	/*
	 * Removes all words found in commonWors.txt from the parameter tweetTexts.
	 */
	@SuppressWarnings("unchecked")
	private List removeCommonEnglishWords(List<String> tweetTexts)
	{
		String[] boringWords = importWordsToArray();
		
		for(int count = 0; count < tweetTexts.size(); count++)
		{
			for(int removeSpot = 0; removeSpot < boringWords.length; removeSpot++)
			{
				if(tweetTexts.get(count).equalsIgnoreCase(boringWords[removeSpot]))
				{
					tweetTexts.remove(count);
					count--;
					removeSpot = boringWords.length;	//Exit the inner loop.
				}
			}
		}
		//Comment this if you want to keep Twitter usernames in your word list.
		removeTwitterUsernamesFromList(tweetTexts);
		
		return tweetTexts;
	}
	
	/*
	 * Removes Twitter usernames from a list of String objects.
	 */
	private void removeTwitterUsernamesFromList(List<String> tweetTexts)
	{
		for(int wordCount = 0; wordCount < tweetTexts.size(); wordCount++)
		{
			if(tweetTexts.get(wordCount).length() >= 1 && tweetTexts.get(wordCount).charAt(0) == '@')
			{
				tweetTexts.remove(wordCount);
				wordCount--;
			}
		}
	}
	
	private void removeEmptyText()
	{
		for(int spot = 0; spot < tweetTexts.size(); spot ++)
		{
			if(tweetTexts.get(spot).equals(""))
			{
				tweetTexts.remove(spot);
				spot --;	//When you remove you have to have -- or you will skip over
			}
		}
	}
	
	/*
	 * Reads the commonWords.txt file and imports the supplied words to a String.
	 */
	private String [] importWordsToArray()
	{
		String [] boringWords;
		int wordCount = 0;
		
			Scanner wordFile = new Scanner(getClass().getResourceAsStream("commonWords.txt"));
			while(wordFile.hasNext())
			{
				wordCount++;
				wordFile.next();
			}
			wordFile = new Scanner(getClass().getResourceAsStream("commonWords.txt"));
			boringWords = new String[wordCount];
			int boringWordCount = 0;
			while(wordFile.hasNext())
			{
				boringWords[boringWordCount] = wordFile.next();
				boringWordCount++;
			}
			wordFile.close();
			
			return boringWords;
	}
}

