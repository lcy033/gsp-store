package com.gsp.app.utils;

import java.util.Date;




import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(value = { "password", "roles" })
public class Staff{

    private static final long serialVersionUID = -1179728802398561715L;
    /**
     * 用户名——用于登录
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 员工号
     */
    private String employeeNo;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 固话
     */
    private String phone;
    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 入职时间
     */
    private Date entryTime;
    /**
     * 禁用时间
     */
    private Date disabledTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 离职时间
     */
    private Date resignationTime;
    /**
     * 是否发送过邮件
     */
    private Boolean sendMail;
    /**
     * 是否启用证书
     */
    private Boolean certEnable;
    /**
     * 证书导入密码
     */
    private String sslCertImportPassword;
    /**
     * 该用户的密码糖(加密用)
     */
    private String passwordSugar;

    /**
     * 密码修改时间
     */
    private Date modifyPasswordTime;

    /**
     * 该人对应的职位
     */
    private Long positionId;



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getDisabledTime() {
		return disabledTime;
	}

	public void setDisabledTime(Date disabledTime) {
		this.disabledTime = disabledTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getResignationTime() {
		return resignationTime;
	}

	public void setResignationTime(Date resignationTime) {
		this.resignationTime = resignationTime;
	}

	public Boolean getSendMail() {
		return sendMail;
	}

	public void setSendMail(Boolean sendMail) {
		this.sendMail = sendMail;
	}

	public Boolean getCertEnable() {
		return certEnable;
	}

	public void setCertEnable(Boolean certEnable) {
		this.certEnable = certEnable;
	}

	public String getSslCertImportPassword() {
		return sslCertImportPassword;
	}

	public void setSslCertImportPassword(String sslCertImportPassword) {
		this.sslCertImportPassword = sslCertImportPassword;
	}

	public String getPasswordSugar() {
		return passwordSugar;
	}

	public void setPasswordSugar(String passwordSugar) {
		this.passwordSugar = passwordSugar;
	}

	public Date getModifyPasswordTime() {
		return modifyPasswordTime;
	}

	public void setModifyPasswordTime(Date modifyPasswordTime) {
		this.modifyPasswordTime = modifyPasswordTime;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

    
}