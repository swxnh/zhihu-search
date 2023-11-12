package com.wenxuan.search.mapper;

import com.wenxuan.search.pojo.AssociationalWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 文轩
* @description 针对表【t_associational_word】的数据库操作Mapper
* @createDate 2023-11-12 02:29:11
* @Entity com.wenxuan.search.pojo.AssociationalWord
*/
@Mapper
public interface AssociationalWordMapper {

    List<String> selectAllAssociationalWord();
}




