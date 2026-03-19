package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChanpinxinxiDao;
import com.yb.entity.ChanpinxinxiEntity;
import com.yb.service.ChanpinxinxiService;
import com.yb.entity.vo.ChanpinxinxiVO;
import com.yb.entity.view.ChanpinxinxiView;

@Service("chanpinxinxiService")
public class ChanpinxinxiServiceImpl extends ServiceImpl<ChanpinxinxiDao, ChanpinxinxiEntity> implements ChanpinxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChanpinxinxiEntity> page = baseMapper.selectPage(new Query<ChanpinxinxiEntity>(params).getPage(),new QueryWrapper<ChanpinxinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChanpinxinxiEntity> wrapper) {
        Page<ChanpinxinxiEntity> page = baseMapper.selectPage(new Query<ChanpinxinxiEntity>(params).getPage(),wrapper);
        Page<ChanpinxinxiView> pageVo = (Page<ChanpinxinxiView>)page.convert(ChanpinxinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChanpinxinxiVO> selectListVO(QueryWrapper<ChanpinxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChanpinxinxiVO selectVO(QueryWrapper<ChanpinxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChanpinxinxiView> selectListView(QueryWrapper<ChanpinxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChanpinxinxiView selectView(QueryWrapper<ChanpinxinxiEntity> wrapper) {
        ChanpinxinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChanpinxinxiView(entity);
        } else {
            return null;
        }
	}


}
