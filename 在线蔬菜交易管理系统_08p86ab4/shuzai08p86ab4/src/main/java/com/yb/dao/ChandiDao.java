package com.yb.dao;

import com.yb.entity.ChandiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChandiVO;
import com.yb.entity.view.ChandiView;


/**
 * 产地
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChandiDao extends BaseMapper<ChandiEntity> {
	
	List<ChandiVO> selectListVO(@Param("ew") QueryWrapper<ChandiEntity> wrapper);
	
	ChandiVO selectVO(@Param("ew") QueryWrapper<ChandiEntity> wrapper);
	
	List<ChandiView> selectListView(@Param("ew") QueryWrapper<ChandiEntity> wrapper);

	List<ChandiView> selectListView(Page page,@Param("ew") QueryWrapper<ChandiEntity> wrapper);

	
	ChandiView selectView(@Param("ew") QueryWrapper<ChandiEntity> wrapper);
	

}
