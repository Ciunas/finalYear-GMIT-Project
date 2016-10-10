package translationIMS;


import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class TranslatorIMS {
  public static void main(String[] args) throws Exception {
     
     
    Translate.setClientId("4579531");
    Translate.setClientSecret("9x5MHdWiJ13cVDRx9o5tb6kPoN2hS5G4A68xFDzr3RE=");

    // Translate an english string to spanish
    String englishString = "Well unwell";
    String spanishTranslation = Translate.execute(englishString, Language.GERMAN);

    System.out.println("Original english phrase: " + englishString);
    System.out.println("Translated spanish phrase: " + spanishTranslation);
 
  }
}