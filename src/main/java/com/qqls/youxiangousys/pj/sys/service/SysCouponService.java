package com.qqls.youxiangousys.pj.sys.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;

public interface SysCouponService {

    /**
     * 初始查询所有优惠券信息且分页
     * @param couponName
     * @param curPage
     * @param pageSize
     * @param couponState
     * @return
     */
    Pagination findAllCoupon(String couponName, Integer curPage, Integer pageSize, Integer couponState);

    /**
     * 修改优惠券状态
     * @param id
     * @param state
     * @return
     */
    Integer updateCouponState(Integer id, Integer state);

    /**
     * 根据id批量删除优惠券数据
     * @param ids
     * @param couponState
     * @return
     */
    Integer deleteCoupon(Integer[] ids, Integer couponState);
}
