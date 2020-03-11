package com.spike.myshop.service.user.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spike.myshop.commons.domain.TbUser;
import com.spike.myshop.commons.mapper.TbUserMapper;
import com.spike.myshop.service.user.api.TbUserService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service(version = "${services.versions.user.v1}")
@Transactional(readOnly = true)
public class TbUserServiceImpl implements TbUserService {

    private TbUserMapper tbUserMapper;

    public TbUserServiceImpl(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }

    @Override
    public List<TbUser> selectAll() {
        return tbUserMapper.selectAll();
    }

    @Override
    public PageInfo<TbUser> page(int start, int length, TbUser tbUser) {
        Example example = new Example(TbUser.class);
        PageHelper.offsetPage(start, length);
        PageInfo<TbUser> pageInfo = new PageInfo<>(tbUserMapper.selectByExample(example));
        return pageInfo;
    }
}
