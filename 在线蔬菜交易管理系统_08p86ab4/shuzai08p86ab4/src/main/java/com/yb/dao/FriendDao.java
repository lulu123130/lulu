package com.yb.dao;

import com.yb.entity.FriendEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.FriendVO;
import com.yb.entity.view.FriendView;


/**
 * 好友表
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface FriendDao extends BaseMapper<FriendEntity> {
	
	List<FriendVO> selectListVO(@Param("ew") QueryWrapper<FriendEntity> wrapper);
	
	FriendVO selectVO(@Param("ew") QueryWrapper<FriendEntity> wrapper);
	
	List<FriendView> selectListView(@Param("ew") QueryWrapper<FriendEntity> wrapper);

	List<FriendView> selectListView(Page page,@Param("ew") QueryWrapper<FriendEntity> wrapper);

    List<FriendView> selectFriendListView(Page page, @Param("params") Map<String, Object> params);
	
	FriendView selectView(@Param("ew") QueryWrapper<FriendEntity> wrapper);
	

}
