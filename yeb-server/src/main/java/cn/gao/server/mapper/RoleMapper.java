package cn.gao.server.mapper;

import cn.gao.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2021-03-26
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoles(Integer andminId);
}
