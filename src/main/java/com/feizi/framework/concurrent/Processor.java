package com.feizi.framework.concurrent;

import java.util.List;

/**
 * Created by feizi on 2018/2/1.
 */
public interface Processor<T> {
    void process(List<T> list);
}
