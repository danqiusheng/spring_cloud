package com.moa.datasource.secondary.dao;

import com.moa.datasource.secondary.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/17.
 * @author  moa
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

}

