package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.CartEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.CartVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.CartView;


/**
 * 购物车表
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface CartService extends IService<CartEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CartVO> selectListVO(QueryWrapper<CartEntity> wrapper);
   	
   	CartVO selectVO(@Param("ew") QueryWrapper<CartEntity> wrapper);
   	
   	List<CartView> selectListView(QueryWrapper<CartEntity> wrapper);
   	
   	CartView selectView(@Param("ew") QueryWrapper<CartEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<CartEntity> wrapper);

   	

}

