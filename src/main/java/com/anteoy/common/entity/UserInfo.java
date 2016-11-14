/**
 * @filename UserInfo.java
 * @package
 * @description 用户
 * @author
 * @date 2012-6-3 上午11:08:14
 * @version v0.1
 */
package com.anteoy.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -1525564808860501218L;

	/** 身份认证ID */
	private String verifyId;

	/** 用户ID */
	private Integer id;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** 用户名称 */
	private String name;

	/** 用户性别 */
	private Integer sex;

	/** 身份证号 */
	private String shenfzh;

	/** 用户状态 */
	private Integer state;

	/** 上次登陆时间 */
	private Date lastlogin;

	/** 登陆的sessionId */
	private String sessionId;

	/** 登录时间 */
	private Date logintime;

	/** 登录IP */
	private String loginip;

	/** 登录过期时间 */
	private Date expired;

	/** 帐号锁定情况 */
	private Integer locked;
	
	/**
	 * @return the 身份认证ID
	 */
	public String getVerifyId() {
		return verifyId;
	}

	/**
	 * @param 身份认证ID the jVerifyId to set
	 */
	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}

	/**
	 * 获取[用户ID]
	 * @return 用户ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置[用户ID]
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取[用户名]
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置[用户名]
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取[密码]
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置[密码]
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取[用户名称]
	 * @return 用户名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置[用户名称]
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取[用户性别]
	 * @return 用户性别
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 设置[用户性别]
	 * @param sex
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 获取[身份证号]
	 * @return 身份证号
	 */
	public String getShenfzh() {
		return shenfzh;
	}

	/**
	 * 设置[身份证号]
	 * @param shenfzh
	 */
	public void setShenfzh(String shenfzh) {
		this.shenfzh = shenfzh;
	}

	/**
	 * 获取[用户状态]
	 * @return 用户状态
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置[用户状态]
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取[上次登陆时间]
	 * @return 上次登陆时间
	 */
	public Date getLastlogin() {
		return lastlogin;
	}

	/**
	 * 设置[上次登陆时间]
	 * @param lastlogin
	 */
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	/**
	 * 获取[登陆的sessionId]
	 * @return 登陆的sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 设置[登陆的sessionId]
	 * @param 登陆的sessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * 获取[登录时间]
	 * @return 登录时间
	 */
	public Date getLogintime() {
		return logintime;
	}

	/**
	 * 设置[登录时间]
	 * @param logintime
	 */
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	/**
	 * 获取[登录IP]
	 * @return 登录IP
	 */
	public String getLoginip() {
		return loginip;
	}

	/**
	 * 设置[登录IP]
	 * @param loginip
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	/**
	 * 获取[登录过期时间]
	 * @return 登录过期时间
	 */
	public Date getExpired() {
		return expired;
	}

	/**
	 * 设置[登录过期时间]
	 * @param expired
	 */
	public void setExpired(Date expired) {
		this.expired = expired;
	}

	/**
	 * 获取[帐号锁定状态]
	 * @return 帐号锁定状态
	 */
	public Integer getLocked() {
		return locked;
	}

	/**
	 * 设置[帐号锁定状态]
	 * @param locked
	 */
	public void setLocked(Integer locked) {
		this.locked = locked;
	}

}
