package com.qqls.youxiangousys.pj.admin.sys.dao;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SysItemDao {

    /**
     * 统计商品数量,用来分页
     * @param itemName
     * @return
     */
    int getCountItem(String itemName);

    /**
     * 根据商品名查商品
     * @param itemName
     * @param start
     * @param pageSize
     * @return
     */
    List<SysItem> findItemByItemName(@Param("itemName") String itemName,@Param("start")Integer start,@Param("pageSize")Integer pageSize);

    /**
     * 修改用户启用禁用
     * @param id
     * @param state
     * @return
     */
    @Update("update t_item set item_state = #{state} where id = #{id}")
    int updateState(Integer id, Integer state);
}
