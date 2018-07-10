import com.company.wms.web.service.IPermissionService;
import com.company.wms.web.service.ISystemMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by xd on 2018/6/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemMenuServiceTest {

    @Autowired
    private ISystemMenuService systemMenuService;

    @Test
    public void reloadPermissionTest() throws IOException {
        System.out.println(systemMenuService.getAllList());
    }


}
