<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
 <form action="../FileUpLoad.action" enctype="multipart/form-data" method="post" >  
          
               用户名：<input type="text" name="usename"> <br/>  
               上传文件：<input type="file" name="file1"><br/>  
              上传文件： <input type="file" name="file2"><br/>  
              <input type="submit" value="提交"/>  
       
     </form>  
       
</body>
</html>