package cn.tedu.mybatisplus.wsk;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器
 *
 * @Author java@Wqy
 * @Version 0.0.1
 */
public class WskCode {
    public static void main(String[] args) {

        //我们需要构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        // 怎么样去执行?需要书写配置策略

        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");// user.dir获取当前项目的目录
        gc.setOutputDir(projectPath+"/src/main/java");// 输出到哪个目录
        gc.setAuthor("java@Wqy");// 配置作者
        gc.setOpen(false); // 是否开启
        gc.setFileOverride(false); // 是否重写文件
        gc.setServiceName("%sService");// 去掉Service的I前缀
        gc.setIdType(IdType.ID_WORKER); // 设置id的类型
        gc.setDateType(DateType.ONLY_DATE); // 设置时间
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        // 2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUsername("root");// 数据库用户名
        dsc.setPassword("root");// 数据库密码
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver"); // 数据库驱动地址
        mpg.setDataSource(dsc); // 添加到数据库源

        // 3.包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.tedu.mybatisplus"); // 项目的父包
        pc.setEntity("pojo"); // 设置实体类的名字
        pc.setMapper("mapper");// 设置Mapper层的名字
        pc.setService("service");// 设置Service层的名字
        pc.setController("controller");// 设置Controller层的名字

        mpg.setPackageInfo(pc); // 添加到包配置中

        // 4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user"); // 配置要操作的数据库表名
        strategy.setNaming(NamingStrategy.underline_to_camel);// Name驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// Column驼峰命名
        strategy.setEntityLombokModel(true);// 是否使用LomBok
        strategy.setLogicDeleteFieldName("deleted");// 设置逻辑删除字段

        // 自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT); // 设置插入时自动填充创建时间
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);// 设置插入和修改时自动填充修改时间
        ArrayList<TableFill> tableFills = new ArrayList<>(); // New一个ArrayList集合
        tableFills.add(gmtCreate); // 添加创建时间
        tableFills.add(gmtModified);// 添加修改时间
        strategy.setTableFillList(tableFills); // 设置到填充配置中

        // 乐观锁配置
        strategy.setVersionFieldName("version"); // 设置乐观锁字段
        strategy.setRestControllerStyle(true);// 开启驼峰命名
        strategy.setControllerMappingHyphenStyle(true);// 开启请求路径RestFul风格
        mpg.setStrategy(strategy);

        mpg.execute();// 执行
    }
}
