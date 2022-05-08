create table `task`
(
    `id`            int         NOT NULL primary key AUTO_INCREMENT,
    `biz_id`        varchar(32) not null,
    `inst_id`       varchar(32) not null,
    `bar`           varchar(32) not null,
    `begin`         bigint(20) not null,
    `end`           bigint(20) not null,
    `task_limit`    int(11) not null,
    `task_threads`  int(11) not null,
    `task_interval` bigint(20) not null,
    `create_at`     timestamp   not null default current_timestamp,
    unique index uni_idx_biz_id(`biz_id`)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


create table `task_detail`
(
    `id`        int           NOT NULL primary key AUTO_INCREMENT,
    `biz_id`    varchar(32)   not null,
    `params`    varchar(2000) not null default '',
    `status`    int           not null default 0 COMMENT '0 默认状态 1 已完成',
    `start_at`  bigint(20) not null,
    `create_at` timestamp     not null default current_timestamp,
    `update_at` timestamp     not null default current_timestamp on update current_timestamp,
    index       idx_biz_id(`biz_id`)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;