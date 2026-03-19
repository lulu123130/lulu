package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.FriendDao;
import com.yb.entity.FriendEntity;
import com.yb.service.FriendService;
import com.yb.entity.vo.FriendVO;
import com.yb.entity.view.FriendView;

@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendDao, FriendEntity> implements FriendService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FriendEntity> page = baseMapper.selectPage(new Query<FriendEntity>(params).getPage(),new QueryWrapper<FriendEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<FriendEntity> wrapper) {
        Page<FriendEntity> page = baseMapper.selectPage(new Query<FriendEntity>(params).getPage(),wrapper);
        Page<FriendView> pageVo = (Page<FriendView>)page.convert(FriendView::new);
        return new PageUtils(pageVo);
 	}

    @Override
    public PageUtils queryFriendPage(Map<String, Object> params) {
        Page<FriendView> page =new Query<FriendView>(params).getPage();
        page.setRecords(baseMapper.selectFriendListView(page, params));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
    
    @Override
	public List<FriendVO> selectListVO(QueryWrapper<FriendEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public FriendVO selectVO(QueryWrapper<FriendEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<FriendView> selectListView(QueryWrapper<FriendEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public FriendView selectView(QueryWrapper<FriendEntity> wrapper) {
        FriendEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new FriendView(entity);
        } else {
            return null;
        }
	}


}
