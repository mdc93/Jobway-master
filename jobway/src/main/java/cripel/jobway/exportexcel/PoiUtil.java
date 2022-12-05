package cripel.jobway.exportexcel;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtil {
	
	private PoiUtil(){}
	
	/**
	 * Create cell in an excel file with an hashmap
	 *
	 * @param data     linked hash map with column key and content value
	 * @param workbook
	 * @param sheet
	 */
	public static void export(LinkedHashMap<Integer, Object> data, XSSFWorkbook workbook, XSSFSheet sheet) {
		Row row = sheet.createRow(sheet.getLastRowNum() + 1);
		for (Map.Entry<Integer, Object> entry : data.entrySet()) {
			createCell(workbook, row, entry.getKey(), entry.getValue());

		}

	}

	/**
	 * Create a cell in an Excel file
	 *
	 * @param wb          the workbook
	 * @param row         the excel row
	 * @param columnCount the excel column
	 * @param value       the value to create
	 */
	public static Cell createCell(XSSFWorkbook wb, Row row, int columnCount, Object value) {

		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Date) {
			cell.setCellValue((Date) value);
			cell.setCellStyle(styleDate(wb));
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else {
			cell.setCellValue(String.valueOf(value));
			if (cell.getStringCellValue().length() == 4 && cell.getStringCellValue().contains("null")) {
				cell.setCellValue("");
			}
		}

		return cell;
	}

	public static void resizeColumn(XSSFSheet sheet, int lastColumn) {
		for (int i = 0; i < lastColumn; i++) {
			sheet.autoSizeColumn(i);
		}

	}

	private static CellStyle styleDate(XSSFWorkbook wb) {
		CellStyle styleDate = wb.createCellStyle();
		XSSFDataFormat df = wb.createDataFormat();
		styleDate.setDataFormat(df.getFormat("dd/mm/yyyy"));
		return styleDate;
	}

	public static CellStyle styleAllBorder(CellStyle style, BorderStyle border) {
		style.setBorderBottom(border);
		style.setBorderLeft(border);
		style.setBorderRight(border);
		style.setBorderTop(border);
		return style;
	}
}
