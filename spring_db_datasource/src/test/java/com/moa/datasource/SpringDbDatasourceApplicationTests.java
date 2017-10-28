package com.moa.datasource;

import com.moa.datasource.model.Student;
import com.moa.datasource.service.TotalService;
import com.moa.datasource.student.StudentRepository;
import com.moa.datasource.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDbDatasourceApplicationTests {


    @Autowired
    private TotalService totalService;

    @Test
    public void test() {
        totalService.save();
    }
}
