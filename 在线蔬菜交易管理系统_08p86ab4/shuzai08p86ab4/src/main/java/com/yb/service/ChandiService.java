package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChandiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChandiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChandiView;


/**
 * 产地
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChandiService extends IService<ChandiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChandiVO> selectListVO(QueryWrapper<ChandiEntity> wrapper);
   	
   	ChandiVO selectVO(@Param("ew") QueryWrapper<ChandiEntity> wrapper);
   	
   	List<ChandiView> selectListView(QueryWrapper<ChandiEntity> wrapper);
   	
   	ChandiView selectView(@Param("ew") QueryWrapper<ChandiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChandiEntity> wrapper);

   	

}

