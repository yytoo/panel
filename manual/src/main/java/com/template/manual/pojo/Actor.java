package com.template.manual.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Y
 * @since 2024-12-26
 */
@Getter
@Setter
@TableName("actor")
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "actor_id", type = IdType.AUTO)
    private Short actorId;

    @TableField("first_name")
    private String firstName;

    @TableField("last_name")
    private String lastName;

    @TableField("last_update")
    private LocalDateTime lastUpdate;
}
