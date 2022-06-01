package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.News;
import com.yxh.springboot.mapper.NewsMapper;
import com.yxh.springboot.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-20
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
