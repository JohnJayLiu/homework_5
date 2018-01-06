package JohnJay.homework;
import vo.Program;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.omg.CORBA.PRIVATE_MEMBER;

import dataManager.DataBaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uiManager.Mycontroller;
import webSpider.Manager;

/**
 * Hello world!
 *
 */

public class App extends Application
{
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
    	static {
    		try {
    			reader =Resources.getResourceAsReader("config/Configure.xml");
    			sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
    		}catch (Exception e) {
				// TODO: handle exception
    			e.printStackTrace();
			}
    	}
    	public static SqlSessionFactory getSession() {
    		return sqlSessionFactory;
    	}
    	
    public static void main( String[] args)
    {
    	SqlSession session=sqlSessionFactory.openSession();
    	Mycontroller.setSession(session);
    	
    	Thread mThread =new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Manager manager=new Manager();
		    	List<String> htmls=manager.getHtmlFromWeb();
		    	for (String s:htmls) {
		    		Program program=manager.parseHtml(s);
		    		if (program.getDeadlineWithAid()==null)program.setDeadlineWithAid("");
		    		if (program.getDeadlineWithoutAid()==null)program.setDeadlineWithoutAid("");
		    		if ((program.getDeadlineWithAid().length()<=255)&&(program.getDeadlineWithoutAid().length()<=255))
		    		{
		    			session.insert("dataManager.DataBaseManager.insertProgram", program);
		    			session.commit();
		    		}
		    		
		    		
		    	}
			}
		});
		mThread.start();
    	launch(args);
    	
    }

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/Scene1.fxml"));

            primaryStage.setTitle("Program");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

	}
	
}
