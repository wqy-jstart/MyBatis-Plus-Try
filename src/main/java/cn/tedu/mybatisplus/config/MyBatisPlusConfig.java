package cn.tedu.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 扫描mapper文件夹
@MapperScan("cn.tedu.mybatisplus.mapper") // 扫描Mapper文件夹(原来是在config包里)
@EnableTransactionManagement// 自动管理事务
@Configuration
public class MyBatisPlusConfig {

    // 注册乐观锁
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    // 逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    //性能分析插件
//    @Bean
//    @Profile({"dev","test"})//设置dev开发、test测试 环境开启  保证我们的效率
//    public PerformanceInterceptor performanceInterceptor(){
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(100);//设置sql最大执行时间*ms，如果超过了则不执行
//        performanceInterceptor.setFormat(true);//开启sql格式化
//        return performanceInterceptor;
//    }
}
