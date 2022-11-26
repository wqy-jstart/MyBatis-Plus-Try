package cn.tedu.mybatisplus;

import cn.tedu.mybatisplus.mapper.UserMapper;
import cn.tedu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MyBatis-Plus条件构造器中Wrapper的测试
 *
 * @Author java@Wqy
 * @Version 0.0.1
 */
@Slf4j
@SpringBootTest
public class WrapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testWrapper1(){

        // 参数是一个Wrapper，条件构造器，和Map对比学习
        // 查询name不为空，email不为空，age大于18的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",18);// "greater equals"大于等于
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void testWrapper2(){
        // 查询name=wqy的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","wqy");
        //查询一个数据selectOne，若查询出多个会报错
        //Expected one result (or null) to be returned by selectOne(), but found: *
        //若出现多个结果使用list或map
        User user = userMapper.selectOne(wrapper);//查询一个数据，若出现多个结果使用list或map
        log.debug("查询成功，数据：{}",user);
    }

    @Test
    public void testWrapper3(){
        // 查询age在10~20之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",10,20);// 区间
        Integer count = userMapper.selectCount(wrapper);
        log.debug("查询成功!数量为:{}",count);
    }

    @Test
    public void testWrapper4(){
        // 模糊查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("name","s")// 不含s的:%s%
                .likeRight("email","t");// 邮箱t开头的:t%
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    @Test
    public void testWrappers(){
        // 子查询 查询id<5的用户的信息
        //  SELECT id,name,age,email,gmt_create,gmt_modified,version,deleted
        //  FROM user
        //  WHERE deleted=0
        //  AND id IN (select id from user where id<5)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询中查出来
        wrapper.inSql("id","select id from user where id<5");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    public void testWrapper6(){
        // 查询全部数据并根据id进行升序或降序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id"); // 降序
//        wrapper.orderByAsc("id");// 升序
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
