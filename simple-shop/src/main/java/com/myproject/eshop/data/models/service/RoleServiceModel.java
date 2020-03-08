package com.myproject.eshop.data.models.service;

public class RoleServiceModel extends BaseServiceModel implements Comparable<RoleServiceModel> {

    private String authority;

    public RoleServiceModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority.substring(5);
    }

    @Override
    public int compareTo(RoleServiceModel role) {
        return (this.getAuthority().compareTo(role.getAuthority()));
    }
}
