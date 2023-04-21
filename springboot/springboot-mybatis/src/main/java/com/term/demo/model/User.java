package com.term.demo.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.io.Serializable;

@Validated
public class User implements Serializable {
    private int id;
    @NotBlank(message = "名字不能为空")
    private String name;
    @NotBlank(message = "请选择性别")
    private String sexy;
    @NotNull(message = "年龄不能为空")
    @DecimalMin(value = "10", message = "年龄必须大于10")
    @DecimalMax(value = "150", message = "年龄必须小于150")
    private String age;
    @NotBlank(message = "工作不能为空")
    private String job;
    private String hobby;

    private String createTime;
    private String updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sexy='" + sexy + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", hobby='" + hobby + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
