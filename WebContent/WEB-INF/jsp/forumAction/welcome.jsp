<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
</head>
<body>
<!-- 引入导航栏 -->
	<%@ include file="/WEB-INF/jsp/public/nav.jsp"%>
<!-- 代码 开始 -->
    <div class="grid">
        <figure class=effect-oscar>
            <img src="img/11.jpg" alt=""/>
            <figcaption>
                <h2>欢迎使用 <span>SISP FORUM</span></h2>
                <p>Milo went to the woods. He took a fun ride and never came back.</p>
                <p>Romeo never knows what he wants. He seemed to be very cross about something.</p>
                <p>Bubba likes to appear out of thin air.</p>
                <s:a action="forum_index"></s:a>
            </figcaption>			
        </figure>
    </div>
    
</body>
</html>