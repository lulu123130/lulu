package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChatmessageDao;
import com.yb.entity.ChatmessageEntity;
import com.yb.service.ChatmessageService;
import com.yb.entity.vo.ChatmessageVO;
import com.yb.entity.view.ChatmessageView;

@Service("chatmessageService")
public class ChatmessageServiceImpl extends ServiceImpl<ChatmessageDao, ChatmessageEntity> implements ChatmessageService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChatmessageEntity> page = baseMapper.selectPage(new Query<ChatmessageEntity>(params).getPage(),new QueryWrapper<ChatmessageEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChatmessageEntity> wrapper) {
        Page<ChatmessageEntity> page = baseMapper.selectPage(new Query<ChatmessageEntity>(params).getPage(),wrapper);
        Page<ChatmessageView> pageVo = (Page<ChatmessageView>)page.convert(ChatmessageView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChatmessageVO> selectListVO(QueryWrapper<ChatmessageEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChatmessageVO selectVO(QueryWrapper<ChatmessageEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChatmessageView> selectListView(QueryWrapper<ChatmessageEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChatmessageView selectView(QueryWrapper<ChatmessageEntity> wrapper) {
        ChatmessageEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChatmessageView(entity);
        } else {
            return null;
        }
	}


}
