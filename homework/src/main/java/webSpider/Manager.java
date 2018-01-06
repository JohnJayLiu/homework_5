package webSpider;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vo.Program;

public class Manager implements Parser,WebSpider{
	public Program parseHtml(String html) {
		Program temp=new Program();
		Document document=Jsoup.parse(html);
		org.jsoup.nodes.Element elem =document.select("div#AllContents").first();
		// TODO Auto-generated method stub
		temp.setCountry("America");
		temp.setDeadlineWithAid(null);	
		temp.setSchool(elem.children().select("span").first().text());
		temp.setDegree(elem.children().select("h1").first().child(1).text());
		
		
		if (document.select("h4:contains(Application Deadline:)").first()!=null) {
			if(document.select("h4:contains(Application Deadline:)").first().nextElementSibling().text().contains("funding")) {
				Element element2=document.select("h4:contains(Application Deadline:)").first().nextElementSibling().children().select("em:contains(funding)").first();
				temp.setDeadlineWithAid(element2.previousElementSibling().text()+" "+element2.text());
				temp.setDeadlineWithoutAid(document.select("h4:contains(Application Deadline:)").first().nextElementSibling().text().replaceAll(element2.previousElementSibling().text(),"").replaceAll(element2.text(), ""));
			}
			else temp.setDeadlineWithoutAid(document.select("h4:contains(Application Deadline:)").first().nextElementSibling().text());
			}
		else temp.setDeadlineWithoutAid(null);
		
		temp.setUniversity("University of Delaware");
		Element element=null;
		Elements elements =document.select("a[href]");
		for (Element e:elements) {
			if (e.attr("href").contains("mailto")) {
				element=e;
				break;
				}
			}
		temp.setPhoneNumber(element.previousSibling().outerHtml().replaceAll("•", ""));
		temp.setEmail(element.attr("href"));
		temp.setLocation(element.parent().text().replace(element.previousSibling().outerHtml(),"").replaceAll(element.text(), ""));
		temp.setProgramName(elem.children().select("h1").first().child(0).text());
		temp.setHomepage(elem.children().select("h1").first().child(0).attr("href"));
		String id=UUID.randomUUID().toString().replaceAll("-", "");
		temp.setId(id);
		return temp;
		}
	@Override
	public List<String> getHtmlFromWeb()  {
		// TODO Auto-generated method stub
		//获取主页
		List<String>htmls=new LinkedList<>();
		String url ="http://grad.udel.edu/graduate-programs/?frm-page-2654=";
		for (int i=1;i<=3;i++) {
			Connection connection=Jsoup.connect(url+i);
			Document document=null;
			connection.timeout(10000);
			try {
				document= connection.get();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
			org.jsoup.select.Elements elements=
					document.getElementsByAttributeValue("class", "button-primarygold5");
			for(org.jsoup.nodes.Element elem:elements){
				if (elem.text().contains("Program Details")) {
					String str=elem.attr("href");
					htmls.add(str);
					}
				}
			}
		SpiderThread myThread1=new SpiderThread(htmls.subList(0, htmls.size()/3));
		SpiderThread myThread2=new SpiderThread(htmls.subList( htmls.size()/3, htmls.size()/3*2));
		SpiderThread myThread3=new SpiderThread(htmls.subList( htmls.size()/3*2, htmls.size()));
		myThread1.start();
		myThread2.start();
		myThread3.start();
		try {
			myThread1.join();
			myThread2.join();
			myThread3.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		List <String >strings=new LinkedList<>(SpiderThread.htmls);
		return strings;
		}
	}
