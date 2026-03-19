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
 * 产品信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@TableName("chanpinxinxi")
@Data
public class ChanpinxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ChanpinxinxiEntity() {
		
	}
	
	public ChanpinxinxiEntity(T t) {
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
	 * 产品名称
	 */
	private String chanpinmingcheng;
	
	/**
	 * 产品分类
	 */
	private String chanpinfenlei;
	
	/**
	 * 图片
	 */
	private String tupian;
	
	/**
	 * 总净含量
	 */
	private String zongjinghanliang;
	
	/**
	 * 贮存条件
	 */
	private String zhucuntiaojian;
	
	/**
	 * 生长季节
	 */
	private String shengzhangjijie;
	
	/**
	 * 生长周期
	 */
	private String shengzhangzhouqi;
	
	/**
	 * 产地
	 */
	private String chandi;
	
	/**
	 * 产品详情
	 */
	private String chanpinxiangqing;
	
	/**
	 * 卖家账号
	 */
	private String maijiazhanghao;
	
	/**
	 * 单限
	 */
	private Integer onelimittimes;
	
	/**
	 * 库存
	 */
	private Integer alllimittimes;
	
	/**
	 * 最近点击时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date clicktime;
	
	/**
	 * 评论数
	 */
	private Integer discussnum;
	
	/**
	 * 评分
	 */
	private Double totalscore;
	
	/**
	 * 价格
	 */
	private Double price;
	
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	private Integer onshelves;
	
	/**
	 * 收藏数
	 */
	private Integer storeupnum;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
