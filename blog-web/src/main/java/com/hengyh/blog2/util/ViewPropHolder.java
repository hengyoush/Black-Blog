package com.hengyh.blog2.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "view")
@Data
public class ViewPropHolder {

    private Integer maxPagenation;
    private Integer perPageSize;
    private String articlesSortBy;
}
