package Version2;

public class Converter {

	public Converter() {
		
	}
	
	public String convertInput(String input) {
		
		String[] werte = input.split("\\*");
		
		String convertedInput = "";
		
		for(int i = 0; i < Integer.parseInt(werte[0]); i++) {
			convertedInput += "0";
		}
		convertedInput += "1";
		
		for(int i = 0; i < Integer.parseInt(werte[1]); i++) {
			convertedInput += "0";
		}
		
		System.out.println(convertedInput);
		
		return convertedInput;
	}

}
