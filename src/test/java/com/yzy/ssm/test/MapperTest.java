package com.yzy.ssm.test;

import com.yzy.ssm.bean.Department;
import com.yzy.ssm.bean.Employee;
import com.yzy.ssm.mapper.DepartmentMapper;
import com.yzy.ssm.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName MapperTest
 * @Description TODO
 * @Author yzy 729141789@qq.com
 * @Date 2022-04-11 11:14
 * @Version
 **/

/**
 * 测试dao层的工作
 * @author lfy
 *推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    /**
     * 测试DepartmentMapper
     * @Description TODO
     * @author yzy 729141789@qq.com
     * @Date 11:24 2022/4/11
     * @param
     * @return void
     */
    @Test
    public void springTest() {
        System.out.println(departmentMapper);
    }

    @Test
    public void insertDepartmentTest() {
        departmentMapper.insertSelective(new Department(null, "开发部"));
        departmentMapper.insertSelective(new Department(null, "测试部"));
    }

    @Test
    public void insertEmployeeTest() {
        employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "123@qq.com", 1));
    }

    @Test
    public void batchInsertEmployeeTest() {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5);
            int j=uid.charAt(0);
            String gender=null;
            if (j % 2 == 0) {
                gender = "M";
            } else {
                gender = "F";
            }
            Math.random();
            mapper.insertSelective(new Employee(null, i + "_" + uid, gender, uid + "@123.com", (int) (Math.random() * 2)+1 ));
        }
    }
}
