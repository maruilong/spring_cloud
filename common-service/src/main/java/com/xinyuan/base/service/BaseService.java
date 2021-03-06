package com.xinyuan.base.service;

import com.xinyuan.base.common.service.Order;
import com.xinyuan.base.common.service.PageBean;
import com.xinyuan.base.common.service.ParamCondition;
import com.xinyuan.base.common.service.SelectParam;
import com.xinyuan.base.common.util.EntityUtils;
import com.xinyuan.base.common.util.ReflectionUtils;
import com.xinyuan.base.common.util.ResultUtil;
import com.xinyuan.base.common.web.Conditions;
import com.xinyuan.base.common.web.Message;
import com.xinyuan.base.common.web.PageBody;
import com.xinyuan.base.mapper.BaseJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 基础Service层
 *
 * @author
 * @since 2018-03-06
 */
@Slf4j
public abstract class BaseService<J extends BaseJpaRepository<T, ID>, T, ID extends Serializable> {

    @Autowired
    protected J bizRepository;

    @Autowired
    protected EntityManager entityManager;

    /**
     * 业务新增方法(初始化和校验)
     *
     * @author 2018-03-06 14:00
     */
    @Transactional(rollbackFor = Exception.class)
    public T save(T entity) {
        String fieldName = "id";

        T jpaResult = bizRepository.saveAndFlush(entity);
        //清空一级缓存
        entityManager.clear();

        T result = null;

        if (ReflectionUtils.hasField(jpaResult, fieldName)) {

            result = bizRepository.getOne((ID) ReflectionUtils.getFieldValue(jpaResult, fieldName));
        }
        return result;
    }

    /**
     * 业务删除方法(初始化和校验)
     *
     * @author 2018-03-06 14:01
     */
    public void remove(ID id) {
        T entity = bizRepository.getOne(id);
        if (entity != null) {
            if (ReflectionUtils.hasField(entity, "deleted")) {
                ReflectionUtils.invokeSetter(entity, "deleted", 1);
            }
            bizRepository.save(entity);
        }
    }


    /**
     * 业务更新方法(初始化和校验)
     *
     * @author 2018-03-06 14:59
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(T entity) {
        T result = null;
        if (ReflectionUtils.hasField(entity, "id")) {
            ID id = (ID) ReflectionUtils.getFieldValue(entity, "id");
            result = bizRepository.getOne(id);
        }
        EntityUtils.copyPropertiesIgnoreNull(entity, result);
        bizRepository.saveAndFlush(result);
    }

    /**
     * 查询单个方法
     *
     * @author 2018-03-06 14:59
     */
    public T get(ID id) {
        return bizRepository.getOne(id);
    }

    /**
     * 业务条件查询方法(带分页参数)
     *
     * @author 2018-03-08 9:17
     */
    public Page findByCondition(Integer pageNum, Integer pageSize, Sort sort, List<SelectParam> selectParams) {
        int page = 1;
        int limit = 10;
        if (pageNum != null) {
            page = pageNum;
        }
        if (pageSize != null) {
            limit = pageSize;
        }
        Specification querySpecifi = getSpecification(selectParams, false);
        PageBean pageBean = new PageBean(page, limit,sort);
        return bizRepository.findAll(querySpecifi, pageBean);
    }

    /**
     * 业务条件查询方法(带分页参数)
     *
     * @author 2018-03-08 9:17
     */
    public Page findByCondition(Integer pageNum, Integer pageSize, List<SelectParam> selectParams) {
        int page = 1;
        int limit = 10;
        if (pageNum != null) {
            page = pageNum;
        }
        if (pageSize != null) {
            limit = pageSize;
        }

        Specification querySpecifi = getSpecification(selectParams, false);
        PageBean pageBean = new PageBean(page, limit);
        return bizRepository.findAll(querySpecifi, pageBean);
    }

    /**
     * 不带分页条件查询
     *
     * @author 2018-03-13 16:03
     */
    public List<T> findByCondition(List<SelectParam> selectParams) {
        return bizRepository.findAll(getSpecification(selectParams, false));
    }

