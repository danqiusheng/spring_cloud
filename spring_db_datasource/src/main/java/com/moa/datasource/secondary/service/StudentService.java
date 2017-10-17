package com.moa.datasource.secondary.service;

import com.moa.datasource.secondary.dao.StudentRepository;
import com.moa.datasource.secondary.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/17.
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public  void save(Student student){
        studentRepository.save(student);
    }
}
