package cn.tedu.mybatisplus.mapper;

import cn.tedu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

// 在对应的Mapper上面实现基本的类 BaseMapper
@Repository //代表持久层
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD已经编写完成!
    // 不需要像以前配置一大堆文件
}
