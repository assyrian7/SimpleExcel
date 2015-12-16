import java.util.ArrayList;
import java.util.Scanner;

/*
 * Welcome to TextExcel.
 * The purpose of this to create a simple excel program that can handle inputs
 * of numbers, dates, strings, or formulas. This program also allows the user save
 * and load data.
 */


public class TextExcel {
	private static CellMatrix matrix = CellMatrix.getInstance();
	
	/*
	 * The main method reads user input and loops until the program terminates with
	 * the exit command.
	 */
	
	public static void main(String[] args) throws Exception{
		
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to TextExcel.");
		String input = "";
		System.out.println("Type (help) for a list of commands");

		while (!input.contains("exit")){
			userInput(input, matrix);
			System.out.print("Enter command: ");
			input = console.nextLine();
		}
		System.out.println("Goodbye");
		
	}
	

	/*
	 * The userInput method handles all commands and inputs.
	 */
	
	public static void userInput(String input, CellMatrix matrix) throws Exception{
		matrix = CellMatrix.getInstance();
		Scanner console = new Scanner(System.in);
		if (input.contains("new")){
			System.out.print("Enter rows: ");
			int row = console.nextInt();
			System.out.print("Enter columns: ");
			int columnz = console.nextInt();
			System.out.print("Enter cell space: ");
			int spacess = console.nextInt();
			matrix = CellMatrix.getInstance();
			matrix.reset(row, columnz, spacess);
			String dashes = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			matrix.create();
		}
		else if (input.contains("print")){
			matrix.print();
		}
		else if (input.contains("help")){
			help();
		}
		else if (input.contains("clear")){
			matrix.clear();
		}
		else if (input.contains("sort")){
			matrix.sort();
		}
		else if (input.indexOf("=") == 3) {
			int equal = input.indexOf("=");
			char col = input.charAt(0);
			int colu = (char) (col - 'A');
			int r = Integer.parseInt(input.substring(1, 2));
			String change = input.substring(equal + 2);
			
			int length = change.length();
			if (!CellParser.isFormula(change) && length > matrix.getSpaces()){
				change = change.substring(0, matrix.getSpaces());
			}
			
			if (change.equals("<empty>")){
				change = "";
			}
			if (input.length() == 2){
				int a = input.charAt(0) - 'A';
				int b = Integer.parseInt(input.substring(1));
				if (b < matrix.getRows() && a < matrix.getColumn()){
					change = matrix.getCell(a, b).getValue();
					System.out.println("[Grid Cell]");
				}
			}

			if (CellParser.isDouble(change)){
				System.out.println("[Number]");
			}
			else if (CellParser.isDate(change)){
				System.out.println("[Date]");
			}
			
			else if (CellParser.isString(change)){
				System.out.println("[String]");
			}
			else if (CellParser.isCell(change)){
				System.out.println("[Empty Cell]");
			}
			else if (CellParser.isFormula(change)){
				System.out.println("[Formula]");
			}
			
			else{
				change = matrix.getCell(colu, r).getValue();
				System.err.println("Invalid input");
			}
			matrix.setCell(colu, r, change);
		
		}
		else if (input.contains("return")){
			int space = input.indexOf(" ");
			char column = input.charAt(space + 1);
			int col = (char) (column - 'A');
			int row = Integer.parseInt(input.substring(space + 2));
			matrix.recall(col, row);
		}
		else if (input.contains("average")){
			System.out.print("Enter starting cell: ");
			String startCell = console.next();
			System.out.print("Enter ending cell: ");
			String endCell = console.next();
			int col1 = startCell.charAt(0) - 'A';
			int row1 = Integer.parseInt(startCell.substring(1));
			int col2 = endCell.charAt(0) - 'A';
			int row2 = Integer.parseInt(endCell.substring(1));
			System.out.println("The average double value is: " + matrix.average(col1, row1, col2, row2));
		}
		else if (input.contains("sum")){
			System.out.print("Enter starting cell: ");
			String startCell = console.next();
			System.out.print("Enter ending cell: ");
			String endCell = console.next();
			int col1 = startCell.charAt(0) - 'A';
			int row1 = Integer.parseInt(startCell.substring(1));
			int col2 = endCell.charAt(0) - 'A';
			int row2 = Integer.parseInt(endCell.substring(1));
			System.out.println("The sum double value is: " + matrix.sum(col1, row1, col2, row2));

		}
		else if (input.contains("save")) {
			int space = input.indexOf(" ");
			String filepath = input.substring(space + 1);
			PersistenceHelper.save(filepath, matrix);
		}
		else if (input.contains("load")){
			int space = input.indexOf(" ");
			String filepath = input.substring(space + 1);
			PersistenceHelper.load(filepath, matrix);
		}
		
	}
	
	/*
	 * The help method simply prints a list of commands with a description of each.
	 */
	
	public static void help(){
		System.out.println("Commands: ");
		System.out.println();
		System.out.println("new: creates a new spreadsheet");
		System.out.println();
		System.out.println("exit: terminates the program");
		System.out.println();
		System.out.println("print: prints the entire spreadsheet and any changed values");
		System.out.println();
		System.out.println("save: saves the data to a file of your choosing in your hdd/sdd \n   ex. save out.txt");
		System.out.println();
		System.out.println("load: loads data from a file to be implemented into a running spreadsheet \n   ex. load out.txt");
		System.out.println();
		System.out.println("clear: this will clear all inputed values and refresh the sheet");
		System.out.println();
		System.out.println("return: returns a value for specific cell \n   e.x return B5");
		System.out.println();
		System.out.println("Etc: to change values in spreadsheet there is a specific syntax");
		System.out.println();
		System.out.println("Strings must be in quotations \n   e.x. A1 = \"myName\"");
		System.out.println();
		System.out.println("Numbers can be in regular format \n   e.x. A1 = 23.5");
		System.out.println();
		System.out.println("Dates must be inputed as MM/DD/YYYY \n   e.x. A1 = 12/02/2014");
		System.out.println();
		System.out.println("Formulas can be inputed as regular formulas, formulas using cell values,\nor even setting a cell value directly to another cell.\n   ex. A1 = (2 + 2 * 4 - 3 / 1)\n\n   ex. A1 = (A1 + 3 * 4)\n\n   ex. A1 = (A2)");
		System.out.println();
		System.out.println("Anything else will be treated as a null value and will not be alter the spreadsheet's values.");
		System.out.println();
	}
}


