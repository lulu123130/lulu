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

import com.yb.entity.ChanpinxinxiEntity;
import com.yb.entity.view.ChanpinxinxiView;

import com.yb.service.ChanpinxinxiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;
import com.yb.service.StoreupService;
import com.yb.entity.StoreupEntity;

/**
 * 产品信息
 * 后端接口
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@RestController
@RequestMapping("/chanpinxinxi")
public class ChanpinxinxiController {
    @Autowired
    private ChanpinxinxiService chanpinxinxiService;

    @Autowired
    private StoreupService storeupService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChanpinxinxiEntity chanpinxinxi,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shangjia")) {
			chanpinxinxi.setMaijiazhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<ChanpinxinxiEntity> ew = new QueryWrapper<ChanpinxinxiEntity>();
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);


        //查询结果
		PageUtils page = chanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpinxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ChanpinxinxiEntity chanpinxinxi,
                @RequestParam(required = false) Double onelimittimesstart,
                @RequestParam(required = false) Double onelimittimesend,
                @RequestParam(required = false) Double alllimittimesstart,
                @RequestParam(required = false) Double alllimittimesend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimeend,
                @RequestParam(required = false) Double discussnumstart,
                @RequestParam(required = false) Double discussnumend,
                @RequestParam(required = false) Double totalscorestart,
                @RequestParam(required = false) Double totalscoreend,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
                @RequestParam(required = false) Double onshelvesstart,
                @RequestParam(required = false) Double onshelvesend,
                @RequestParam(required = false) Double storeupnumstart,
                @RequestParam(required = false) Double storeupnumend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChanpinxinxiEntity> ew = new QueryWrapper<ChanpinxinxiEntity>();
        if(onelimittimesstart!=null) ew.ge("onelimittimes", onelimittimesstart);
        if(onelimittimesend!=null) ew.le("onelimittimes", onelimittimesend);
        if(alllimittimesstart!=null) ew.ge("alllimittimes", alllimittimesstart);
        if(alllimittimesend!=null) ew.le("alllimittimes", alllimittimesend);
        if(clicktimestart!=null) ew.ge("clicktime", clicktimestart);
        if(clicktimeend!=null) ew.le("clicktime", clicktimeend);
        if(discussnumstart!=null) ew.ge("discussnum", discussnumstart);
        if(discussnumend!=null) ew.le("discussnum", discussnumend);
        if(totalscorestart!=null) ew.ge("totalscore", totalscorestart);
        if(totalscoreend!=null) ew.le("totalscore", totalscoreend);
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);
        if(onshelvesstart!=null) ew.ge("onshelves", onshelvesstart);
        if(onshelvesend!=null) ew.le("onshelves", onshelvesend);
        if(storeupnumstart!=null) ew.ge("storeupnum", storeupnumstart);
        if(storeupnumend!=null) ew.le("storeupnum", storeupnumend);

        //查询结果
		PageUtils page = chanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpinxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChanpinxinxiEntity chanpinxinxi){
       	QueryWrapper<ChanpinxinxiEntity> ew = new QueryWrapper<ChanpinxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chanpinxinxi, "chanpinxinxi"));
        return R.ok().put("data", chanpinxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChanpinxinxiEntity chanpinxinxi){
        QueryWrapper< ChanpinxinxiEntity> ew = new QueryWrapper< ChanpinxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chanpinxinxi, "chanpinxinxi"));
		ChanpinxinxiView chanpinxinxiView =  chanpinxinxiService.selectView(ew);
		return R.ok("查询产品信息成功").put("data", chanpinxinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChanpinxinxiEntity chanpinxinxi = chanpinxinxiService.getById(id);
		chanpinxinxi.setClicktime(new Date());
		chanpinxinxiService.updateById(chanpinxinxi);
        chanpinxinxi = chanpinxinxiService.selectView(new QueryWrapper<ChanpinxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chanpinxinxi,deSens);
        return R.ok().put("data", chanpinxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChanpinxinxiEntity chanpinxinxi = chanpinxinxiService.getById(id);
		chanpinxinxi.setClicktime(new Date());
		chanpinxinxiService.updateById(chanpinxinxi);
        chanpinxinxi = chanpinxinxiService.selectView(new QueryWrapper<ChanpinxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chanpinxinxi,deSens);
        return R.ok().put("data", chanpinxinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChanpinxinxiEntity chanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpinxinxi);
        chanpinxinxiService.save(chanpinxinxi);
        return R.ok().put("data",chanpinxinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChanpinxinxiEntity chanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpinxinxi);
        chanpinxinxiService.save(chanpinxinxi);
        return R.ok().put("data",chanpinxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChanpinxinxiEntity chanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpinxinxi);
        //全部更新
        chanpinxinxiService.updateById(chanpinxinxi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chanpinxinxiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ChanpinxinxiEntity chanpinxinxi, HttpServletRequest request,String pre){
        QueryWrapper<ChanpinxinxiEntity> ew = new QueryWrapper<ChanpinxinxiEntity>();
        ew.eq("onshelves","1");
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

		PageUtils page = chanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpinxinxi), params), params));
        return R.ok().put("data", page);
    }




    // hasAlipay:否




}
