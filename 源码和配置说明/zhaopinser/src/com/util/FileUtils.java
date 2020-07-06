package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUtils {

	private FileUtils() {

	}

	public static void download(HttpServletRequest request,
			HttpServletResponse response, String relativeFilePath) {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ relativeFilePath;
			fileName = fileName.replace("\\", "/");//统一分隔符格式
			File file = new File(fileName);
			//如果文件不存在
			if (file == null || !file.exists()) {
				String msg = "file not exists!";
				System.out.println(msg);
				PrintWriter out = response.getWriter();
				out.write(msg);
				out.flush();
				out.close();
				return;
			}

			String fileType = request.getSession().getServletContext()
					.getMimeType(fileName);
			if (fileType == null) {
				fileType = "application/octet-stream";
			}
			response.setContentType(fileType);
			System.out.println("文件类型是：" + fileType);
			String simpleName = fileName.substring(fileName.lastIndexOf("/")+1);
			String newFileName = new String(simpleName.getBytes(), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename="+newFileName);

			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(
					response.getOutputStream());

			byte[] buffer = new byte[1024];
			int length = 0;

			while ((length = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, length);
			}

			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param request HttpServletRequest
	 * @param relativeUploadPath 上传文件保存的相对路径，例如"upload/"，注意，末尾的"/"不要丢了
	 * @param maxSize 上传的最大文件尺寸，单位字节
	 * @param thresholdSize 最大缓存，单位字节
	 * @param fileTypes 文件类型，会根据上传文件的后缀名判断。<br>
	 * 比如支持上传jpg,jpeg,gif,png图片,那么此处写成".jpg .jpeg .gif .png",<br>
	 * 也可以写成".jpg/.jpeg/.gif/.png"，类型之间的分隔符是什么都可以，甚至可以不要，<br>
	 * 直接写成".jpg.jpeg.gif.png"，但是类型前边的"."不能丢
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request, String relativeUploadPath, int maxSize, int thresholdSize, String fileTypes) {
		// 设置字符编码
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String tempPath = relativeUploadPath + "temp"; // 临时文件目录
		String serverPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
		fileTypes = fileTypes.toLowerCase(); // 将后缀全转换为小写

		//如果上传文件目录和临时目录不存在则自动创建
		if (!new File(serverPath + relativeUploadPath).isDirectory()) {
			new File(serverPath + relativeUploadPath).mkdirs();
		}
		if (!new File(serverPath + tempPath).isDirectory()) {
			new File(serverPath + tempPath).mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(thresholdSize); // 最大缓存
		factory.setRepository(new File(serverPath + tempPath));// 临时文件目录

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(maxSize);// 文件最大上限

		List<String> filePaths = new ArrayList<String>();

		List<FileItem> items;
		try {
			items = upload.parseRequest(request);
			// 获取所有文件列表
			for (FileItem item : items) {
				
				// 获得文件名，文件名包括路径
				if (!item.isFormField()) { // 如果是文件
					// 文件名
					String fileName = item.getName().replace("\\", "/");
					//文件后缀名
					String suffix = null;
					if (fileName.lastIndexOf(".") > -1) {
						suffix = fileName.substring(fileName.lastIndexOf("."));
					} else { //如果文件没有后缀名，不处理，直接跳过本次循环
						continue;
					}
					
					// 不包含路径的文件名
					String SimpleFileName = fileName;
					if (fileName.indexOf("/") > -1) {
						SimpleFileName = fileName.substring(fileName
								.lastIndexOf("/") + 1);
					}

					// 如果文件类型字符串中包含该后缀名，保存该文件
					if (fileTypes.indexOf(suffix.toLowerCase()) > -1) {
						String uuid = UUID.randomUUID().toString();
						SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
						String absoluteFilePath = serverPath
								+ relativeUploadPath + sf.format(new Date())
								+ " " + uuid + " " + SimpleFileName;
						item.write(new File(absoluteFilePath));
						filePaths.add(absoluteFilePath);
					} 
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filePaths;
	}

	/**
	 * 文件上传
	 * 
	 * @param request HttpServletRequest
	 * @param relativeUploadPath 上传文件保存的相对路径，例如"upload/"，注意，末尾的"/"不要丢了
	 * @param maxSize 上传的最大文件尺寸，单位字节
	 * @param fileTypes 文件类型，会根据上传文件的后缀名判断。<br>
	 * 比如支持上传jpg,jpeg,gif,png图片,那么此处写成".jpg .jpeg .gif .png",<br>
	 * 也可以写成".jpg/.jpeg/.gif/.png"，类型之间的分隔符是什么都可以，甚至可以不要，<br>
	 * 直接写成".jpg.jpeg.gif.png"，但是类型前边的"."不能丢
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request, String relativeUploadPath, int maxSize, String fileTypes) {
		return upload(request, relativeUploadPath, maxSize, 5*1024, fileTypes);
	}
	
	/**
	 * 文件上传，不限大小
	 * 
	 * @param request HttpServletRequest
	 * @param relativeUploadPath 上传文件保存的相对路径，例如"upload/"，注意，末尾的"/"不要丢了
	 * @param fileTypes 文件类型，会根据上传文件的后缀名判断。<br>
	 * 比如支持上传jpg,jpeg,gif,png图片,那么此处写成".jpg .jpeg .gif .png",<br>
	 * 也可以写成".jpg/.jpeg/.gif/.png"，类型之间的分隔符是什么都可以，甚至可以不要，<br>
	 * 直接写成".jpg.jpeg.gif.png"，但是类型前边的"."不能丢
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request, String relativeUploadPath, String fileTypes) {
		return upload(request, relativeUploadPath, -1, 5*1024, fileTypes);
	}
}
