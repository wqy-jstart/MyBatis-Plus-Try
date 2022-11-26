package cn.tedu.mybatisplus;

import cn.tedu.mybatisplus.mapper.UserMapper;
import cn.tedu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootTest
class MybatisPlusApplicationTests {

    // 继承了BaseMapper,所有的方法都来自父类
    // 我们也可以扩展自己的方法!
    @Autowired
    private UserMapper userMapper;

    @Test // 测试查询列表
    void contextLoads() {
        // 参数是一个Wrapper ,条件构造器,这里先不用 null
        // 查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 测试插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("wqy");
        user.setAge(19);
        user.setId(8L);
        user.setEmail("2168149199@qq.com");
        int result = userMapper.insert(user);
        log.debug("插入成功,受影响的行数为:{}",result);
    }

    // 测试修改
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(6L);
        user.setName("我叫Wqy");
        int i = userMapper.updateById(user);
        log.debug("修改成功!受影响的行数为:{}",i);
    }

    @Test// 测试乐观锁成功
    public void testOptimisticLocker1(){
        // 1.查询用户信息
        User user = userMapper.selectById(1L);
        // 2.修改用户信息
        user.setAge(20);
        user.setEmail("12345678@qq.com");
        // 2.执行修改操作
        userMapper.updateById(user);
    }

    @Test// 测试乐观锁失败  多线程下
    public void testOptimisticLocker2(){
        //线程1
        User user1 = userMapper.selectById(1L);
        user1.setAge(1);
        user1.setEmail("2803708553@qq.com");
        //模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setAge(2);
        user2.setEmail("2803708553@qq.com");
        userMapper.updateById(user2);
        //自旋锁来多次尝试提交！
        userMapper.updateById(user1);//如果没有乐观锁就会覆盖插队线程的值
    }

    @Test//通过id查询单个用户
    public void testSelectById(){
        User user = userMapper.selectById(1596121094836748289L);
        System.out.println(user);
    }

    @Test//通过id查询多个用户
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        users.forEach(System.out::println);
        //System.out.println(users);
    }

    @Test//通过条件查询之一  map
    public void testMap(){
        HashMap<String, Object> map = new HashMap<>();
        //自定义要查询的
        map.put("name","wqy");
        map.put("age",19);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test//测试分页查询
    public void testPage(){
        //参数一current：当前页   参数二size：页面大小
        //使用了分页插件之后，所有的分页操作都变得简单了
        Page<User> page = new Page<>(1,4);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println("总页数==>"+page.getTotal());
    }

    @Test // 测试删除
    public void testDeleteById(){
        int count = userMapper.deleteById(1596066721037320195L);
        log.debug("删除成功!影响的条数为:{}",count);
    }

    @Test // 批量删除
    public void testDeleteBatch(){
        int count = userMapper.deleteBatchIds(Arrays.asList(1359507762519068675L, 1359507762519068676L));
        log.debug("删除成功,影响的条数:{}",count);
    }

    @Test // 条件删除
    public void testD(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("age","19");
        map.put("name","wqy");
        userMapper.deleteByMap(map);
    }

    @Test // 测试根据id删除
    public void testDeleteById1(){
        int count = userMapper.deleteById(1596121094836748289L);
        log.debug("删除成功!影响数据的条数为:{}",count);
    }

    @Test // 测试性能分析插件后的查询列表
    public void contextLoads1() {
        //参数是一个wrapper ，条件构造器，这里我们先不用 null
        //查询全部的用户
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
