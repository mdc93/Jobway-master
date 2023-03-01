package cripel.jobway.exportexcel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class PoiTableViewExcel {

	private TableView<?> tableView;
	private final XSSFWorkbook workbook;

	public PoiTableViewExcel(TableView<?> tableView, XSSFWorkbook workbook) {
		this.tableView = tableView;
		this.workbook = workbook;

	}

	/**
	 * Create header and export {@link #tableView} content to excel
	 */
	public void exportExcel() {
		XSSFSheet sheet = createHeader();
		fillTable(sheet);
	}

	/**
	 * Create header in excel with {@link TableView}'s headers
	 */
	private XSSFSheet createHeader() {
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow row = sheet.createRow(0);
		for (int i = 0; i < tableView.getColumns().size(); i++) {
			Cell cell = PoiUtil.createCell(workbook, row, i, tableView.getColumns().get(i).getText());
			cell.setCellStyle(PoiUtil.styleAllBorder(cell.getCellStyle(), BorderStyle.THIN));
			sheet.setColumnWidth(i, 20 * 256);
		}
		return sheet;
	}

	/**
	 * Method to fill the {@link XSSFSheet} with {@link TableView} data.
	 *
	 * @param sheet {@link XSSFSheet} to fill
	 */
	private void fillTable(XSSFSheet sheet) {
		for (int i = 0; i < tableView.getItems().size(); i++) {
			XSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < tableView.getColumns().size(); j++) {
				Object cell = tableView.getColumns().get(j).getCellData(i);
				if (cell != null && !(cell instanceof Button)) {
					PoiUtil.createCell(workbook, row, j, cell);
				}
			}
		}
		// add a line "Total", SUM of all colonnes 
		int lastRow = sheet.getLastRowNum();
		XSSFRow rowTotal = sheet.createRow(lastRow + 1);
		PoiUtil.createCell(workbook, rowTotal, 0, "Total en heure");

		Double total = 0.0;
		for (int i = 0; i < tableView.getItems().size(); i++) {
			
		  Object cell = tableView.getColumns().get(1).getCellData(i);
		  if (cell instanceof Number) {
		    total += ((Number) cell).doubleValue();
		  }
		}
		
		total = total/ 60.0 ;
		
		String formattedTotal = String.format("%.2f", total);
		
		PoiUtil.createCell(workbook, rowTotal, 1, formattedTotal);
		}
	
}
