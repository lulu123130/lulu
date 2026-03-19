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

import com.yb.entity.ChandiEntity;
import com.yb.entity.view.ChandiView;

import com.yb.service.ChandiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 产地
 * 后端接口
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@RestController
@RequestMapping("/chandi")
public class ChandiController {
    @Autowired
    private ChandiService chandiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChandiEntity chandi,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChandiEntity> ew = new QueryWrapper<ChandiEntity>();


        //查询结果
		PageUtils page = chandiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chandi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ChandiEntity chandi,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChandiEntity> ew = new QueryWrapper<ChandiEntity>();

        //查询结果
		PageUtils page = chandiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chandi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChandiEntity chandi){
       	QueryWrapper<ChandiEntity> ew = new QueryWrapper<ChandiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chandi, "chandi"));
        return R.ok().put("data", chandiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChandiEntity chandi){
        QueryWrapper< ChandiEntity> ew = new QueryWrapper< ChandiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chandi, "chandi"));
		ChandiView chandiView =  chandiService.selectView(ew);
		return R.ok("查询产地成功").put("data", chandiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChandiEntity chandi = chandiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chandi,deSens);
        return R.ok().put("data", chandi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChandiEntity chandi = chandiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chandi,deSens);
        return R.ok().put("data", chandi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChandiEntity chandi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(chandiService.count(new QueryWrapper<ChandiEntity>().eq("chandi", chandi.getChandi()))>0) {
            return R.error("产地已存在");
        }
        //ValidatorUtils.validateEntity(chandi);
        chandiService.save(chandi);
        return R.ok().put("data",chandi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChandiEntity chandi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(chandiService.count(new QueryWrapper<ChandiEntity>().eq("chandi", chandi.getChandi()))>0) {
            return R.error("产地已存在");
        }
        //ValidatorUtils.validateEntity(chandi);
        chandiService.save(chandi);
        return R.ok().put("data",chandi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChandiEntity chandi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chandi);
        //验证字段唯一性，否则返回错误信息
        if(chandiService.count(new QueryWrapper<ChandiEntity>().ne("id", chandi.getId()).eq("chandi", chandi.getChandi()))>0) {
            return R.error("产地已存在");
        }
        //全部更新
        chandiService.updateById(chandi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chandiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
