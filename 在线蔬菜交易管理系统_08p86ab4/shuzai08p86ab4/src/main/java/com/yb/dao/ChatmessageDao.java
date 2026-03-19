package com.yb.dao;

import com.yb.entity.ChatmessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChatmessageVO;
import com.yb.entity.view.ChatmessageView;


/**
 * 消息表
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChatmessageDao extends BaseMapper<ChatmessageEntity> {
	
	List<ChatmessageVO> selectListVO(@Param("ew") QueryWrapper<ChatmessageEntity> wrapper);
	
	ChatmessageVO selectVO(@Param("ew") QueryWrapper<ChatmessageEntity> wrapper);
	
	List<ChatmessageView> selectListView(@Param("ew") QueryWrapper<ChatmessageEntity> wrapper);

	List<ChatmessageView> selectListView(Page page,@Param("ew") QueryWrapper<ChatmessageEntity> wrapper);

	
	ChatmessageView selectView(@Param("ew") QueryWrapper<ChatmessageEntity> wrapper);
	

}
