import com.company.wms.domain.Employee;
import com.company.wms.web.service.ILoginService;
import com.company.wms.web.service.IPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by xd on 2018/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PermissionTest {

    @Autowired
    private IPermissionService permissionService;

    @Test
    public void reloadPermissionTest() throws IOException {
        permissionService.reloadPermission();
    }

}
