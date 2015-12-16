import java.util.ArrayList;
import java.util.List;

/*
 * This class initializes and modifies the spreadsheet itself.
 * The spreadsheet is initialized with a number of rows, columns, 
 * and spaces in between each separate cell.
 */

public class CellMatrix {
	private static int rows;
	private static int columns;
	private static int spaces;
	private static char column;
	private static String input;
	private static Cell[][] cellgrid;
	private static List<Cell> cellTrackList = new ArrayList<Cell>();
	private static CellMatrix instance = new CellMatrix(8, 8, 8);
	
	/*
	 * The constructor has purposely been set as private to prevent 
	 * multiple instances of the matrix and force the user to only
	 * use one instance.
	 */
	
	private CellMatrix(int rows, int columns, int spaces){
		this.rows = rows;
		this.columns = columns;
		this.spaces = spaces;
		this.column = column;
		column = (char) ('A' + columns);
		cellgrid = new Cell[rows * 2 + 2][columns + 1];
	}
	
	/*
	 * The getSpaces method returns the number of spaces
	 * between the cells.
	 */
	public static List<Cell> getCellTrackList(){
		return cellTrackList;
	}
	public static int getSpaces(){
		return spaces;
	}
	
	/*
	 * The getRows method returns the number of rows in the
	 * spreadsheet.
	 */
	
	public static int getRows(){
		return rows * 2;
	}
	
	/*
	 * The getColumn method returns the number of columns in the
	 * spreadsheet.
	 */
	
	public static int getColumn(){
		return columns + 1;
	}
	public String getInput(){
		return input;
	}
	
	/*
	 * The getInstance method returns the only instance of the
	 * CellMatrix constructor.
	 */
	
	public static CellMatrix getInstance(){
		return instance;
	}
	
	/*
	 * The getCell method returns the data of a specific cell in the 
	 * spreadsheet. 
	 */
	
	public Cell getCell(int column, int row){
		return cellgrid[row * 2][column + 1];

	}
	
	/*
	 * The create method initializes all of the cells with a null value
	 * of white space.
	 */
	
	public void create(){
		String space = "                                                                                                                                                                                                     ";
		String dashes = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		for (int i = 0; i < cellgrid.length; i++){
			for (int j = 0; j < cellgrid[0].length; j++){
				cellgrid[i][j] = new Cell();
				cellgrid[i][j].setValue("");
			}
		}
	}
	
	/*
	 * The print method prints the entire spreadsheet to the console as
	 * well as all updated values.
	 */
	
	public void print(){
		String space = "                                                                                                                                                                                                     ";
		String dashes = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		for (int i = 0; i < cellgrid.length; i++){
			for (int j = 0; j < cellgrid[0].length; j++){
				
				if (i % 2 != 0){
					System.out.print(dashes.substring(0, spaces) + "+");
					continue;
				}
				
				if (i > 0 && j == 0 && spaces % 2 != 0 && i <= 18){
					
					System.out.print(space.substring(0, spaces /2) + i / 2 + space.substring(0, spaces / 2) + "|");
					continue;	
				}
				if (i > 0 && j == 0 && spaces % 2 != 0 && i > 18){
					System.out.print(space.substring(0, spaces /2) + i / 2 + space.substring(0, spaces / 2 - 1) + "|");
					continue;
				}
				if (i > 0 && j == 0 && spaces % 2 == 0 && i <= 18){
					System.out.print(space.substring(0, spaces/2 - 1) + i / 2 + space.substring(0, spaces / 2) + "|");
					continue;
				}
				if (i > 0 && j == 0 && spaces % 2 == 0 && i > 18){
					System.out.print(space.substring(0, spaces / 2 - 1) + i / 2 + space.substring(0, spaces / 2 - 1) + "|");
					continue;
				}
						
					
						
					
				
				if (j > 0 && i == 0){
					char column = (char)('A' + j - 1);
					if (spaces % 2 != 0){
						System.out.print(space.substring(0, spaces/2) + column + space.substring(0, spaces / 2) + "|");
						
						if (columns > 26){
							
							System.out.print(space.substring(0, spaces/2) + column + space.substring(0, spaces / 2));

							
						}
						

						continue;
					}
					else if (spaces % 2 == 0){
						System.out.print(space.substring(0, spaces/2 - 1) + column + space.substring(0, spaces / 2)  + "|");
						if (columns > 26){
							System.out.print(space.substring(0, spaces/2 - 1) + column + space.substring(0, spaces / 2));

							
						}
						continue;
						
						
						
					}
					
				}
				int length = cellgrid[i][j].getDisplayValue();
				
				if (spaces % 2 != 0 && length % 2 == 0){
					System.out.print(space.substring(0, (spaces - length) / 2) + cellgrid[i][j] +  space.substring(0,  (spaces - length) / 2 + 1) + "|");
				}
				else if (spaces % 2 != 0 && length % 2 != 0){
					System.out.print(space.substring(0, (spaces - length) / 2) + cellgrid[i][j] +  space.substring(0,  (spaces - length) / 2) + "|");

				}
				
				else if (spaces % 2 == 0 && length % 2 == 0){
					System.out.print(space.substring(0, (spaces - length) / 2) + cellgrid[i][j] +  space.substring(0,  (spaces - length) / 2) + "|");
				}
				else if (spaces % 2 == 0 && length % 2 != 0){
					System.out.print(space.substring(0, (spaces - length) / 2) + cellgrid[i][j] +  space.substring(0,  (spaces - length) / 2 + 1) + "|");
				}
				

			}
			System.out.println();
		}

	}
	
	
	/*
	 * The reset method reinitializes the single CellMatrix instance with
	 * new parameters.
	 */
	
