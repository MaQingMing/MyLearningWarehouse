package com.atguigu.r2dbc.entity;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/4 17:18
 */
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Date;

@Table("t_book")
@Data
public class TBook {

    @Id
    private Long id;
    private String title;
    private Long authorId;
    private Instant publishTime; //响应式中日期的映射用 Instant 或者 LocalXxx


//    private TAuthor author; //每本书有唯一作者；

}
