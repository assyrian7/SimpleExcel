import java.text.SimpleDateFormat;
import java.util.*;

/*
 * This class is in charge of handling date inputs. 
 */

public class DateCell extends Cell{
	private String input;
	private Date date;
	private SimpleDateFormat formatter;
	public DateCell(){	
		formatter = new SimpleDateFormat("mm/dd/yyyy");
	}
	public void setValue(String input){
		this.input = input;
	}
	public int getDisplayValue(){
		setValue(input);
		return input.length();
	}
	public String getValue(){
		setValue(input);
		return input;
	}
	public String toString(){
		setValue(input);
		return input;
	}
}
