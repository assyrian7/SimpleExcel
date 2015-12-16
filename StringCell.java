
/*
 * This class is in charge of handling string inputs.
 */

public class StringCell extends Cell{
	private String input;
	private String space = "                                                                     ";
	public StringCell(){
	}
	public void setValue(String input){
		this.input = input;
	}
	
	public String getValue(){
		setValue(input);
		return input;
	}
	public int getDisplayValue(){
		return input.length();
	}
	public String toString(){
		setValue(input);
		return input;
	}
	
}
