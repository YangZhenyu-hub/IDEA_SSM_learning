package com.yzy.ssm.bean;

public class Department {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dept.dept_id
     *
     * @mbg.generated Sun Apr 10 11:35:29 CST 2022
     */
    private Integer deptId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dept.dept_name
     *
     * @mbg.generated Sun Apr 10 11:35:29 CST 2022
     */
    private String deptName;

    public Department() {
    }

    public Department(Integer deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dept.dept_id
     *
     * @return the value of t_dept.dept_id
     *
     * @mbg.generated Sun Apr 10 11:35:29 CST 2022
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dept.dept_id
     *
     * @param deptId the value for t_dept.dept_id
     *
     * @mbg.generated Sun Apr 10 11:35:29 CST 2022
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dept.dept_name
     *
     * @return the value of t_dept.dept_name
     *
     * @mbg.generated Sun Apr 10 11:35:29 CST 2022
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dept.dept_name
     *
     * @param deptName the value for t_dept.dept_name
     *
     * @mbg.generated Sun Apr 10 11:35:29 CST 2022
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}