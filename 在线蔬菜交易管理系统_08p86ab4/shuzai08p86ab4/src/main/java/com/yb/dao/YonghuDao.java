package com.yb.dao;

import com.yb.entity.YonghuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.YonghuVO;
import com.yb.entity.view.YonghuView;


/**
 * 用户
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface YonghuDao extends BaseMapper<YonghuEntity> {
	
	List<YonghuVO> selectListVO(@Param("ew") QueryWrapper<YonghuEntity> wrapper);
	
	YonghuVO selectVO(@Param("ew") QueryWrapper<YonghuEntity> wrapper);
	
	List<YonghuView> selectListView(@Param("ew") QueryWrapper<YonghuEntity> wrapper);

	List<YonghuView> selectListView(Page page,@Param("ew") QueryWrapper<YonghuEntity> wrapper);

	
	YonghuView selectView(@Param("ew") QueryWrapper<YonghuEntity> wrapper);
	

}
