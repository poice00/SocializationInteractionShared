TY.loadedModule("TY.io.cookie");
jQuery.cookie=function(c,b,a){if(1<arguments.length&&"[object Object]"!==""+b){a=jQuery.extend({},a);if(null===b||void 0===b)a.expires=-1;if("number"===typeof a.expires){var e=a.expires,d=a.expires=new Date;d.setDate(d.getDate()+e)}b=""+b;return document.cookie=[encodeURIComponent(c),"=",a.raw?b:encodeURIComponent(b),a.expires?"; expires="+a.expires.toUTCString():"",a.path?"; path="+a.path:"",a.domain?"; domain="+a.domain:"",a.secure?"; secure":""].join("")}a=b||{};d=a.raw?function(a){return a}:decodeURIComponent;
return(e=RegExp("(?:^|; )"+encodeURIComponent(c)+"=([^;]*)").exec(document.cookie))?d(e[1]):null};TY.namespace("TY.io.cookie");TY.io.cookie=TY.io.cookie;TY.extend(TY.io.cookie,{set:function(c,b,a){this.config={expires:7,path:"/",domain:"",secure:!1};jQuery.extend(this.config,a);jQuery.cookie(c,b,this.config)},get:function(c){return jQuery.cookie(c)},del:function(c,b){this.config={path:"/",domain:""};jQuery.extend(this.config,b);jQuery.cookie(c,null,this.config)}});
