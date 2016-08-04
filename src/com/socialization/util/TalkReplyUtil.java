package com.socialization.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.socialization.domain.Talk;
import com.socialization.domain.TalkReply;

public class TalkReplyUtil {
	public static List<TalkReply> getAllTalkReplys(List<TalkReply> topTalkReplyList) {
		List<TalkReply> childList = new ArrayList<TalkReply>();
		walkDepartmentTreeList(topTalkReplyList,"--",childList);
		
		return childList;
	}

	private static void walkDepartmentTreeList(Collection<TalkReply> topTalkReplyList, String perfix, List<TalkReply> childList) {
		for (TalkReply top : topTalkReplyList) {
			TalkReply TalkReplyTop = new TalkReply();
			TalkReplyTop.setId(top.getId());
			TalkReplyTop.setContent(perfix+top.getContent());
			childList.add(TalkReplyTop);
			
			walkDepartmentTreeList(top.getChildrens(), "　"+perfix, childList);
		}
	}


	public static List<TalkReply> getAllTalkReplys(TalkReply TalkReply, List<TalkReply> TalkReplyList) {
		List<TalkReply> childList = new ArrayList<TalkReply>();
		for (TalkReply tr : TalkReplyList) {
			if(tr.getParent()!=null){
				if(tr.getParent().getId()==TalkReply.getId()){
					childList.add(tr);
				}
			}
		}
		return childList;
	}
	
	public static void deleteRecursionTalkReply(Talk talk,TalkReply TalkReply, TalkReply tr,Collection<TalkReply> childrens,TalkReply other) {
		for (TalkReply child : childrens) { 
			if(child==TalkReply) {	//如果有帖子的子帖是最新回复的
				System.out.println("=========" + child.getId());
				talk.setLastReply(other);
			}
			else deleteRecursionTalkReply(talk, TalkReply, tr, child.getChildrens(),other);
		}
}

}
