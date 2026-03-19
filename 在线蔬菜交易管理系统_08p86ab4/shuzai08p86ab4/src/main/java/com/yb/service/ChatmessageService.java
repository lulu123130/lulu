package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChatmessageEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChatmessageVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChatmessageView;


/**
 * 消息表
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChatmessageService extends IService<ChatmessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChatmessageVO> selectListVO(QueryWrapper<ChatmessageEntity> wrapper);
   	
   	ChatmessageVO selectVO(@Param("ew") QueryWrapper<ChatmessageEntity> wrapper);
   	
   	List<ChatmessageView> selectListView(QueryWrapper<ChatmessageEntity> wrapper);
   	
   	ChatmessageView selectView(@Param("ew") QueryWrapper<ChatmessageEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChatmessageEntity> wrapper);

   	

}

