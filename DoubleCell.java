
/*
 * This class is in charge of handling number inputs.
 */

public class DoubleCell extends Cell{
	private double input;
	

	public DoubleCell(){
	}
	public void setValue(String input){
		this.input = Double.parseDouble(input);
	}
	public String getValue(){
		setValue(input);
		return Double.toString(input);
	}
	public double getDoubleValue(){
		setValue(input);
		return input;
	}
	public int getDisplayValue(){
		String in = Double.toString(input);
		int length = in.length();
		return length;
	}
	public double getLengthValue(int length){
		return length;
	}
	
	public String toString(){
		setValue(input);
		return input + "";
	}
}
