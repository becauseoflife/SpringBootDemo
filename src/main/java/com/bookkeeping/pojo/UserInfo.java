package com.bookkeeping.pojo;

import javax.persistence.*;

@Table(name = "user_info")
public class UserInfo {
    @Id
    private String account;

    @Id
    private String id;

    private String password;

    private String name;

    @Column(name = "netName")
    private String netname;

    private String telephone;

    @Column(name = "hope_save")
    private Double hopeSave;

    @Column(name = "week_max_cost")
    private Double weekMaxCost;

    @Column(name = "month_max_cost")
    private Double monthMaxCost;

    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return netName
     */
    public String getNetname() {
        return netname;
    }

    /**
     * @param netname
     */
    public void setNetname(String netname) {
        this.netname = netname;
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return hope_save
     */
    public Double getHopeSave() {
        return hopeSave;
    }

    /**
     * @param hopeSave
     */
    public void setHopeSave(Double hopeSave) {
        this.hopeSave = hopeSave;
    }

    /**
     * @return week_max_cost
     */
    public Double getWeekMaxCost() {
        return weekMaxCost;
    }

    /**
     * @param weekMaxCost
     */
    public void setWeekMaxCost(Double weekMaxCost) {
        this.weekMaxCost = weekMaxCost;
    }

    /**
     * @return month_max_cost
     */
    public Double getMonthMaxCost() {
        return monthMaxCost;
    }

    /**
     * @param monthMaxCost
     */
    public void setMonthMaxCost(Double monthMaxCost) {
        this.monthMaxCost = monthMaxCost;
    }
}