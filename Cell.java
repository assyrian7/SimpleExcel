

public class Cell {
	public String input;
	private int rows;
	private int column;
	private double inputs;
	public Cell(){
		
	}
	public void setValue(String input){
		this.input = input;
	}
	public String getInputValue(){
		return null;
	}
	public void clear(){
		
	}
	public String getValue(int c, int rows){
		return null;
	}
	public double getValueAsDouble() throws Exception{
		throw new Exception("not Implemented");
	}
	public int getDisplayValue(){
		return input.length();
	}
	public double getDoubleValue(){
		return 0;
	}
	public String getValue(){
		setValue(input);
		return input;
	}
	public String getFormula(){
		return null;
	}
	public void setValue(int column, int row, String input){
		
	}
	public String toString(){
		setValue(input);
		return input;
	}
	public void setValue(double inputs){
		this.inputs = inputs;
	}
	
	public void setValue(int month, int day, int year){
	}
	
}
