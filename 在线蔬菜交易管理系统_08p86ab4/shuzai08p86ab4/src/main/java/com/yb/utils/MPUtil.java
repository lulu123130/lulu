package com.yb.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Mybatis-Plus工具类
 */
public class MPUtil {
    public static final char UNDERLINE = '_';

    /**
     * 将 bean 转换为 Map，忽略@Alias 注解，使用字段名作为 key
     *
     * @param bean 要转换的 bean 对象
     * @return Map 对象
     */
    public static Map<String, Object> beanToMapIgnoreAlias(Object bean) {
        String json = JSON.toJSONString(bean);
        Map<String, Object> result = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
        });
        if (result != null) {
            result.entrySet().removeIf(entry -> entry.getValue() == null);
        }
        return result;
    }

    //mybatis plus allEQ 表达式转换
    public static Map allEQMapPre(Object bean, String pre) {
        Map<String, Object> map = beanToMapIgnoreAlias(bean);
        return camelToUnderlineMap(map, pre);
    }

    //mybatis plus allEQ 表达式转换
    public static Map allEQMap(Object bean) {
        Map<String, Object> map = beanToMapIgnoreAlias(bean);
        return camelToUnderlineMap(map, "");
    }

    public static QueryWrapper allLikePre(QueryWrapper wrapper, Object bean, String pre) {
        Map<String, Object> map = beanToMapIgnoreAlias(bean);
        Map result = camelToUnderlineMap(map, pre);

        return genLike(wrapper, result);
    }

    public static QueryWrapper allLike(QueryWrapper wrapper, Object bean) {
        Map result = beanToMapIgnoreAlias(bean);
        return genLike(wrapper, result);
    }


    public static QueryWrapper genLike(QueryWrapper wrapper, Map param) {
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            //if(i>0) wrapper.and();
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            if (null == entry.getValue()) {
                continue;
            }
            String value = (String) entry.getValue();
            wrapper.like(key, value);
            i++;
        }
        return wrapper;
    }

    public static QueryWrapper likeOrEq(QueryWrapper wrapper, Object bean) {
        Map result = beanToMapIgnoreAlias(bean);
        return genLikeOrEq(wrapper, result);
    }

    public static QueryWrapper genLikeOrEq(QueryWrapper wrapper, Map param) {
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            //if(i>0) wrapper.and();
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            if (null == entry.getValue()) {
                continue;
            }
            if (entry.getValue().toString().contains("%")) {
                wrapper.like(key, entry.getValue().toString().replace("%", ""));
            } else {
                wrapper.eq(key, entry.getValue());
            }
            i++;
        }
        return wrapper;
    }

    public static QueryWrapper allEq(QueryWrapper wrapper, Object bean) {
        Map result = beanToMapIgnoreAlias(bean);
        return genEq(wrapper, result);
    }


    public static QueryWrapper genEq(QueryWrapper wrapper, Map param) {
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            //if(i>0) wrapper.and();
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            if (null == entry.getValue()) {
                continue;
            }
            wrapper.eq(key, entry.getValue());
            i++;
        }
        return wrapper;
    }


    public static QueryWrapper between(QueryWrapper wrapper, Map<String, Object> params) {
        for (String key : params.keySet()) {
            String columnName = "";
            if (key.endsWith("_start")) {
                columnName = key.substring(0, key.indexOf("_start"));
                if (StringUtils.isNotBlank(params.get(key).toString())) {
                    wrapper.ge(columnName, params.get(key));
                }
            }
            if (key.endsWith("_end")) {
                columnName = key.substring(0, key.indexOf("_end"));
                if (StringUtils.isNotBlank(params.get(key).toString())) {
                    wrapper.le(columnName, params.get(key));
                }
            }
        }
        return wrapper;
    }

    public static QueryWrapper sort2(QueryWrapper wrapper, Map<String, Object> params) {
        String order = "";
        if (params.get("order") != null && StringUtils.isNotBlank(params.get("order").toString())) {
            order = params.get("order").toString();
        }
        if (params.get("sort") != null && StringUtils.isNotBlank(params.get("sort").toString())) {
            if (order.equalsIgnoreCase("desc")) {
                wrapper.orderByDesc(Arrays.asList(params.get("sort")));
            } else {
                wrapper.orderByAsc(Arrays.asList(params.get("sort")));
            }
        }
        return wrapper;
    }

    public static QueryWrapper sort(QueryWrapper wrapper, Map<String, Object> params) {
        List<String> orderList = new ArrayList<String>();
        List<String> sortList = new ArrayList<String>();
        if (params.get("order") != null && StringUtils.isNotBlank(params.get("order").toString())) {
            orderList = Arrays.asList(params.get("order").toString().split(","));
        }
        if (params.get("sort") != null && StringUtils.isNotBlank(params.get("sort").toString())) {
            sortList = Arrays.asList(params.get("sort").toString().split(","));
        }
        if (orderList != null && sortList != null && orderList.size() == sortList.size()) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).equalsIgnoreCase("desc")) {
                    wrapper.orderByDesc(Arrays.asList(sortList.get(i)));
                } else {
                    wrapper.orderByAsc(Arrays.asList(sortList.get(i)));
                }
            }
        }
        return wrapper;
    }


    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] ages) {
        System.out.println(camelToUnderline("ABCddfANM"));
    }

    public static Map camelToUnderlineMap(Map param, String pre) {

        Map<String, Object> newMap = new HashMap<String, Object>();
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            String newKey = camelToUnderline(key);
            if (null == entry.getValue()) {
                continue;
            }
            if (pre.endsWith(".")) {
                newMap.put(pre + newKey, entry.getValue());
            } else if (StringUtils.isEmpty(pre)) {
                newMap.put(newKey, entry.getValue());
            } else {

                newMap.put(pre + "." + newKey, entry.getValue());
            }
        }
        return newMap;
    }
}
