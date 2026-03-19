package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.AddressEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.AddressVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.AddressView;


/**
 * 地址
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface AddressService extends IService<AddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<AddressVO> selectListVO(QueryWrapper<AddressEntity> wrapper);
   	
   	AddressVO selectVO(@Param("ew") QueryWrapper<AddressEntity> wrapper);
   	
   	List<AddressView> selectListView(QueryWrapper<AddressEntity> wrapper);
   	
   	AddressView selectView(@Param("ew") QueryWrapper<AddressEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<AddressEntity> wrapper);

   	

}

