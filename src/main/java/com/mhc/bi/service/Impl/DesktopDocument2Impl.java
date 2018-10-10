package com.mhc.bi.service.Impl;

import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.mapper.hue.DesktopDocument2Mapper;
import com.mhc.bi.service.DesktopDocument2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author baiyan
 * @date 2018/09/27
 * @description
 */
@Service
public class DesktopDocument2Impl implements DesktopDocument2Service {
    @Autowired
    DesktopDocument2Mapper desktopDocument2Mapper;
    @Override
    public DesktopDocument2 getDesktopDocument2(String name) {
        return desktopDocument2Mapper.selectByName(name);
    }
}
