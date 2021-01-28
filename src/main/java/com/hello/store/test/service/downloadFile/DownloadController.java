package com.hello.store.test.service.downloadFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 下载文件 测试中 20210128
 * @author 
 *
 */
@RestController
@RequestMapping("download")
public class DownloadController {
	

	@GetMapping("test")
	public void writeExcel(HttpServletRequest request,HttpServletResponse response) {
		
		downLoadFile("D:\\TEMP\\upload", "\\aa.txt", "测试.txt", response);
	}
	
	/**
	 * 
	 * @param realFileName    原来文件的名字（包括后缀）
	 * @param rootPath        根目录
	 * @param relativeFileUrl 文件相对地址（包括uuid.**的文件名)
	 */
	private void downLoadFile(String rootPath, String relativeFileUrl, String realFileName,
			HttpServletResponse response) {
		String path = rootPath + relativeFileUrl;
		File file = null;

		try {
			// path是指欲下载的文件的路径。
			file = new File(path);

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			// 清空response
			response.reset();
			// 设置response的Header，设置下载文件的真实名字（包括后缀）
//			String lString = new String(realFileName.getBytes("UTF-8"));
//			String iso_filename = parseGBK(lString);
			response.addHeader("Content-Disposition",
//			"attachment;filename=" + parseGBK(new String(realFileName.getBytes("UTF-8"))));
			"attachment;filename=" + URLEncoder.encode(realFileName, "UTF-8"));
//			"attachment;filename=" + new String(realFileName.getBytes("UTF-8")));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");

			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Allow-Origin", "*");

			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			fis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
