package com.gsp.app.dao;

import java.io.Serializable;

/**
 * 通用dao接口
 *
 * @param <T>
 * @author xiaobowen
 */
public interface BaseDao<T> extends Serializable {
    /**
     * 查询记录
     *
     * @param id
     * @return
     * @author xiaobowen
     */
    public T query(Long id);

    public void create(T obj);
}
