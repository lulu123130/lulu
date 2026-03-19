package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.OrdersDao;
import com.yb.entity.OrdersEntity;
import com.yb.service.OrdersService;
import com.yb.entity.vo.OrdersVO;
import com.yb.entity.view.OrdersView;

@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, OrdersEntity> implements OrdersService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrdersEntity> page = baseMapper.selectPage(new Query<OrdersEntity>(params).getPage(),new QueryWrapper<OrdersEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<OrdersEntity> wrapper) {
        Page<OrdersEntity> page = baseMapper.selectPage(new Query<OrdersEntity>(params).getPage(),wrapper);
        Page<OrdersView> pageVo = (Page<OrdersView>)page.convert(OrdersView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<OrdersVO> selectListVO(QueryWrapper<OrdersEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public OrdersVO selectVO(QueryWrapper<OrdersEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<OrdersView> selectListView(QueryWrapper<OrdersEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public OrdersView selectView(QueryWrapper<OrdersEntity> wrapper) {
        OrdersEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new OrdersView(entity);
        } else {
            return null;
        }
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, QueryWrapper<OrdersEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, QueryWrapper<OrdersEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, QueryWrapper<OrdersEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
