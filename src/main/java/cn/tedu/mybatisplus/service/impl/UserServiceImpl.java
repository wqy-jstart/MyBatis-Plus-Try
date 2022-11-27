package cn.tedu.mybatisplus.service.impl;

import cn.tedu.mybatisplus.pojo.User;
import cn.tedu.mybatisplus.mapper.UserMapper;
import cn.tedu.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author java@Wqy
 * @since 2022-11-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
