package com.kailismart.com.service;

import com.kailismart.com.entity.Staff;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 * 角色服务
 */
public interface RoleService {
    /**
     * 根据角色编码获取人员集合
     * @param coding 角色编码
     * @return 职员集合
     */
    List<Staff> getStaffByRoleCoding(String coding);
}
