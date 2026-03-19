package com.yb.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.*;
import cn.hutool.core.annotation.Alias;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 消息表
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@TableName("chatmessage")
@Data
public class ChatmessageEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ChatmessageEntity() {
		
	}
	
	public ChatmessageEntity(T t) {
		try {
			BeanUtils.copyProperties(t,this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 好友用户ID
	 */
	private Long fid;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 格式(1:文字，2:图片)
	 */
	private Integer format;
	
	/**
	 * 消息已读(0:未读，1:已读)
	 */
	private Integer isread;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
