package com.yb.dao;

import com.yb.entity.AddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.AddressVO;
import com.yb.entity.view.AddressView;


/**
 * 地址
 * 
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface AddressDao extends BaseMapper<AddressEntity> {
	
	List<AddressVO> selectListVO(@Param("ew") QueryWrapper<AddressEntity> wrapper);
	
	AddressVO selectVO(@Param("ew") QueryWrapper<AddressEntity> wrapper);
	
	List<AddressView> selectListView(@Param("ew") QueryWrapper<AddressEntity> wrapper);

	List<AddressView> selectListView(Page page,@Param("ew") QueryWrapper<AddressEntity> wrapper);

	
	AddressView selectView(@Param("ew") QueryWrapper<AddressEntity> wrapper);
	

}
