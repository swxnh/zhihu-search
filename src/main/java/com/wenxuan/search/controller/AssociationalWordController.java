package com.wenxuan.search.controller;

import com.wenxuan.search.service.AssociationalWordServer;
import com.wenxuan.search.utils.ResponseResult;
import com.wenxuan.search.vo.AssociationalWordVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 关联词控制器
 * @author 文轩
 */
@RestController
@RequestMapping("/associationalWord")
public class AssociationalWordController {

    private final AssociationalWordServer associationalWordServer;

    public AssociationalWordController(AssociationalWordServer associationalWordServer) {
        this.associationalWordServer = associationalWordServer;
    }

    /**
     * 获取关联词
     */
    @RequestMapping("/getAssociationalWord")
    public ResponseResult<List<AssociationalWordVO>> getAssociationalWord(@RequestParam String keyword) {
        return ResponseResult.ok(associationalWordServer.getAssociationalWord(keyword));

    }
}
