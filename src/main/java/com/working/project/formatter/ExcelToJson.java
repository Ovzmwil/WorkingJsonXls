package com.working.project.formatter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import com.jayway.restassured.path.json.JsonPath;

public class ExcelToJson {

	private static HashMap<String, String> dataMap = new HashMap<String, String>();

	public static void main(String[] args) {
		getData();
	}

	public static void getData() {
		try {
			List<List<String>> dataList = new ArrayList<List<String>>();
			FileInputStream file = new FileInputStream(new File("planilha/Massa Auto.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			List<String> header = new ArrayList<String>();
			Row headerRow = sheet.getRow(0);
			for (Cell cell : headerRow) {
				header.add(cell.getStringCellValue());
			}

			for (Row row : sheet) {
				List<String> tempList = new ArrayList<String>();
				for (Cell cell : row) {
					tempList.add(cell.getStringCellValue());
					if (tempList.size() == header.size() && dataList.size() <= sheet.getLastRowNum()) {
						dataList.add(tempList);
					}
				}
			}

			JSONObject json = new JSONObject();

			for (int n = 0; n < dataList.size(); n++) {
				dataMap.clear();
				for (int i = 0; i < header.size(); i++) {
					dataMap.put(header.get(i), dataList.get(n).get(i));
				}

				json.append("data", dataMap.clone());
			}
			

//			JsonPath jsonPath = new JsonPath(json.toString());
			
			System.out.println(json);

			file.close();
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}