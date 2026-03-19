package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.OrdersEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.OrdersVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.OrdersView;


/**
 * 订单
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface OrdersService extends IService<OrdersEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<OrdersVO> selectListVO(QueryWrapper<OrdersEntity> wrapper);
   	
   	OrdersVO selectVO(@Param("ew") QueryWrapper<OrdersEntity> wrapper);
   	
   	List<OrdersView> selectListView(QueryWrapper<OrdersEntity> wrapper);
   	
   	OrdersView selectView(@Param("ew") QueryWrapper<OrdersEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<OrdersEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,QueryWrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,QueryWrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,QueryWrapper<OrdersEntity> wrapper);



}

