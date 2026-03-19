package com.yb.entity.view;

import com.yb.entity.FriendEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.yb.utils.EncryptUtil;
 

/**
 * 好友表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@TableName("friend")
public class FriendView  extends FriendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public FriendView(){
	}
 
 	public FriendView(FriendEntity friendEntity){
 	try {
			BeanUtils.copyProperties(friendEntity,this);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


    private String content;

    private int notreadnum;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNotreadnum() {
        return notreadnum;
    }

    public void setNotreadnum(int notreadnum) {
        this.notreadnum = notreadnum;
    }
}
