package com.kailismart.com.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-08-06.
 * 职员服务接口
 */
public interface StaffService {
    /**
     * 根据职员ID获取职员信息
     * @param Id
     * @return
     */
    Staff getStaffById(String Id);
    /**
     * 根据职员代码获取职员对象
     * @param coding 职员代码
     * @return
     */
    Staff getStaffByCoding(String coding);

    /**
     * 获取采购职员集合
     * @return
     */
    List<Staff> getProStaff();

    /**
     * 职员登录
     * @param name 用户名
     * @param passwd    用户密码，在pm2中为【住址：pj00436】
     * @return 职员对象
     */
    Staff login(String name,String passwd);

    /**
     * 获取职员集合
     * @param pageBounds 分页对象
     * @return 职员集合
     */
    List<Staff> getStaffs(PageBounds pageBounds);

    /**
     * 根据指定的职员id集合获取职员集合对象
     * @param counts 总计集合
     * @return 职员对象
     */
    List<Staff> getStaffByCount(List<Count> counts);


    /**
     * 根据字符串搜索职员集合
     * @param str 字符串
     * @return 职员集合
     */
    List<Staff> seek(String str);
}
