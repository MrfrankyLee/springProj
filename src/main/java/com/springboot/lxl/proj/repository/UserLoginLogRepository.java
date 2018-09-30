package main.java.com.springboot.lxl.proj.repository;

import main.java.com.springboot.lxl.proj.entity.UserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
@Repository
public interface UserLoginLogRepository extends JpaRepository<UserLoginLog ,Long> {
}
