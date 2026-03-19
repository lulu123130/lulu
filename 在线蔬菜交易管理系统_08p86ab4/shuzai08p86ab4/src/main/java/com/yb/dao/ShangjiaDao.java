package com.yb.dao;

import com.yb.entity.ShangjiaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ShangjiaVO;
import com.yb.entity.view.ShangjiaView;


/**
 * 商家
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ShangjiaDao extends BaseMapper<ShangjiaEntity> {
	
	List<ShangjiaVO> selectListVO(@Param("ew") QueryWrapper<ShangjiaEntity> wrapper);
	
	ShangjiaVO selectVO(@Param("ew") QueryWrapper<ShangjiaEntity> wrapper);
	
	List<ShangjiaView> selectListView(@Param("ew") QueryWrapper<ShangjiaEntity> wrapper);

	List<ShangjiaView> selectListView(Page page,@Param("ew") QueryWrapper<ShangjiaEntity> wrapper);

	
	ShangjiaView selectView(@Param("ew") QueryWrapper<ShangjiaEntity> wrapper);
	

}
