package dataManager;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import vo.Program;
public interface DataBaseManager {

	@Select ("select * from program where Id=#{id}")
	public Program getProgramById(int id);
	public void insertProgram(Program program);
	public void updataProgram(Program program);
	public List<Program> selectProgram(String key);
	public void deleteProgram(String Id);
}
