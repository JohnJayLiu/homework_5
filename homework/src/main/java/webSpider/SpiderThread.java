package webSpider;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SpiderThread extends Thread{
	
		public List <String>urlList;
		static List <String >htmls=new LinkedList<>();
		public SpiderThread(List<String > uList) {
			// TODO Auto-generated constructor stub
			urlList =new LinkedList<>(uList);
		}
		public void run () {
			for (String url:urlList) {
				System.out.println(url);
				String string=gainHtml(url);
				htmls.add(string);
			}
		}
		String gainHtml(String url) {
			Document document=null;
			Connection connection=Jsoup.connect(url);
			try {
				document=connection.get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return document.html();
		}
}
