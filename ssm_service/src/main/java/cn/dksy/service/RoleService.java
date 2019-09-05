package cn.dksy.service;

import cn.dksy.entity.Permission;
import cn.dksy.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws Exception;

    void save(Role role);

    Role findById(String roleId);

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);

    void deleteRoleById(String roleId);
}
