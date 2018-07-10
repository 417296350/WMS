import com.company.wms.domain.Department;
import com.company.wms.domain.Employee;
import com.company.wms.web.service.ILoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LoginServiceTest {

    @Autowired
    private ILoginService loginService;

    @Test
    public void login(){
        Employee tempUser = new Employee();
        tempUser.setName("username");
        loginService.Login("1","1");
    }

}
