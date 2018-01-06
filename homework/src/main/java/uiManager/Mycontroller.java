package uiManager;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.SortingFocusTraversalPolicy;

import org.apache.ibatis.session.SqlSession;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import vo.Program;

public class Mycontroller implements Initializable{
	@FXML private Button searchutton;
	@FXML private TextField searchTextField;
	@FXML private TableView<Program> resultTable;
	@FXML private TableColumn<Program,String> country;
	@FXML private TableColumn<Program,String>  university;
	@FXML private TableColumn<Program,String>  school;
	@FXML private TableColumn<Program,String>  programName;
	@FXML private TableColumn<Program,String>  degree;
	@FXML private TableColumn<Program,String>  Id;
	@FXML private Label lable;
	String key=null;
	List<Program>programs;
	static SqlSession session;
	
	public static void  setSession(SqlSession session1) {
		session=session1;
	}
	@Override
	
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		country.setCellValueFactory(cellData->cellData.getValue().getCountryProperty());
		school.setCellValueFactory(cellData->cellData.getValue().getSchoolProperty());
		university.setCellValueFactory(cellData->cellData.getValue().getUniversityProperty());
		programName.setCellValueFactory(cellData->cellData.getValue().getProgramNameProperty());
		degree.setCellValueFactory(cellData->cellData.getValue().getDegreeProperty());
		Id.setCellValueFactory(cellData->cellData.getValue().getIdProperty());
		lable.setWrapText(true);
		}
	public void searchProgram(ActionEvent event) {
		
		key =searchTextField.getText();
		if(key=="cs"||key=="Cs"||key=="CS")
			key="computer science";
		ObservableList<Program> list=FXCollections.observableArrayList();
		
		programs=session.selectList("dataManager.DataBaseManager.selectProgram",key);
		session.commit();
		list.addAll(programs);
		resultTable.setItems(list);
	}
	public void showInfo(MouseEvent event) {
		int index=resultTable.getSelectionModel().getSelectedIndex();
		Program program=programs.get(index);
		String string="University: "+program.getUniversity()+"\n"
				+"Country: "+program.getCountry()+"\n"
				+"ProgramName: "+program.getProgramName()+"\n"
				+"School: "+program.getSchool()+"\n"
				+"Degree: "+program.getDegree()+"\n"
				+"Email: "+program.getEmail()+"\n"
				+"PhoneNumber: "+program.getPhoneNumber()+"\n"
				+"Location: "+program.getLocation()+"\n"
				+"DeadlineWithAid: "+program.getDeadlineWithAid()+"\n"
				+"DeadlineWithoutAid:"+program.getDeadlineWithoutAid()+"\n"
				+"Homepage: "+program.getHomepage()+"\n";
		lable.setText(string);
	}
}
