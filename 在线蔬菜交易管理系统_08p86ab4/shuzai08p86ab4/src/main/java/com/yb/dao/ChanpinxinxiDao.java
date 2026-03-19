package com.yb.dao;

import com.yb.entity.ChanpinxinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChanpinxinxiVO;
import com.yb.entity.view.ChanpinxinxiView;


/**
 * 产品信息
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChanpinxinxiDao extends BaseMapper<ChanpinxinxiEntity> {
	
	List<ChanpinxinxiVO> selectListVO(@Param("ew") QueryWrapper<ChanpinxinxiEntity> wrapper);
	
	ChanpinxinxiVO selectVO(@Param("ew") QueryWrapper<ChanpinxinxiEntity> wrapper);
	
	List<ChanpinxinxiView> selectListView(@Param("ew") QueryWrapper<ChanpinxinxiEntity> wrapper);

	List<ChanpinxinxiView> selectListView(Page page,@Param("ew") QueryWrapper<ChanpinxinxiEntity> wrapper);

	
	ChanpinxinxiView selectView(@Param("ew") QueryWrapper<ChanpinxinxiEntity> wrapper);
	

}
