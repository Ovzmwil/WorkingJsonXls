package com.working.project.util;

import java.util.List;

import jxl.CellView;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

@SuppressWarnings("deprecation")
public class CellController {

    // WritableSheet sheet;
    //
    // public CellController(WritableSheet sheet) {
    // this.sheet = sheet;
    // }

    public void addCell(WritableSheet sheet, int column, int row, String text) {
	while(sheet.getCell(column, row) != null ) {
	    row=+1;
	}
	Label label = new Label(column, row, text);
	try {
	    sheet.addCell(label);
	    CellView cell = sheet.getColumnView(column);
	    cell.setAutosize(true);
	    sheet.setColumnView(column, cell);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void addCell(WritableSheet sheet, int column, int row, Object obj) {
	Label label;
	
	while(sheet.getCell(column, row) != null ) {
	    row=+1;
	}

	if (obj == null) {
	    label = new Label(column, column, "");
	} else {
	    label = new Label(column, column, obj.toString());
	}

	try {
	    sheet.addCell(label);
	    CellView cell = sheet.getColumnView(column);
	    cell.setAutosize(true);
	    sheet.setColumnView(column, cell);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void writeHeader(List<String> list, WritableSheet sheet) throws WriteException {
	WritableFont writableFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
	WritableCellFormat cellFormat = new WritableCellFormat(writableFont);

	cellFormat.setWrap(true);
	cellFormat.setAlignment(Alignment.CENTRE);

	try {
	    for (int i = 0; i < list.size(); i++) {
		Label label = new Label(i, 0, list.get(i), cellFormat);
		sheet.addCell(label);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void writeHeader(List<String> list, WritableSheet sheet, WritableCellFormat cellFormat)
	    throws WriteException {
	try {
	    for (int i = 0; i < list.size(); i++) {
		Label label = new Label(i, 0, list.get(i), cellFormat);
		sheet.addCell(label);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
