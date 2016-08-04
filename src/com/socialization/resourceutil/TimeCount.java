package com.socialization.resourceutil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;
import com.socialization.domain.Resource;

public class TimeCount {
	
	public Date timeCount(String string) {  //取搜索时间
		try {
			string = new String(string.getBytes("ISO-8859-1"),"UTF-8");  //解决从页面获取的值为乱码的问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		Date date=new Date();		
	    Calendar calendar=new GregorianCalendar(); 
	    calendar.setTime(date); 	
		switch (string) {
		case "请选择日期":
	           calendar.add(Calendar.DATE, -3650); 
				break;
		case "一周以内":
			System.out.println();
           calendar.add(Calendar.DATE, -7); 
           System.out.println(calendar);
			break;
		case "一月以内":
			calendar.add(Calendar.DATE, -30); 
			break;
		case "一年以内":
			calendar.add(Calendar.DATE, -365); 
			break;
		default:
			break;
		}
		return calendar.getTime();
	}
	public String name(String str) {
		str=str.substring(str.lastIndexOf(".")+1);
		String url=null;
		switch (str) {
		case "rar":
			url="img\\resource\\rar.gif";
			break;
		case "zip":
			url="img\\resource\\zip.gif";
			break;
		case "mp3":
			url="img\\resource\\mp3.gif";
			break;
		case "jpg":
			url="img\\resource\\jpg.gif";
			break;
		case "pdf":
			url="img\\resource\\pdf.gif";
			break;
		case "txt":
			url="img\\resource\\txt.gif";
			break;
		case "doc":
			url="img\\resource\\doc.gif";
			break;
		case "aac":
			url="img\\resource\\aac.gif";
			break;
		case "ace":
			url="img\\resource\\ace.gif";
			break;
		case "ai":
			url="img\\resource\\ai.gif";
			break;
		case "ain":
			url="img\\resource\\ain.gif";
			break;
		case "amr":
			url="img\\resource\\amr.gif";
			break;
		case "app":
			url="img\\resource\\app.gif";
			break;
		case "arj":
			url="img\\resource\\arj.gif";
			break;
		case "asf":
			url="img\\resource\\asf.gif";
			break;
		case "asp":
			url="img\\resource\\asp.gif";
			break;
		case "aspx":
			url="img\\resource\\aspx.gif";
			break;
		case "av":
			url="img\\resource\\av.gif";
			break;
		case "avi":
			url="img\\resource\\avi.gif";
			break;
		case "bin":
			url="img\\resource\\bin.gif";
			break;
		case "bmp":
			url="img\\resource\\bmp.gif";
			break;
		case "cab":
			url="img\\resource\\cab.gif";
			break;
		case "cad":
			url="img\\resource\\cad.gif";
			break;
		case "cat":
			url="img\\resource\\cat.gif";
			break;
		case "cdr":
			url="img\\resource\\cdr.gif";
			break;
		case "chm":
			url="img\\resource\\chm.gif";
			break;
		case "com":
			url="img\\resource\\com.gif";
			break;
		case "css":
			url="img\\resource\\css.gif";
			break;
		case "cur":
			url="img\\resource\\cur.gif";
			break;
		case "dat":
			url="img\\resource\\dat.gif";
			break;
		case "db":
			url="img\\resource\\db.gif";
			break;
		case "dll":
			url="img\\resource\\dll.gif";
			break;
		case "dmv":
			url="img\\resource\\dmv.gif";
			break;
		case "dot":
			url="img\\resource\\dot.gif";
			break;
		case "dps":
			url="img\\resource\\dps.gif";
			break;
		case "dpt":
			url="img\\resource\\dpt.gif";
			break;
		case "dwg":
			url="img\\resource\\dwg.gif";
			break;
		default:
			url="img\\resource\\"+str+".gif";
			break;
		}
		return url;
	}
	@SuppressWarnings("unchecked")
	public static Map sortMap(Map oldMap) {   //对MAP中的键值对按VAlue值进行排序 
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
  
            @Override  
            public int compare(Entry<java.lang.String, Integer> arg0,  
                    Entry<java.lang.String, Integer> arg1) {  
                return arg0.getValue() - arg1.getValue();  
            }  
        });  
        Map newMap = new LinkedHashMap();  
        for (int i = list.size()-1; i >=0 ; i--) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }
	public Map<String, Integer> resToMap2(List<Resource> list) {  //取得排行最多的五个标签
		String resTagList="";
		for (int i = 0; i < list.size(); i++) {  //获取每个资源的标签，并用空格隔开
          resTagList=resTagList+list.get(i).getResTags()+" ";
		}
		String[] resT=resTagList.split("\\s+"); //按空格隔开，变成数组
		Map<String, Integer> map = new TreeMap<String, Integer>(); //统计每个标签出现的次数 
        for (int i = 0; i < resT.length; i++) {  
            if (map.containsKey(resT[i])) {  
                int tempCount = map.get(resT[i]);  
                map.put(resT[i], ++tempCount);  
            } else {  
                map.put(resT[i], 1);  
            }  
        }
        map=sortMap(map);  //调用排序算法
        Map<String, Integer> mapresult=new TreeMap<String,Integer>();
        Set<String> set=map.keySet();
        Iterator<String> iterator=set.iterator(); 
        int i=5;
        while (iterator.hasNext()&&i>0) {
        String ss=iterator.next();
        int ii=map.get(ss);
        mapresult.put(ss, ii);
        i--;
		}
        return mapresult;
	}
	public Map<String, Integer> resToMap(List<Resource> list) {  //取得排行最多的五个标签
		String resTagList="";
		for (int i = 0; i < list.size(); i++) {  //获取每个资源的标签，并用空格隔开
          resTagList=resTagList+list.get(i).getResTags()+" ";
		}
		String[] resT=resTagList.split("\\s+"); //按空格隔开，变成数组
		Map<String, Integer> map = new HashMap<String, Integer>(); //统计每个标签出现的次数 
        for (int i = 0; i < resT.length; i++) {  
            if (map.containsKey(resT[i])) {  
                int tempCount = map.get(resT[i]);  
                map.put(resT[i], ++tempCount);  
            } else {  
                map.put(resT[i], 1);  
            }  
        }
        List<Entry<String,Integer>> list2 =new ArrayList<Entry<String,Integer>>(map.entrySet());
        Collections.sort(list2, new Comparator<Map.Entry<String, Integer>>() {
        	public int compare(Map.Entry<String, Integer> o1,
        			Map.Entry<String, Integer> o2) {
        		return (o2.getValue() - o1.getValue());
        	}
        });
        Map<String, Integer> mapresult = new LinkedHashMap<String, Integer>(); //linked迭代时按插入顺序，Tree插入时按KEY的值排序
        for (int i = 0; i < 5 ; i++) {
        	System.out.println(list2.get(i).getKey()+list2.get(i).getValue());
        	//listresult.add(i, <list2.get(i).getKey(), list2.get(i).getValue());
        	mapresult.put(list2.get(i).getKey(), list2.get(i).getValue());  
        }  
        return mapresult; 
	}
}
