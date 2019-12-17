package com.hello.store.test.service.excel;

import java.io.OutputStream;

public interface ExcelService {

	void writeExcel2xls(String[] ids,OutputStream os) throws Exception;

	
}
