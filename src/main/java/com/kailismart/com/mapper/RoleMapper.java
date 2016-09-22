package com.kailismart.com.mapper;

import com.kailismart.com.entity.Role;
import com.kailismart.com.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 * 角色mapper
*/
@Repository
public interface RoleMapper {
    /**
     * 根据角色编码获取该角色所有职员
     * @param coding 角色编码
     * @return 职员集合
     */
    List<Staff> getStaffListByRoleCoding(@Param("coding") String coding);

    /**
     * 根据角色编码获取角色对象
     * @param coding
     * @return
     */
    Role getRoleByCoding(String coding);
}
