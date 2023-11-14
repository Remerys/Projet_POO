package hero;
/*
import java.util.ArrayList;
import java.util.List;*/

public class Inventory {
	//private List<Item> items = new ArrayList<>();
	private final int MAX_VOLUME = 100;
	private int volume;
	
	private Inventory(int volume) {
		this.volume = volume;
	}
}
