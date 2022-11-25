package cn.tedu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 对应数据库中的主键(uuid,自增id,雪花算法,redis)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtModified;
    @Version// 乐观锁version注解
    private Integer version;
    @TableLogic // 逻辑删除
    private Integer deleted;
}
