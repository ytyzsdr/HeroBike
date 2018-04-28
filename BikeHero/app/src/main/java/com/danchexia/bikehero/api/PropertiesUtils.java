package com.danchexia.bikehero.api;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjinyi on 2017/3/24.
 */

public class PropertiesUtils {

    public static <V, T> List<V> mappingApiToListBean(List<T> apiModelList, Class<V> vClass)
    {
        List<V> result = new ArrayList<>();
        if (apiModelList != null && apiModelList.size() > 0)
        {
            for (T t : apiModelList)
            {
                V v = mappingApiToBean(t, vClass);
                result.add(v);
            }
        }
        return result;
    }

    public static <V, T> V mappingApiToBean(T apiModel, Class<V> vClass)
    {
        if(apiModel == null || vClass == null)
        {
            return null;
        }

        V result = null;
        try {
            result = vClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field [] fields = vClass.getDeclaredFields();
        if (fields != null && fields.length > 0)
        {
            for (Field field: fields)
            {
                ApiProperty apiPro = field.getAnnotation(ApiProperty.class);
                if (apiPro != null)
                {
                    String proName = apiPro.value();
                    try {
                        Object resultObj = null;
                        if (proName.indexOf(".") > 0)
                        {
                            String [] proArr = proName.split("\\.");
                            resultObj =  getObjProperty(proArr[0], apiModel);
                            for (int i = 1; i < proArr.length; i++)
                            {
                                resultObj =  getObjProperty(proArr[i], resultObj);
                            }
                        }
                        else
                        {
                            proName = proName.substring(0,1).toUpperCase() + proName.substring(1);
                            resultObj = apiModel.getClass().getMethod("get"+proName).invoke(apiModel);
                        }
                        String fieldName  = field.getName();
                        fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                        if (resultObj != null)
                        {
                            vClass.getDeclaredMethod("set"+fieldName, field.getType()).invoke(result, resultObj);
                        }
                    } catch (NoSuchMethodException e) {
                        Log.e("PropertiesUtils", e.getMessage());
                        continue;
                    } catch (InvocationTargetException e) {
                        Log.e("PropertiesUtils", e.getMessage());
                        continue;
                    } catch (IllegalAccessException e) {
                        Log.e("PropertiesUtils", e.getMessage());
                        continue;
                    }
                }
            }
        }
        return result;
    }


    private static <T> Object getObjProperty(String fieldName, T apiModel)
    {
        try {
            Field field = apiModel.getClass().getDeclaredField(fieldName);
            if (field != null)
            {
                fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                Object result = apiModel.getClass().getMethod("get"+fieldName).invoke(apiModel);
                return result;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T>  List<T> copyBeanListProperties(List<? extends Object> sourceList, Class<T> targetClazz)
    {
        if (sourceList == null || targetClazz == null || sourceList.size() < 1)
            return null;

        List<T> targetList = new ArrayList<>();

        for (Object object: sourceList)
        {
            T t = copyBeanProperties(object, targetClazz);
            targetList.add(t);
        }
        return targetList;
    }

    public static <T>  T copyBeanProperties(Object source, Class<T> targetClazz)
    {
        if (source == null || targetClazz == null)
            return null;

        Gson gson = new Gson();
        String sourceString = gson.toJson(source);
        T t = gson.fromJson(sourceString, targetClazz);
        return t;
    }
}
