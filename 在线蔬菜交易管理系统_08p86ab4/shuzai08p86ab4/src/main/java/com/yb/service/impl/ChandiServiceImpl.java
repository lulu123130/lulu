package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChandiDao;
import com.yb.entity.ChandiEntity;
import com.yb.service.ChandiService;
import com.yb.entity.vo.ChandiVO;
import com.yb.entity.view.ChandiView;

@Service("chandiService")
public class ChandiServiceImpl extends ServiceImpl<ChandiDao, ChandiEntity> implements ChandiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChandiEntity> page = baseMapper.selectPage(new Query<ChandiEntity>(params).getPage(),new QueryWrapper<ChandiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChandiEntity> wrapper) {
        Page<ChandiEntity> page = baseMapper.selectPage(new Query<ChandiEntity>(params).getPage(),wrapper);
        Page<ChandiView> pageVo = (Page<ChandiView>)page.convert(ChandiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChandiVO> selectListVO(QueryWrapper<ChandiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChandiVO selectVO(QueryWrapper<ChandiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChandiView> selectListView(QueryWrapper<ChandiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChandiView selectView(QueryWrapper<ChandiEntity> wrapper) {
        ChandiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChandiView(entity);
        } else {
            return null;
        }
	}


}
