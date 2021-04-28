package gui;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates 3 language tokens.
 * 
 * @author Adrien Ponce & Ulises Fernandez
 * @version 4/24/21
 */
public class Language
{

  public static final int ENGLISH = 0;
  public static final int SPANISH = 1;
  public static final int FRENCH = 2;
  public static final int SELECTION = 0;
  public static final int DIVIDE_BY_ZERO = 1;
  public static final int IMPROPER_OPERAND = 2;

  public static final String[] LANGUAGES = {"English", "Espanol", "Francais"};
  
  /**
   * HashMap that stores all the translated phrases.
   */
  public static final Map<Integer, String[]> TRANSLATIONS;
  
  static
  {
    TRANSLATIONS = new HashMap<>();
    TRANSLATIONS.put(ENGLISH,
        new String[] {"English Selected", "Cannot divide by 0", "Must enter a + or -"});
    TRANSLATIONS.put(SPANISH, new String[] {"Espanol Seleccionado", "No se puede dividir por 0",
        "Debe ingresar un + o -"});
    TRANSLATIONS.put(FRENCH, new String[] {"Francais Selectionne", "Impossible de diviser par 0",
        "Doit entrer un + ou -"});
  }

  private static int currentLang;

  /**
   * Sets the current Language.
   * 
   * @param lang
   *          the Language code to set currentLang
   */
  public static void setLanguage(final int lang)
  {
    currentLang = lang;
  }

  /**
   * @return the string language
   */
  public static String getLanguage()
  {
    return LANGUAGES[currentLang];
  }

  /**
   * @param code the code for translation
   * @return the dialog for translation
   */
  public static String getDialog(final int code)
  {
    return TRANSLATIONS.get(currentLang)[code];
  }
}
