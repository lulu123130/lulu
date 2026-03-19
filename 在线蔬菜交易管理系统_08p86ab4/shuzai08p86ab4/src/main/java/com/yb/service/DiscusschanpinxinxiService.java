package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.DiscusschanpinxinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.DiscusschanpinxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.DiscusschanpinxinxiView;


/**
 * 产品信息评论
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface DiscusschanpinxinxiService extends IService<DiscusschanpinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusschanpinxinxiVO> selectListVO(QueryWrapper<DiscusschanpinxinxiEntity> wrapper);
   	
   	DiscusschanpinxinxiVO selectVO(@Param("ew") QueryWrapper<DiscusschanpinxinxiEntity> wrapper);
   	
   	List<DiscusschanpinxinxiView> selectListView(QueryWrapper<DiscusschanpinxinxiEntity> wrapper);
   	
   	DiscusschanpinxinxiView selectView(@Param("ew") QueryWrapper<DiscusschanpinxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<DiscusschanpinxinxiEntity> wrapper);

   	

}

