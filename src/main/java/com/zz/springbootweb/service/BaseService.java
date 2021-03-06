package com.zz.springbootweb.service;

import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.zz.springbootweb.repository.BaseDao;
import com.zz.springbootweb.utils.DynamicSpecifications;
import com.zz.springbootweb.utils.SearchFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.*;

public abstract class BaseService<M, ID extends Serializable> {

    @Autowired
    protected BaseDao<M, ID> baseDao;

    public M get(ID id) {
        return baseDao.findOne(id);
    }

    public List<M> get(Collection<ID> ids) {
        Iterable<M> elements = baseDao.findAll(ids);
        List<M> models = new ArrayList<>();
        Iterators.addAll(models, elements.iterator());
        return models;
    }

    public Page<M> findPage(Map<String, SearchFilter> filterMaps, Pageable pageable) {
        Specification<M> specification = DynamicSpecifications.bySearchFilter(filterMaps.values());
        return baseDao.findAll(specification, pageable);
    }

    public M findOne(String key, Object value) {
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put(key, new SearchFilter(key, SearchFilter.Operator.EQ, value));
        Specification<M> specification = DynamicSpecifications.bySearchFilter(filters.values());
        return baseDao.findOne(specification);
    }

    public Page<M> findPage(Pageable pageable) {
        return baseDao.findAll(pageable);
    }

    public List<M> findAll() {
        return baseDao.findAll();
    }

    public Long getTotalCount() {
        return baseDao.count();
    }

    public boolean isExist(ID id) {
        return baseDao.exists(id);
    }

    public M save(M entity) {
        return baseDao.save(entity);
    }

    public Collection<M> save(Collection<M> entites) {
        return baseDao.save(entites);
    }

    public M update(ID id, M entity) {
        M existM = get(id);
        copyNonNullProperties(entity, existM);
        baseDao.save(existM);
        return existM;
    }

    public void delete(M entity) {
        baseDao.delete(entity);
    }

    public void delete(ID id) {
        baseDao.delete(id);
    }

    public void delete(Collection<M> entitys) {
        baseDao.delete(entitys);
    }


    private void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
