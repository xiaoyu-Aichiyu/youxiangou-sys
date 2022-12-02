package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SysCouponDao {

    /**
     * 统计优惠券的总条数
     * @param couponName
     * @param couponState
     * @return
     */
    Integer getCouponCount(@Param("couponName") String couponName, @Param("couponState") Integer couponState);

    /**
     * 初始查询且分页
     * @param couponName
     * @param start
     * @param pageSize
     * @param couponState
     * @return
     */
    List<SysCoupon> findAllCoupon(@Param("couponName") String couponName, @Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("couponState") Integer couponState);

    /**
     * 修改优惠券状态
     * @param id
     * @param state
     * @return
     */
    @Update("update t_coupon set coupon_state = #{state} where id = #{id}")
    Integer updateCouponState(@Param("id") Integer id, @Param("state") Integer state);

    /**
     * 根据id批量删除优惠券数据
     * @param ids
     * @param couponState
     * @return
     */
    Integer deleteCouponByIds(@Param("ids") Integer[] ids, @Param("couponState") Integer couponState);
}
