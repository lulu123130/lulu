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
import com.yb.service.TokenService;
import com.yb.entity.TokenEntity;
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

import com.yb.entity.ShangjiaEntity;
import com.yb.entity.view.ShangjiaView;

import com.yb.service.ShangjiaService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 商家
 * 后端接口
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@RestController
@RequestMapping("/shangjia")
public class ShangjiaController {
    @Autowired
    private ShangjiaService shangjiaService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        ShangjiaEntity u = shangjiaService.getOne(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", username));
        if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 如果用户是待审核状态，返回请联系管理员审核提示
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"shangjia",  "商家" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ShangjiaEntity shangjia){
    	//ValidatorUtils.validateEntity(shangjia);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	ShangjiaEntity u = shangjiaService.getOne(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", shangjia.getMaijiazhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同卖家账号，否则返回错误信息
        if(shangjiaService.count(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", shangjia.getMaijiazhanghao()))>0) {
            return R.error("卖家账号已存在");
        }
		Long uId = new Date().getTime();
		shangjia.setId(uId);
        //保存用户
        shangjiaService.save(shangjia);
        return R.ok();
    }



	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ShangjiaEntity u = shangjiaService.getById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        ShangjiaEntity u = shangjiaService.getOne(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        shangjiaService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,ShangjiaEntity shangjia){
        QueryWrapper<ShangjiaEntity> ew = new QueryWrapper<ShangjiaEntity>();
        QueryWrapper<ShangjiaEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangjia), params), params);
        List<Map> list = shangjiaService.list(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getMaijiazhanghao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShangjiaEntity shangjia,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ShangjiaEntity> ew = new QueryWrapper<ShangjiaEntity>();


        //查询结果
		PageUtils page = shangjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangjia), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ShangjiaEntity shangjia,
                @RequestParam(required = false) Double moneystart,
                @RequestParam(required = false) Double moneyend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ShangjiaEntity> ew = new QueryWrapper<ShangjiaEntity>();
        if(moneystart!=null) ew.ge("money", moneystart);
        if(moneyend!=null) ew.le("money", moneyend);

        //查询结果
		PageUtils page = shangjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangjia), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShangjiaEntity shangjia){
       	QueryWrapper<ShangjiaEntity> ew = new QueryWrapper<ShangjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shangjia, "shangjia"));
        return R.ok().put("data", shangjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShangjiaEntity shangjia){
        QueryWrapper< ShangjiaEntity> ew = new QueryWrapper< ShangjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shangjia, "shangjia"));
		ShangjiaView shangjiaView =  shangjiaService.selectView(ew);
		return R.ok("查询商家成功").put("data", shangjiaView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShangjiaEntity shangjia = shangjiaService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shangjia,deSens);
        return R.ok().put("data", shangjia);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShangjiaEntity shangjia = shangjiaService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shangjia,deSens);
        return R.ok().put("data", shangjia);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(shangjiaService.count(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", shangjia.getMaijiazhanghao()))>0) {
            return R.error("卖家账号已存在");
        }
        //ValidatorUtils.validateEntity(shangjia);
        //验证账号唯一性，否则返回错误信息
        ShangjiaEntity u = shangjiaService.getOne(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", shangjia.getMaijiazhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	shangjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		shangjia.setId(new Date().getTime());
        shangjiaService.save(shangjia);
        return R.ok().put("data",shangjia.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(shangjiaService.count(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", shangjia.getMaijiazhanghao()))>0) {
            return R.error("卖家账号已存在");
        }
        //ValidatorUtils.validateEntity(shangjia);
        //验证账号唯一性，否则返回错误信息
        ShangjiaEntity u = shangjiaService.getOne(new QueryWrapper<ShangjiaEntity>().eq("maijiazhanghao", shangjia.getMaijiazhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	shangjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		shangjia.setId(new Date().getTime());
        shangjiaService.save(shangjia);
        return R.ok().put("data",shangjia.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shangjia);
        //验证字段唯一性，否则返回错误信息
        if(shangjiaService.count(new QueryWrapper<ShangjiaEntity>().ne("id", shangjia.getId()).eq("maijiazhanghao", shangjia.getMaijiazhanghao()))>0) {
            return R.error("卖家账号已存在");
        }
        //全部更新
        shangjiaService.updateById(shangjia);
        if(null!=shangjia.getMaijiazhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(shangjia.getMaijiazhanghao());
            tokenService.update(tokenEntity, new UpdateWrapper<TokenEntity>().eq("userid", shangjia.getId()));
        }
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ShangjiaEntity> list = new ArrayList<ShangjiaEntity>();
        for(Long id : ids) {
            ShangjiaEntity shangjia = shangjiaService.getById(id);
            shangjia.setSfsh(sfsh);
            shangjia.setShhf(shhf);
            list.add(shangjia);
        }
        shangjiaService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shangjiaService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
