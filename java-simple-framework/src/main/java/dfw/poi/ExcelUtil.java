
package dfw.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
	
	private HSSFWorkbook workbook = null;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public Map<Integer, List<Object>> readExcel(String path,String sheetName){
		Map<Integer, List<Object>> map;
		try {
			workbook  = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			map = retrieveMapFromSheet(sheet);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}
    
	public Map<Integer, List<Object>> readExcel(String path){
		Map<Integer, List<Object>> map;
		try {
			workbook  = new HSSFWorkbook(new FileInputStream(path));
			
			//当前sheet
			int activeSheetIndex = workbook.getActiveSheetIndex();
			HSSFSheet sheet = workbook.getSheetAt(activeSheetIndex);
			map = retrieveMapFromSheet(sheet);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return map;
	}

	private Map<Integer, List<Object>> retrieveMapFromSheet(HSSFSheet sheet) {

		Map<Integer, List<Object>> map = new HashMap<Integer, List<Object>>();
		if (sheet == null) {
			return map;
		}
		int rowNum = sheet.getLastRowNum();
		for (int i = 0; i <=rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			int colNum = row.getPhysicalNumberOfCells();
			List<Object> cells = new ArrayList<>();
			for (int j = 0; j < colNum; j++) {
				HSSFCell hssfCell = row.getCell(j);
				cells.add(getCellPrimateValue(hssfCell));
			}
			map.put(i, cells);
		}
		return map;
	}

	/**获取cell的实际格式的对象*/
	private Object getCellPrimateValue(HSSFCell hssfCell) {
		int cellType = hssfCell.getCellType();
		Object value;
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

	@Test
	public void readExcel(){
		String resource = ExcelUtil.class.getResource("").getPath();
		Map<Integer, List<Object>> readExcel = new ExcelUtil().readExcel(resource+"/excel1.xls","测试上传");
		Assert.assertTrue(readExcel.size() > 0);

		readExcel = new ExcelUtil().readExcel(resource+"/excel1.xls","测试上传1");
		Assert.assertEquals(0, readExcel.size());
	}
}
