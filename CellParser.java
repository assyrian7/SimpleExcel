import java.text.SimpleDateFormat;
import java.util.Date;


/*
 * This class handles Cell parsing between numbers, dates, strings,
 * and formulas.
 */

public class CellParser {
	private static CellMatrix matrix;
	private static String input;
	public CellParser(){
		matrix = CellMatrix.getInstance();
	}
	
	/*
	 * The isString method determines if an input is a StringCell.
	 */
	
	public static boolean isString(String input){
		if (!input.contains("\"")){
			return false;
		}
		return true;
	}
	
	/*
	 * The isDouble method determines if an input is a DoubleCell.
	 */
	
	public static boolean isDouble(String input){
		try {
			Double.parseDouble(input);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	/*
	 * The isFormula method determines if an input is a FormulaCell.
	 */
	
	public static boolean isFormula(String input){
		if (input.contains("*") || input.contains("/") || input.contains("+") || input.contains("-") || (input.substring(0, 1).equals("(") && input.substring(input.length() - 1).equals(")"))){
			return true;
		}
		return false;
	}
	
	/*
	 * The isDate method determines if an input is a DateCell.
	 */
	
	public static boolean isDate(String input){
		Date date;
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
		try{
			date = formatter.parse(input);
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	/*
	 * The isCell method determines if an input is an ordinary
	 * Cell.
	 */
	
	public static boolean isCell(String input){
		if (input.equals("")){
			return true;
		}
		return false;
	}
	
	public static String getInput(){
		return input;
	}
	
	/*
	 * The Cell parse method parses the input and determines which
	 * type of Cell it will be.
	 */
	
	public static Cell parse (String input) {
		if (isDouble(input)){
			return new DoubleCell();
		}
		
		else if (isDate(input)){
			return new DateCell();
		}
		
		else if (isString(input)){
			return new StringCell();
		}
		else if (isCell(input)){
			return new Cell();
		}
		else if (isFormula(input)){
			return new FormulaCell();
		}
		
		
		else{
			return new NullCell();
		}
	}
}
