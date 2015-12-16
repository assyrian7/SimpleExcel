JCC = javac
JFLAGS = -g

default: Cell.class CellMatrix.class CellParser.class DateCell.class StringCell.class DoubleCell.class FormulaCell.class NullCell.class PersistenceHelper.class TextExcel.class

Cell.class: Cell.java
	$(JCC) $(JFLAGS) Cell.java

CellMatrix.class: CellMatrix.java
	$(JCC) $(JFLAGS) CellMatrix.java

CellParser.class: CellParser.java
	$(JCC) $(JFLAGS) CellParser.java

DateCell.class: DateCell.java
	$(JCC) $(JFLAGS) DateCell.java

StringCell.class: StringCell.java
	$(JCC) $(JFLAGS) StringCell.java

DoubleCell.class: DoubleCell.java
	$(JCC) $(JFLAGS) DoubleCell.java

FormulaCell.class: FormulaCell.java
	$(JCC) $(JFLAGS) FormulaCell.java

NullCell.class: NullCell.java
	$(JCC) $(JFLAGS) NullCell.java

PersistenceHelper.class: PersistenceHelper.java
	$(JCC) $(JFLAGS) PersistenceHelper.java

TextExcel.class: TextExcel.java
	$(JCC) $(JFLAGS) TextExcel.java

clean:
	rm *.class