package cn.com.hxfz.model;

import java.util.Date;

/**
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: cn.com.hxfz.model.taskmanage
 * @作者: qiangxuan
 * @时间: 2018/3/16 16:06
 * @版本: V1.0
 */
public class TimeTask {

    private String id;
    private String name;
    private String group;
    private String expression;
    private String status;
    private String classname;
    private String description;
    private Date startTime;
    private Date endTime;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTimes;
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(Date updateTimes) {
        this.updateTimes = updateTimes;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
