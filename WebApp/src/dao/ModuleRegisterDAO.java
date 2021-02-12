package dao;

import java.util.List;
import entity.Module;

public interface ModuleRegisterDAO {
	boolean register(List<Module> moduleList, String student_id);
	boolean delete(List<Module> moduleList, String student_id);
}
