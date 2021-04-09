package cn.gao.server.mapper;

import cn.gao.server.pojo.Admin;
import cn.gao.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2021-03-26
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    List<Menu> getMenusByAdminId(Integer id);
}
