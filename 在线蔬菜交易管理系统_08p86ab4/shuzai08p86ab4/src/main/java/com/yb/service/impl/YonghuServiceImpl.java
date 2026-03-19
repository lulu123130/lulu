package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.YonghuDao;
import com.yb.entity.YonghuEntity;
import com.yb.service.YonghuService;
import com.yb.entity.vo.YonghuVO;
import com.yb.entity.view.YonghuView;
import com.yb.service.TokenService;
import com.yb.entity.TokenEntity;

@Service("yonghuService")
public class YonghuServiceImpl extends ServiceImpl<YonghuDao, YonghuEntity> implements YonghuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YonghuEntity> page = baseMapper.selectPage(new Query<YonghuEntity>(params).getPage(),new QueryWrapper<YonghuEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<YonghuEntity> wrapper) {
        Page<YonghuEntity> page = baseMapper.selectPage(new Query<YonghuEntity>(params).getPage(),wrapper);
        Page<YonghuView> pageVo = (Page<YonghuView>)page.convert(YonghuView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<YonghuVO> selectListVO(QueryWrapper<YonghuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YonghuVO selectVO(QueryWrapper<YonghuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YonghuView> selectListView(QueryWrapper<YonghuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YonghuView selectView(QueryWrapper<YonghuEntity> wrapper) {
        YonghuEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new YonghuView(entity);
        } else {
            return null;
        }
	}


}
