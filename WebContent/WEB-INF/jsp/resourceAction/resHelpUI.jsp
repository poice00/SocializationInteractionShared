<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

<style type="text/css"> 
.help_con{ width:700px; margin:0 0 20px 200px; padding:34px; border:solid 1px #ccc;}
.help_con .tagContent{display:none; margin:0 0 40px 0; font-size:14px;}
.help_con .tagContent h1{margin:0 0 20px 0; padding:0 0 10px 0; border-bottom:solid 1px #ccc; font:bold 18px/30px Helvetica, Tahoma, Arial, sans-serif; text-align:center;}
.help_con .tagContent h4{margin:0 0 0 0; font:bold 14px/26px Helvetica, Tahoma, Arial, sans-serif;}
.help_con .tagContent p,
.help_con .tagContent dl,
.help_con .tagContent ul{margin:0 0 1em 0; font:normal 14px/26px Helvetica, Tahoma, Arial, sans-serif;}
.help_con .tagContent dt{font-weight:bold;}
.help_con .selectTag{display:block;}
.help_con ol{margin:0 0 20px 0; padding:0 0 0 1.5em;}
.help_con ol li{list-style:decimal;}
</style> 
<jsp:include page="/WEB-INF/jsp/public/style.jsp" flush="true"></jsp:include>
</head>
<body>
<!-- 导航栏 -->
<%@ include file="/WEB-INF/jsp/public/nav.jsp" %>
<center>
<div class="nav-bar">
        	<s:a action="resource_homePage" >首页</s:a>
        	<s:a action="resource_resTypeUI" >资源分类</s:a>
			<s:a action="resource_resRankUI">排行榜</s:a>
			<s:a action="resource_resHelpUI" >帮助</s:a>
			<s:a action="resource_highsearchUI">高级搜索</s:a>
			<s:a action="myresource_uplist">我的资源</s:a>
			<s:a action="myresource_showupload">我要上传</s:a>	
</div>
</center>
<div class="container">
<div class="help_con" id="tagContent">
<div class="tagContent selectTag" id="tagContent">
		<h1>资源上传协议</h1>
		<h2>审核标准</h2>
		<p>为确保广大用户能够正常便捷地使用下载频道资源，SISP对于用户上传的所有资源都会进行审核。以下资源将不会被审核通过。</p>
		<dl>
			<dt>1.资源包含中国法律、法规、规章、条例以及任何具有法律效力之规范所限制或禁止的内容，包括但不限于以下情形：</dt>
			<dd>
				<ul>
					<li>反对宪法所确定的基本原则的；</li>
					<li>危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；</li>
					<li>损害国家荣誉和利益的；</li>
					<li>煽动民族仇恨、民族歧视、破坏民族团结的；</li>
					<li>破坏国家宗教政策，宣扬邪教和封建迷信的；</li>
					<li>散布谣言，扰乱社会秩序，破坏社会稳定的；</li>
					<li>散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；</li>
					<li>侮辱或者诽谤他人，侵害他人合法权利的；</li>
					<li>煽动非法集会、结社、游行、示威、聚众扰乱社会秩序的；</li>
					<li>以非法民间组织名义活动的；</li>
					<li>含有虚假、有害、胁迫、侵害他人隐私、骚扰、侵害、中伤、粗俗、猥亵、或其它道德上令人反感的内容；</li>
					<li>含有中国法律、法规、规章、条例以及任何具有法律效力之规范所限制或禁止的其它内容的。</li>
				</ul>
			</dd>
			<dt>2.资源包含广告、垃圾信息等内容。</dt>
			<dt>3.资源无法正常显示：对于资源自身存在问题，导致用户正常浏览无法得到保障时，资源将不被通过。</dt>
			<dt>4.资源包含其他不符合SISP网站规则的内容。</dt>
		</dl>
		<p>&nbsp;</p>

		<h2>权利提示</h2>
		<p>请勿在未经授权的情况下，上传任何可能涉及侵权的资源，除非您是该资源的合法权利人或该资源不侵犯任何第三方的合法权益。</p>
		<p>1.
			SISP上的内容完全来自用户上传，SISP并不对其进行任何编辑或修改。SISP的用户不能侵犯包括他人的著作权在内的知识产权以及其他权利。</p>
		<ul>
			<li>1)
				未经著作权人同意擅自对他人的作品进行全部或部分复制，修改，改编，翻译，汇编等，有可能侵害到他人的著作权时，不得把相关内容上传发布到SISP。</li>
			<li>2)
				SISP的用户可以为介绍、评论、研究等目的，在合理的范围内适当引用他人已经发表的作品，但应当注明作者姓名、作品名称，并不得侵犯著作权人及其他权利人的合法权益。</li>
		</ul>
		<p>2.用户承诺：其所上传的所有资源内容符合中国法律法规和规范性文件的相关规定，不侵犯任何第三方的合法权益。如用户违反前述保证，SISP有权删除相关资源内容，并可以暂停或终止向该用户提供服务。</p>
		<p>3.如因SISP用户上传的内容侵犯了第三方的合法权利，第三方向SISP提出举报，确认侵权后SISP有权删除相关的内容。</p>
		<p>4.
			当权利人发现SISP的内容侵犯其合法权益时，权利人可向SISP举报，SISP将根据中国法律法规和规范性文件的规定采取移除相关内容等合理措施。请发送举报邮件到客服邮箱webmaster(at)SISP.net。</p>
		<p>&nbsp;</p>

		<h2>使用条款</h2>
		<p>1.
			对于用户上传到SISP上的任何内容，用户同意SISP在全世界范围内享有免费的、永久的、不可撤销的、非排他性的使用和再许可的权利。SISP享有修改、复制、发行、表演、展览、信息网络传播、改编、翻译、汇编等权利。</p>
		<p>2. 用户上传到SISP的资源及其内容不代表SISP观点，SISP不对资源及其内容的真实、完整、准确及合法性进行任何保证。</p>
		<p>3. 用户应妥善保管自己的SISP账户及其密码。因用户自身原因丢失账户、密码或其他重要信息等，用户自行承担一切不利后果。</p>
		<p>4.
			因系统维护或升级等原因而需暂停服务时，SISP将事先发布公告。因硬件故障或者其他不可抗力而导致暂停服务，于暂停服务期间造成的一切不便与损失，SISP不负任何责任。</p>
		<p>5.
			SISP有权根据互联网的发展和中华人民共和国有关法律、法规及规范性文件的变化，不断修改和完善SISP下载频道协议的相关条款。SISP保留随时修改下载频道协议条款的权利。用户使用SISP，即意味着同意并自愿遵守下载频道协议中所有条款及其最新版本。</p>
		<p>6. 本协议未涉及的问题参见国家有关法律法规。当本协议与国家法律法规冲突时，以国家法律法规为准。</p>
		<p>本协议的最终解释权归SISP所有。</p>
</div>
</div>
</div>
<!-- 页脚 -->
<%@ include file="/WEB-INF/jsp/public/footer.jsp" %>
</body>
</html>