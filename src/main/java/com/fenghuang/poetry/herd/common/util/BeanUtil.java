package com.fenghuang.poetry.herd.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

public final class BeanUtil extends BeanUtils {

    private BeanUtil () {
    }

    public static void copy(Object source, Object target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }
}
