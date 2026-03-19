package com.yb.dao;

import com.yb.entity.ChargerecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChargerecordVO;
import com.yb.entity.view.ChargerecordView;


/**
 * 充值记录表
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChargerecordDao extends BaseMapper<ChargerecordEntity> {
	
	List<ChargerecordVO> selectListVO(@Param("ew") QueryWrapper<ChargerecordEntity> wrapper);
	
	ChargerecordVO selectVO(@Param("ew") QueryWrapper<ChargerecordEntity> wrapper);
	
	List<ChargerecordView> selectListView(@Param("ew") QueryWrapper<ChargerecordEntity> wrapper);

	List<ChargerecordView> selectListView(Page page,@Param("ew") QueryWrapper<ChargerecordEntity> wrapper);

	
	ChargerecordView selectView(@Param("ew") QueryWrapper<ChargerecordEntity> wrapper);
	

}
