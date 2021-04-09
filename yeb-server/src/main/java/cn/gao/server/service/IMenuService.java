package cn.gao.server.service;

import cn.gao.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Administrator
 * @since 2021-03-26
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenusByAdminId();
    List<Menu> getMenusWithRole();
}
