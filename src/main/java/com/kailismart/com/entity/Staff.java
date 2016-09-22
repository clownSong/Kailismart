package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-04.
 * 职员对象
 */
public class Staff extends BaseEntity {
    private String name;        //员工名称  pj00402
    private byte sex;         //性别    pj00403 (1:男，2:女)
    private String coding;      //职员编码
    private String sectionCoding;      //部门编码  pj00417 （varchar(20)）
    private String tel;         //手机号   pj00420(varchar(200))
    private String email;           //邮箱号（pj00441(varchar(100))）
    private String passwd;      //用户密码
    private String roleName;        //角色名称
    private String dutyName;        //职务名称

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getSectionCoding() {
        return sectionCoding;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public void setSectionCoding(String sectionCoding) {
        this.sectionCoding = sectionCoding;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
