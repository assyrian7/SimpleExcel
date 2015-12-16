import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class is in charge of handling formula inputs.
 */

public class FormulaCell extends Cell{
	private String input;
	private CellMatrix matrix;
	private String formula;
	private String result;
	public FormulaCell(){
		matrix = CellMatrix.getInstance();
		
	}
	
	/*
	 * The operandParse method operandParses a formula into an array.
	 */
	
	private ArrayList<String> parse(String b){
		
		ArrayList<String> tokens = new ArrayList<String>();
		Scanner s = new Scanner(b);
		while (s.hasNext()){
			String token = s.next();
			tokens.add(token);
		}
		s.close();
		return tokens;
		
	}
	
	/*
	 * The operandParse method parses every token in the formula string.
	 */
	public double operandParse(String s){
		int row = 100;
		int column = 100;
		if (s.charAt(0) >= 'A' && Integer.parseInt(s.substring(1)) < matrix.getRows()){
			
			column = s.charAt(0) - 'A';
			row = Integer.parseInt(s.substring(1));
			List<Cell> trackList = matrix.getCellTrackList();
			//System.out.println("Checking" + this);
			if (trackList.indexOf(this) >= 0){
				throw new IllegalArgumentException("Circular reference detected");
				//trackList.remove(this);

			}
			Cell cell = matrix.getCell(column, row);

			trackList.add(this);
			try{
				double d = cell.getDoubleValue();
				matrix.getCellTrackList().remove(this);
				return d;
			} catch(Exception e){
				matrix.getCellTrackList().remove(this);
				throw e;
			}
			
		}
		else{
			double d = Double.parseDouble(s);
			return d;
		}
	}
	
	public boolean isGridCell(String input) {
		if (input.length() == 2 && input.charAt(0) - 'A' < matrix.getColumn() && Integer.parseInt(input.substring(1)) < matrix.getRows() && !input.substring(0, 1).equals("-")){
			return true;			
		}
		
		return false;
	}
	
	/*
	 * The evaluate method evaluates the operandParsed string array.
	 */
	
	private String evaluate(String a){
		String result = "";
		formula = a;
		ArrayList<String> tokens = parse(a);
		ArrayList<String> outcome = new ArrayList<String>();
		int i = 0;
		while ( i < tokens.size()){
			if (tokens.get(i).equals("*")){

				String lef = outcome.get(outcome.size() - 1);
				
				double left = operandParse(lef);
				
				

				
				i++;
				String l = tokens.get(i);
				double right = operandParse(l);
				outcome.set(outcome.size() - 1, Double.toString(left * right));
			}
			else if (tokens.get(i).equals("/")){

				String lef = outcome.get(outcome.size() - 1);
				
				double left = operandParse(lef);

				
				i++;
				String l = tokens.get(i);
				double right = operandParse(l);
				outcome.set(outcome.size() - 1, Double.toString(left / right));


			}
			else{
				outcome.add(tokens.get(i));
			}
			i++;
		}
		tokens = outcome;
		outcome = new ArrayList<String>();
		i = 0;
		while ( i < tokens.size()){
			if (tokens.get(i).equals("+")){

				String lef = outcome.get(outcome.size() - 1);
				double left = operandParse(lef);
				
				i++;
				String l = tokens.get(i);
				double right = operandParse(l);
				
				
					
					

				
				outcome.set(outcome.size() - 1, Double.toString(left + right));

			}
			else if (tokens.get(i).equals("-")){

				String lef = outcome.get(outcome.size() - 1);
				double left = operandParse(lef);
				
				
				i++;
				String l = tokens.get(i);
				double right = operandParse(l);
				
				
				outcome.set(outcome.size() - 1, Double.toString(left - right));
			}

			
			else{
				outcome.add(tokens.get(i));
			}
			i++;
		}
		result = outcome.get(0);
		
		if (a.length() == 2){
			result = Double.toString(operandParse(a));
		}
		
		return result;
	}
	
	/*
	 * The value method returns the double value for any referenced 
	 * cells.
	 */
	
	private double value (String input){
		int column = input.charAt(0) - 'A';
		int row = Integer.parseInt(input.substring(1));
		return matrix.getCell(column, row).getDoubleValue();
	}
	public double getDoubleValue(){
		return Double.parseDouble(evaluate(input.substring(1, input.length() - 1)));
	}
	public void setValue(String in){
		input = in;
		String inp = "";
		if (in.indexOf("(") == 0 && in.indexOf(")") == in.length() - 1){
			inp = in.substring(1, in.length() - 1);
		}
		String result = evaluate(inp);

		if (result.length() > CellMatrix.getSpaces()){
			result = result.substring(0, CellMatrix.getSpaces());
		}
		if (isGridCell(in)){
			result = Double.toString(value(in));
		}
		this.result = result;
	}
	public int getDisplayValue(){
		setValue(input);
		return result.length();
	}
	
	/*
	 * The getFormula method returns the formula itself to see what
	 * is being evaluated.
	 */
	
	public String getFormula(){
		return input;
	}
	
	public String getValue(){
		setValue(input);

		return result;
	}
	public String toString(){
		setValue(input);
		return result;
	}
}