    /**
     * 不带分页条件查询
     *
     * @author 2018-03-13 16:03
     */
    public T getByCondition(List<SelectParam> selectParams) {
        List<T> list = bizRepository.findAll(getSpecification(selectParams, false));

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 非业务表的条件查询(不带deleted字段的条件查询)
     *
     * @author 2018-03-21 18:59
     */
    public List<T> findByConditionAndDelete(List<SelectParam> selectParams) {
        return bizRepository.findAll(getSpecification(selectParams, true));
    }

    /**
     * 封装Specification对象
     *
     * @author 2018-03-13 16:04
     */
    private Specification getSpecification(List<SelectParam> selectParams, boolean isDelete) {
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (selectParams != null) {
                for (SelectParam s : selectParams) {
                    switch (s.getCondition()) {
                        case DB_EQUAL:
                            predicates.add(criteriaBuilder.equal(root.get(s.getParamKey()),
                                    s.getParamValue()));
                            break;
                        case DB_GREATER_THAN:
                            predicates.add(criteriaBuilder.greaterThan(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case DB_LESS_THAN:
                            predicates.add(criteriaBuilder.lessThan(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case DB_LIKE:
                            predicates.add(criteriaBuilder.like(root.get(s.getParamKey()),
                                    "%" + s.getParamValue() + "%"));
                            break;
                        case DB_GREATER_THAN_EQUAL:
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case DB_LESS_THAN_EQUAL:
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case DB_NOT_EQUAL:
                            predicates.add(criteriaBuilder.notEqual(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case DB_IN:
                            String key = s.getParamKey();
                            CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(key));
                            List<Object> list = (List<Object>) s.getParamValue();
                            for (Object id : list) {
                                in.value(id);
                            }
                            predicates.add(in);
                            break;
                        default:
                            break;
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    /**
     * 通用条件查询
     *
     * @param list
     * @return
     */
    public List<SelectParam> getSelectParamList(List<Conditions> list) {
        List<SelectParam> selectParamList = new ArrayList<>();
        if (list != null) {
            for (Conditions mapBean : list) {
                ParamCondition paramCondition = null;
                String condition = mapBean.getCondition();
                switch (condition) {
                    case "DB_GREATER_THAN":
                        paramCondition = ParamCondition.DB_GREATER_THAN;
                        break;
                    case "DB_LESS_THAN":
                        paramCondition = ParamCondition.DB_LESS_THAN;
                        break;
                    case "DB_LIKE":
                        paramCondition = ParamCondition.DB_LIKE;
                        break;
                    case "DB_GREATER_THAN_EQUAL":
                        paramCondition = ParamCondition.DB_GREATER_THAN_EQUAL;
                        break;
                    case "DB_LESS_THAN_EQUAL":
                        paramCondition = ParamCondition.DB_LESS_THAN_EQUAL;
                        break;
                    case "DB_IN":
                        paramCondition = ParamCondition.DB_IN;
                        break;
                    case "DB_NOT_EQUAL":
                        paramCondition = ParamCondition.DB_NOT_EQUAL;
                        break;
                    default:
                        paramCondition = ParamCondition.DB_EQUAL;
                        break;
                }

                if (!ObjectUtils.isEmpty(mapBean.getValue())) {
                    SelectParam selectParam = new SelectParam(mapBean.getKey(), mapBean.getValue(), paramCondition);
                    selectParamList.add(selectParam);
                }

            }
        }
        return selectParamList;
    }


    public Page query(PageBody pageBody) {
        Page page;
        Sort sort = sort(pageBody.getOrders());

        if (pageBody.getConditions() != null) {
            Conditions conditions = new Conditions("deleted", "0", "DB_EQUAL");
            pageBody.getConditions().add(conditions);
        }

        if (pageBody.getPageBean() != null) {
            page = findByCondition(pageBody.getPageBean().getPageNumber(), pageBody.getPageBean().getPageSize(), sort, getSelectParamList(pageBody.getConditions()));
        } else {
            page = findByCondition(1, Integer.MAX_VALUE, sort, getSelectParamList(pageBody.getConditions()));
        }
        return page;
    }

    public Sort sort(List<Order> orders) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        for (Order order : orders) {
            Sort innerSort = null;
            if (order != null) {
                if (("DB_DESC").equals(order.getDirection())) {
                    sort = new Sort(Sort.Direction.DESC, order.getProperties());
                } else {
                    sort = new Sort(Sort.Direction.ASC, order.getProperties());
                }
            }
            sort.and(innerSort);
        }
        return sort;
    }

    /**
     * 台帐专用
     * @param pageBody
     * @return
     */

    public Page accountQuery(PageBody pageBody) {
        Page page;
        Sort sort = accountSort(pageBody.getOrders());

        /*if (pageBody.getConditions() != null) {
            Conditions conditions = new Conditions("deleted", "0", "DB_EQUAL");
            pageBody.getConditions().add(conditions);
        }*/

        if (pageBody.getPageBean() != null) {
            page = findByCondition(pageBody.getPageBean().getPageNumber(), pageBody.getPageBean().getPageSize(), sort, getSelectParamList(pageBody.getConditions()));
        } else {
            page = findByCondition(1, Integer.MAX_VALUE, sort, getSelectParamList(pageBody.getConditions()));
        }
        return page;
    }
    /**
     * 台帐专用
     * @param orders
     * @return
     */
    public Sort accountSort(List<Order> orders) {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        for (Order order : orders) {
            Sort innerSort = null;
            if (order != null) {
                if (("DB_DESC").equals(order.getDirection())) {
                    sort = new Sort(Sort.Direction.DESC, order.getProperties());
                } else {
                    sort = new Sort(Sort.Direction.ASC, order.getProperties());
                }
            }
            sort.and(innerSort);
        }
        return sort;
    }







    public Message upload(MultipartFile file) throws Exception {
        String url;
        String saveDirectoryPath;

        String osName = System.getProperties().getProperty("os.name");
        if (osName.equals("Linux")) {
            saveDirectoryPath = "/DATA/WEB/www/upload";
            url = "/upload";
        } else {
            saveDirectoryPath = "d:/images\\";
            url = "d:/images";
        }

        File saveDirectory = new File(saveDirectoryPath);

        if (!saveDirectory.isDirectory() && !saveDirectory.exists()) {
            saveDirectory.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        StringBuilder name;
        String fileName = file.getOriginalFilename();

        if (StringUtils.isEmpty(file)) {
            return ResultUtil.error(2001, "图片格式不对");
        }

        if (file.isEmpty()) {
            return ResultUtil.error(1007, "文件为空");
        }

        String suffix = org.apache.commons.lang.StringUtils.substringAfterLast(fileName, ".");

        FileOutputStream out = null;

        //if (suffix.equals("png") || suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("bmp") || suffix.equals("psd")||suffix.equals("mp4")||suffix.equals("ogg")||suffix.equals("webm")) {
        name = new StringBuilder("/" + uuid + ".");
        name.append(suffix);
        log.info(name + "");
        out = new FileOutputStream(saveDirectoryPath + name.toString());
        url = url + name.toString();
        /*} else {
            name = new StringBuilder("/" + fileName);
            out = new FileOutputStream(saveDirectoryPath + name.toString());
            url = url + name.toString();
        }*/
        out.write(file.getBytes());
        out.flush();
        out.close();

        return ResultUtil.success(url);
    }
}
