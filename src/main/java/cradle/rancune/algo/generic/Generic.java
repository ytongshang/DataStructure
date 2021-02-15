package cradle.rancune.algo.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rancune@126.com on 2020/7/11.
 */
public class Generic<T> {

    public void test(List<String> list) {
    }

    public void test1(List list) {

    }

    public static void testGenericType(List<String> data) {
        //如何获取data传入的是泛型类型
        Class<?> aClass = data.getClass();
        //Class实现了Type接口
        Type aType = aClass;
        //判断aType是否有泛型(返回false)
        System.out.println(aType instanceof ParameterizedType);
    }

    public static <E> void testSuperGenericType(List<E> list) {
        Class<?> clazz = list.getClass();
        Type type = clazz.getGenericSuperclass();
        Class<?> parameterType = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println(parameterType);
    }

    public static void main(String[] args) {
        Class<?> clazz = Generic.class;
        try {
            Method test = clazz.getDeclaredMethod("test", List.class);
            Type t = test.getGenericParameterTypes()[0];
            System.out.println(t);
            Class<?> parameterType = (Class<?>) ((ParameterizedType) t).getActualTypeArguments()[0];
            System.out.println(parameterType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            Method test1 = clazz.getDeclaredMethod("test1", List.class);
            System.out.println(test1.getGenericParameterTypes()[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        testGenericType(new ArrayList<>());
        ArrayList<String> list = new ArrayList<String>(){};
        testSuperGenericType(list);
    }


}