	public void reset(int row, int column, int spaces){
		instance = new CellMatrix(row, column, spaces);
	}
	
	
	/*
	 * The setCell method set values to cells based on the specific inputs.
	 */
	
	public void setCell(int column, int rows, String input) {
		String space = "                                                                                                                                                                                                       ";
		cellgrid[rows * 2][column + 1] = CellParser.parse(input);
		if (input.contains("\"")){
			int s = input.indexOf("\"");
			input = input.substring(s + 1, input.length() - 1);
		}
		cellgrid[rows * 2][column + 1].setValue(input);
		int length = cellgrid[rows * 2][column + 1].getDisplayValue();
		if (length > getSpaces()){
			cellgrid[rows * 2][column + 1].getValue();
		}
		
	}
	
	/*
	 * The clear method refreshes the entire spreadsheet with null Cell
	 * values.
	 */
	
	public void clear(){
		for (int i = 0; i < cellgrid.length; i++){
			for (int j = 0; j < cellgrid[0].length; j++){
				cellgrid[i][j] = new Cell();
				cellgrid[i][j].setValue("");
			}
		}
	}
	
	/*
	 * The recall method prints the value of a specific cell to the 
	 * console.
	 */
	
	public void recall(int column, int row){
		String out = (cellgrid[row * 2][column + 1].getValue());
		if (cellgrid[row * 2][column + 1] instanceof FormulaCell){
			out = cellgrid[row * 2][column + 1].getFormula();
		}
		System.out.println(out);
	}
	
	/*
	 * The average method returns the average double value of the specific 
	 * box of cells entered.
	 */
	
	public double average(int startColumn, int startRow, int endColumn, int endRow){
		double sum = 0;
		int counter = 0;
		for (int i = startRow; i <= endRow; i++){
			for (int j = startColumn; j <= endColumn; j++){
				sum += getCell(j, i).getDoubleValue();
				counter++;
			}
		}
		return sum / counter;
	}
	
	/*
	 * The sum method functions the same as the average method except that it
	 * returns the sum of the double values.
	 */
	
	public double sum(int startColumn, int startRow, int endColumn, int endRow){
		double sum = 0;
		for (int i = startRow; i <= endRow; i++){
			for (int j = startColumn; j <= endColumn; j++){
				sum += getCell(j, i).getDoubleValue();
			}
		}
		return sum;
	}
	
	/*
	 * The sort method assigns a number to all cells. The purpose of this is to test
	 * that all cells can be set without an error.
	 */
	
	public void sort(){
		double k = 0;
		for (int i = 2; i < cellgrid.length; i++){
			for (int j = 1; j < cellgrid[0].length; j++){
				if (i % 2 != 0){
					continue;
				}
				cellgrid[i][j] = new DoubleCell();
				String integer = Double.toString(k);
				cellgrid[i][j].setValue(integer);
				k++;
			}
		}
	}
		
	/*
	 * The getSaveData method returns a string array of all of the cell
	 * values for PersistenceHelper.
	 */
	
	public String[] getSaveData(){
		String[] data = new String[((cellgrid.length - 2) / 2) * (cellgrid[0].length - 1)];
		int k = 0;
		for (int i = 1; i < cellgrid.length / 2; i++){
			for (int j = 0; j < cellgrid[0].length - 1; j++){
				data[k] = cellgrid[i * 2][j + 1].getValue();
				k++;
			}
		}
		return data;
	}
	
	/*
	 * The loadFrom method sets the value of cells from the array parameter.
	 */
	
	public void loadFrom(String[] dataLoad){
		CellParser parse = new CellParser();
		int k = 0;
		if ((((cellgrid.length - 2) / 2) * (cellgrid[0].length - 1)) == dataLoad.length){
			for (int i = 1; i < cellgrid.length / 2; i++){
				for (int j = 0; j < cellgrid[0].length - 1; j++){
					cellgrid[i * 2][j + 1] = parse.parse(dataLoad[k]);
					cellgrid[i * 2][j + 1].setValue(dataLoad[k]);
					k++;
				}
			}
		}
	}
	
}
