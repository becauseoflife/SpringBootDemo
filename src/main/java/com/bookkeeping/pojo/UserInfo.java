package com.bookkeeping.pojo;

import javax.persistence.*;

@Table(name = "user_info")
public class UserInfo {
    @Id
    private String account;

    private String password;

    private String name;

    @Column(name = "netName")
    private String netname;

    private String telephone;

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
}