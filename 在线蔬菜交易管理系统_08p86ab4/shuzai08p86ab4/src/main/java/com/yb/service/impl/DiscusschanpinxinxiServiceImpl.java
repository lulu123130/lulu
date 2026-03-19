package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.DiscusschanpinxinxiDao;
import com.yb.entity.DiscusschanpinxinxiEntity;
import com.yb.service.DiscusschanpinxinxiService;
import com.yb.entity.vo.DiscusschanpinxinxiVO;
import com.yb.entity.view.DiscusschanpinxinxiView;

@Service("discusschanpinxinxiService")
public class DiscusschanpinxinxiServiceImpl extends ServiceImpl<DiscusschanpinxinxiDao, DiscusschanpinxinxiEntity> implements DiscusschanpinxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusschanpinxinxiEntity> page = baseMapper.selectPage(new Query<DiscusschanpinxinxiEntity>(params).getPage(),new QueryWrapper<DiscusschanpinxinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<DiscusschanpinxinxiEntity> wrapper) {
        Page<DiscusschanpinxinxiEntity> page = baseMapper.selectPage(new Query<DiscusschanpinxinxiEntity>(params).getPage(),wrapper);
        Page<DiscusschanpinxinxiView> pageVo = (Page<DiscusschanpinxinxiView>)page.convert(DiscusschanpinxinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<DiscusschanpinxinxiVO> selectListVO(QueryWrapper<DiscusschanpinxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusschanpinxinxiVO selectVO(QueryWrapper<DiscusschanpinxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusschanpinxinxiView> selectListView(QueryWrapper<DiscusschanpinxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusschanpinxinxiView selectView(QueryWrapper<DiscusschanpinxinxiEntity> wrapper) {
        DiscusschanpinxinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new DiscusschanpinxinxiView(entity);
        } else {
            return null;
        }
	}


}
