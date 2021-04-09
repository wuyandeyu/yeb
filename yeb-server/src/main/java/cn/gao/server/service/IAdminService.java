package cn.gao.server.service;

import cn.gao.server.pojo.Admin;
import cn.gao.server.pojo.Menu;
import cn.gao.server.pojo.RespBean;
import cn.gao.server.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Administrator
 * @since 2021-03-26
 */
public interface IAdminService extends IService<Admin> {

    RespBean login(String username, String password, String code, HttpServletRequest request);

    Admin getAdminByUserName(String username);

    List<Role> getRoles(Integer andminId );

}
