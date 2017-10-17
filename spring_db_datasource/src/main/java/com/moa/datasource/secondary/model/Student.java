package com.moa.datasource.secondary.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/10/17.
 * @author  moa
 */

@Entity
@Table(name="t_student")
public class Student {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false)
    private Integer age;

    public Student() {
    }

    public Student(String studentName, Integer age) {
        this.studentName = studentName;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public Student setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }
}
