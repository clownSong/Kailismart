package com.kailismart.com.entity;

import java.sql.Date;

/**
 * Created by Administrator on 2016-08-04.
 * 企业（相关单位）对象
 */
public class Company extends BaseEntity {
    private String name;        //单位名称
    private String folder;      //单位目录
    private byte isSupply;      //是否供应商（0:否，1：是）
    private byte isClient;      //是否客户（0:否，1：是）
    private String logDate;       //登记时间
    private String unitNumber;      //单位编号
    private String taxNumber;       //税务号
    private String bankNumber;     //银行账号
    private String  openAccount;        //开户行
    private String relationP;       //联系人
    private String telephoneP;       //座机
    private String emailP;          //财务邮箱
    private String tel;             //财务手机号
    private String address;         //单位地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public byte getIsSupply() {
        return isSupply;
    }

    public void setIsSupply(byte isSupply) {
        this.isSupply = isSupply;
    }

    public byte getIsClient() {
        return isClient;
    }

    public void setIsClient(byte isClient) {
        this.isClient = isClient;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getOpenAccount() {
        return openAccount;
    }

    public void setOpenAccount(String openAccount) {
        this.openAccount = openAccount;
    }

    public String getRelationP() {
        return relationP;
    }

    public void setRelationP(String relationP) {
        this.relationP = relationP;
    }

    public String getTelephoneP() {
        return telephoneP;
    }

    public void setTelephoneP(String telephoneP) {
        this.telephoneP = telephoneP;
    }

    public String getEmailP() {
        return emailP;
    }

    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
