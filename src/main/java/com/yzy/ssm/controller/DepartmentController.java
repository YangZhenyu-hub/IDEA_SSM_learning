package com.yzy.ssm.controller;

import com.yzy.ssm.bean.Department;
import com.yzy.ssm.bean.Msg;
import com.yzy.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理部门请求
 * @ClassName DepartmentController
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-04-14 10:46
 * @Version
 **/
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/depts")
    @ResponseBody
    public Msg getDepts() {
        List<Department> depts = departmentService.getAllDepts();
        return Msg.success().add("dept", depts);
    }
}
