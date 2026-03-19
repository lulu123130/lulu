package com.yb.entity.vo;

import com.yb.entity.CartEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 购物车表
 * @author 
 * @email 
 * @date 2026-03-10 12:19:38
 */
public class CartVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用户id
	 */
	
	private Long userid;
		
	/**
	 * 商品id
	 */
	
	private Long goodid;
		
	/**
	 * 名称
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
	 * 商户名称
	 */
	
	private String maijiazhanghao;
				
	
	/**
	 * 设置：用户id
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
				
	
	/**
	 * 设置：商品id
	 */
	 
	public void setGoodid(Long goodid) {
		this.goodid = goodid;
	}
	
	/**
	 * 获取：商品id
	 */
	public Long getGoodid() {
		return goodid;
	}
				
	
	/**
	 * 设置：名称
	 */
	 
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	
	/**
	 * 获取：名称
	 */
	public String getGoodname() {
		return goodname;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * 获取：图片
	 */
	public String getPicture() {
		return picture;
	}
				
	
	/**
	 * 设置：购买数量
	 */
	 
	public void setBuynumber(Integer buynumber) {
		this.buynumber = buynumber;
	}
	
	/**
	 * 获取：购买数量
	 */
	public Integer getBuynumber() {
		return buynumber;
	}
				
	
	/**
	 * 设置：单价
	 */
	 
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 获取：单价
	 */
	public Double getPrice() {
		return price;
	}
				
	
	/**
	 * 设置：商户名称
	 */
	 
	public void setMaijiazhanghao(String maijiazhanghao) {
		this.maijiazhanghao = maijiazhanghao;
	}
	
	/**
	 * 获取：商户名称
	 */
	public String getMaijiazhanghao() {
		return maijiazhanghao;
	}
			
}
