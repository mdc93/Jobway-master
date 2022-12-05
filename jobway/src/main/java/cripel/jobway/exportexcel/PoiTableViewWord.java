package cripel.jobway.exportexcel;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


import javafx.scene.control.TableView;

/**
 * Class to parse a {@link TableView} and writing it in Word
 *
 */
public class PoiTableViewWord {

	/** The tableView to parse */
	private final TableView<Object> tableView;
	private final XWPFDocument document;
	
	private enum StringBoolean{
		YES("Oui"),
		NO("Non"),
		NULL("");
		private String value;
		
		StringBoolean(String value) {
			this.value=value;
		}
		
		@Override
		public String toString(){
			return this.value;
		}
	}

	public PoiTableViewWord(TableView<Object> tableView, XWPFDocument document) {
		this.tableView = tableView;
		this.document = document;
	}

	public void exportWord() {
		document.createParagraph();
		XWPFTable table = createTable();
		fillTable(table);
		document.createParagraph();
	}

	/**
	 * Create a word {@link XWPFTable} with header of the {@link TableView}
	 *
	 * @return the table
	 */
	private XWPFTable createTable() {
		XWPFTable table = document.createTable();
		XWPFTableRow row = table.getRow(0);
		for (int i = 0; i < tableView.getColumns().size(); i++) {
			if (row.getCell(i) != null)
				row.getCell(i).setText(tableView.getColumns().get(i).getText());
			else
				row.addNewTableCell().setText(tableView.getColumns().get(i).getText());
			row.getCell(i).getParagraphs().get(0).getRuns().get(0).setBold(true);
		}
		return table;
	}

	/**
	 * Method to fill the {@link XWPFTable} with {@link TableView} data.
	 *
	 * @param table {@link XWPFTable} to fill
	 */
	private void fillTable(XWPFTable table) {

		for (int i = 0; i < tableView.getItems().size(); i++) {
			XWPFTableRow row = table.createRow();
			for (int j = 0; j < tableView.getColumns().size(); j++) {
				Object cell = tableView.getColumns().get(j).getCellData(i);
				createCell(row, cell, j);
			}
		}
	}

	/**
	 * Set a the text of {@link TableView} cell to the same coordinate of
	 * {@link XWPFTableRow}
	 *
	 * @param row  where the cells are created
	 * @param cell Cell content of {@link TableView}
	 * @param col  column of the cell
	 */
	private void createCell(XWPFTableRow row, Object cell, int col) {
		if (row.getCell(col) != null)
			row.getCell(col).setText(stringValue(cell));
		else
			row.addNewTableCell().setText(stringValue(cell));

	}

	/**
	 * Get the appropriate string value of an object {@link StringBoolean} if boolean
	 *
	 * @param object
	 * @return the {@link String#valueOf(object)} of the object
	 */
	private String stringValue(Object object) {
		if (object instanceof Boolean) {
			if (String.valueOf(object) == null) {
				return StringBoolean.NULL.toString();
			} else if (Boolean.TRUE.equals(object)) {
				return StringBoolean.YES.toString();
			} else {
				return StringBoolean.NO.toString();
			}
		} else if (String.valueOf(object).contains("null")) {
			return "";
		} else {
			return String.valueOf(object);
		}
	}
}
