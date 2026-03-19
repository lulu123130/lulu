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

import com.yb.entity.FriendEntity;
import com.yb.entity.view.FriendView;

import com.yb.service.FriendService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 好友表
 * 后端接口
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;









     /**
     * 后台列表
     */
    @RequestMapping("/page2")
    @IgnoreAuth
    public R page2(@RequestParam Map<String, Object> params, HttpServletRequest request){
        PageUtils page = friendService.queryFriendPage(params);
        Map<String, String> deSens = new HashMap<>();
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FriendEntity friend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<FriendEntity> ew = new QueryWrapper<FriendEntity>();


        //查询结果
		PageUtils page = friendService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, friend), params), params));
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
    public R list(@RequestParam Map<String, Object> params,FriendEntity friend,
                @RequestParam(required = false) Double typestart,
                @RequestParam(required = false) Double typeend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<FriendEntity> ew = new QueryWrapper<FriendEntity>();
        if(typestart!=null) ew.ge("type", typestart);
        if(typeend!=null) ew.le("type", typeend);

        //查询结果
		PageUtils page = friendService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, friend), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FriendEntity friend){
       	QueryWrapper<FriendEntity> ew = new QueryWrapper<FriendEntity>();
      	ew.allEq(MPUtil.allEQMapPre( friend, "friend"));
        return R.ok().put("data", friendService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FriendEntity friend){
        QueryWrapper< FriendEntity> ew = new QueryWrapper< FriendEntity>();
 		ew.allEq(MPUtil.allEQMapPre( friend, "friend"));
		FriendView friendView =  friendService.selectView(ew);
		return R.ok("查询好友表成功").put("data", friendView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FriendEntity friend = friendService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(friend,deSens);
        return R.ok().put("data", friend);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FriendEntity friend = friendService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(friend,deSens);
        return R.ok().put("data", friend);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FriendEntity friend, HttpServletRequest request){
        //ValidatorUtils.validateEntity(friend);
        friendService.save(friend);
        return R.ok().put("data",friend.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FriendEntity friend, HttpServletRequest request){
        //ValidatorUtils.validateEntity(friend);
        friendService.save(friend);
        return R.ok().put("data",friend.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        FriendEntity friend = friendService.getOne(new QueryWrapper<FriendEntity>().eq("", username));
        return R.ok().put("data", friend);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody FriendEntity friend, HttpServletRequest request){
        //ValidatorUtils.validateEntity(friend);
        //全部更新
        friendService.updateById(friend);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        friendService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,FriendEntity friend, HttpServletRequest request,String pre){
        QueryWrapper<FriendEntity> ew = new QueryWrapper<FriendEntity>();
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

		PageUtils page = friendService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, friend), params), params));
        return R.ok().put("data", page);
    }




    // hasAlipay:$hasAlipay




}
