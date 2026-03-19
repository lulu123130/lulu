package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.YonghuEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.YonghuVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.YonghuView;


/**
 * 用户
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface YonghuService extends IService<YonghuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YonghuVO> selectListVO(QueryWrapper<YonghuEntity> wrapper);
   	
   	YonghuVO selectVO(@Param("ew") QueryWrapper<YonghuEntity> wrapper);
   	
   	List<YonghuView> selectListView(QueryWrapper<YonghuEntity> wrapper);
   	
   	YonghuView selectView(@Param("ew") QueryWrapper<YonghuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<YonghuEntity> wrapper);

   	

}

