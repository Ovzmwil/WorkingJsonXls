package com.working.project.formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.working.project.json.JsonClass;
import com.working.project.util.CellController;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class JsonToXls {

    // static WorkbookSettings workbookSettings = new WorkbookSettings();
    static BufferedReader reader = null;
    static Gson gson = new Gson();
    static CellController control = new CellController();

    public static void main(String args[]) throws WriteException {
	try {
	    // workbookSettings.setEncoding("UTF-8");

	    reader = new BufferedReader(new FileReader("[JSONPATH]"));
	    JsonClass jsonInfo = gson.fromJson(reader, JsonClass.class);
	    // WritableWorkbook workbook = Workbook.createWorkbook(new File("output.xls"),
	    // workbookSettings);
	    WritableWorkbook workbook = Workbook.createWorkbook(new File("output.xls"));

	    WritableSheet sheet = workbook.createSheet("First Sheet", 0);

	    /* Header */
	    List<String> list = new ArrayList<String>();
	    list.add("example");

	    control.writeHeader(list, sheet);

	    if (jsonInfo != null) {
		control.addCell(sheet, 0, 0, jsonInfo.getExample());
	    }
	    workbook.write();
	    workbook.close();

	    System.out.println("Success!");

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (reader != null) {
		try {
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
