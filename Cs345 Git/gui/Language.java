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

  private static int currentLang;
  
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
  public static final Map<Integer, String[]> translations;
  static {
    translations = new HashMap<>();
    translations.put(ENGLISH, new String[]{"English Selected", "Cannot divide by 0", "Must enter a + or -"});
    translations.put(SPANISH, new String[]{"Espanol Seleccionado", "No se puede dividir por 0", "Debe ingresar un + o -"});
    translations.put(FRENCH, new String[]{"Francais Selectionne", "Impossible de diviser par 0", "Doit entrer un + ou -"});
  }
  
  /**
   * Sets the current Language.
   * @param lang the Language code to set currentLang
   */
  public static void setLanguage(int lang) {
    currentLang = lang;
  }
  
  public static String getLanguage() {
    return LANGUAGES[currentLang];
  }
  
  public static String getDialog(int code) {
    return translations.get(currentLang)[code];
  }
}

