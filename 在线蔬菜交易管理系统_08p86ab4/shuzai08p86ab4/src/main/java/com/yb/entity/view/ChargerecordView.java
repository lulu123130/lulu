package com.yb.entity.view;

import com.yb.entity.ChargerecordEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.yb.utils.EncryptUtil;
 

/**
 * 充值记录表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@TableName("chargerecord")
public class ChargerecordView  extends ChargerecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChargerecordView(){
	}
 
 	public ChargerecordView(ChargerecordEntity chargerecordEntity){
 	try {
			BeanUtils.copyProperties(chargerecordEntity,this);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
