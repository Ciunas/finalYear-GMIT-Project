/*
 * Copyright 2013 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ims_translate;

  

/**
 * Language - an enum of language codes supported by the Yandex API
 */

public enum Language {
	
  ALBANIAN("sq"),
  //DEFAULT(Translate.DEFAULT_LANG.toString()),
  ARMENIAN("hy"),
  AZERBAIJANI("az"),
  ARABIC("ar"),
  BELARUSIAN("be"),
  BULGARIAN("bg"),
  CATALAN("ca"),
  CROATIAN("hr"),
  CZECH("cs"),
  DANISH("da"),
  DUTCH("nl"),
  ENGLISH("English"),
  ESTONIAN("et"),
  FINNISH("fi"),
  FRENCH("French"),
  GERMAN("German"),
  GEORGIAN("ka"),
  GREEK("el"),
  HUNGARIAN("hu"),
  ITALIAN("it"),
  LATVIAN("lv"),
  LITHUANIAN("lt"),
  MACEDONIAN("mk"),
  NORWEGIAN("no"),
  POLISH("pl"),
  PORTUGUESE("pt"),
  ROMANIAN("ro"),
  RUSSIAN("ru"),
  SERBIAN("sr"),
  SLOVAK("sk"),
  SLOVENIAN("sl"),
  SPANISH("es"),
  SWEDISH("sv"),
  TURKISH("tr"),
  UKRAINIAN("uk");

/**
   * String representation of this language.
   */
  private  String language = "en" ;

  /**
   * Enum constructor.
   * @param pLanguage The language identifier.
   */
  private Language(final String pLanguage) {
    language = pLanguage;
  }

  public static Language fromString(final String pLanguage) {
	  System.out.println("here");
    for (Language l : values()) {
      if (l.toString().equals(pLanguage)) {
    	  System.out.println("Returning");
        return l;
      }
    }
    return null;
  }

  /**
   * Returns the String representation of this language.
   * @return The String representation of this language.
   */
  @Override
  public String toString() {
    return language;
  }

}
