package com.template.manual.service.impl;

import com.template.manual.pojo.Actor;
import com.template.manual.mapper.ActorMapper;
import com.template.manual.service.IActorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Y
 * @since 2024-12-26
 */
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements IActorService {

}
