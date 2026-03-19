package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.AddressDao;
import com.yb.entity.AddressEntity;
import com.yb.service.AddressService;
import com.yb.entity.vo.AddressVO;
import com.yb.entity.view.AddressView;

@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressDao, AddressEntity> implements AddressService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AddressEntity> page = baseMapper.selectPage(new Query<AddressEntity>(params).getPage(),new QueryWrapper<AddressEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<AddressEntity> wrapper) {
        Page<AddressEntity> page = baseMapper.selectPage(new Query<AddressEntity>(params).getPage(),wrapper);
        Page<AddressView> pageVo = (Page<AddressView>)page.convert(AddressView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<AddressVO> selectListVO(QueryWrapper<AddressEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public AddressVO selectVO(QueryWrapper<AddressEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<AddressView> selectListView(QueryWrapper<AddressEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public AddressView selectView(QueryWrapper<AddressEntity> wrapper) {
        AddressEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new AddressView(entity);
        } else {
            return null;
        }
	}


}
