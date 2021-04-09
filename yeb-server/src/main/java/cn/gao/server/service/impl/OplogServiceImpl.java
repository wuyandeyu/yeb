package cn.gao.server.service.impl;

import cn.gao.server.mapper.OplogMapper;
import cn.gao.server.pojo.Oplog;
import cn.gao.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
