package com.socialization.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.socialization.domain.Reply;
import com.socialization.domain.Topic;

public class ReplyUtil {

	public static List<Reply> getAllReplys(List<Reply> topReplyList) {
		List<Reply> childList = new ArrayList<Reply>();
		walkDepartmentTreeList(topReplyList,"--",childList);
		
		return childList;
	}

	private static void walkDepartmentTreeList(Collection<Reply> topReplyList, String perfix, List<Reply> childList) {
		for (Reply top : topReplyList) {
			Reply replyTop = new Reply();
			replyTop.setId(top.getId());
			replyTop.setContent(perfix+top.getContent());
			childList.add(replyTop);
			
			walkDepartmentTreeList(top.getChildrens(), "　"+perfix, childList);
		}
	}


	public static List<Reply> getAllReplys(Reply reply, List<Reply> replyList) {
		List<Reply> childList = new ArrayList<Reply>();
		for (Reply r : replyList) {
			if(r.getParent()!=null){
				if(r.getParent().getId()==reply.getId()){
					childList.add(r);
				}
			}
		}
		return childList;
	}
	
	public static void deleteRecursionReply(Topic topic,Reply reply, Reply r,Collection<Reply> childrens,Reply other) {
		for (Reply child : childrens) { 
			if(child==reply) {	//如果有帖子的子帖是最新回复的
				System.out.println("=========" + child.getId());
				topic.setLastReply(other);
			}
			else deleteRecursionReply(topic, reply, r, child.getChildrens(),other);
		}
}

}
