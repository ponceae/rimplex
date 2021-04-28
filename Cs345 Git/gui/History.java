package gui;

import java.util.ArrayList;

/**
 * History class.
 * 
 * @author o
 * @version 4/28/21
 */
public class History
{
  
  private static History theHistory;
  private ArrayList<String> history;

  private History()
  {
    history = new ArrayList<String>();
  }

  /**
   * Adds the string.
   * 
   * @param str the string
   */
  public void add(final String str)
  {
    history.add(str);
  }

  /**
   * Resets the history.
   */
  public void reset()
  {
    history = new ArrayList<>();
  }

  /**
   * @return a string representation of history.
   */
  public String toString()
  {
    if (history == null || history.size() == 0)
    {
      return "";
    }
    String str = "";
    for (String s : history)
    {
      str += s + "\n";
    }
    return str;
  }

  /**
   * Get the history instance.
   * @return the instance
   */
  public static History getInstance()
  {
    if (theHistory == null) 
    {
      theHistory = new History();
    }
    return theHistory;
  }
}
