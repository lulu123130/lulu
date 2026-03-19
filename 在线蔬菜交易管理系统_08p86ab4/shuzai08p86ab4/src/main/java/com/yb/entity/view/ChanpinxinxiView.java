package com.yb.entity.view;

import com.yb.entity.ChanpinxinxiEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.yb.utils.EncryptUtil;
 

/**
 * 产品信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@TableName("chanpinxinxi")
public class ChanpinxinxiView  extends ChanpinxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChanpinxinxiView(){
	}
 
 	public ChanpinxinxiView(ChanpinxinxiEntity chanpinxinxiEntity){
 	try {
			BeanUtils.copyProperties(chanpinxinxiEntity,this);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
