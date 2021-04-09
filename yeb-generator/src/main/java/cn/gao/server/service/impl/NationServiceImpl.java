package cn.gao.server.service.impl;

import cn.gao.server.pojo.Nation;
import cn.gao.server.mapper.NationMapper;
import cn.gao.server.service.INationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2021-03-26
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
