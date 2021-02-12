package dao;

import java.util.List;
import entity.Module;

public interface ModuleDAO {
	List<Module> getModule(int year, int semester);
}
