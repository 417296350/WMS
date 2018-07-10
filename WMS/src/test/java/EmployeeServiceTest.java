import com.company.wms.dao.IEmployeeDAO;
import com.company.wms.dao.impl.EmployeeDAOImpl;
import com.company.wms.domain.Employee;
import com.company.wms.query.impl.EmployeeQuery;
import com.company.wms.result.PageResult;
import com.company.wms.web.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {

    @Autowired
    private IEmployeeDAO employeeDAO;

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void getAllList(){
        System.out.println(employeeDAO.getAllList());
    }


    @Test
    public void query(){
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setKeyword("1");
        employeeQuery.setDepartId(2L);
        PageResult result = employeeDAO.getQuery(employeeQuery);
        System.out.println(result);
    }

    @Test
    public void batchDelete(){
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        Employee employee = new Employee();
        employee.setId(2L);
        employeeService.batchDelete(ids);
    }

}
