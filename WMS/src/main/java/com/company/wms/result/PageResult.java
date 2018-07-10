package com.company.wms.result;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/8.
 */
@Getter
public class PageResult<T> {

    //-----------------分页数据
    // 总页数
    private Integer pageTotalCount;
    // 总条数
    private Long dataTotalCount;
    // 每页显示的最大数据
    private Integer pageSize;
    // 当前页
    private Integer currentPage;
    // 上一页
    private Integer lastPage;
    // 下一页
    private Integer nextPage;

    //-----------------元素数据
    private List<T> dataResults;


    // 返回一个空PageResult
    public static PageResult empty(){
        return new PageResult();
    }



    // 无参构造
    public PageResult(){
        initDefault();
    }

    // 参数构造方法
    public PageResult(Long dataTotalCount,Integer pageSize,Integer currentPage,List<T> dataResults){

        // 1.校验
        if ( pageSize <= 0 || currentPage < 1 ){
            initDefault();
            return;
        }

        // 2.处理
        this.dataTotalCount = dataTotalCount;
        this.pageTotalCount = Math.toIntExact((dataTotalCount % pageSize == 0) ? dataTotalCount / pageSize : dataTotalCount / pageSize + 1);
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.lastPage = (currentPage <= 1) ? 1 : currentPage -1;
        this.nextPage = (currentPage >= this.pageTotalCount) ? this.pageTotalCount : currentPage + 1 ;
        this.dataResults = dataResults;
    }


    // 默认
    public void initDefault(){
        this.dataTotalCount = 0L;
        this.pageTotalCount = 0;
        this.currentPage = 1;
        this.lastPage = 1;
        this.nextPage = 1;
        this.dataResults = new ArrayList<>();
    }



    @Override
    public String toString() {
        return "PageResult{" +
                "pageTotalCount=" + pageTotalCount +
                ", dataTotalCount=" + dataTotalCount +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", lastPage=" + lastPage +
                ", nextPage=" + nextPage +
                ", dataResults=" + dataResults +
                '}';
    }
}
