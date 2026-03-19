package com.yb.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.yb.utils.*;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yb.annotation.IgnoreAuth;

import com.yb.entity.ChanpinfenleiEntity;
import com.yb.entity.view.ChanpinfenleiView;

import com.yb.service.ChanpinfenleiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 产品分类
 * 后端接口
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@RestController
@RequestMapping("/chanpinfenlei")
public class ChanpinfenleiController {
    @Autowired
    private ChanpinfenleiService chanpinfenleiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChanpinfenleiEntity chanpinfenlei,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChanpinfenleiEntity> ew = new QueryWrapper<ChanpinfenleiEntity>();


        //查询结果
		PageUtils page = chanpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpinfenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChanpinfenleiEntity chanpinfenlei,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChanpinfenleiEntity> ew = new QueryWrapper<ChanpinfenleiEntity>();

        //查询结果
		PageUtils page = chanpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpinfenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChanpinfenleiEntity chanpinfenlei){
       	QueryWrapper<ChanpinfenleiEntity> ew = new QueryWrapper<ChanpinfenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chanpinfenlei, "chanpinfenlei"));
        return R.ok().put("data", chanpinfenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChanpinfenleiEntity chanpinfenlei){
        QueryWrapper< ChanpinfenleiEntity> ew = new QueryWrapper< ChanpinfenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chanpinfenlei, "chanpinfenlei"));
		ChanpinfenleiView chanpinfenleiView =  chanpinfenleiService.selectView(ew);
		return R.ok("查询产品分类成功").put("data", chanpinfenleiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChanpinfenleiEntity chanpinfenlei = chanpinfenleiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chanpinfenlei,deSens);
        return R.ok().put("data", chanpinfenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChanpinfenleiEntity chanpinfenlei = chanpinfenleiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chanpinfenlei,deSens);
        return R.ok().put("data", chanpinfenlei);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChanpinfenleiEntity chanpinfenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(chanpinfenleiService.count(new QueryWrapper<ChanpinfenleiEntity>().eq("chanpinfenlei", chanpinfenlei.getChanpinfenlei()))>0) {
            return R.error("产品分类已存在");
        }
        //ValidatorUtils.validateEntity(chanpinfenlei);
        chanpinfenleiService.save(chanpinfenlei);
        return R.ok().put("data",chanpinfenlei.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChanpinfenleiEntity chanpinfenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(chanpinfenleiService.count(new QueryWrapper<ChanpinfenleiEntity>().eq("chanpinfenlei", chanpinfenlei.getChanpinfenlei()))>0) {
            return R.error("产品分类已存在");
        }
        //ValidatorUtils.validateEntity(chanpinfenlei);
        chanpinfenleiService.save(chanpinfenlei);
        return R.ok().put("data",chanpinfenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChanpinfenleiEntity chanpinfenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpinfenlei);
        //验证字段唯一性，否则返回错误信息
        if(chanpinfenleiService.count(new QueryWrapper<ChanpinfenleiEntity>().ne("id", chanpinfenlei.getId()).eq("chanpinfenlei", chanpinfenlei.getChanpinfenlei()))>0) {
            return R.error("产品分类已存在");
        }
        //全部更新
        chanpinfenleiService.updateById(chanpinfenlei);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chanpinfenleiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
