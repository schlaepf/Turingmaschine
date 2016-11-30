package Version2;

import java.util.HashMap;

public class Zustand {

	HashMap<String, String> uebergaenge;

	public Zustand(String zeichenGelesenVar1, String doVar1,
				   String zeichenGelesenVar2, String doVar2)
	{
		uebergaenge = new HashMap<String, String>();
		uebergaenge.put(zeichenGelesenVar1, doVar1);
		uebergaenge.put(zeichenGelesenVar2, doVar2);
	}
	
	
	public Zustand(String zeichenGelesenVar1, String doVar1,
			   	   String zeichenGelesenVar2, String doVar2,
			   	   String zeichenGelesenVar3, String doVar3)
	{
		uebergaenge = new HashMap<String, String>();
		uebergaenge.put(zeichenGelesenVar1, doVar1);
		uebergaenge.put(zeichenGelesenVar2, doVar2);
		uebergaenge.put(zeichenGelesenVar3, doVar3);
	}
	
	public Zustand(String zeichenGelesenVar1, String doVar1,
		   	   String zeichenGelesenVar2, String doVar2,
		   	   String zeichenGelesenVar3, String doVar3,
		   	   String zeichenGelesenVar4, String doVar4)
	{
		uebergaenge = new HashMap<String, String>();
		uebergaenge.put(zeichenGelesenVar1, doVar1);
		uebergaenge.put(zeichenGelesenVar2, doVar2);
		uebergaenge.put(zeichenGelesenVar3, doVar3);
		uebergaenge.put(zeichenGelesenVar4, doVar4);
	}

	public String gibUebergang(String geleseneZeichen) {
		return uebergaenge.get(geleseneZeichen);
	}

}
