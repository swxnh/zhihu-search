package com.wenxuan.search;

import com.wenxuan.search.service.AssociationalWordServer;
import com.wenxuan.search.service.EsPaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchApplicationTests {

   @Autowired
   private AssociationalWordServer associationalWordServer;

    @Test
    void contextLoads() {
         associationalWordServer.getAssociationalWord("拥塞控制");
    }

}
