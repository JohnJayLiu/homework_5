package vo;
import java.io.Serializable;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
public class Program {
	
	    // 唯一标识id,32位随机字母数字
	    private String Id;

	    // 国家
	    private String country;

	    // 学校
	    private String university;

	    // 学院
	    private String school;

	    // 项目名称
	    private String program_name;

	    // 项目主页
	    private String homepage;

	    // 地址
	    private String location;

	    // 项目申请咨询邮箱
	    private String email;

	    // 联系方式
	    private String phone_number;

	    // 学位
	    private String degree;

	    // 申请截止时间（奖学金）
	    private String deadline_with_aid;

	    // 申请截止时间（无奖学金）
	    private String deadline_without_aid;

	    public String getId() {
	        return Id;
	    }
	    public StringProperty getIdProperty() {
	        return new SimpleStringProperty(Id);
	    }
	    public void setId(String id) {
	        this.Id = id;
	    }

	   
	    public String getUniversity() {
	        return university;
	    }
	    public StringProperty getUniversityProperty() {
	        return new SimpleStringProperty(university);
	    }
	    
	    public void setUniversity(String university) {
	        this.university = university;
	    }

	    public String getSchool() {
	        return school;
	    }
	    public StringProperty getSchoolProperty() {
	        return new SimpleStringProperty(school);
	    }
	    public void setSchool(String school) {
	        this.school = school;
	    }

	    public String getProgramName() {
	        return program_name;
	    }
	    public StringProperty getProgramNameProperty() {
	        return new SimpleStringProperty(program_name);
	    }
	    public void setProgramName(String programName) {
	        this.program_name = programName;
	    }

	    public String getHomepage() {
	        return homepage;
	    }

	    public void setHomepage(String homepage) {
	        this.homepage = homepage;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhoneNumber() {
	        return phone_number;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phone_number = phoneNumber;
	    }

	    public String getDegree() {
	        return degree;
	    }
	    public StringProperty getDegreeProperty() {
	        return new SimpleStringProperty(degree);
	    }
	    public void setDegree(String degree) {
	        this.degree = degree;
	    }

	    public String getDeadlineWithAid() {
	        return deadline_with_aid;
	    }

	    public void setDeadlineWithAid(String deadlineWithAid) {
	        this.deadline_with_aid = deadlineWithAid;
	    }

	    public String getDeadlineWithoutAid() {
	        return deadline_without_aid;
	    }

	    public void setDeadlineWithoutAid(String deadlineWithoutAid) {
	        this.deadline_without_aid = deadlineWithoutAid;
	    }

	    public String getCountry() {
	        return country;
	    }
	    public StringProperty getCountryProperty() {
	        return new SimpleStringProperty(country);
	    }
	    public void setCountry(String country) {
	        this.country = country;
	    }
	}


