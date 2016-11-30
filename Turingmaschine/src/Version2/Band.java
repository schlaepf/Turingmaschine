package Version2;

import java.util.ArrayList;

public class Band {

	private ArrayList<String> bandinhalt = new ArrayList<String>();
	
	public Band(){
		initialisiereBand();
	}
	
	private void initialisiereBand() {
		for(int i = 0; i < 31; i++) {
			if(i == 15) {
				bandinhalt.add(i, "q0");
			}
			else {
				bandinhalt.add(i, "_");
			}
		}
	}
	
	public Band(ArrayList<String> bandinhalt) {
		initialisiereBand();
		for(int i = 16; i < bandinhalt.size()+16; i++){
			if(this.bandinhalt.size() > i) {
				this.bandinhalt.set(i, bandinhalt.get(i-16));
			}
			else {
				this.bandinhalt.add(i, bandinhalt.get(i-16));
			}
		}
	}
	
	public String gibZeichenBeiPosition() {
		String zeichen = "";
		for(int i = 0; i < bandinhalt.size(); i++) {
			if(bandinhalt.get(i).startsWith("q")) {
				if(bandinhalt.size() >= i) {
					bandinhalt.add("_");
					zeichen = bandinhalt.get(i+1);
					return zeichen;
				}
				else {
					zeichen = bandinhalt.get(i+1);
					return zeichen;
				}
			}
		}
		return zeichen;
	}
	
	
	public void bandinhaltAusgeben() {
		if(getPositionVonSchreibkopf() -15 < 0) {
			bandinhalt.add(0, "_");
		}
		int bandAusgabeAnfang = getPositionVonSchreibkopf() -15;
		int positionVonSchreibkpf = getPositionVonSchreibkopf();
		//bandinhalt vor schreibkopf ausgeben
		for(int i = bandAusgabeAnfang; i < positionVonSchreibkpf; i++) {
			System.out.print("|");
			System.out.print(bandinhalt.get(i));
		}
		// bandinhalt nach schreibkopf ausgeben
		int bandAusgabeEnde = getPositionVonSchreibkopf() + 16;
		for(int i = getPositionVonSchreibkopf(); i < bandAusgabeEnde; i++) {
			System.out.print("|");
			System.out.print(bandinhalt.get(i));
		}
		System.out.print("|");
		System.out.println();
	}
	
	public void schreibeAufBand(String element){
		int positionVonSchreibkopf = getPositionVonSchreibkopf();
		if(positionVonSchreibkopf+1 > bandinhalt.size()) {
			bandinhalt.add(element);
		}
		else {
			bandinhalt.set(positionVonSchreibkopf+1, element);
		}
	}

	/**
	 * @return the bandinhalt
	 */
	public ArrayList<String> getBandinhalt() {
		return bandinhalt;
	}

	public int getPositionVonSchreibkopf() {
		for(int i = 0; i < bandinhalt.size(); i++) {
			if(bandinhalt.get(i).startsWith("q")) {
				return i;
			}
		}
		return -1;
	}

	public void bewegeSchreibkopf(String richtung) {
		if("R".equals(richtung)) {
			String aktuellesZeichen = gibZeichenBeiPosition();
			int positionVonSchreibkopf = getPositionVonSchreibkopf();
			String zustand = bandinhalt.get(positionVonSchreibkopf);
			bandinhalt.set(positionVonSchreibkopf, aktuellesZeichen);
			bandinhalt.set(positionVonSchreibkopf+1, zustand);
			
		}
		else if("L".equals(richtung)) {
			String aktuellesZeichen = gibZeichenBeiPosition();
			for(int i = 0; i<bandinhalt.size(); i++) {
				if(bandinhalt.get(i).startsWith("q")) {
					if(getPositionVonSchreibkopf() == 0) {
						aktuellesZeichen = "_";
					}
					else {
					aktuellesZeichen = bandinhalt.get(i-1);
					}
				}
			}
			int positionVonSchreibkopf = getPositionVonSchreibkopf();
			String zustand = bandinhalt.get(positionVonSchreibkopf);
			bandinhalt.set(positionVonSchreibkopf, aktuellesZeichen);
			bandinhalt.set(positionVonSchreibkopf-1, zustand);
		}
	}
	
	public void ersetzeAltenZustandMitNeuem(String neuerZustand) {
		int positionVonSchreibkopf = getPositionVonSchreibkopf();
		bandinhalt.set(positionVonSchreibkopf, neuerZustand);
	}
}
