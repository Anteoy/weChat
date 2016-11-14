package com.anteoy.common;

public class ZixunMessage {
	
	private String targetOpenid;// 医生的openid
	
	private String zixbt; // 咨询标题
	
	private String zixnr; // 咨询内容
	
	private String zxsj; // 咨询时间
	
	private TemplateType mublx; // 模板类型
	
	private String huifsj; // 回复时间
	
	private String huifnr; // 回复内容
	
	private String huifr; // 回复人
	
	private String zxr;
	
	private String shnr; // 审核内容
	
	private String shjg; // 审核结果
	
	private String zxid;
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 *获得咨询id
	 */
	public String getZxid() {
		return zxid;
	}
	
	/**
	 *设置咨询id
	 */
	public void setZxid(String zxid) {
		this.zxid = zxid;
	}
	/**
	 *获得审核内容
	 */
	public String getShnr() {
		return shnr;
	}

	/**
	 *设置审核内容
	 */
	public void setShnr(String shnr) {
		this.shnr = shnr;
	}

	/**
	 *获得审核结果
	 */
	public String getShjg() {
		return shjg;
	}

	/**
	 *设置审核结果
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	/**
	 *获得接收者的openid
	 */
	public String getTargetOpenid() {
		return targetOpenid;
	}

	/**
	 *设置接收者的openid
	 */
	public void setTargetOpenid(String targetOpenid) {
		this.targetOpenid = targetOpenid;
	}

	/**
	 *获得咨询标题
	 */
	public String getZixbt() {
		return zixbt;
	}
	
	/**
	 *设置咨询标题
	 */
	public void setZixbt(String zixbt) {
		this.zixbt = zixbt;
	}
	
	/**
	 *设置咨询内容
	 */
	public String getZixnr() {
		return zixnr;
	}
	
	/**
	 *获得咨询内容
	 */
	public void setZixnr(String zixnr) {
		this.zixnr = zixnr;
	}

	/**
	 *获得咨询时间
	 */
	public String getZxsj() {
		return zxsj;
	}

	/**
	 *设置咨询时间
	 */
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}

	/**
	 *获得模板消息类型
	 */
	public TemplateType getMublx() {
		return mublx;
	}

	/**
	 *设置模板类型
	 */
	public void setMublx(TemplateType mublx) {
		this.mublx = mublx;
	}

	/**
	 *获得回复时间
	 */
	public String getHuifsj() {
		return huifsj;
	}

	/**
	 *设置回复时间
	 */
	public void setHuifsj(String huifsj) {
		this.huifsj = huifsj;
	}

	/**
	 *获得回复内容
	 */
	public String getHuifnr() {
		return huifnr;
	}

	/**
	 *设置回复内容
	 */
	public void setHuifnr(String huifnr) {
		this.huifnr = huifnr;
	}

	/**
	 *获得回复人
	 */
	public String getHuifr() {
		return huifr;
	}

	/**
	 *设置回复人
	 */
	public void setHuifr(String huifr) {
		this.huifr = huifr;
	}

	/**
	 *获得咨询人
	 */
	public String getZxr() {
		return zxr;
	}

	/**
	 *设置咨询人
	 */
	public void setZxr(String zxr) {
		this.zxr = zxr;
	}
	
}