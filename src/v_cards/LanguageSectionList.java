package v_cards;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import v_cards.list.EntryAdapter;
import v_cards.list.EntryItem;
import v_cards.list.Item;
import v_cards.list.SectionItem;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class LanguageSectionList extends ListActivity {
    /** Called when the activity is first created. */
	
	 ArrayList<Item> items = new ArrayList<Item>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       AssetManager manager = getAssets();
        
        VCardsXmlParser xmlParser = new VCardsXmlParser();
        
        try {
			xmlParser.parseXML(manager);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String currentSectionLetter = new String("");
        
        for (String currentLanguage : xmlParser.languageNameArray)
        {
        	if (!currentSectionLetter.contains(currentLanguage.substring(0,1)))
        	{
        		currentSectionLetter = currentLanguage.substring(0,1);
        		items.add(new SectionItem(currentSectionLetter));
        	}
        	
        	items.add(new EntryItem(currentLanguage));
        }
        
        EntryAdapter adapter = new EntryAdapter(this, items);
        
        setListAdapter(adapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	if(!items.get(position).isSection()){
    		
    		Intent intent = new Intent(LanguageSectionList.this, Language.class);   
    		startActivity(intent);
    	}
    	
    	super.onListItemClick(l, v, position, id);
    }
}