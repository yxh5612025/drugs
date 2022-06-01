package com.yxh.springboot.service.impl;

import com.yxh.springboot.entity.In;
import com.yxh.springboot.entity.Ware;
import com.yxh.springboot.mapper.InMapper;
import com.yxh.springboot.mapper.OutMapper;
import com.yxh.springboot.mapper.WareMapper;
import com.yxh.springboot.service.IInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁希宏
 * @since 2022-03-21
 */
@Service
public class InServiceImpl extends ServiceImpl<InMapper, In> implements IInService {

    @Resource
    private WareMapper wareMapper;
    @Resource
    private OutMapper outMapper;
    @Resource
    private InMapper inMapper;
//    @Override
//    public void updateWare(Long id) throws ServiceException {
//        Out out = outMapper.selectById(id);
//        if (out==null){
//            throw new ServiceException("600","库存不存在");
//        }
//        In inStore = new In();
//        Integer amount = inStore.getAmount();//入库数
//
//
//    }




    @Override
    public boolean create(In in, Ware ware) {
        int insert = inMapper.insert(in);
            if (ware!=null){
                wareMapper.addWare(in.getAmount(),in.getName());
            }else {
                wareMapper.insertDrug(in.getName(),in.getAmount(),in.getBusiness(),in.getImg(),in.getDescription());
            }

        return false;
    }


}
