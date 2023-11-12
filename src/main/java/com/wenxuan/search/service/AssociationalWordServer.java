package com.wenxuan.search.service;

import com.wenxuan.search.pojo.AssociationalWord;
import com.wenxuan.search.vo.AssociationalWordVO;

import java.util.List;

/**
 * 关联词服务
 * @Author: 文轩
 */
public interface AssociationalWordServer {

    /**
     * 获取关联词
     */
    List<AssociationalWordVO> getAssociationalWord(String keyword);

    int getRelativeLength(String word);

    List<String> split(String word);
}
