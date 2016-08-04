TY.namespace("ui");TY.loadedModule("TY.ui.popV2");if(typeof window.TY.ui.pop=="undefined"){TY.ui.pop=function(a){this.init(a)}}(function(a){TY.extend(a.prototype,{version:"2.0",setConfig:function(d){var c=new Date().getTime();this.id="TY_ui_pop_"+c;var b={el:null,isShowHead:true,isClickable:false,isShowButton:true,isShowBorder:true,isDrag:true,isMod:true,isShowCloseIco:true,body:"",headTxt:"\u63d0\u793a",yesTxt:"\u786e\u5b9a",cancelTxt:"\u53d6\u6d88",yesHandler:null,cancelHandler:null,closeHandler:null,type:"alert",buttons:[{id:"submitBtn",text:"\u786e\u5b9a",handler:null},{id:"operation1",text:"\u64cd\u4f5c\u4e00",handler:null}],modColor:"#000",modOpacity:0.1,modZindex:100,render:null,fixed:true,scrollable:true,isMax:false,isPadding:true,padding:"20px",esc:true,skinClass:""};d.buttons=d.buttons||d.Buttons;this.args=TY.extend({},b,d);if(this.args.isPadding==false){this.args.padding=0}if(this.args.el){this.args.fixed=false}},createHtml:function(){var c=[];c.push('<table id="'+this.id+'" class="TY_ui_window">');if(this.args.isShowBorder){c.push('	<tr><td class="btl"></td><td class="btc"></td><td class="btr"></td></tr>');c.push('	<tr><td class="bcl"></td><td class="bcc">')}else{c.push('	<tr><td class="bcc">')}if(this.args.isShowHead){c.push('	<div class="topBar">');c.push('		<h3 class="title">'+this.args.headTxt+"</h3>");if(this.args.isShowCloseIco){c.push('	<div class="closeBtn" ></div>')}c.push("	</div>")}c.push('	<div class="ty-content clearfix" style="'+(this.args.isPadding==false?"padding:0;":"padding:"+this.args.padding)+'">');c.push(this.args.body);c.push("	</div>");if(this.args.isShowButton){c.push('<div class="btnArea">');switch(this.args.type){case"alert":c.push('<input type="button" class="yes" value="'+this.args.yesTxt+'"/>');break;case"confirm":c.push('<input type="button" class="cancel" value="'+this.args.cancelTxt+'"/>');c.push('<input type="button" class="yes" value="'+this.args.yesTxt+'"/>');break;case"window":var f=[];if(TY.isArray(this.args.buttons)){f=this.args.buttons.reverse();for(var e=0,b=f.length;e<b;e++){c.push('<input type="button" id="'+f[e]["id"]+'" value="'+f[e]["text"]+'"/>')}}break;default:break}c.push("</div>")}if(this.args.isShowBorder){c.push('	</td><td class="bcr"></td></tr>');c.push('	<tr><td class="bfl"></td><td class="bfc"></td><td class="bfr"></td></tr>')}else{c.push("	</td></tr>")}c.push("</table>");c.push('<iframe frameborder="0" class="iframeDiv" id="iframe_'+this.id+'"/>');if(this.args.isMod){var g=this;TY.loader("TY.ui.mod",function(){g.mod=new TY.ui.mod({id:"mod_"+g.id,scrollable:g.args.scrollable,modZindex:g.args.modZindex,modColor:g.args.modColor,modOpacity:g.args.modOpacity})})}if(this.args.skinClass){c.unshift('<div class="'+this.args.skinClass+'">');c.push("</div>")}TY("body").append(c.join(""));this.pop=TY("#"+this.id);this.popIframe=TY("#iframe_"+this.id);this.pop.css("position",this.args.fixed?"fixed":"absolute");this.setContentWidth();this.setPosition();this.args.render&&this.args.render.call(this);this.show();var d=this.pop.find(".btnArea input");if(d.size()||(d=this.pop.find(".closeBtn")).size()){d.eq(0).focus()}},setContentWidth:function(d){var f=d?d.data.object:this,b=TY("#"+f.id),c=TY(".ty-content",b);if(f.args.isMax){c.css({width:TY(window).width()-parseFloat(TY(c).css("paddingLeft"))-parseFloat(TY(c).css("paddingRight"))-30,overflow:"hidden"});c.css({height:TY(window).height()-parseFloat(TY(c).css("paddingTop"))-parseFloat(TY(c).css("paddingBottom"))-70,overflow:"hidden"})}else{if(f.args.width){if(typeof f.args.width==="number"){width=f.args.width+"px";c.css({width:width,overflow:"hidden"})}else{c.css({width:f.args.width,overflow:"hidden"})}}if(f.args.height){if(typeof f.args.height==="number"){height=f.args.height+"px";c.css({height:height,overflow:"hidden"})}else{c.css({height:f.args.height,overflow:"hidden"})}}if(b.width()>(TY(window).width()-20)){c.css({width:TY(window).width()/2-parseFloat(TY(c).css("paddingLeft"))-parseFloat(TY(c).css("paddingRight"))-30,overflow:"hidden"})}if(b.height()>TY(window).height()){c.css({height:TY(window).height()/2-parseFloat(TY(c).css("paddingTop"))-parseFloat(TY(c).css("paddingBottom"))-70,overflow:"hidden","overflow-y":"auto"})}if(b.width()<200){c.css({width:200})}}},setPosition:function(f){var h=f?f.data.object:this,g={},c=TY("#"+h.id),d=TY("#iframe_"+h.id);var b=(self.innerHeight||document.documentElement.clientHeight||document.body.clientHeight);if(TY.browser.msie&&h.args.fixed&&TY.browser.version==6){c.css({position:"absolute"});d.css({position:"absolute"});TY.event.add(window,"scroll",h.popScroll,{id:h.id})}if(h.args.el){g.top=h.args.el.offset().top+TY.util.getElRealSize(h.args.el).height;g.left=h.args.el.offset().left-(c.width()-TY.util.getElRealSize(h.args.el).width)/2;if(c.height()>(TY(window).height()-(h.args.el.offset().top-TY(window).scrollTop())-TY.util.getElRealSize(h.args.el).height)){g.top=h.args.el.offset().top-c.height()}if(g.left<0){g.left=h.args.el.offset().left}if((g.left+c.width())>TY(window).width()){g.left=h.args.el.offset().left-(c.width()-TY.util.getElRealSize(h.args.el).width)}}else{if(h.args.fixed){g.top=(b-c.height()-parseFloat(c.css("borderTopWidth"))-parseFloat(c.css("borderBottomWidth"))-parseFloat(c.css("paddingTop"))-parseFloat(c.css("paddingBottom"))-parseFloat(c.css("marginTop"))-parseFloat(c.css("marginBottom")))/2}else{g.top=TY(window).scrollTop()+(b-c.height()-parseFloat(c.css("borderTopWidth"))-parseFloat(c.css("borderBottomWidth"))-parseFloat(c.css("paddingTop"))-parseFloat(c.css("paddingBottom"))-parseFloat(c.css("marginTop"))-parseFloat(c.css("marginBottom")))/2}if(TY.browser.msie&&TY.browser.version==6){g.top=TY(window).scrollTop()+(TY(window).height()-c.height()-parseFloat(c.css("borderTopWidth"))-parseFloat(c.css("borderBottomWidth"))-parseFloat(c.css("paddingTop"))-parseFloat(c.css("paddingBottom"))-parseFloat(c.css("marginTop"))-parseFloat(c.css("marginBottom")))/2}g.left=(TY(window).width()-c.width()-parseFloat(c.css("borderLeftWidth"))-parseFloat(c.css("borderRightWidth"))-parseFloat(c.css("paddingLeft"))-parseFloat(c.css("paddingRight"))-parseFloat(c.css("marginLeft"))-parseFloat(c.css("marginRight")))/2}g.width=c.width();g.height=c.height();c.css({top:g.top,left:g.left,"z-index":+h.args.modZindex+3*TY(".TY_ui_window").length,display:"inline-block",zoom:1});d.css({top:g.top,left:g.left,width:g.width,height:g.height})},popScroll:function(g){try{var b=TY("#"+g.data.id),c=TY("#iframe_"+g.data.id),d,f=null;if(typeof(f)!="undefined"&&f!=null){clearTimeout(f)}f=setTimeout(function(){d=TY(window).scrollTop()+(TY(window).height()-b.height()-parseFloat(b.css("borderTopWidth"))-parseFloat(b.css("borderBottomWidth"))-parseFloat(b.css("paddingTop"))-parseFloat(b.css("paddingBottom"))-parseFloat(b.css("marginTop"))-parseFloat(b.css("marginBottom")))/2;b.stop(true,false);c.stop(true,false);b.animate({top:d},"fast");c.animate({top:d},"fast")},100)}catch(g){throw new Exception("IE6 popScroll ERROR")}},clickToRemove:function(c){var b=c.target;var d=c.data.object;if(!TY.contains(TY("#"+d.id)[0],b)){d.remove()}if(d.args.closeHandler){d.args.closeHandler()}},bindEvent:function(){var d=this,b=TY("#"+this.id),c=TY("#iframe_"+this.id);if(this.args.esc){TY(document).bind("keyup.TY_ui_pop",function(f){if(f.which===27){d.remove();if(d.args.closeHandler){d.args.closeHandler()}}})}b.find(".closeBtn").click(function(){d.remove();if(d.args.closeHandler){d.args.closeHandler()}});TY("#"+this.id+" .btnArea :input").click(function(){if(d.args.type=="window"){d.args.buttons[TY(this).index()]["handler"]()}else{if(TY(this).hasClass("yes")){var e=true;if(d.args.yesHandler){e=d.args.yesHandler()}if(e===undefined){e=true}if(e){d.remove()}}else{if(d.args.cancelHandler){d.args.cancelHandler()}d.remove()}}});if(this.args.isDrag){b.find(".topBar").hover(function(){TY(this).css("cursor","move")});TY.loader("TY.ui.draggable",function(){if(d.args.isShowHead){b.draggable({handle:".topBar",callback:function(){c.css({top:b.css("top"),left:b.css("left")})}})}else{b.draggable({callback:function(){c.css({top:b.css("top"),left:b.css("left")})}})}})}if(this.args.isClickable){setTimeout(function(){TY.event.add(document,"click",d.clickToRemove,{object:d})},100)}TY.event.add(window,"resize",d.setPosition,{object:d})},show:function(){TY("#"+this.id).show();TY("#iframe_"+this.id).show()},remove:function(){TY("#"+this.id).remove();TY("#iframe_"+this.id).remove();TY(document).unbind("keyup.TY_ui_pop");TY.event.remove(window,"resize",this.setPosition);TY.event.remove(window,"resize",this.setContentWidth);if(this.mod!=null){this.mod.remove()}if(TY.browser.msie&&this.args.fixed&&TY.browser.version==6){TY.event.remove(window,"scroll",this.popScroll)}if(this.args.isClickable){TY.event.remove(document,"click",this.clickToRemove)}this.destory()},destory:function(){try{var d=this,c;for(c in d){c=null}setTimeout(function(){delete d},100)}catch(b){throw new Error("TY.ui.pop.destory ERROR")}},init:function(b){var c=this;this.setConfig(b);this.createHtml();this.bindEvent()}})})(TY.ui.pop);TY.ui.popV2=TY.ui.pop;