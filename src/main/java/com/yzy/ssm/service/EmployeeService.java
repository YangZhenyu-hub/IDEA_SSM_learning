package com.yzy.ssm.service;

import com.yzy.ssm.bean.Employee;
import com.yzy.ssm.bean.EmployeeExample;
import com.yzy.ssm.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeService
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-04-11 16:29
 * @Version
 **/
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 20:53 2022/4/14
     * @param
     * @return java.util.List<com.yzy.ssm.bean.Employee>
     */
    public List<Employee> getEmps() {
        return employeeMapper.selectByExampleWithDepartment(null);
    }

    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count==0;
    }

    /**
     * 新增员工
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 16:02 2022/4/15
     * @param employee
     * @return void
     */
    public void saveEmp(Employee employee) {
        // TODO Auto-generated method stub
        employeeMapper.insertSelective(employee);
    }

    /**
     * 修改员工信息
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 16:02 2022/4/15
     * @param employee
     * @return void
     */
    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除员工
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 21:55 2022/4/15
     * @param empId
     * @return void
     */
    public void deleteEmployee(Integer empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }

    /**
     * 批量删除
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 0:16 2022/4/16
     * @param empIdList
     * @return void
     */
    public void deleteBatch(List<Integer> empIdList) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(empIdList);
        employeeMapper.deleteByExample(example);

    }
}
