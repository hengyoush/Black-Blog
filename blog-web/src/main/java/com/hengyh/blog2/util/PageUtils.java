package com.hengyh.blog2.util;

import com.hengyh.blog2.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PageUtils {

    @Autowired
    private ViewPropHolder holder;

    public  List<Integer> getPageNums(Integer totalPages,// 从一开始
                                                    Integer currentPage// 从1开始
    ) {
        Integer max = holder.getMaxPagenation();
        ArrayList<Integer> list = new ArrayList<>();
        ;
        if (totalPages <= max) {
            for (int i = 1; i <= totalPages; i++)
                list.add(i);
        } else if (currentPage <= max / 2) {
            for (int i = 1; i <= max; i++)
                list.add(i);
        } else if (totalPages - currentPage < max / 2) {
            for (int i = totalPages; i > totalPages - max; i--)
                list.add(i);
            Collections.reverse(list);
        } else {
            for (int i = currentPage - max / 2; i <= currentPage + max / 2; i++)
                list.add(i);
        }
        return list;
    }

    /**
     * 前端分页参数注入逻辑单独抽出
     *
     * @param articles 当前页的文章列表
     * @param model    要修改的模型
     */
    public void pageHelper(Page<Article> articles, Model model) {
        model.addAttribute("articles", articles);
        model.addAttribute("pageNums", getPageNums(articles.getTotalPages(), articles.getNumber() + 1));
    }
}
