CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT ,
                            `username` varchar(20) NOT NULL DEFAULT '',
                            `password` varchar(32) NOT NULL DEFAULT '',
                            `email` varchar(50) DEFAULT NULL,
                            `avatar` varchar(255) DEFAULT NULL,
                            `is_deleted` boolean NOT NULL DEFAULT false ,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB;
drop table sys_user;
