package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.annotation.RequiredLog;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.dao.SysCouponDao;
import com.qqls.youxiangousys.pj.sys.entity.SysCoupon;
import com.qqls.youxiangousys.pj.sys.service.SysCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysCouponServiceImpl implements SysCouponService {

    @Autowired
    private SysCouponDao couponDao;

    /**
     * 初始查询所有优惠券信息且分页
     * @param couponName
     * @param curPage
     * @param pageSize
     * @param couponState
     * @return
     */
    public Pagination findAllCoupon(String couponName, Integer curPage, Integer pageSize, Integer couponState) {
        //1.验证参数
        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码或者每页条数！！");
        //2.得到角色的总条数
        Integer count = couponDao.getCouponCount(couponName,couponState);
        //3.创建分页对象，算出所有属性
        Pagination pageObj = new Pagination(curPage,count,pageSize);
        curPage = pageObj.getCurPage();//得到当前页
        pageSize = pageObj.getPageSize();//得到每页条数
        //分页跳过条数=（当前页 - 1）*每页条数
        Integer start = (curPage - 1) * pageSize;
        //4.根据参数找角色
        List<SysCoupon> list = couponDao.findAllCoupon(couponName,start,pageSize,couponState);
        pageObj.setPageData(list);
        return pageObj;
    }

    /**
     * 修改优惠券状态
     * @param id
     * @param state
     * @return
     */
    @RequiredLog("修改优惠券状态")
    public Integer updateCouponState(Integer id, Integer state) {
        Assert.isEmpty(id == null || id == 0, "请选择要修改状态的优惠券！");
        Assert.isEmpty(state == null, "操作有误！");
        Integer n = couponDao.updateCouponState(id, state);
        Assert.isEmpty(n == 0, "状态修改失败！");
        return n;
    }

    /**
     * 根据id批量删除优惠券数据
     * @param ids
     * @param couponState
     * @return
     */
    @RequiredLog("删除商品")
    public Integer deleteCoupon(Integer[] ids, Integer couponState) {
        String message1 = "请选择要删除的数据！";
        String message2 = "优惠券删除失败！";
        if (couponState == 1){
            message1 = "请选择要启用的数据！";
            message2 = "优惠券启用失败！";
        }
        Assert.isEmpty(ids == null || ids.length == 0,message1);
        Integer n = couponDao.deleteCouponByIds(ids,couponState);
        Assert.isEmpty(n == 0,message2);
        return n;
    }

    /**
     * 添加优惠券信息
     * @param coupon
     * @return
     */
    @RequiredLog("新增优惠券")
    public Integer insertCoupon(SysCoupon coupon) {
        Assert.isEmpty(coupon == null,"请将表单信息填写完整！！");
        Integer n = couponDao.insertCoupon(coupon);
        Assert.isEmpty(n == 0,"优惠券数据添加失败！！");
        return n;
    }

    /**
     * 修改优惠券信息
     * @param coupon
     * @return
     */
    @RequiredLog("修改优惠券")
    public Integer updateCoupon(SysCoupon coupon) {
        Assert.isEmpty(coupon == null,"请将表单信息填写完整！！");
        Integer n = couponDao.updateCoupon(coupon);
        Assert.isEmpty(n == 0,"优惠券数据修改失败！！");
        return n;
    }
}
