package Version2;

public class Zustandskontrolle {
	
	private Zustand q0;
	private Zustand q1;
	private Zustand q2;
	private Zustand q3;
	private Zustand q4;
	
	private Band band1;
	private Band band2;
	private Band band3;
	
	private String[] uebergangsfunktion;
	
	private int anzahlSchritte;
	
	private int ergebnis;
	
	boolean stepByStep = true;
	
	
	public Zustandskontrolle(Band band1, Band band2, Band band3) {
		q0 = new Zustand("0__", "0__RSS", "1__", "1__RSS");
		q1 = new Zustand("0__", "_0_RRS", "___", "___LSS");
		q2 = new Zustand("___", "___LSS", "1__", "1__LLS", "0__", "0__SLR", "_0_", "_0_SSS");
		q3 = new Zustand("00_", "000SLR", "0__", "0__LRS", "_0_", "_0_SSS", "___", "___SSS");
		q4 = new Zustand("00_", "000SRR", "0__", "0__LLS", "_0_", "_0_SSS", "___", "___SSS");
		
		this.band1 = band1;
		this.band2 = band2;
		this.band3 = band3;
		anzahlSchritte = 0;
		ergebnis = 0;
	}
	
	public void zustand0() {
		uebergangsfunktion = splitUebergangsfunktion(q0.gibUebergang(gibGeleseneZeichen()));
		while(uebergangsfunktion[0].equals("0")) {
			schreibeZeichenAufBaender(uebergangsfunktion);
			bewegeSchreibkoepfe(uebergangsfunktion);
			baenderAusgeben();
			uebergangsfunktion = splitUebergangsfunktion(q0.gibUebergang(gibGeleseneZeichen()));
			anzahlSchritte++;
		}
		zustandsUebergangQ0zuQ1();
	}
	
	public void zustand1() {
		uebergangsfunktion = splitUebergangsfunktion(q1.gibUebergang(gibGeleseneZeichen()));
		while(uebergangsfunktion[1].equals("0")){
			schreibeZeichenAufBaender(uebergangsfunktion);
			bewegeSchreibkoepfe(uebergangsfunktion);
			baenderAusgeben();
			uebergangsfunktion = splitUebergangsfunktion(q1.gibUebergang(gibGeleseneZeichen()));
			anzahlSchritte++;
		}
		zustandsUebergangQ1zuQ2();
	}
	
	public void zustand2() {
		uebergangsfunktion = splitUebergangsfunktion(q2.gibUebergang(gibGeleseneZeichen()));
		while((uebergangsfunktion[0].equals("_") && uebergangsfunktion[0].equals("_")) || (uebergangsfunktion[0].equals("1") && uebergangsfunktion[0].equals("_"))){
			schreibeZeichenAufBaender(uebergangsfunktion);
			bewegeSchreibkoepfe(uebergangsfunktion);
			baenderAusgeben();
			uebergangsfunktion = splitUebergangsfunktion(q2.gibUebergang(gibGeleseneZeichen()));
			anzahlSchritte++;
		}
		zustandsUebergangQ2zuQ3();
	}
	
	public void zustand3() {
		uebergangsfunktion = splitUebergangsfunktion(q3.gibUebergang(gibGeleseneZeichen()));
		while(uebergangsfunktion[1].equals("0") && uebergangsfunktion[0].equals("0")){
			schreibeZeichenAufBaender(uebergangsfunktion);
			bewegeSchreibkoepfe(uebergangsfunktion);
			baenderAusgeben();
			uebergangsfunktion = splitUebergangsfunktion(q3.gibUebergang(gibGeleseneZeichen()));
			anzahlSchritte++;
			ergebnis++;
		}
		if(uebergangsfunktion[0].equals("0") || uebergangsfunktion[1].equals("_")) {
			zustandsUebergangQ3zuQ4();
		}
		else {
			zustandsUebergangQ3zuQ5();
		}
	}
	
	public void zustand4() {
		uebergangsfunktion = splitUebergangsfunktion(q4.gibUebergang(gibGeleseneZeichen()));
		while(uebergangsfunktion[2].equals("0")){
			schreibeZeichenAufBaender(uebergangsfunktion);
			bewegeSchreibkoepfe(uebergangsfunktion);
			baenderAusgeben();
			uebergangsfunktion = splitUebergangsfunktion(q4.gibUebergang(gibGeleseneZeichen()));
			anzahlSchritte++;
			ergebnis++;
		}
		if(uebergangsfunktion[0].equals("0")) {
			zustandsUebergangQ4zuQ3();
		}
		else{
			zustandsUebergangQ4zuQ5();
		}
	}
	
	private void zustand5() {
		band1.bandinhaltAusgeben();
		band2.bandinhaltAusgeben();
		band3.bandinhaltAusgeben();
		System.out.println("Wort akzeptiert");
		System.out.println("Ergebnis: " + ergebnis);
		System.out.println("Anzahl Schritte: " + anzahlSchritte);

	}
	
