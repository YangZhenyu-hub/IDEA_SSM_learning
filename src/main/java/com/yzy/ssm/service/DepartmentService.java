package com.yzy.ssm.service;

import com.yzy.ssm.bean.Department;
import com.yzy.ssm.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-04-14 10:47
 * @Version
 **/
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepts() {
        List<Department> depts = departmentMapper.selectByExample(null);
        return depts;
    }

}
