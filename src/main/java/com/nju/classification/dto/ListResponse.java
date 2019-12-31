package com.nju.classification.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: 分页
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:12
 */
@Data
@Builder
public class ListResponse {

    protected int totalPages;

    protected int pageIndex;

    protected int pageSize;

    protected Object result;
}
