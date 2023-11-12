package com.wenxuan.search.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 文轩
 */
@Data
public class AssociationalWordVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 联想词
     */
    private String associationalWord;

    /**
     * 分数
     */
    private Double score;
}
