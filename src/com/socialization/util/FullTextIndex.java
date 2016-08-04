package com.socialization.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.LockObtainFailedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.socialization.domain.Activity;
import com.socialization.domain.Resource;
import com.socialization.domain.Topic;
import com.socialization.service.TopicService;
import com.socialization.service.resource.ActiviService;
import com.socialization.service.resource.UpdowncollectResService;

import javassist.compiler.ast.Keyword;

/**
 * @desc 对论坛、资源、活动生成全文索引，
 * 并进行创建、增加、修改、删除索引操作
 * @author yanbaobin@yeah.net
 * Aug 13, 2015 3:39:50 PM
 */
public class FullTextIndex {
	
	private static StringBuilder path;	//索引路径
	
	private static FullTextIndex fti;	//单例模式
	
	/*没有注入成功？？？*/
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	protected static TopicService topicService	=  (TopicService) ac.getBean("topicServiceImpl");
	protected static UpdowncollectResService resourceService	=  (UpdowncollectResService) ac.getBean("updowncollectResServiceImpl");
	protected static ActiviService activityService	=  (ActiviService) ac.getBean("activiServiceImpl");

	private FullTextIndex(){
		
	}
	
	public static FullTextIndex getInstance(){
		if(fti == null)
			fti = new FullTextIndex();
		
		return fti;
	}
	
	/**
	 * @desc 根据关键词搜索
	 * @param keywrods String 关键词
	 * @param scope String 搜索范围---论坛、资源、活动
	 * @return HashMap<String,String> 搜索结果
	 * @author yanbaobin@yeah.net
	 * @date Aug 13, 2015 8:05:41 PM
	 */
	public static HashMap<String, String> search(String keywords, String scope){
		
		HashMap<String, String> result = new HashMap<>();	//搜索结果
		if("".equals(keywords))
			return result;
		
		path = new StringBuilder(System.getProperty("user.dir") + "\\index\\").append(scope);
		
		File file = new File(path.toString());
		
		System.out.println(path + "....." + file.exists());
		
		/*索引不存在，创建索引*/
		if(!file.exists())
			createIndex(scope);
		
		/*按关键字查询*/
		try {
			IndexSearcher is = new IndexSearcher(path.toString());
			Analyzer analyzer = new StandardAnalyzer();
			
			QueryParser qpTitle = new QueryParser("title",analyzer);
			QueryParser qpContent = new QueryParser("content",analyzer);
			
			Query queryTitle = null;
			Query queryContent = null;
			try {
				queryTitle = qpTitle.parse(keywords);
				queryContent = qpContent.parse(keywords);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(is != null){
				Hits hitsTitle = is.search(queryTitle);
				Hits hitsContent = is.search(queryContent);
				
				for (int i = 0; i < hitsTitle.length(); i++){
					System.out.println("score:" + hitsTitle.score(i));
					result.put(hitsTitle.doc(i).get("id"), hitsTitle.doc(i).get("title"));
				}
				
				for (int i = 0; i < hitsContent.length(); i++)
					result.put(hitsContent.doc(i).get("id"), hitsContent.doc(i).get("title"));
			}
			
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @desc 创建索引
	 * @param scope 三种类型 论坛、资源、活动 
	 * @author yanbaobin@yeah.net
	 * @date Aug 13, 2015 4:51:12 PM
	 */
	public static void createIndex(String scope){ 
		
		Analyzer analyzer = new StandardAnalyzer();
		try {
			IndexWriter indexwriter = new IndexWriter(path.toString(), analyzer, true);
			
			/*论坛索引*/
			if("forum".equals(scope)){
				List<Topic> topicList =  topicService.findAll();
				
				for (Topic topic : topicList) {
					Field idField = new Field("id", topic.getId().toString(), Field.Store.YES, Field.Index.NO);
					Field titleField = new Field("title", topic.getTitle(), Field.Store.YES, Field.Index.TOKENIZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					Field contentField = new Field("content",topic.getContent(), Field.Store.YES, Field.Index.TOKENIZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					
					System.out.println("正在索引..." + topic.getTitle() + "::::" + topic.getContent());

					Document doc = new Document();			//终于找到错误了，尼玛，竟然是因为一直使用一个doc，都把爹给搞崩溃了
					doc.add(idField);
					doc.add(titleField);
					doc.add(contentField);
					
					indexwriter.addDocument(doc);
					
				}
			}
			
			/*资源索引*/
			else if("resource".equals(scope)){
				List<com.socialization.domain.Resource> resourceList = resourceService.findAll();
				
				for (Resource resource : resourceList) {
					Field idField = new Field("id", resource.getId().toString(), Field.Store.YES, Field.Index.NO);
					Field nameField = new Field("title", resource.getName(), Field.Store.YES,
							Field.Index.TOKENIZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					Field descriptionField = new Field("content", resource.getDescription(),
							Field.Store.YES, Field.Index.TOKENIZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					
					System.out.println("正在索引..." + resource.getName() + "::::" + resource.getDescription());
					
					Document doc = new Document();
					doc.add(idField);
					doc.add(nameField);
					doc.add(descriptionField);
					
					indexwriter.addDocument(doc);
				}
			}
			
			/*活动索引*/
			else if("activity".equals(scope)){
				List<Activity> activitieList = activityService.findAll();
				
				for (Activity activity : activitieList) {
					Field idField = new Field("id", activity.getId().toString(), Field.Store.YES, Field.Index.TOKENIZED);
					Field nameField = new Field("title", activity.getActivityName(), Field.Store.YES, Field.Index.TOKENIZED);
					Field descriptionField = new Field("content", activity.getActivitydescription(), Field.Store.YES, Field.Index.TOKENIZED);
					
					Document doc = new Document();
					doc.add(idField);
					doc.add(nameField);
					doc.add(descriptionField);
					
					indexwriter.addDocument(doc);
				}
			}
			
			/*优化索引*/
			indexwriter.optimize();
			indexwriter.close();
			
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HashMap<String, String> hm = search("", "forum");
		
		Set<String> str = hm.keySet();
		
		for (String string : str) {
			System.out.println(string + ":" + hm.get(string));
		}
	}
}
