package gui;

/**
 * Encapsulates 3 language tokens.
 * 
 * @author Adrien Ponce & Ulises Fernandez
 * @version 4/24/21
 */
public enum Language
{

  ENGLISH("English"), 
  SPANISH("Espanol"), 
  FRENCH("Francais"), 
  ENGLISH_DIVIDE_BY_ZERO("Cannot Divide by 0"), 
  SPANISH_DIVIDE_BY_ZERO("No se Puede Dividir por 0"), 
  FRENCH_DIVIDE_BY_ZERO("Impossible de Diviser par 0");

  private String langToken;

  /**
   * Creates a language token.
   * 
   * @param description
   *          the language description
   */
  private Language(String description)
  {
    langToken = description;
  }

  /**
   * @return the token
   */
  public String getToken()
  {
    return langToken;
  }
}
