package com.lunarku.shop.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunarku.search.mapper.SearchItemMapper;
import com.lunarku.shop.common.pojo.SearchItem;

public class ItemAddMessageListener implements MessageListener {
	
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage)message;
			String text = textMessage.getText();
			long itemId = Long.parseLong(text);
			System.out.println(itemId);
			// 根据商品id查询数据，获取商品信息
			// 等待事务提交
			Thread.sleep(1000);
			SearchItem searchItem = searchItemMapper.getItem(itemId);
			// 创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			//向文档对象中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			// 把文档写入到索引库
			solrServer.add(document);
			// 提交
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
