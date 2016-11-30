package Version2;
import java.util.ArrayList;
import java.util.Scanner;

public class Start {
	
	public static void main(String[] args) {
		new Start().initialisiere();
	}
	
	private void initialisiere() {

		String eingabe = leseEingabe();
		
		Converter c = new Converter();
		
		String konvertierteEingabe = c.convertInput(eingabe);
		
		ArrayList<String> inputArray = moveStringToArrayList(konvertierteEingabe);
		Maschine maschine = new Maschine(inputArray);
		maschine.run();
	}

	private String leseEingabe() {
		System.out.println("Input eingeben: ");
		Scanner sc = new Scanner(System.in);
		String eingabe = sc.nextLine();
		sc.close();
		return eingabe;
	}

	private ArrayList<String> moveStringToArrayList(String input) {

		String[] eingabeArrayTemp = input.split("");
		ArrayList<String> inputArrayList = new ArrayList<String>();
		// convert string array to int array
		for (int i = 0; i < eingabeArrayTemp.length; i++) {
			inputArrayList.add(eingabeArrayTemp[i]);
		}
		return inputArrayList;
	}
}
