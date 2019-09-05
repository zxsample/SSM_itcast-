package cn.dksy.service;

import cn.dksy.entity.Role;
import cn.dksy.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;
    /**
     * //查询用户以及用户可以添加的角色
     * @param userid
     * @return
     * @throws Exception
     */
    List<Role> findOtherRoles(String userid);

    /**
     * //给用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds);
}
