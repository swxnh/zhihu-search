package com.wenxuan.search.service.impl;

import com.wenxuan.search.pojo.EsPaper;
import com.wenxuan.search.repository.EsPaperRepository;
import com.wenxuan.search.service.EsPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

/**
 * @author 文轩
 */
@Service
@Slf4j
public class EsPaperServiceImpl implements EsPaperService {


    private final EsPaperRepository esPaperRepository;


    public EsPaperServiceImpl(EsPaperRepository esPaperRepository) {
        this.esPaperRepository = esPaperRepository;
    }


    /**
     * 搜索正文
     */
    @Override
    @Cacheable(value = "searchContent", key = "#keyword+'&'+#page+'&'+#size")
    public Page<EsPaper> searchContent(String keyword, Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page-1, size, sort);
        Page<EsPaper> byContent = esPaperRepository.findByContent(keyword,  pageable);


        //高亮
        byContent.forEach(esPaper ->
            esPaper.setContent(esPaper.getContent().replace(keyword, "<span style='color:red'>" + keyword + "</span>"))
        );
        return byContent;
    }



    /**
     * 搜索标题
     *
     * @param keyword 关键字
     * @param page    页码
     * @param size    每页大小
     * @return Page<EsPaper>
     */
    @Override
    @Cacheable(value = "searchTitle", key = "#keyword+'&'+#page+'&'+#size")
    public Page<EsPaper> searchTitle(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<EsPaper> byTitle = esPaperRepository.findByTitle(keyword, pageable);

        //高亮
        byTitle.forEach(esPaper ->
                esPaper.setTitle(esPaper.getTitle().replace(keyword, "<span style='color:red'>" + keyword + "</span>"))
        );
        return byTitle;
    }

    /**
     * 搜索摘要
     *
     * @param keyword 关键字
     * @param page    页码
     * @param size    每页大小
     * @return Page<EsPaper>
     */
    @Override
    public Page<EsPaper> searchExcerpt(String keyword, Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, size, sort);
        Page<EsPaper> byTitleAndContent = esPaperRepository.findByExcerptTitleOrExcerpt(keyword, keyword, pageable);

        //高亮
        byTitleAndContent.forEach(esPaper -> {
            esPaper.setTitle(esPaper.getExcerptTitle().replace(keyword, "<span style='color:red'>" + keyword + "</span>"));
            esPaper.setContent(esPaper.getExcerpt().replace(keyword, "<span style='color:red'>" + keyword + "</span>"));
        });
        return byTitleAndContent;
    }

    /**
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @Override
    @Cacheable(value = "searchAll", key = "#keyword+'&'+#page+'&'+#size")
    public Page<EsPaper> searchAll(String keyword, Integer page, Integer size) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, size);
        Page<EsPaper> byTitleAndContentAndName = esPaperRepository.findByTitleOrContentOrName(keyword, keyword, keyword, pageable);

        //高亮
        byTitleAndContentAndName.forEach(esPaper -> {
            String title = esPaper.getTitle();
            if (title != null) {
                esPaper.setTitle(title.replaceAll(keyword, "<span style='color:red'>" + keyword + "</span>"));
            }
            String content = esPaper.getContent();
            if (content != null) {
                esPaper.setContent(content.replaceAll(keyword, "<span style='color:red'>" + keyword + "</span>"));
            }

            String name = esPaper.getName();
            if (name != null) {
                esPaper.setName(name.replaceAll(keyword, "<span style='color:red'>" + keyword + "</span>"));
            }
        });




        return byTitleAndContentAndName;
    }

    /**
     * 查询数据总数
     */
    @Override
    public Long count() {
        return esPaperRepository.count();
    }

    /**
     * 根据id查询数据
     *
     * @param id
     */
    @Override
    public EsPaper selectById(Long id) {
        return esPaperRepository.findById(id).orElse(null);
    }

    /**
     * 删除索引
     */
    @Override
    public void deleteIndex() {
        esPaperRepository.deleteAll();
    }


}
