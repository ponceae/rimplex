package gui;

/**
 * Encapsulates 3 language tokens.
 * 
 * @author Adrien Ponce & Ulises Fernandez
 * @version 4/24/21
 */
public enum Language
{

  ENGLISH("English"), SPANISH("Espanol"), FRENCH("Francais");
  
  private String langToken;
  
  /**
   * Creates a language token.
   * 
   * @param description the language description
   */
  private Language(String description) {
    langToken = description;
  }
  
  /**
   * @return the token
   */
  public String getToken() {
    return langToken;
  }
}
