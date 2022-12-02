package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.common.annotation.RequiredLog;
import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.service.SysCouponService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coupon")
public class SysCouponController {

    @Autowired
    private SysCouponService couponService;

    /**
     * 初始查询所有优惠券信息且分页
     * @param couponName
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequiredLog("查找优惠券")
    @RequiresPermissions("sys:coupon:view")
    @RequestMapping("findAllCoupon")
    public JsonResult findAllCoupon(String couponName,Integer curPage,Integer pageSize,Integer couponState){
        Pagination obj = couponService.findAllCoupon(couponName,curPage,pageSize,couponState);
        return new JsonResult(obj);
    }

    /**
     * 修改优惠券状态
     * @return
     */
    @RequestMapping("updateCouponState")
    public JsonResult updateCouponState(Integer id,Integer state){
        JsonResult jr = new JsonResult(couponService.updateCouponState(id,state));
        jr.setMessage("状态修改成功！");
        return jr;
    }

    /**
     * 根据id批量删除优惠券数据
     * @param ids
     * @param couponState
     * @return
     */
    @RequestMapping("deleteCoupon")
    public JsonResult deleteCoupon(@RequestParam("ids[]") Integer[] ids, Integer couponState){
        return new JsonResult(couponService.deleteCoupon(ids,couponState));
    }
}
