package com.example.springbootmybatisplus.interceptor;

import com.example.springbootmybatisplus.entity.PredictImportRecordEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author: yinchao
 * @ClassName: MyInterceptor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/7/20 09:02
 */
@Slf4j
@Component
//@Intercepts(value = {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
       /* MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        log.info("------sqlId------" + sqlId);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.info("------sqlCommandType------" + sqlCommandType);
        log.info("拦截查询请求 Executor#update 方法" + invocation.getMethod());
        if (parameter == null) {
            return invocation.proceed();
        }
        if (SqlCommandType.SELECT == sqlCommandType) {

            Field[] fields = getAllFields(parameter);
            for (Field field : fields) {
                log.info("------field.name------" + field.getName());
                try {
                    // 注入创建时间
                    if ("createTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createDate = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createDate == null || local_createDate.equals("")) {
                            field.setAccessible(true);
                            field.set(parameter, new Date());
                            field.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            Field[] fields = getAllFields(parameter);
            for (Field field : fields) {
                log.info("------field.name------" + field.getName());
                try {
                    if ("id".equals(field.getName())) {
                        System.out.println(field);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Object proceed = invocation.proceed();
        log.info("变更之后的值");*/
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性;：MetaObject是Mybatis提供的一个用于方便、
        //优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
        MetaObject metaObject = MetaObject
                .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                        new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();
        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        //数据库连接信息
//        Configuration configuration = mappedStatement.getConfiguration();
//        ComboPooledDataSource dataSource = (ComboPooledDataSource)configuration.getEnvironment().getDataSource();
//        dataSource.getJdbcUrl();

        BoundSql boundSql = statementHandler.getBoundSql();
        //获取到原始sql语句
        String sql = boundSql.getSql();
        String mSql = sql + " limit 2";
        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, mSql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("=======begin");
        System.out.println(properties.getProperty("param1"));
        System.out.println(properties.getProperty("param2"));
        Interceptor.super.setProperties(properties);
        System.out.println("=======end");
    }

    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
