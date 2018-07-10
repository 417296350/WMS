package com.company.wms.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.company.wms.dao.ISystemMenuDAO;
import com.company.wms.domain.SystemMenu;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;
import com.company.wms.vo.SystemMenuVO;
import com.company.wms.web.service.ISystemMenuService;
import lombok.Setter;
import org.w3c.dom.ls.LSInput;

import java.util.*;

/**
 * Created by xd on 2018/5/5.
 */
public class SystemMenuServiceImpl implements ISystemMenuService{

    @Setter
    private ISystemMenuDAO systemMenuDAO;

    @Override
    public List<SystemMenu> getAllList() {
        return systemMenuDAO.getAllList();
    }

    @Override
    public List<SystemMenu> getAllChildMenus() {
        return systemMenuDAO.getAllChildMenus();
    }

    @Override
    public List<SystemMenu> getChildMenusByParentSn(String parentSn) {
        return systemMenuDAO.getChildMenusByParentSn(parentSn);
    }

    @Override
    public String getChildMenusJsonByParentSn(String parentSn) {

        List<SystemMenu> systemMenus = getChildMenusByParentSn(parentSn);

        Map<String,Object> json = new HashMap<>();
        List<Map<String,Object>> jsonArray = new ArrayList<>();

        for (SystemMenu menu : systemMenus) {
            jsonArray.add(menu.toJson());
        }

        return JSON.toJSONString(jsonArray);
    }

    @Override
    public PageResult<SystemMenu> getQuery(BaseQuery query) {
        return systemMenuDAO.getQuery(query);
    }

    @Override
    public List<SystemMenuVO> getMenuStructure(Long parentId) {
        List<SystemMenuVO> systemMenus = new ArrayList<>();
        if ( parentId > 0 ){
            //非根目录递归得到数据
            setParentMenuToSystemMenus(systemMenus,parentId);
        }
        Collections.reverse(systemMenus);
        return systemMenus;
    }

    @Override
    public SystemMenu get(SystemMenu systemMenu) {
        return systemMenuDAO.get(systemMenu.getId());
    }

    @Override
    public void save(SystemMenu systemMenu) {
        if (systemMenu.getParentMenu() != null && systemMenu.getParentMenu().getId() == -1){
            systemMenu.setParentMenu(null);
        }
        systemMenuDAO.save(systemMenu);
    }

    @Override
    public void update(SystemMenu systemMenu) {
        if (systemMenu.getParentMenu() != null && systemMenu.getParentMenu().getId() == -1){
            systemMenu.setParentMenu(null);
        }
        systemMenuDAO.update(systemMenu);
    }

    @Override
    public void delete(SystemMenu systemMenu) {
        systemMenuDAO.delete(systemMenu);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        systemMenuDAO.batchDelete(ids);
    }

    @Override
    public String getMenuName(Long id){
        if (id == -1L){
            return "根目录";
        }else {
            return systemMenuDAO.get(id).getName();
        }
    }

    /**
     * 根据 父菜单Id递归的到所有父菜单及父菜单的所有上级菜单对象，同时封装到systemMenus集合中
     * @param systemMenus 封装所有父菜单对象的集合
     * @param parentId 父菜单Id
     */
    private void setParentMenuToSystemMenus(List<SystemMenuVO> systemMenus,Long parentId){

        SystemMenu querySystemMenu = systemMenuDAO.get(parentId);

        if (querySystemMenu.getParentMenu() != null ){
            Long queryParentId = querySystemMenu.getParentMenu().getId();
            systemMenus.add(new SystemMenuVO(querySystemMenu.getId(),querySystemMenu.getName()));
            setParentMenuToSystemMenus(systemMenus,queryParentId);
        }else {
            systemMenus.add(new SystemMenuVO(querySystemMenu.getId(),querySystemMenu.getName()));
        }

    }

}

