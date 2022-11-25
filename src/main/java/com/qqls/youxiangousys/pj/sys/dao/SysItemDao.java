package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SysItemDao {

    /**
     * 统计商品数量,用来分页
     * @param itemName
     * @return
     */
    Integer getCountItem(@Param("itemName")String itemName, @Param("itemState")Integer itemState);

    /**
     * 根据商品名查商品
     * @param itemName
     * @param start
     * @param pageSize
     * @return
     */
    List<SysItem> findItemByItemName(@Param("itemName") String itemName,@Param("start")Integer start,@Param("pageSize")Integer pageSize, @Param("itemState") Integer itemState);

    /**
     * 修改用户启用禁用
     * @param id
     * @param state
     * @return
     */
    @Update("update t_item set item_state = #{state} where id = #{id}")
    Integer updateState(Integer id, Integer state);

    /**
     * 修改用户推荐状态
     * @param id
     * @param itemSell
     * @return
     */
    @Update("update t_item set item_sell = #{itemSell} where id = #{id}")
    Integer updateSell(Integer id, Integer itemSell);

    /**
     * 根据id删除商品信息
     * @param ids
     * @return
     */
    Integer deleteItemByIds(@Param("ids") Integer[] ids,@Param("itemState") Integer itemState);

    /**
     * 查看已被删除的商品数据
     * @return
     */
    @Select("select * from t_item where item_state = 2")
    List<SysItem> findDeleteAfter();

    /**
     * 导出所有商品
     */
    @Select("select * from t_item")
    List<SysItem> findExportThisItem();

    /**
     * 导入插入商品
     * @param itemData
     * @return
     */
    Integer insertItemExcel(List<SysItem> itemData);

    /**
     * 查询重复的导入商品
     * @param itemName
     * @param itemNote
     * @return
     */
    @Select("select * from t_item where item_name=#{itemName} and item_note=#{itemNote}")
    SysItem findCarByNameAndNote(@Param("itemName") String itemName, @Param("itemNote") String itemNote);
}
