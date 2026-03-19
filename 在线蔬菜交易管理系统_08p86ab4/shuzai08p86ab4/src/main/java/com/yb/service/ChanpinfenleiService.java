package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChanpinfenleiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChanpinfenleiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChanpinfenleiView;


/**
 * 产品分类
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChanpinfenleiService extends IService<ChanpinfenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChanpinfenleiVO> selectListVO(QueryWrapper<ChanpinfenleiEntity> wrapper);
   	
   	ChanpinfenleiVO selectVO(@Param("ew") QueryWrapper<ChanpinfenleiEntity> wrapper);
   	
   	List<ChanpinfenleiView> selectListView(QueryWrapper<ChanpinfenleiEntity> wrapper);
   	
   	ChanpinfenleiView selectView(@Param("ew") QueryWrapper<ChanpinfenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChanpinfenleiEntity> wrapper);

   	

}

