<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="fileFormOk.jsp" method="post" enctype="multipart/form-data">
<!-- enctype는 파일을 전송할대 쓰는 방식  -->
파일:<input type="file" name="file">가나다 <br>
<input type="text" name="test" value="연습중입니다"><br>
<input type="submit" value="file upload"> <br>

</form>
</body>
</html>