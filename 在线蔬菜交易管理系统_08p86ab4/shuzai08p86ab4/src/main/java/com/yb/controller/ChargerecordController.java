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

import com.yb.entity.ChargerecordEntity;
import com.yb.entity.view.ChargerecordView;

import com.yb.service.ChargerecordService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 充值记录表
 * 后端接口
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@RestController
@RequestMapping("/chargerecord")
public class ChargerecordController {
    @Autowired
    private ChargerecordService chargerecordService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChargerecordEntity chargerecord,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            chargerecord.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        //设置查询条件
        QueryWrapper<ChargerecordEntity> ew = new QueryWrapper<ChargerecordEntity>();


        //查询结果
		PageUtils page = chargerecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chargerecord), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ChargerecordEntity chargerecord,
                @RequestParam(required = false) Double amountstart,
                @RequestParam(required = false) Double amountend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChargerecordEntity> ew = new QueryWrapper<ChargerecordEntity>();
        if(amountstart!=null) ew.ge("amount", amountstart);
        if(amountend!=null) ew.le("amount", amountend);

        //查询结果
		PageUtils page = chargerecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chargerecord), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChargerecordEntity chargerecord){
       	QueryWrapper<ChargerecordEntity> ew = new QueryWrapper<ChargerecordEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chargerecord, "chargerecord"));
        return R.ok().put("data", chargerecordService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChargerecordEntity chargerecord){
        QueryWrapper< ChargerecordEntity> ew = new QueryWrapper< ChargerecordEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chargerecord, "chargerecord"));
		ChargerecordView chargerecordView =  chargerecordService.selectView(ew);
		return R.ok("查询充值记录表成功").put("data", chargerecordView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChargerecordEntity chargerecord = chargerecordService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chargerecord,deSens);
        return R.ok().put("data", chargerecord);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChargerecordEntity chargerecord = chargerecordService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chargerecord,deSens);
        return R.ok().put("data", chargerecord);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChargerecordEntity chargerecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chargerecord);
        if(chargerecord.getUserid()==null){
            chargerecord.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        chargerecordService.save(chargerecord);
        return R.ok().put("data",chargerecord.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChargerecordEntity chargerecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chargerecord);
        chargerecordService.save(chargerecord);
        return R.ok().put("data",chargerecord.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        ChargerecordEntity chargerecord = chargerecordService.getOne(new QueryWrapper<ChargerecordEntity>().eq("", username));
        return R.ok().put("data", chargerecord);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody ChargerecordEntity chargerecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chargerecord);
        //全部更新
        chargerecordService.updateById(chargerecord);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chargerecordService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ChargerecordEntity chargerecord, HttpServletRequest request,String pre){
        QueryWrapper<ChargerecordEntity> ew = new QueryWrapper<ChargerecordEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = chargerecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chargerecord), params), params));
        return R.ok().put("data", page);
    }




    // hasAlipay:$hasAlipay




}
