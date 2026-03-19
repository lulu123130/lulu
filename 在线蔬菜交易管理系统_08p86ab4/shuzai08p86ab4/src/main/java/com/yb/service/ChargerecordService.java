package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChargerecordEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChargerecordVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChargerecordView;


/**
 * 充值记录表
 *
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public interface ChargerecordService extends IService<ChargerecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChargerecordVO> selectListVO(QueryWrapper<ChargerecordEntity> wrapper);
   	
   	ChargerecordVO selectVO(@Param("ew") QueryWrapper<ChargerecordEntity> wrapper);
   	
   	List<ChargerecordView> selectListView(QueryWrapper<ChargerecordEntity> wrapper);
   	
   	ChargerecordView selectView(@Param("ew") QueryWrapper<ChargerecordEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChargerecordEntity> wrapper);

   	

}

