package com.springboot.lxl.proj.repository;

import com.springboot.lxl.proj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description  数据访问接口
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByMobileOruOrUsername(String mobile,String username);

    User getByMobile(String mobile);
}
