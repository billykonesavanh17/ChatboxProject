package chat.model;

import java.util.ArrayList;

/**
 * Base version of the 2015 Chatbot class. Only stub methods are provided. Students will complete methods as part
 * of the project.
 * @author Billy Konesavanh
 * @version 1.2 10/23/15 Completed the lengthChecker method.  Fixed the Constructor and getter for userName
 */
public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private ArrayList<String> keyboardMashList;
	private String userName;
	private String content;
	
	/**
	 * Creates an instance of the Chatbot with the supplied username.
	 * @param userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.userName = userName;
		this.memesList = new ArrayList<String>();
		this.politicalTopicList = new ArrayList<String>();
		this.keyboardMashList = new ArrayList<String>();
		this.content = "food";
		
		buildMemesList();
		buildPoliticalTopicsList();
		buildKeyboardMashList();
		
	}
	
	private void buildMemesList()
	{
		this.memesList.add("doge");
		this.memesList.add("cute animals");
		this.memesList.add("Aliens");
		this.memesList.add("rare pepe");
		this.memesList.add("bad luck brian");
		this.memesList.add("spoderman");
		this.memesList.add("what if I told you");
		this.memesList.add("unhelpful high school teacher");
		this.memesList.add("me gusta");
		this.memesList.add("troll face");
	}
	
	private void buildPoliticalTopicsList()
	{
		this.politicalTopicList.add("abortion");
		this.politicalTopicList.add("gun control");
		this.politicalTopicList.add("global warming");
		this.politicalTopicList.add("racism");
		this.politicalTopicList.add("election");
		this.politicalTopicList.add("democrat");
		this.politicalTopicList.add("republican");
		this.politicalTopicList.add("liberal");
		this.politicalTopicList.add("conservative");
		this.politicalTopicList.add("Trump");
		this.politicalTopicList.add("Clinton");
		this.politicalTopicList.add("Biden");
		this.politicalTopicList.add("Carson");
		this.politicalTopicList.add("Rubio");
		this.politicalTopicList.add("Fiorina");
		this.politicalTopicList.add("Sanders");
		this.politicalTopicList.add("vote");
		this.politicalTopicList.add("11/4/16");
				
	}
	
	private void buildKeyboardMashList()
	{
		this.keyboardMashList.add("sdf");
		this.keyboardMashList.add("dfg");
		this.keyboardMashList.add("cvb");
		this.keyboardMashList.add(",./");
		this.keyboardMashList.add("asdjkljkl;adsjkl;afs");
	}
	
	/**
	 * Checks the length of the supplied string. Returns false if the supplied String is empty or null,
	 * otherwise returns true. 
	 * @param currentInput
	 * @return A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		
		if(currentInput != null)
		{
			if(currentInput.length() >= 1)
			{
				hasLength = true;
			}
		}
		
		if(currentInput != null && currentInput.length() > 0)
		{
			hasLength = true;
		}
		
		return hasLength;
	}
	
	/**
	 * Checks if the supplied String matches the content area for this Chatbot instance.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether it matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean hasContent = false;
		
		if(currentInput.toLowerCase().contains(content.toLowerCase()))
		{
			hasContent = true;
		}
		
		return hasContent;
	}
	
	public boolean quitChecker(String currentInput)
	{
		boolean hasQuit = false;
		
		if(currentInput.equals("quit"))
		{
			hasQuit = true;
		}
		
		return hasQuit;
	}
	
	/**
	 * Checks if supplied String matches ANY of the topics in the politicalTopicsList. Returns
	 * true if it does find a match and false if it does not match.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean hasTopic = false;
		
		for(String currentTopic: politicalTopicList)
		{
			if(currentTopic.equalsIgnoreCase(currentInput))
			{
				hasTopic = true;
			}
		}
		return hasTopic;
	}
	
	
	/**
	 * Checks to see that the supplied String value is in the current memesList variable.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean hasMeme = false;
		
		for(String currentMeme: memesList)
		{
			if(currentMeme.equalsIgnoreCase(currentInput))
			{
				hasMeme = true;
			}		
		}
		

		return hasMeme;
	}
	
	public boolean keyboardMashChecker(String currentInput)
	{
		boolean hasMash = false;
		
		for(String currentMash: keyboardMashList)
		{
			if(currentMash.equalsIgnoreCase(currentInput))
			{
				hasMash = true;
			}
		}
		
		return hasMash;
	}
	
		public String processConversation(String currentInput)
	{
		String nextConversation = "Cool, what else would you like to talk about?";
		int randomTopic = (int) (Math.random()*6); //Generates a random number between 0 and 5.
		switch (randomTopic)
		{
		case 0:
			if(memeChecker(currentInput))
			{
				nextConversation = "That is a very popular meme this year.  What else are you " + "wanting to talk about?";
			}
			break;
		case 1:
			if(politicalTopicChecker(currentInput))
			{
				nextConversation = "Politics are cool, but is there anything else you want to talk about?";
			}
			break;
		case 2:
			if(keyboardMashChecker(currentInput))
			{
				nextConversation = "I don't understand your keyboard mash, please write some real words.";
			}
		case 3:
			if(contentChecker(currentInput))
			{
				nextConversation = "Sweet, anything else on your mind buddy";
			}
			break;
		case 4:
			if(currentInput.length() > 20)
			{
				nextConversation = "Interesting, anything else?";
			}
			break;
		case 5:
			nextConversation = "Nice, but how do you really feel?";
			break;
		default:
			nextConversation = "The universe has ended sad panda";
			break;
		}
			
					
		return nextConversation;
	}
	
	/**
	 * Returns the username of this Chatbot instance.
	 * @return The username of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * Returns the content area for this Chatbot instance.
	 * @return The content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Getter method for the memesList object.
	 * @return The reference to the meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}
	
	/**
	 * Getter method for the politicalTopicList object.
	 * @return The reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}
	
	/**
	 * Updates the content area for this Chatbot instance.
	 * @param content The updated value for the content area.
	 */
	public void setContent(String content)
	{
		
	}
}