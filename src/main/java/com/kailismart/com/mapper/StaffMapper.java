package com.kailismart.com.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-06.
 * 职员mapper
 */
@Repository
public interface StaffMapper {
    /**
     * 根据职员ID获取职员对象
     * @param ID
     */
    Staff getStaffById(String ID);

    /**
     * 根据职员代码获取职员对象
     * @param coding 职员代码
     * @return 职员对象
     */
    Staff getStaffByCoding(String coding);

    /**
     * 获取采购人员集合
     * @return 采购人员集合
     */
    List<Staff> getProStaff();

    /**
     * 职员登录
     * @param name 用户名
     * @param passwd    用户密码，在pm2中为【住址：pj00436】
     * @return 职员对象
     */
    Staff login(@Param("name") String name,@Param("passwd") String passwd);

    /**
     * 根据字符串检索职员集合
     * @param str 字符串
     * @return 职员集合
     */
    List<Staff> seek(String str);


    /**
     * 根据id集合获取职员集合
     * @param counts id集合
     * @return 职员集合
     */
    List<Staff> getStaffByCount(@Param("list") List<Count> counts);

    /**
     * 根据分页对象获取职员
     * @param pageBounds 分页对象
     * @return 职员集合
     */
    List<Staff> getStaffs(PageBounds pageBounds);
}
