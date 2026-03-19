package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChargerecordDao;
import com.yb.entity.ChargerecordEntity;
import com.yb.service.ChargerecordService;
import com.yb.entity.vo.ChargerecordVO;
import com.yb.entity.view.ChargerecordView;

@Service("chargerecordService")
public class ChargerecordServiceImpl extends ServiceImpl<ChargerecordDao, ChargerecordEntity> implements ChargerecordService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChargerecordEntity> page = baseMapper.selectPage(new Query<ChargerecordEntity>(params).getPage(),new QueryWrapper<ChargerecordEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChargerecordEntity> wrapper) {
        Page<ChargerecordEntity> page = baseMapper.selectPage(new Query<ChargerecordEntity>(params).getPage(),wrapper);
        Page<ChargerecordView> pageVo = (Page<ChargerecordView>)page.convert(ChargerecordView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChargerecordVO> selectListVO(QueryWrapper<ChargerecordEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChargerecordVO selectVO(QueryWrapper<ChargerecordEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChargerecordView> selectListView(QueryWrapper<ChargerecordEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChargerecordView selectView(QueryWrapper<ChargerecordEntity> wrapper) {
        ChargerecordEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChargerecordView(entity);
        } else {
            return null;
        }
	}


}
