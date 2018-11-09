package com.mhc.bi.service;

import com.mhc.bi.domain.theadvisor.ShellContent;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description
 */
public interface ShellContentService {

        public int insertShellContent(ShellContent content);
        public List<ShellContent> selectShellContent();
        public int updateShellContent(ShellContent content);
        public int deleteShellContent(ShellContent content);
        public  ShellContent selectByName(String shellName);

}
