create table if not exists photos (
    id BIGINT AUTO_INCREMENT primary key,
    file_name varchar(200),
    content_type varchar(200),
    data binary large object
);