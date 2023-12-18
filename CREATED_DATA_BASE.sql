create schema weather_api;

create table weather_api.weather_condition
(
    id bigint auto_increment primary key,
    code bigint       not null,
    icon varchar(255) null,
    text varchar(255) null
);

create table weather_api.weather_location
(
    id bigint auto_increment primary key,
    country         varchar(255) null,
    latitude        double       not null,
    local_date_time datetime(6)  null,
    localtime_epoch bigint       not null,
    longitude       double       not null,
    name            varchar(255) null,
    region          varchar(255) null,
    time_zone_id    varchar(255) null
);

create table weather_api.current_weather
(
    id bigint auto_increment primary key,
    cloud                int          not null,
    feels_likec          double       not null,
    feels_likef          double       not null,
    gust_kph             double       not null,
    gust_mph             double       not null,
    humidity             int          not null,
    is_day               int          not null,
    last_updated         datetime(6)  null,
    last_updated_epoch   bigint       not null,
    precip_in            int          not null,
    precip_mm            int          not null,
    pressure_in          double       not null,
    pressure_mb          int          not null,
    tempc                int          not null,
    tempf                int          not null,
    uv                   int          not null,
    vis_km               double       not null,
    vis_miles            double       not null,
    wind_degree          double       not null,
    wind_dir             varchar(255) null,
    wind_kph             double       not null,
    wind_mph             double       not null,
    weather_condition_id bigint       null,
    constraint UK_pr1ccrau4pasfekn6ej37r7ls
        unique (weather_condition_id),
    constraint FK4vcsloey4ctt66pkilotaee0u
        foreign key (weather_condition_id) references weather_condition (id)
);

create table weather_api.weather
(
    id bigint auto_increment primary key,
    current_weather_id  bigint null,
    weather_location_id bigint null,
    constraint UK_auewl00uyimpfm2g60d0xwacg
        unique (weather_location_id),
    constraint UK_t6yc3g8a7oax5yis3piojwg5p
        unique (current_weather_id),
    constraint FK2b4ntged167gwg8schg3646ey
        foreign key (weather_location_id) references weather_location (id),
    constraint FKhwj8fi5gh8yvvaixurq61i2q
        foreign key (current_weather_id) references current_weather (id)
);
