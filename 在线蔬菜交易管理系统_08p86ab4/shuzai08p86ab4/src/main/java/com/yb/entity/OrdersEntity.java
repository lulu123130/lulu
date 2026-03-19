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
 * 订单
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
@TableName("orders")
@Data
public class OrdersEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public OrdersEntity() {
		
	}
	
	public OrdersEntity(T t) {
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
	 * 订单编号
	 */
	private String orderid;
	
	/**
	 * 商品id
	 */
	private Long goodid;
	
	/**
	 * 商品名称
	 */
	private String goodname;
	
	/**
	 * 图片
	 */
	private String picture;
	
	/**
	 * 购买数量
	 */
	private Integer buynumber;
	
	/**
	 * 单价
	 */
	private Double price;
	
	/**
	 * 总价
	 */
	private Double total;
	
	/**
	 * 支付类型
	 */
	private Integer type;
	
	/**
	 * 订单状态
	 */
	private String status;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 电话
	 */
	private String tel;
	
	/**
	 * 收货人
	 */
	private String consignee;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 用户id
	 */
	private Long userid;
	
	/**
	 * 商品表名
	 */
	private String tablename;
	
	/**
	 * 物流
	 */
	private String logistics;
	
	/**
	 * 商户名称
	 */
	private String maijiazhanghao;
	
	/**
	 * 是否审核
	 */
	private String sfsh;
	
	/**
	 * 审核回复
	 */
	private String shhf;
	
	/**
	 * 用户角色
	 */
	private String role;
	
	/**
	 * 退货原因
	 */
	private String returnreason;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
