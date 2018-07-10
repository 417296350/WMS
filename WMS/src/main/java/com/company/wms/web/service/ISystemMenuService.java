package com.company.wms.web.service;

import com.company.wms.domain.SystemMenu;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;
import com.company.wms.vo.SystemMenuVO;

import java.util.List;
import java.util.Map;

/**
 * Created by xd on 2018/5/5.
 */
public interface ISystemMenuService {

    /**
     * 获取所有系统菜单对象
     * @return 返回List<SystemMenu>
     */
    public List<SystemMenu> getAllList();

    /**
     * 根据查询条件进行查询，返回PageResult对象
     * @param query 查询条件对象
     * @return PageResult对象(封装了系统菜单对象集合、分页信息等)
     */
    public PageResult<SystemMenu> getQuery(BaseQuery query);

    /**
     * 根据父菜单ID获取父菜单及父菜单的所有上级目录对象
     * @param parentId 父菜单Id
     * @return 返回获取父菜单及父菜单的所有上级目录对象的集合
     */
    public List<SystemMenuVO> getMenuStructure(Long parentId);

    /**
     * 获取所有系统菜单
     * @return 返回List<SystemMenu>
     */
    public SystemMenu get(SystemMenu systemMenu);

    /**
     * 保存系统菜单对象
     * @param systemMenu 要被保存的SystemMenu对象
     */
    public void save(SystemMenu systemMenu);

    /**
     * 更新系统菜单对象
     * @param systemMenu 要被更新的SystemMenu对象
     */
    public void update(SystemMenu systemMenu);

    /**
     * 删除系统菜单对象
     * @param systemMenu 要被删除的SystemMenu对象
     */
    public void delete(SystemMenu systemMenu);

    /**
     * 批量删除系统菜单对象
     * @param ids
     */
    public void batchDelete(List<Long> ids);

    /**
     * 获取菜单名称
     * @param id 菜单id
     * @return   1.如果id=-1返回根目录 2.如果id!=-1返回当前菜单名称
     */
    public String getMenuName(Long id);

    /**
     * 获取所有子菜单
     * @return 返回子菜单
     */
    public List<SystemMenu> getAllChildMenus();

    /**
     * 根据父菜单sn获取所有子菜单
     * @param parentSn 父菜单sn
     * @return 返回子菜单集合
     */
    List<SystemMenu> getChildMenusByParentSn(String parentSn);

    /**
     * 根据父菜单sn获取所有子菜单
     * @param parentSn 父菜单sn
     * @return 返回子菜单JSON字符串
     */
    String getChildMenusJsonByParentSn(String parentSn);
}
