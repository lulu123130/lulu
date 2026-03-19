package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChanpinxinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChanpinxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChanpinxinxiView;


/**
 * 产品信息
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChanpinxinxiService extends IService<ChanpinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChanpinxinxiVO> selectListVO(QueryWrapper<ChanpinxinxiEntity> wrapper);
   	
   	ChanpinxinxiVO selectVO(@Param("ew") QueryWrapper<ChanpinxinxiEntity> wrapper);
   	
   	List<ChanpinxinxiView> selectListView(QueryWrapper<ChanpinxinxiEntity> wrapper);
   	
   	ChanpinxinxiView selectView(@Param("ew") QueryWrapper<ChanpinxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChanpinxinxiEntity> wrapper);

   	

}

