
package dfw.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
	
	private HSSFWorkbook workbook = null;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
	public Map<Integer, List<Object>> readExcel(String path){
		Map<Integer, List<Object>> map = new HashMap<Integer, List<Object>>();
		try {
			workbook  = new HSSFWorkbook(new FileInputStream(path));
			
			//当前sheet
			int activeSheetIndex = workbook.getActiveSheetIndex();
			HSSFSheet sheet = workbook.getSheetAt(activeSheetIndex);
			String sheetName = sheet.getSheetName();
			System.out.println(sheetName);
			
			//遍历获取每行的每列
			int rowNum = sheet.getLastRowNum();
			for (int i = 0; i <=rowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				int colNum = row.getPhysicalNumberOfCells();
				List<Object> cells = new ArrayList<Object>();
				for (int j = 0; j < colNum; j++) {
					HSSFCell hssfCell = row.getCell(j);
					getCellPrimateValue(hssfCell);
					cells.add(row.getCell(j));
				}
				map.put(i, cells);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return map;
	}

	/**获取cell的实际格式的对象*/
	private Object getCellPrimateValue(HSSFCell hssfCell) {
		int cellType = hssfCell.getCellType();
		Object value = null;
		switch (cellType) {
			case HSSFCell.CELL_TYPE_NUMERIC :
				value = hssfCell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_STRING :
				value = hssfCell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN :
				value = hssfCell.getBooleanCellValue();
				break;
			case HSSFCell.CELL_TYPE_FORMULA:{
		        Date date = hssfCell.getDateCellValue();
		        value = sdf.format(date);
				break;
			}
			default :
				value = "";
				break;
		}
		return value;
	}
	
	
	public static void main(String[] args) {
		String resource = ExcelUtil.class.getResource("").getPath();
		Map<Integer, List<Object>> readExcel = new ExcelUtil().readExcel(resource+"/excel1.xls");
		System.out.println(readExcel);
	}
}
