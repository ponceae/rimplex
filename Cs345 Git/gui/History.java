package gui;

import java.util.ArrayList;

public class History
{

  private ArrayList<String> history;
  private static History theHistory;

  private History()
  {
    history = new ArrayList<String>();
  }

  public void add(String str)
  {
    history.add(str);
  }

  public void reset()
  {
    history = new ArrayList<>();
  }

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

  public static History getInstance()
  {
    if (theHistory == null) {
      theHistory = new History();
    }
    return theHistory;
  }
}
