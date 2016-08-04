<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源上传</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/interaction.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>

<style>
.aaa{display:inline-block; width:140px; height:40px; background: url(resimg/blue_upload_file.png); position:relative; overflow:hidden;}
.bbb{position:absolute; right:0; top:0; font-size:100px; opacity:0; filter:alpha(opacity=0);}
</style>
</head>
<body>
<!-- 导航栏 -->
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<div class="container">
<%@ include file="/WEB-INF/jsp/resourceAction/common.jsp" %>
<input type="hidden" id="panShow"  value="upload" /> 
		<div class="row" style="margin-top: 20px;">
			<div class="span9" style="float:left;margin-left: 150px;width: 600px;">
				<div class="row" >
				<s:actionmessage/>
					<s:form   action="myresource_upResCommit" enctype="multipart/form-data" method="POST">
					<table border="0" cellspacing="0" cellpadding="0">
							<colgroup span="1">
								<col width="330px" />
								<col />
							</colgroup>
							<colgroup span="2">
								<col width="500px" />
								<col />
							</colgroup>
							 <tr>
							 <td rowspan="2" style="text-align: right;font-size: 18px;">
							<a href="#" class="aaa"> 
							<s:file name="upFile" cssStyle="width:200px" cssClass="bbb" id="filesToUpload" onchange="show_uploadfile();"></s:file>
							</a></td>
							
							<td style="text-align: left;font-size: 18px;">
							<div style="float:left"><ul style="list-style-type:none;" id="fileList">
						      <li id="li_userfile">（未选择文件）</li> 
						     </ul> </div><div style="float:left" id="showFileInfoId"></div></td>
							</tr>
							 <tr>
							<td  style="text-align: left;font-size: 18px;">你可以上传不超过<font color="red">100M</font>的文件！</td>
							</tr>
							<tr>
							<td colspan="2" style="text-align: center;font-size: 18px;" ><font color="red">${warnMessage }</font></td>
							</tr>		
							<tr style="height: 50px;">
								<th  style="text-align: right;font-size: 18px;">资源名称：</th>
								<td><s:textfield name="name" cssStyle= "width:300px;height:30px;margin-bottom: 0px;" ></s:textfield></td>
							</tr>
							<tr style="height: 50px;font-size: 18px;">
								<th style="text-align: right;font-size: 18px;">关键词（Tag）：</th>
								<td><s:textfield name="resTags" cssStyle= "width:300px;height:30px;margin-bottom: 0px;" placeholder="标签不多余10个字符，请用空格隔开"></s:textfield></td>
							</tr>
							<tr style="height: 50px;">
								<th style="text-align: right;font-size: 18px;">资源分类：</th>
								<td><s:select name="resTypeId" list="#resTypes"
										listKey="id" listValue="name" headerKey="" headerValue="请选择类型" cssStyle= "width:300px;height:30px;margin-bottom: 0px;">
									</s:select></td>
							</tr>
							<tr style="height: 50px;">
								<th style="text-align: right;font-size: 18px;">资源描述：</th>
								<td><s:textarea name="description" placeholder="描述>=20个字符,不支持HTML标签；详细的资源描述有机会获得我们的推荐。" cssStyle= "width:300px;height:100px;margin-bottom: 0px;">
									</s:textarea></td>
							</tr>
							<tr style="height: 50px;">
							<td colspan="2" align="center"><input id="btn_submit" name="" type="image" src="resimg/btn_submit.png"></td>
								<%-- <td colspan="2"><s:submit value="开始上传"></s:submit></td> --%>
							</tr>
						</table>	
		            </s:form>
				</div>
			</div>
			<div class="span3" style="float:left;margin-left: 100px;margin-top: 10px;width: 200px;">
				<s:form class="form-search" action="myresource_normalSearch.action" method="get">
					<div class="input-append">
						<input name="searchName" class="span2 search-query" type="text" style="height: 34px;"/>
						<button type="submit" class="btn">查找</button>
					</div>
				</s:form>
				<p>相关推荐</p>
				<div>
		         <s:iterator value="#session.resTagMap" status="status">
		         <s:if test="%{}"></s:if>
		      <s:a action="resource_singalresTagUI?resTg=%{key}">${key }(${value })</s:a>
	       </s:iterator>
	    </div>
		</div>		
	</div>
</div>
<!-- 页脚 -->
<div style="height:20px"></div>
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
<script type="text/javascript">
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
 function show_uploadfile(){
	         var input = document.getElementById("filesToUpload");
	         var ul = document.getElementById("fileList");
	         while (ul.hasChildNodes()){
	             ul.removeChild(ul.firstChild);
	         }
	         for(var i = 0; i < input.files.length; i++){
	             var li = document.createElement("li");
	             li.innerHTML = input.files[i].name;
	             ul.appendChild(li);
	         }
	         if(!ul.hasChildNodes()){
	             var li = document.createElement("li");
	             li.innerHTML = "没有文件被选中！";
	             ul.appendChild(li);
	         }	         
	         var uploadFileId = document.getElementById("filesToUpload");	         
	         // 显示信息ID
	         var showFileInfoId = document.getElementById("showFileInfoId"); 	         
	         if (isIE && !uploadFileId.files) {   	          
	          var filePath = uploadFileId.value;     
	          var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
	          var file = fileSystem.GetFile (filePath);	          
	          // fileSize不能用var申明
	          fileSize = file.Size;           
	         }else{   
	            fileSize = uploadFileId.files[0].size; 	           
	         }	         
	         var size = fileSize/1024/1024;
	         //2048=2Mb
	         var fileName = uploadFileId.value;
	        /*  var fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length); 
	         showFileInfoId.innerHTML = "文件格式：" + fileType + "<br />"; */
	         showFileInfoId.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;大小：" + size.toFixed(2) + "Mb";
	         if(size>99){
	        	 alert("文件过大，请重新选择！");
	        	 showFileInfoId.innerHTML = "";
	        	 while (ul.hasChildNodes()){
		             ul.removeChild(ul.firstChild);
		             var li = document.createElement("li");
		             li.innerHTML = "没有文件被选中！";
		             ul.appendChild(li);
		             input.select(); 
		             document.selection.clear(); 
	     }
	 }
}
/*  function checkFileSizeFun(){
	 
	 // 文件ID
	 var uploadFileId = document.getElementById("uploadFileId");
	 
	 // 显示信息ID
	 var showFileInfoId = document.getElementById("showFileInfoId"); 
	 
	 if (isIE && !uploadFileId.files) {   
	  
	  var filePath = uploadFileId.value;     
	  var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
	  var file = fileSystem.GetFile (filePath);
	  
	  // fileSize不能用var申明
	  fileSize = file.Size;  
	 
	 }else{   
	    fileSize = uploadFileId.files[0].size; 
	   
	 }  
	 
	 var size = fileSize/1024/1024;
	 //2048=2Mb
	 var fileName = uploadFileId.value;
	 var fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length); 
	 showFileInfoId.innerHTML = "文件格式：" + fileType + "<br />";
	 showFileInfoId.innerHTML += "文件大小：" + size.toFixed(2) + "Mb<br />";
	} */
</script>

</body>
</html>