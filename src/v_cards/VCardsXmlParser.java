package v_cards;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.Collections;

import android.content.res.*;

public class VCardsXmlParser
{
	private Map<String, String> languageDictionary;
	public ArrayList<String> languageNameArray;
	
	public VCardsXmlParser()
	{
		languageDictionary = new HashMap<String, String>();
		languageNameArray = new ArrayList<String>();
	}
	
	public String getTranslation(String selectedLanguage)
	{	
		return languageDictionary.get(selectedLanguage);
	}

    public void parseXML (AssetManager manager)
        throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        InputStream is = manager.open("Languages.xml");
        xpp.setInput(is, null);
        int eventType = xpp.getEventType();
        String name = new String();
        String text = new String();
        
        while (eventType != XmlPullParser.END_DOCUMENT) {
         if(eventType == XmlPullParser.START_DOCUMENT) {
             //System.out.println("Start document");
         } else if(eventType == XmlPullParser.END_DOCUMENT) {
             //System.out.println("End document");
         } else if(eventType == XmlPullParser.START_TAG) {
        	 if (xpp.getName().contains("name"))
        	 {
        		 //System.out.println(xpp.getName());
        		 // we know the next item after a start tag is text
        		 // if it's a name or text tag
        		 eventType = xpp.next();
        		 //System.out.println(xpp.getText());
        		 name = xpp.getText();
        		 name = name.trim();
        	 }
        	 else if(xpp.getName().contains("text"))
        	 {
        		 //System.out.println(xpp.getName());
        		 // we know the next item after a start tag is text
        		 // if it's a name or text tag
        		 eventType = xpp.next();
        		 //System.out.println(xpp.getText());
        		 text = xpp.getText();
        		 text = text.trim();
        		 
        		 languageNameArray.add(name);
        		 languageDictionary.put(name, text);
        		 //System.out.println(name);
        		 //System.out.println(text);
        	 }
             //System.out.println("Start tag "+xpp.getName());
         } else if(eventType == XmlPullParser.END_TAG) {
             //System.out.println("End tag "+xpp.getName());
         } else if(eventType == XmlPullParser.TEXT) {
             //System.out.println("Text "+xpp.getText());
         }
         eventType = xpp.next();
        }
        
        is.close();
        
        Collections.sort(languageNameArray);
    }
}
