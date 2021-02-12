package dao;

import java.util.List;
import entity.Module;

public interface HistoryModuleDAO {
	List<Module> getModuleRegister(String id, int year, int semester);
}
