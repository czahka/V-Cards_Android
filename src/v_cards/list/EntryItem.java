package v_cards.list;


public class EntryItem implements Item{

	public final String title;

	public EntryItem(String title) {
		this.title = title;
	}
	
	@Override
	public boolean isSection() {
		return false;
	}

}
