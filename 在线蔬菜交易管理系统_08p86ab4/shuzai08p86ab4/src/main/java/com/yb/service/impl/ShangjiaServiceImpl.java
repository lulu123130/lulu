package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ShangjiaDao;
import com.yb.entity.ShangjiaEntity;
import com.yb.service.ShangjiaService;
import com.yb.entity.vo.ShangjiaVO;
import com.yb.entity.view.ShangjiaView;
import com.yb.service.TokenService;
import com.yb.entity.TokenEntity;

@Service("shangjiaService")
public class ShangjiaServiceImpl extends ServiceImpl<ShangjiaDao, ShangjiaEntity> implements ShangjiaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangjiaEntity> page = baseMapper.selectPage(new Query<ShangjiaEntity>(params).getPage(),new QueryWrapper<ShangjiaEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ShangjiaEntity> wrapper) {
        Page<ShangjiaEntity> page = baseMapper.selectPage(new Query<ShangjiaEntity>(params).getPage(),wrapper);
        Page<ShangjiaView> pageVo = (Page<ShangjiaView>)page.convert(ShangjiaView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ShangjiaVO> selectListVO(QueryWrapper<ShangjiaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShangjiaVO selectVO(QueryWrapper<ShangjiaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShangjiaView> selectListView(QueryWrapper<ShangjiaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShangjiaView selectView(QueryWrapper<ShangjiaEntity> wrapper) {
        ShangjiaEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ShangjiaView(entity);
        } else {
            return null;
        }
	}


}
