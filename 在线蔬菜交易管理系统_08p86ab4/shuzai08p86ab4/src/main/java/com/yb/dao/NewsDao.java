package com.yb.dao;

import com.yb.entity.NewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.NewsVO;
import com.yb.entity.view.NewsView;


/**
 * 蔬菜资讯
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface NewsDao extends BaseMapper<NewsEntity> {
	
	List<NewsVO> selectListVO(@Param("ew") QueryWrapper<NewsEntity> wrapper);
	
	NewsVO selectVO(@Param("ew") QueryWrapper<NewsEntity> wrapper);
	
	List<NewsView> selectListView(@Param("ew") QueryWrapper<NewsEntity> wrapper);

	List<NewsView> selectListView(Page page,@Param("ew") QueryWrapper<NewsEntity> wrapper);

	
	NewsView selectView(@Param("ew") QueryWrapper<NewsEntity> wrapper);
	

}
