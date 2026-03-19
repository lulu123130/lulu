package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ShangjiaEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ShangjiaVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ShangjiaView;


/**
 * 商家
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ShangjiaService extends IService<ShangjiaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShangjiaVO> selectListVO(QueryWrapper<ShangjiaEntity> wrapper);
   	
   	ShangjiaVO selectVO(@Param("ew") QueryWrapper<ShangjiaEntity> wrapper);
   	
   	List<ShangjiaView> selectListView(QueryWrapper<ShangjiaEntity> wrapper);
   	
   	ShangjiaView selectView(@Param("ew") QueryWrapper<ShangjiaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ShangjiaEntity> wrapper);

   	

}

