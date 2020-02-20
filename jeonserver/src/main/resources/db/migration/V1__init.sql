create table pdu
(
    event_id         text primary key,
    room_id          text,
    version          text,
    sender           text,
    origin           text,
    origin_server_ts bigint,
    type             text,
    state_key        text,
    content          jsonb,
    depth            bigint,
    redacts          text,
    age              bigint,
    replaces_state   text,
    prev_sender      text,
    prev_content     jsonb,
    redacted_because text,
    sha256           text,
    signatures       jsonb,
    prev_events      jsonb,
    auth_events      jsonb,

    created_at       timestamptz,
    stream_id        text
);

create index "pdu-room_id" on pdu (room_id);
create index "pdu-stream_id-created_at" on pdu (stream_id, created_at desc);

-- create table pdu_graph
-- (
--     event_id  text references pdu (event_id),
--     parent_id text references pdu (event_id),
--     primary key (event_id, parent_id)
-- );
--
-- create table room_state
-- (
--     room_id   text primary key,
--     topic_id  text references pdu (event_id),
--     avatar_id text references pdu (event_id)
-- );
--
-- create table room_aliases
-- (
--     room_id text references pdu (event_id),
--     alias   text,
--     server  text,
--     primary key (room_id, alias)
-- );
--
-- create table room_server
-- (
--     room_id   text references pdu (event_id),
--     server    text,
--     joined_at timestamptz,
--     primary key (room_id, server)
-- );
--
-- create table room_membership
-- (
--     room_id   text references pdu (event_id),
--     member_id text references pdu (event_id)
-- );
--
