package cn.gao.server.service.impl;

import cn.gao.server.bo.AdminUserDetails;
import cn.gao.server.mapper.MenuMapper;
import cn.gao.server.pojo.Menu;
import cn.gao.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2021-03-26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = ((AdminUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations< String,Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus =(List<Menu>) valueOperations.get("menu_"+adminId);
        if(CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenusByAdminId(adminId);
            valueOperations.set("menu_",menus);
        }

        return menus;
    }

    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }
}
