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
			fileName = fileName.replace("\\", "/");//ͳһ�ָ�����ʽ
			File file = new File(fileName);
			//����ļ�������
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
			System.out.println("�ļ������ǣ�" + fileType);
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
	 * �ļ��ϴ�
	 * 
	 * @param request HttpServletRequest
	 * @param relativeUploadPath �ϴ��ļ���������·��������"upload/"��ע�⣬ĩβ��"/"��Ҫ����
	 * @param maxSize �ϴ�������ļ��ߴ磬��λ�ֽ�
	 * @param thresholdSize ��󻺴棬��λ�ֽ�
	 * @param fileTypes �ļ����ͣ�������ϴ��ļ��ĺ�׺���жϡ�<br>
	 * ����֧���ϴ�jpg,jpeg,gif,pngͼƬ,��ô�˴�д��".jpg .jpeg .gif .png",<br>
	 * Ҳ����д��".jpg/.jpeg/.gif/.png"������֮��ķָ�����ʲô�����ԣ��������Բ�Ҫ��<br>
	 * ֱ��д��".jpg.jpeg.gif.png"����������ǰ�ߵ�"."���ܶ�
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request, String relativeUploadPath, int maxSize, int thresholdSize, String fileTypes) {
		// �����ַ�����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String tempPath = relativeUploadPath + "temp"; // ��ʱ�ļ�Ŀ¼
		String serverPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
		fileTypes = fileTypes.toLowerCase(); // ����׺ȫת��ΪСд

		//����ϴ��ļ�Ŀ¼����ʱĿ¼���������Զ�����
		if (!new File(serverPath + relativeUploadPath).isDirectory()) {
			new File(serverPath + relativeUploadPath).mkdirs();
		}
		if (!new File(serverPath + tempPath).isDirectory()) {
			new File(serverPath + tempPath).mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(thresholdSize); // ��󻺴�
		factory.setRepository(new File(serverPath + tempPath));// ��ʱ�ļ�Ŀ¼

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(maxSize);// �ļ��������

		List<String> filePaths = new ArrayList<String>();

		List<FileItem> items;
		try {
			items = upload.parseRequest(request);
			// ��ȡ�����ļ��б�
			for (FileItem item : items) {
				
				// ����ļ������ļ�������·��
				if (!item.isFormField()) { // ������ļ�
					// �ļ���
					String fileName = item.getName().replace("\\", "/");
					//�ļ���׺��
					String suffix = null;
					if (fileName.lastIndexOf(".") > -1) {
						suffix = fileName.substring(fileName.lastIndexOf("."));
					} else { //����ļ�û�к�׺����������ֱ����������ѭ��
						continue;
					}
					
					// ������·�����ļ���
					String SimpleFileName = fileName;
					if (fileName.indexOf("/") > -1) {
						SimpleFileName = fileName.substring(fileName
								.lastIndexOf("/") + 1);
					}

					// ����ļ������ַ����а����ú�׺����������ļ�
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
	 * �ļ��ϴ�
	 * 
	 * @param request HttpServletRequest
	 * @param relativeUploadPath �ϴ��ļ���������·��������"upload/"��ע�⣬ĩβ��"/"��Ҫ����
	 * @param maxSize �ϴ�������ļ��ߴ磬��λ�ֽ�
	 * @param fileTypes �ļ����ͣ�������ϴ��ļ��ĺ�׺���жϡ�<br>
	 * ����֧���ϴ�jpg,jpeg,gif,pngͼƬ,��ô�˴�д��".jpg .jpeg .gif .png",<br>
	 * Ҳ����д��".jpg/.jpeg/.gif/.png"������֮��ķָ�����ʲô�����ԣ��������Բ�Ҫ��<br>
	 * ֱ��д��".jpg.jpeg.gif.png"����������ǰ�ߵ�"."���ܶ�
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request, String relativeUploadPath, int maxSize, String fileTypes) {
		return upload(request, relativeUploadPath, maxSize, 5*1024, fileTypes);
	}
	
	/**
	 * �ļ��ϴ������޴�С
	 * 
	 * @param request HttpServletRequest
	 * @param relativeUploadPath �ϴ��ļ���������·��������"upload/"��ע�⣬ĩβ��"/"��Ҫ����
	 * @param fileTypes �ļ����ͣ�������ϴ��ļ��ĺ�׺���жϡ�<br>
	 * ����֧���ϴ�jpg,jpeg,gif,pngͼƬ,��ô�˴�д��".jpg .jpeg .gif .png",<br>
	 * Ҳ����д��".jpg/.jpeg/.gif/.png"������֮��ķָ�����ʲô�����ԣ��������Բ�Ҫ��<br>
	 * ֱ��д��".jpg.jpeg.gif.png"����������ǰ�ߵ�"."���ܶ�
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request, String relativeUploadPath, String fileTypes) {
		return upload(request, relativeUploadPath, -1, 5*1024, fileTypes);
	}
}
