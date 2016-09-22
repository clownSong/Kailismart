package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Staff;
import com.kailismart.com.mapper.RoleMapper;
import com.kailismart.com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    public List<Staff> getStaffByRoleCoding(String coding) {
        return roleMapper.getStaffListByRoleCoding(coding);
    }
}
