package com.yb.dao;

import com.yb.entity.CartEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.CartVO;
import com.yb.entity.view.CartView;


/**
 * 购物车表
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface CartDao extends BaseMapper<CartEntity> {
	
	List<CartVO> selectListVO(@Param("ew") QueryWrapper<CartEntity> wrapper);
	
	CartVO selectVO(@Param("ew") QueryWrapper<CartEntity> wrapper);
	
	List<CartView> selectListView(@Param("ew") QueryWrapper<CartEntity> wrapper);

	List<CartView> selectListView(Page page,@Param("ew") QueryWrapper<CartEntity> wrapper);

	
	CartView selectView(@Param("ew") QueryWrapper<CartEntity> wrapper);
	

}
