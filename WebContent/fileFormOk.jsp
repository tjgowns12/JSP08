<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String path =request.getRealPath("fileUpDown");
out.print("fileFolder path : "+path);
int size = 1024 * 1024 * 10; //10M
String file = "";	String oriFile = "";

MultipartRequest multi = new MultipartRequest(
	request, path, size, "utf-8", new DefaultFileRenamePolicy());
file = multi.getFilesystemName("file");
oriFile = multi.getOriginalFileName("file");
out.print("<br>DefaultFileRenamePolicy() : "+
					"동일한 이름의 파일이 존재 한다면 1,2,3을 넣어 주겠다");
out.print("<br>file(저장될 파일 이름) : " + file);
out.print("<br>oriFile(실제 파일 이름) : " + oriFile);
%>
test:<%=multi.getParameter("test") %><br>
<!-- 
경로 파일 이름  JSP08안에 경로가 지정되어있음
Int size는 키로바이트 메가바이트 10메가바이트 크기를 나타냄
Multipart로 넘기면 multipart로 해당하는 값으로 받아온다
Request,경로,사이즈,한글처리,new defaultFileRenamePolicy
는 같은 파일이 들어올때 뒤에 숫자가 붙는다 1,2,3,4,,,

 -->

</body>
</html>