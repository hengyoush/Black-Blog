package com.hengyh.blog2.service;

import com.hengyh.blog2.Article;

public interface AdminService {

    /**
     * 管理端功能，处理文章修改
     * @param newArticle 从页面传来的文章
     * @return 修改好的文章
     */
    Article processEdit(Article newArticle);

    /**
     * 管理端功能，处理文章新增
     * @param newArticle 从页面传来的文章
     * @return 新增的文章
     */
    Article processAdd(Article newArticle);

    /**
     * 管理端功能，刪除文章
     * @param id
     * @return
     */
    void deleteArticle(Long id);
}
