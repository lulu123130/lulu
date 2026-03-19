package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.FriendEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.FriendVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.FriendView;


/**
 * 好友表
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface FriendService extends IService<FriendEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FriendVO> selectListVO(QueryWrapper<FriendEntity> wrapper);
   	
   	FriendVO selectVO(@Param("ew") QueryWrapper<FriendEntity> wrapper);
   	
   	List<FriendView> selectListView(QueryWrapper<FriendEntity> wrapper);
   	
   	FriendView selectView(@Param("ew") QueryWrapper<FriendEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<FriendEntity> wrapper);

    PageUtils queryFriendPage(Map<String, Object> params);
   	

}

