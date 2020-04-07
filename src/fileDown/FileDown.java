package fileDown;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class FileDown
 */
@WebServlet("/FileDown")
public class FileDown extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");//1.���ϸ� ��������
		//2. ��� ��������
		String saveDir = super.getServletContext().getRealPath("fileUpDown");
		File file = new File(saveDir+"/"+fileName);
		System.out.println("���ϸ� : "+fileName);
		//3. ���� ���� ��������
		String mimeType = getServletContext().getMimeType(file.toString());
		System.out.println("mimeType : "+mimeType);//������ ������ �����´�.
		if(mimeType == null) {
			//http���� ���� ������ �𸦶� �����ϴ� ����� �ȴ�.8��Ʈ ������ /�������α׷� .egg, .bin ��
			response.setContentType("application/octet-stream");
			System.out.println("���� ������ �𸦶� ���� ��");
		}
		// 4. �ٿ�ε�� ���ϸ��� ����
		String downName = null;
		System.out.println("request.getHeader : "+request.getHeader("user-agent"));
		if(request.getHeader("user-agent").indexOf("Trident") == -1){//Trident : �ͽ��÷η� ��
			downName = new String(fileName.getBytes("UTF-8"), "8859_1");
			System.out.println("�ͽ��÷η� �� ����");
		}
		else{
			downName = new String(fileName.getBytes("euc-kr"), "8859_1");
			System.out.println("�ͽ� �÷η� ����");
		}
		// 5. ���� ��ü�� ���� ������ �ٿ��� �����ڴ�(content-disposition��ưƮ�� ó��)(attachment(����)). 
		response.setHeader("Content-Disposition","attachment;filename=\"" + downName + "\";");
		// 6. ��û�� ������ �о Ŭ���̾�Ʈ������ �����Ѵ�.
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b [] = new byte[1024];
		int data = 0;
		while((data=(fileInputStream.read(b, 0, b.length))) != -1)
			servletOutputStream.write(b, 0, data);
		
		servletOutputStream.close();
		fileInputStream.close();
		System.out.println("������ ����~~~");
	}
}

