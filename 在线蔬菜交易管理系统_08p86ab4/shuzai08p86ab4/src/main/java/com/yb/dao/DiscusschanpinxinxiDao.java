package com.yb.dao;

import com.yb.entity.DiscusschanpinxinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.DiscusschanpinxinxiVO;
import com.yb.entity.view.DiscusschanpinxinxiView;


/**
 * 产品信息评论
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface DiscusschanpinxinxiDao extends BaseMapper<DiscusschanpinxinxiEntity> {
	
	List<DiscusschanpinxinxiVO> selectListVO(@Param("ew") QueryWrapper<DiscusschanpinxinxiEntity> wrapper);
	
	DiscusschanpinxinxiVO selectVO(@Param("ew") QueryWrapper<DiscusschanpinxinxiEntity> wrapper);
	
	List<DiscusschanpinxinxiView> selectListView(@Param("ew") QueryWrapper<DiscusschanpinxinxiEntity> wrapper);

	List<DiscusschanpinxinxiView> selectListView(Page page,@Param("ew") QueryWrapper<DiscusschanpinxinxiEntity> wrapper);

	
	DiscusschanpinxinxiView selectView(@Param("ew") QueryWrapper<DiscusschanpinxinxiEntity> wrapper);
	

}
