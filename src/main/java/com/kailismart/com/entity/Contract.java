package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-08.
 * 采购合同对象
 */
public class Contract extends BaseEntity {
    private String name = "";        //合同名称  02
    private Company partyA;    //甲方对象
    private Company partyB;     //乙方对象
    private Double price;       //合同总价
    private String currency;    //币种
    private Staff staff;        //合同拟定人
    private String serialNumber;    //合同编号
    private Company oCompany;       //运营单位
    private ContractType type;      //合同类型
    private City city;              //收货地址

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getPartyA() {
        return partyA;
    }

    public void setPartyA(Company partyA) {
        this.partyA = partyA;
    }

    public Company getPartyB() {
        return partyB;
    }

    public void setPartyB(Company partyB) {
        this.partyB = partyB;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Company getoCompany() {
        return oCompany;
    }

    public void setoCompany(Company oCompany) {
        this.oCompany = oCompany;
    }
}