	private String[] splitUebergangsfunktion(String uebergangsfunktion) {
		return uebergangsfunktion.split("");
	}
	
	private void schreibeZeichenAufBaender(String[] uebergangsfunktion) {
		band1.schreibeAufBand(uebergangsfunktion[0]);
		band2.schreibeAufBand(uebergangsfunktion[1]);
		band3.schreibeAufBand(uebergangsfunktion[2]);
	}
	
	private void baenderAusgeben() {
		if(stepByStep) {
			band1.bandinhaltAusgeben();
			band2.bandinhaltAusgeben();
			band3.bandinhaltAusgeben();
			System.out.println();
		}
	}
	
	private void bewegeSchreibkoepfe(String[] uebergangsfunktion) {
		band1.bewegeSchreibkopf(uebergangsfunktion[3]);
		band2.bewegeSchreibkopf(uebergangsfunktion[4]);
		band3.bewegeSchreibkopf(uebergangsfunktion[5]);
	}
	
	private void zustandsUebergangQ0zuQ1() {
		uebergangsfunktion = splitUebergangsfunktion(q0.gibUebergang(gibGeleseneZeichen()));
		schreibeZeichenAufBaender(uebergangsfunktion);
		bewegeSchreibkoepfe(uebergangsfunktion);
		ersetzeAlteMitNeuenZustaenden("q1");
		baenderAusgeben();
		anzahlSchritte++;
		zustand1();
	}
	
	private void zustandsUebergangQ1zuQ2() {
		uebergangsfunktion = splitUebergangsfunktion(q1.gibUebergang(gibGeleseneZeichen()));
		schreibeZeichenAufBaender(uebergangsfunktion);
		bewegeSchreibkoepfe(uebergangsfunktion);
		ersetzeAlteMitNeuenZustaenden("q2");
		baenderAusgeben();
		anzahlSchritte++;
		zustand2();
	}
	
	private void zustandsUebergangQ2zuQ3() {
		uebergangsfunktion = splitUebergangsfunktion(q2.gibUebergang(gibGeleseneZeichen()));
		schreibeZeichenAufBaender(uebergangsfunktion);
		bewegeSchreibkoepfe(uebergangsfunktion);
		ersetzeAlteMitNeuenZustaenden("q3");
		baenderAusgeben();
		anzahlSchritte++;
		zustand3();
	}
	
	private void zustandsUebergangQ3zuQ4() {
		uebergangsfunktion = splitUebergangsfunktion(q3.gibUebergang(gibGeleseneZeichen()));
		schreibeZeichenAufBaender(uebergangsfunktion);
		bewegeSchreibkoepfe(uebergangsfunktion);
		ersetzeAlteMitNeuenZustaenden("q4");
		baenderAusgeben();
		anzahlSchritte++;
		zustand4();
	}
	
	private void zustandsUebergangQ3zuQ5() {
		uebergangsfunktion = splitUebergangsfunktion(q3.gibUebergang(gibGeleseneZeichen()));
		schreibeZeichenAufBaender(uebergangsfunktion);
		bewegeSchreibkoepfe(uebergangsfunktion);
		ersetzeAlteMitNeuenZustaenden("q5");
		baenderAusgeben();
		anzahlSchritte++;
		zustand5();
	}
	
	private void zustandsUebergangQ4zuQ3() {
		uebergangsfunktion = splitUebergangsfunktion(q4.gibUebergang(gibGeleseneZeichen()));
		schreibeZeichenAufBaender(uebergangsfunktion);
		bewegeSchreibkoepfe(uebergangsfunktion);
		ersetzeAlteMitNeuenZustaenden("q3");
		baenderAusgeben();
		anzahlSchritte++;
		zustand3();
	}
	
	private void zustandsUebergangQ4zuQ5() {
		uebergangsfunktion = splitUebergangsfunktion(q4.gibUebergang(gibGeleseneZeichen()));
		schreibeZeichenAufBaender(uebergangsfunktion);
		bewegeSchreibkoepfe(uebergangsfunktion);
		ersetzeAlteMitNeuenZustaenden("q5");
		baenderAusgeben();
		anzahlSchritte++;
		zustand5();
	}
	
	private String gibGeleseneZeichen() {
		String geleseneZeichen = "";
		geleseneZeichen += band1.gibZeichenBeiPosition();
		geleseneZeichen += band2.gibZeichenBeiPosition();
		geleseneZeichen += band3.gibZeichenBeiPosition();
		return geleseneZeichen;
	}
	
	private void ersetzeAlteMitNeuenZustaenden(String zustand) {
		band1.ersetzeAltenZustandMitNeuem(zustand);
		band2.ersetzeAltenZustandMitNeuem(zustand);
		band3.ersetzeAltenZustandMitNeuem(zustand);
	}
}
