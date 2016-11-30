package Version2;
import java.util.ArrayList;

public class Maschine {
	
	private Band band1;
	private Band band2;
	private Band band3;

	private Zustandskontrolle zk;
	
	public Maschine(ArrayList<String> eingabe) {
		band1 = new Band(eingabe);
		band2 = new Band();
		band3 = new Band();
		zk = new Zustandskontrolle(band1, band2, band3);
	}
	
	public void run() {

		band1.bandinhaltAusgeben();
		band2.bandinhaltAusgeben();
		band3.bandinhaltAusgeben();
		System.out.println();
		zk.zustand0();
	}
	
}
