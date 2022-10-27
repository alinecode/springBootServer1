package com.hello.store.test.service.excel;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Override
	public void writeExcel2xls(String[] ids,OutputStream os) throws Exception {
		
		// TODO 可以数据库根据ids查出数据，然后循环放入cell

		// 构建数据
		List<Object[]> list = new ArrayList<Object[]>();

		// 构建第一行
		String[] strings = new String[5];

		strings[0] = "第一列";
		strings[1] = "第二列";
		strings[2] = "第三列";
		strings[3] = "第四列";
		strings[4] = "第五列";

		list.add(strings);

		// 构建其他行
		for (int i = 0; i < ids.length; i++) {

			String[] cells = new String[5];
			// TODO 把查询数据放入cells
			cells[0] = ids[i];
			cells[1] = ids[i];
			cells[2] = ids[i];
			cells[3] = ids[i];
			cells[4] = ids[i];
			
			list.add(cells);
			
		}
			
//		ExcelUtils.writeExcel_ToXLS(list, os);
//		os.flush();
//		os.close();
	}


}
