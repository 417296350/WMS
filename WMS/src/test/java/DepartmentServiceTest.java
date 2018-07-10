import com.company.wms.dao.IDepartmentDAO;
import com.company.wms.dao.IEmployeeDAO;
import com.company.wms.domain.Department;
import com.company.wms.web.service.IDepartmentService;
import com.company.wms.web.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xd on 2018/5/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {

    @Autowired
    private IDepartmentDAO departmentDAO;

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void getAllList(){
        Department department = new Department();
        department.setName("1");
        departmentService.save(department);
    }

}
