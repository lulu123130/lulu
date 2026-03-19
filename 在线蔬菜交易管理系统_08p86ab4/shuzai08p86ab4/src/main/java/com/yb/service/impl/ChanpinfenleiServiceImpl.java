package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChanpinfenleiDao;
import com.yb.entity.ChanpinfenleiEntity;
import com.yb.service.ChanpinfenleiService;
import com.yb.entity.vo.ChanpinfenleiVO;
import com.yb.entity.view.ChanpinfenleiView;

@Service("chanpinfenleiService")
public class ChanpinfenleiServiceImpl extends ServiceImpl<ChanpinfenleiDao, ChanpinfenleiEntity> implements ChanpinfenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChanpinfenleiEntity> page = baseMapper.selectPage(new Query<ChanpinfenleiEntity>(params).getPage(),new QueryWrapper<ChanpinfenleiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChanpinfenleiEntity> wrapper) {
        Page<ChanpinfenleiEntity> page = baseMapper.selectPage(new Query<ChanpinfenleiEntity>(params).getPage(),wrapper);
        Page<ChanpinfenleiView> pageVo = (Page<ChanpinfenleiView>)page.convert(ChanpinfenleiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChanpinfenleiVO> selectListVO(QueryWrapper<ChanpinfenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChanpinfenleiVO selectVO(QueryWrapper<ChanpinfenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChanpinfenleiView> selectListView(QueryWrapper<ChanpinfenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChanpinfenleiView selectView(QueryWrapper<ChanpinfenleiEntity> wrapper) {
        ChanpinfenleiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChanpinfenleiView(entity);
        } else {
            return null;
        }
	}


}
