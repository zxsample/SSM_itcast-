package cn.dksy.dao;

import cn.dksy.entity.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {


    @Select("select * from member where id=#{id}")
     Member findById(String id) throws Exception;
}
