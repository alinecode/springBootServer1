package com.hello.store.test.util;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtils {

	
//	public static void writeExcel_ToXLS(List<Object[]> list,OutputStream os) throws Exception {
//		// 创建一个空白的 WorkBook
//		@SuppressWarnings("resource")
//		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();// 2007
//
//		int rows = list.size() / 65535;// 每页最大65535 row
//		rows = (list.size() % 65535) != 0 ? rows + 1 : rows;
//
//		for (int i = 0; i < rows; i++) {
//
//			HSSFSheet st = hssfWorkbook.createSheet("第" + (i + 1) + "页");
//			// 循环数据 row
//			for (int j = i * 65535; j <= ((i + 1) * 65535); j++) {
//				if (j >= list.size()) {
//					break;
//				}
//				Object[] data_row = list.get(j);
//				HSSFRow row = st.createRow(j - (i * 65535));
//				// 循环 cell
//				for (int k = 0; k < data_row.length; k++) {
//					Object data_cell = data_row[k];
//					if (data_cell != null) {
//						// 设置位置 和 类型
//						@SuppressWarnings("deprecation")
//						HSSFCell cell = row.createCell(k, Cell.CELL_TYPE_STRING);
//						// 设置值
//						cell.setCellValue(new HSSFRichTextString(data_cell.toString()));
//					}
//					if (k >= 255) { throw new Exception("数据项不能大于255个"); }
//				}
//
//			}
//
//		}
//		
////		FileOutputStream writeFile = new FileOutputStream("d:\\仓库模板.xls");
//		hssfWorkbook.write(os);
//	}
	
	
	/**
	 * 导出Excel2007格式
	 * @param list 行数据
	 * @param os 输出流
	 * @throws Exception
	 */
//	public static void writeExcel_ToXLS(List<Object[]> list,OutputStream os) throws Exception {
//		
//		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();// 2007workbook
//		
//		// 限制分页
//		int pages = list.size()/65535;  // 一页最多65535行
//		
//		pages = list.size()%65535!=0 ? pages+1 : pages;  // 无法整除就多一页来装
//		// 限制分页结束
//		
//		// 循环每页写数据
//		for (int i = 0; i < pages; i++) {
//			
//			HSSFSheet st = hssfWorkbook.createSheet("第" + (i + 1) + "页");
//			
//			// 循环每一段65535内的数据，也就是循环每一页的数据
//			for (int j = i * 65535; j <= ((i + 1) * 65535); j++) {
//				
//				if (j>=list.size()) {  // 最后一页可能没有65535个
//					break;
//				}
//				Object[] data_row = list.get(j); // 取出一行数据
//				HSSFRow row = st.createRow(j - (i * 65535)); // 创建一行。参数是行号
//				
//				for (int k = 0; k < data_row.length; k++) { // 循环这一行内的每一个数据 (cell,一个最小的四方格)
//					
//					Object data_cell = data_row[k]; // 第k个
//					if (data_cell != null) {
//						// 设置位置 和 类型
//						HSSFCell cell = row.createCell(k, Cell.CELL_TYPE_STRING);
//						// 设置值
//						cell.setCellValue(new HSSFRichTextString(data_cell.toString()));
//					}
//					if (k >= 255) { throw new Exception("数据项不能大于255个"); } // 一行255
//				}
//				
//			}
//			
//		}
//		hssfWorkbook.write(os);
//	}
	
}
