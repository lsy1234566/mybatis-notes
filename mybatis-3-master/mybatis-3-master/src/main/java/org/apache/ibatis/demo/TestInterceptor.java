package org.apache.ibatis.demo;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 有四种type
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 * ParameterHandler (getParameterObject, setParameters)
 * ResultSetHandler (handleResultSets, handleOutputParameters)
 * StatementHandler (prepare, parameterize, batch, update, query)
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class TestInterceptor implements Interceptor {

  /**
   * 插件核心方法
   * @param invocation
   * @return
   * @throws Throwable
   */
  public Object intercept(Invocation invocation) throws Throwable {
    Object target = invocation.getTarget(); //被代理对象
    Method method = invocation.getMethod(); //代理方法
    Object[] args = invocation.getArgs(); //方法参数
    // do something ...... 方法拦截前执行代码块
    Object result = invocation.proceed();
    // do something .......方法拦截后执行代码块
    return result;
  }
  /**织入插件 默认动态代理*/
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

}

