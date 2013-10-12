package v_cards;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;

public class Language extends Activity {

	private VCardsXmlParser xmlParser;
	
	public Language()
	{
		xmlParser = new VCardsXmlParser();
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//System.out.println("Language::onCreate");
        super.onCreate(savedInstanceState);
       AssetManager manager = getAssets();

        try {
			xmlParser.parseXML(manager);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
