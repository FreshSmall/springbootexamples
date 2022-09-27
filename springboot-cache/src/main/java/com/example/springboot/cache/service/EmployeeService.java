package com.example.springboot.cache.service;

import com.example.springboot.cache.bean.Employee;
import com.example.springboot.cache.mapper.EmployeeMapper;
import com.example.springboot.cache.repository.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.DataFormatException;

/**
 * <pre>
 *
 * </pre>
 *
 * <pre>
 * @author mazq
 * 修改记录
 *    修改后版本:     修改人：  修改日期: 2020/04/01 13:50  修改内容:
 * </pre>
 */
@Service
@CacheConfig(cacheNames = {"emp"})
public class EmployeeService {

    private final static AtomicInteger atomicInteger = new AtomicInteger(0);

    Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * @Cacheable的key是不能用#result 属性：
     * cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
     * <p>
     * key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值  1-方法的返回值
     * 编写SpEL； #i d;参数id的值   #a0  #p0  #root.args[0]
     * getEmp[2]
     * <p>
     * keyGenerator：key的生成器；可以自己指定key的生成器的组件id
     * key/keyGenerator：二选一使用;
     * <p>
     * <p>
     * cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
     * <p>
     * condition：指定符合条件的情况下才缓存；
     * ,condition = "#id>0"
     * condition = "#a0>1"：第一个参数的值》1的时候才进行缓存
     * <p>
     * unless:否定缓存；当unless指定的条件为true，可以获取到结果进行判断方法的返回值就不会被缓存；
     * unless = "#result == null"
     * unless = "#a0==2":如果第一个参数的值是2，结果不缓存；
     * sync：是否使用异步模式
     */
    @Cacheable(value = {"emp"}, key = "#id")
    public Employee getEmp(Integer id) {
        LOG.info("查询{}号员工数据,时间:{},count:{}", id, new Date(),atomicInteger.addAndGet(1));
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        Employee employee = employeeDao.getEmpById(id);
        return employee;
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存 修改了数据，同时更新缓存
     */
    @CachePut(value = {"emp"}, key = "#result.id")
    public Employee updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
        LOG.info("更新{}号员工数据", employee.getId());
        return employee;
    }

    /**
     * key：指定要清除的数据
     * allEntries = true：指定清除这个缓存中所有的数据
     * beforeInvocation = false：
     * 默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
     * beforeInvocation = true：
     * 代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */
    @CacheEvict(value = {"emp"}, beforeInvocation = true, key = "#id")
    public void deleteEmp(Integer id) {
        //employeeMapper.deleteEmpById(id);
        int i = 10 / 0;
    }

    // @Caching 定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(/*value={"emp"},*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value={"emp"},*/key = "#result.id"),
                    @CachePut(/*value={"emp"},*/key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }

}
