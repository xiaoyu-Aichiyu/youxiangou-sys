package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysCategory;
import com.qqls.youxiangousys.pj.sys.entity.SysMenu;
import com.qqls.youxiangousys.pj.sys.vo.SysItemParentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysCategoryDao {

    /**
     * 查询所有菜单及父菜单名称
     * @return
     */
    public List<Map<String, Object>> doFindCategory() ;

    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    List<SysCategory> findZtreeCategory();

    /**
     * 查找分类名，上级分类id
     * @param name
     * @param parentId
     * @return
     */
    @Select("select * from t_type where name = #{name} and parent_id = #{parentId}")
    SysCategory findCategoryByName(String name, Integer parentId);

    /**
     *
     * 添加菜单
     * @param category
     * @return
     */
    int saveCategory(SysCategory category);

    /**
     * 修改分类
     * @param category
     * @return
     */
    int updateCategory(SysCategory category);

    /**
     * 通过分类id查找分类所有信息
     * @param typeId
     * @return
     */
    @Select("select * from t_type where id = #{typeId}")
    SysCategory findTypeId(Integer typeId);
}
