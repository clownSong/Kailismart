package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.Staff;
import com.kailismart.com.mapper.StaffMapper;
import com.kailismart.com.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-06.
 * 职员服务对象实现类
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;
    public Staff getStaffById(String Id) {
        return staffMapper.getStaffById(Id);
    }

    public Staff getStaffByCoding(String coding) {
        return staffMapper.getStaffByCoding(coding);
    }

    public List<Staff> getProStaff() {
        return staffMapper.getProStaff();
    }

    public Staff login(String name, String passwd) {
        return staffMapper.login(name,passwd);
    }

    public List<Staff> getStaffs(PageBounds pageBounds) {
        return staffMapper.getStaffs(pageBounds);
    }

    public List<Staff> getStaffByCount(List<Count> counts) {
        return staffMapper.getStaffByCount(counts);
    }

    public List<Staff> seek(String str) {
        return staffMapper.seek(str);
    }

}
