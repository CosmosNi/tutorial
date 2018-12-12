package com.baomidou.ant.sys.service.impl;

import com.baomidou.ant.sys.entity.Config;
import com.baomidou.ant.sys.mapper.ConfigMapper;
import com.baomidou.ant.sys.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-11
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
