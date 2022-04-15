package com.yzy.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzy.ssm.bean.Employee;
import com.yzy.ssm.bean.Msg;
import com.yzy.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.*;

/**
 * @ClassName EmployeeController
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-04-11 16:23
 * @Version
 **/
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    /**
     * 查询员工数据（分页查询）
     *
     * @param pn
     * @param model
     * @return java.lang.String
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 16:27 2022/4/11
     */
    // @RequestMapping(value = "/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getEmps();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "emps";
    }


    @RequestMapping(value = "/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getEmps();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }


    @RequestMapping(value = "/checkUser")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName") String empName) {
        //先判断用户名是否是合法的表达式;
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!empName.matches(regx)) {
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "用户名不可用");
        }
    }

    /**
     * 员工保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            System.out.println(employee);
            employeeService.updateEmployee(employee);
            return Msg.success();
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@RequestParam("empIdListString") String empIdListString) {
        System.out.println(empIdListString);
        String[] empIdSplit = empIdListString.split(",");
        List<Integer> empIdList = new ArrayList<>();
        for (String empId : empIdSplit) {
            empIdList.add(Integer.parseInt(empId));
        }
        if (empIdList.size() == 1) {
            employeeService.deleteEmployee(empIdList.get(0));
        } else {
            employeeService.deleteBatch(empIdList);
        }
        return Msg.success();
    }

}
