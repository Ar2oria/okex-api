CREATE TABLE `btc_usdt_1m`
(
    `id`     int            NOT NULL AUTO_INCREMENT,
    `ts`     bigint(20) default 0 NOT NULL,
    `o`      decimal(16, 8) NOT NULL,
    `h`      decimal(16, 8) NOT NULL,
    `l`      decimal(16, 8) NOT NULL,
    `c`      decimal(16, 8) NOT NULL,
    `vol`    decimal(16, 8) NOT NULL,
    `volCcy` decimal(16, 8) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_idx_ts` (`ts`),
    KEY      `idx_o` (`o`),
    KEY      `idx_h` (`h`),
    KEY      `idx_l` (`l`),
    KEY      `idx_c` (`c`),
    KEY      `idx_vol` (`vol`),
    KEY      `idx_volCcy` (`volCcy`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


create table task
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


create table task_detail
(
    `id`        int           NOT NULL primary key AUTO_INCREMENT,
    `biz_id`    varchar(32)   not null,
    `inst_id`   varchar(32)   not null,
    `bar`       varchar(32)   not null,
    `params`    varchar(2000) not null default '',
    `status`    int           not null default 0 COMMENT '0 默认状态 1 已完成',
    `start_at`  bigint(20) not null,
    `create_at` timestamp     not null default current_timestamp,
    `update_at` timestamp     not null default current_timestamp on update current_timestamp,
    index       idx_biz_id(`biz_id`)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;