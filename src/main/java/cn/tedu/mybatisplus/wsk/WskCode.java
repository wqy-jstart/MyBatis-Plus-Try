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
        gc.setOutputDir("src/main/java/cn/tedu/mybatisplus");// 输出到哪个目录
        gc.setAuthor("java@Wqy");
        gc.setFileOverride(false);
        gc.setServiceName("%sService");// 去掉Service的I前缀
        gc.setIdType(IdType.AUTO);

        mpg.setGlobalConfig(gc);

        // 2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        mpg.setDataSource(dsc);

        mpg.setDataSource(dsc);

        // 3.包的配置
        PackageConfig pc = new PackageConfig();

        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");

        mpg.setPackageInfo(pc);

        // 4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user");
        strategy.setNaming(NamingStrategy.underline_to_camel);// Name驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// Column驼峰命名
        strategy.setEntityLombokModel(true);// 是否使用LomBok
        strategy.setLogicDeleteFieldName("deleted");// 逻辑删除

        // 自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        // 乐观锁配置
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);// 开启驼峰命名

        strategy.setControllerMappingHyphenStyle(true);// 开启请求路径RestFul风格

        mpg.setStrategy(strategy);

        mpg.execute();// 执行


    }
}
