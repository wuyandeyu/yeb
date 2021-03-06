package cn.gao.server.controller;

import cn.gao.server.dto.AdminLoginParam;
import cn.gao.server.pojo.Admin;
import cn.gao.server.pojo.RespBean;
import cn.gao.server.pojo.Role;
import cn.gao.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Description:
 *
 * @Date 2021/3/27 14:21
 * @Created by Administrator
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        List<Role> roles = adminService.getRoles(admin.getId());
        admin.setRoles(roles);
        return admin;
    }


    @ApiOperation(value = "退出登录")
    @RequestMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
}
