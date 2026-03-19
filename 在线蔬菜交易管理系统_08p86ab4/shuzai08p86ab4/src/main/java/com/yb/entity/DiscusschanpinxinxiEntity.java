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
 * 产品信息评论
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@TableName("discusschanpinxinxi")
@Data
public class DiscusschanpinxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DiscusschanpinxinxiEntity() {
		
	}
	
	public DiscusschanpinxinxiEntity(T t) {
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
	 * 关联表id
	 */
	private Long refid;
	
	/**
	 * 用户id
	 */
	private Long userid;
	
	/**
	 * 头像
	 */
	private String avatarurl;
	
	/**
	 * 用户名
	 */
	private String nickname;
	
	/**
	 * 评论内容
	 */
	private String content;
	
	/**
	 * 评分
	 */
	private Double score;
	
	/**
	 * 回复内容
	 */
	private String reply;
	
	/**
	 * 赞
	 */
	private Integer thumbsupnum;
	
	/**
	 * 踩
	 */
	private Integer crazilynum;
	
	/**
	 * 置顶(1:置顶,0:非置顶)
	 */
	private Integer istop;
	
	/**
	 * 赞用户ids
	 */
	private String tuserids;
	
	/**
	 * 踩用户ids
	 */
	private String cuserids;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
