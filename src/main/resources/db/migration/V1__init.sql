create table pdu
(
    event_id         varchar(500) primary key,
    room_id          varchar(500)             not null,
    version          varchar(255)             not null,
    sender           varchar(500)             not null,
    origin           varchar(255)             not null,
    origin_server_ts bigint                   not null,
    local_ts         bigint                   not null,
    type             varchar(300)             not null,
    state_key        varchar(300),
    content          varchar(2000),                     -- json
    depth            bigint                   not null,
    redacts          varchar(500),
    age              bigint,
    replaces_state   varchar(500),
    prev_sender      varchar(500),
    prev_content     varchar(2000),                     -- json
    redacted_because varchar(2000),
    sha256           varchar(2000),
    signatures       varchar(2000)            not null, -- json
    prev_events      varchar(2000),                     -- json
    auth_events      varchar(2000),                     -- json

    created_at       timestamp with time zone not null
);

create index "pdu-room_id" on pdu (room_id);

create table pdu_prev_event
(
    event_id      varchar(500) references pdu (event_id),
    prev_event_id varchar(500) references pdu (event_id),
    depth         bigint,
    primary key (event_id, prev_event_id)
);

create index "pdu_prev_event-event_id" on pdu_prev_event (event_id);
create index "pdu_prev_event-event_id-prev_event_id" on pdu_prev_event (event_id, prev_event_id);

create table pdu_auth_event
(
    event_id      varchar(500) references pdu (event_id),
    auth_event_id varchar(500) references pdu (event_id),
    primary key (event_id, auth_event_id)
);

create index "pdu_auth_event-event_id" on pdu_auth_event (event_id);
