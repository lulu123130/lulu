package com.yb.dao;

import com.yb.entity.ChanpinfenleiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChanpinfenleiVO;
import com.yb.entity.view.ChanpinfenleiView;


/**
 * 产品分类
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChanpinfenleiDao extends BaseMapper<ChanpinfenleiEntity> {
	
	List<ChanpinfenleiVO> selectListVO(@Param("ew") QueryWrapper<ChanpinfenleiEntity> wrapper);
	
	ChanpinfenleiVO selectVO(@Param("ew") QueryWrapper<ChanpinfenleiEntity> wrapper);
	
	List<ChanpinfenleiView> selectListView(@Param("ew") QueryWrapper<ChanpinfenleiEntity> wrapper);

	List<ChanpinfenleiView> selectListView(Page page,@Param("ew") QueryWrapper<ChanpinfenleiEntity> wrapper);

	
	ChanpinfenleiView selectView(@Param("ew") QueryWrapper<ChanpinfenleiEntity> wrapper);
	

}
