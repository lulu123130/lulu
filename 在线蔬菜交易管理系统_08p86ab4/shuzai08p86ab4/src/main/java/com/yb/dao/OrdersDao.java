package com.yb.dao;

import com.yb.entity.OrdersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.OrdersVO;
import com.yb.entity.view.OrdersView;


/**
 * 订单
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface OrdersDao extends BaseMapper<OrdersEntity> {
	
	List<OrdersVO> selectListVO(@Param("ew") QueryWrapper<OrdersEntity> wrapper);
	
	OrdersVO selectVO(@Param("ew") QueryWrapper<OrdersEntity> wrapper);
	
	List<OrdersView> selectListView(@Param("ew") QueryWrapper<OrdersEntity> wrapper);

	List<OrdersView> selectListView(Page page,@Param("ew") QueryWrapper<OrdersEntity> wrapper);

	
	OrdersView selectView(@Param("ew") QueryWrapper<OrdersEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<OrdersEntity> wrapper);



}
