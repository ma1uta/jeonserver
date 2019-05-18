--
-- Initialize the database schema.
--

--
-- Name: abstract_auth; Type: TABLE;
--

CREATE TABLE abstract_auth
(
    type character varying(32) NOT NULL,
    id   bigint                NOT NULL
);

--
-- Name: abstract_auth_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE abstract_auth_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: account_data; Type: TABLE;
--

CREATE TABLE account_data
(
    id      bigint NOT NULL,
    content text,
    room    text,
    type    text,
    user_id bigint
);

--
-- Name: account_data_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE account_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: action_id; Type: SEQUENCE;
--

CREATE SEQUENCE action_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: auth_event; Type: TABLE;
--

CREATE TABLE auth_event
(
    id       bigint NOT NULL,
    auth_id  bigint,
    event_id bigint
);

--
-- Name: auth_event_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE auth_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: device; Type: TABLE;
--

CREATE TABLE device
(
    id      bigint NOT NULL,
    created timestamp with time zone,
    device  text,
    user_id bigint
);

--
-- Name: device_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: device_key; Type: TABLE;
--

CREATE TABLE device_key
(
    id        bigint NOT NULL,
    algorithm text,
    created   timestamp with time zone,
    key       text,
    signature text,
    device_id bigint,
    user_id   bigint
);

--
-- Name: device_key_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE device_key_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: domain; Type: TABLE;
--

CREATE TABLE domain
(
    domain text NOT NULL
);

--
-- Name: edu; Type: TABLE;
--

CREATE TABLE edu
(
    transaction_id bigint NOT NULL,
    event_id       bigint NOT NULL
);

--
-- Name: event; Type: TABLE;
--

CREATE TABLE event
(
    "DTYPE"       character varying(31)  NOT NULL,
    id            bigint                 NOT NULL,
    created       timestamp with time zone,
    depth         bigint,
    event_id      text,
    hash          text,
    origin_server text,
    received      timestamp with time zone,
    sender        text,
    txn_id        text,
    type          text,
    content_id    bigint,
    domain_id     text NOT NULL,
    redacts_id    bigint,
    room_id       bigint
);

--
-- Name: event_content; Type: TABLE;
--

CREATE TABLE event_content
(
    id      bigint NOT NULL,
    content text
);

--
-- Name: event_content_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE event_content_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: event_filter; Type: TABLE;
--

CREATE TABLE event_filter
(
    id bigint NOT NULL
);

--
-- Name: event_filter_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE event_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: event_filter_not_senders; Type: TABLE;
--

CREATE TABLE event_filter_not_senders
(
    id          bigint NOT NULL,
    not_senders text
);

--
-- Name: event_filter_not_types; Type: TABLE;
--

CREATE TABLE event_filter_not_types
(
    id        bigint NOT NULL,
    not_types text
);

--
-- Name: event_filter_senders; Type: TABLE;
--

CREATE TABLE event_filter_senders
(
    id      bigint NOT NULL,
    senders text
);

--
-- Name: event_filter_types; Type: TABLE;
--

CREATE TABLE event_filter_types
(
    id    bigint NOT NULL,
    types text
);

--
-- Name: event_graph; Type: TABLE;
--

CREATE TABLE event_graph
(
    parents_id  bigint NOT NULL,
    children_id bigint NOT NULL
);

--
-- Name: event_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: fed_transaction_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE fed_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: federated_transaction; Type: TABLE;
--

CREATE TABLE federated_transaction
(
    id               bigint NOT NULL,
    origin_server    text,
    origin_server_ts timestamp with time zone,
    received         timestamp with time zone,
    txn_id           text,
    domain_id        text
);

--
-- Name: filter; Type: TABLE;
--

CREATE TABLE filter
(
    id              bigint NOT NULL,
    account_data_id bigint,
    presence_id     bigint,
    room_id         bigint
);

--
-- Name: filter_event_fields; Type: TABLE;
--

CREATE TABLE filter_event_fields
(
    id           bigint NOT NULL,
    event_fields text
);

--
-- Name: filter_event_format; Type: TABLE;
--

CREATE TABLE filter_event_format
(
    id           bigint NOT NULL,
    event_format text
);

--
-- Name: filter_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: incoming_transaction; Type: TABLE;
--

CREATE TABLE incoming_transaction
(
    id             bigint NOT NULL,
    parsed         timestamp with time zone,
    received       timestamp with time zone,
    domain_id      text,
    transaction_id bigint
);

--
-- Name: incoming_transaction_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE incoming_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: media; Type: TABLE;
--

CREATE TABLE media
(
    id           bigint NOT NULL,
    allow_remote boolean,
    created      timestamp with time zone,
    filename     text,
    media_id     text,
    path         text,
    size         bigint,
    domain_id    text
);

--
-- Name: media_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE media_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: media_remote_addresses; Type: TABLE;
--

CREATE TABLE media_remote_addresses
(
    id             bigint NOT NULL,
    remote_address text
);

--
-- Name: notification; Type: TABLE;
--

CREATE TABLE notification
(
    id          bigint NOT NULL,
    profile_tag text,
    read        boolean,
    room_id     text,
    ts          bigint,
    event_id    bigint,
    user_id     bigint
);

--
-- Name: notification_actions; Type: TABLE;
--

CREATE TABLE notification_actions
(
    notification_id bigint NOT NULL,
    action_id       bigint NOT NULL
);

--
-- Name: notification_id; Type: SEQUENCE;
--

CREATE SEQUENCE notification_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: one_time_key; Type: TABLE;
--

CREATE TABLE one_time_key
(
    id        bigint NOT NULL,
    algorithm text,
    created   timestamp with time zone,
    key       text,
    key_id    text,
    user_id   bigint
);

--
-- Name: one_time_key_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE one_time_key_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: one_time_key_signature; Type: TABLE;
--

CREATE TABLE one_time_key_signature
(
    id           bigint NOT NULL,
    algorithm    text,
    signature    text,
    device_id    bigint,
    one_time_key bigint
);

--
-- Name: one_time_key_signature_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE one_time_key_signature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: openid; Type: TABLE;
--

CREATE TABLE openid
(
    id           bigint NOT NULL,
    access_token text,
    created      timestamp with time zone,
    expires      timestamp with time zone,
    server_name  text,
    token_type   text,
    user_id      bigint
);

--
-- Name: openid_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE openid_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: outgoing_queue; Type: TABLE;
--

CREATE TABLE outgoing_queue
(
    id                  bigint NOT NULL,
    created             timestamp with time zone,
    sent_to_transaction timestamp with time zone,
    target              text,
    event_id            bigint,
    transaction_id      bigint
);

--
-- Name: outgoing_queue_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE outgoing_queue_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: outgoing_transaction; Type: TABLE;
--

CREATE TABLE outgoing_transaction
(
    id              bigint NOT NULL,
    attempt_message text,
    created         timestamp with time zone,
    sending_attempt integer,
    sent            timestamp with time zone,
    target          text,
    domain_id       text,
    transaction_id  bigint
);

--
-- Name: outgoing_transaction_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE outgoing_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: password_auth; Type: TABLE;
--

CREATE TABLE password_auth
(
    password text,
    id       bigint NOT NULL
);

--
-- Name: pdu; Type: TABLE;
--

CREATE TABLE pdu
(
    transaction_id bigint NOT NULL,
    event_id       bigint NOT NULL
);

--
-- Name: push_action; Type: TABLE;
--

CREATE TABLE push_action
(
    type character varying(31) NOT NULL,
    id   bigint                NOT NULL
);

--
-- Name: push_condition; Type: TABLE;
--

CREATE TABLE push_condition
(
    id      bigint NOT NULL,
    "is"    text,
    key     text,
    kind    text,
    pattern text,
    rule_id bigint
);

--
-- Name: push_condition_id; Type: SEQUENCE;
--

CREATE SEQUENCE push_condition_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: push_rule; Type: TABLE;
--

CREATE TABLE push_rule
(
    id         bigint NOT NULL,
    "default"  boolean,
    enabled    boolean,
    pattern    text,
    rule_id    text,
    ruleset_id bigint
);

--
-- Name: push_rule_actions; Type: TABLE;
--

CREATE TABLE push_rule_actions
(
    push_rule_id bigint NOT NULL,
    action_id    bigint NOT NULL
);

--
-- Name: push_rule_id; Type: SEQUENCE;
--

CREATE SEQUENCE push_rule_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: pusher; Type: TABLE;
--

CREATE TABLE pusher
(
    id                  bigint NOT NULL,
    app_display_name    text,
    app_id              text,
    device_display_name text,
    kind                text,
    lang                text,
    profile_tag         text,
    pushkey             text,
    user_id             bigint
);

--
-- Name: pusher_data; Type: TABLE;
--

CREATE TABLE pusher_data
(
    id        bigint NOT NULL,
    format    text,
    url       text,
    pusher_id bigint
);

--
-- Name: pusher_data_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE pusher_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: pusher_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE pusher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: redacts; Type: TABLE;
--

CREATE TABLE redacts
(
    id     bigint NOT NULL,
    reason text,
    txn_id text
);


ALTER TABLE redacts
    OWNER TO jeon;

--
-- Name: redacts_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE redacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room; Type: TABLE;
--

CREATE TABLE room
(
    id              bigint NOT NULL,
    created         timestamp with time zone,
    room_id         text,
    version         text,
    visible         boolean,
    domain_id       text,
    latest_state_id bigint
);

--
-- Name: room_event_filter; Type: TABLE;
--

CREATE TABLE room_event_filter
(
    id           bigint NOT NULL,
    contains_url boolean,
    "limit"      bigint
);

--
-- Name: room_event_filter_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_event_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room_event_filter_not_rooms; Type: TABLE;
--

CREATE TABLE room_event_filter_not_rooms
(
    id        bigint NOT NULL,
    not_rooms text
);

--
-- Name: room_event_filter_not_senders; Type: TABLE;
--

CREATE TABLE room_event_filter_not_senders
(
    id          bigint NOT NULL,
    not_senders text
);

--
-- Name: room_event_filter_not_types; Type: TABLE;
--

CREATE TABLE room_event_filter_not_types
(
    id        bigint NOT NULL,
    not_types text
);

--
-- Name: room_event_filter_rooms; Type: TABLE;
--

CREATE TABLE room_event_filter_rooms
(
    id    bigint NOT NULL,
    rooms text
);

--
-- Name: room_event_filter_senders; Type: TABLE;
--

CREATE TABLE room_event_filter_senders
(
    id      bigint NOT NULL,
    senders text
);

--
-- Name: room_event_filter_types; Type: TABLE;
--

CREATE TABLE room_event_filter_types
(
    id    bigint NOT NULL,
    types text
);

--
-- Name: room_filter; Type: TABLE;
--

CREATE TABLE room_filter
(
    id              bigint NOT NULL,
    include_leave   boolean,
    "limit"         bigint,
    account_data_id bigint,
    ephemeral_id    bigint,
    state_id        bigint,
    timeline_id     bigint
);

--
-- Name: room_filter_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room_filter_not_rooms; Type: TABLE;
--

CREATE TABLE room_filter_not_rooms
(
    id        bigint NOT NULL,
    not_rooms text
);

--
-- Name: room_filter_rooms; Type: TABLE;
--

CREATE TABLE room_filter_rooms
(
    id    bigint NOT NULL,
    rooms text
);

--
-- Name: room_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room_report; Type: TABLE;
--

CREATE TABLE room_report
(
    id       bigint NOT NULL,
    reason   text,
    score    bigint,
    event_id bigint,
    room_id  bigint
);

--
-- Name: room_report_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room_server_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_server_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room_servers; Type: TABLE;
--

CREATE TABLE room_servers
(
    id      bigint NOT NULL,
    server  text,
    room_id bigint
);

--
-- Name: room_state; Type: TABLE;
--

CREATE TABLE room_state
(
    id            bigint NOT NULL,
    state_key     text,
    event_id      bigint,
    room_state_id bigint
);

--
-- Name: room_state_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room_state_snapshot; Type: TABLE;
--

CREATE TABLE room_state_snapshot
(
    id                    bigint NOT NULL,
    created               timestamp with time zone,
    avatar_id             bigint,
    canonical_alias_id    bigint,
    encryption_id         bigint,
    guest_access_id       bigint,
    history_visibility_id bigint,
    initial_id            bigint,
    join_rules_id         bigint,
    name_id               bigint,
    permissions_id        bigint,
    prev_id               bigint,
    room_id               bigint,
    server_acl_id         bigint,
    tombstone_id          bigint,
    topic_id              bigint
);

--
-- Name: room_state_snapshot_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_state_snapshot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: room_transaction; Type: TABLE;
--

CREATE TABLE room_transaction
(
    id         bigint NOT NULL,
    created    timestamp with time zone,
    sender     text,
    txn_id     text,
    type       text,
    content_id bigint,
    room_id    bigint
);

--
-- Name: room_transaction_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE room_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: ruleset; Type: TABLE;
--

CREATE TABLE ruleset
(
    id      bigint NOT NULL,
    user_id bigint
);

--
-- Name: ruleset_id; Type: SEQUENCE;
--

CREATE SEQUENCE ruleset_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: send_to_device; Type: TABLE;
--

CREATE TABLE send_to_device
(
    id         bigint NOT NULL,
    device     text,
    "user"     text,
    content_id bigint
);

--
-- Name: send_to_device_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE send_to_device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: signature; Type: TABLE;
--

CREATE TABLE signature
(
    id        bigint NOT NULL,
    key       text,
    server    text,
    signature text,
    event_id  bigint
);

--
-- Name: signature_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE signature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: state_filter; Type: TABLE;
--

CREATE TABLE state_filter
(
    id                        bigint NOT NULL,
    contains_url              boolean,
    include_redundant_members boolean,
    lazy_load_members         boolean,
    "limit"                   bigint
);

--
-- Name: state_filter_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE state_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: state_filter_not_rooms; Type: TABLE;
--

CREATE TABLE state_filter_not_rooms
(
    id        bigint NOT NULL,
    not_rooms text
);

--
-- Name: state_filter_not_senders; Type: TABLE;
--

CREATE TABLE state_filter_not_senders
(
    id          bigint NOT NULL,
    not_senders text
);

--
-- Name: state_filter_not_types; Type: TABLE;
--

CREATE TABLE state_filter_not_types
(
    id        bigint NOT NULL,
    not_types text
);

--
-- Name: state_filter_rooms; Type: TABLE;
--

CREATE TABLE state_filter_rooms
(
    id    bigint NOT NULL,
    rooms text
);

--
-- Name: state_filter_senders; Type: TABLE;
--

CREATE TABLE state_filter_senders
(
    id      bigint NOT NULL,
    senders text
);

--
-- Name: state_filter_types; Type: TABLE;
--

CREATE TABLE state_filter_types
(
    id    bigint NOT NULL,
    types text
);

--
-- Name: tag; Type: TABLE;
--

CREATE TABLE tag
(
    id      bigint NOT NULL,
    name    text,
    "order" double precision,
    user_id bigint
);

--
-- Name: tag_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: thumbnail; Type: TABLE;
--

CREATE TABLE thumbnail
(
    id           bigint NOT NULL,
    allow_remote boolean,
    created      timestamp with time zone,
    height       bigint,
    method       text,
    path         text,
    size         bigint,
    width        bigint,
    media_id     bigint
);

--
-- Name: thumbnail_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE thumbnail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: token; Type: TABLE;
--

CREATE TABLE token
(
    id           bigint NOT NULL,
    created      timestamp with time zone,
    expires      timestamp with time zone,
    last_seen    timestamp with time zone,
    last_seen_ip text,
    token        text,
    device_id    bigint,
    user_id      bigint
);

--
-- Name: token_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: user; Type: TABLE;
--

CREATE TABLE "user"
(
    id               bigint NOT NULL,
    admin            boolean,
    created          timestamp with time zone,
    currently_active boolean,
    last_active_ago  timestamp with time zone,
    presence         text,
    state_msg        text,
    username         text,
    auth_id          bigint,
    domain_id        text
);

--
-- Name: user_id_seq; Type: SEQUENCE;
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: abstract_auth abstract_auth_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY abstract_auth
    ADD CONSTRAINT abstract_auth_pkey PRIMARY KEY (id);


--
-- Name: account_data account_data_constr_room_user_type; Type: CONSTRAINT;
--

ALTER TABLE ONLY account_data
    ADD CONSTRAINT account_data_constr_room_user_type UNIQUE (room, user_id, type);


--
-- Name: account_data account_data_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY account_data
    ADD CONSTRAINT account_data_pkey PRIMARY KEY (id);


--
-- Name: auth_event auth_event_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY auth_event
    ADD CONSTRAINT auth_event_pkey PRIMARY KEY (id);


--
-- Name: device device_constr_device_user; Type: CONSTRAINT;
--

ALTER TABLE ONLY device
    ADD CONSTRAINT device_constr_device_user UNIQUE (device, user_id);


--
-- Name: device_key device_key_constr_device_user_algorithm; Type: CONSTRAINT;
--

ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_constr_device_user_algorithm UNIQUE (device_id, user_id, algorithm);


--
-- Name: device_key device_key_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_pkey PRIMARY KEY (id);


--
-- Name: device device_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);


--
-- Name: domain domain_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY domain
    ADD CONSTRAINT domain_pkey PRIMARY KEY (domain);


--
-- Name: edu edu_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY edu
    ADD CONSTRAINT edu_pkey PRIMARY KEY (transaction_id, event_id);


--
-- Name: event event_constr_domain_event; Type: CONSTRAINT;
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_constr_domain_event UNIQUE (domain_id, event_id);


--
-- Name: event_content event_content_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY event_content
    ADD CONSTRAINT event_content_pkey PRIMARY KEY (id);


--
-- Name: event_filter event_filter_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY event_filter
    ADD CONSTRAINT event_filter_pkey PRIMARY KEY (id);


--
-- Name: event event_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- Name: federated_transaction fed_trans_constr_txn_origin_domain; Type: CONSTRAINT;
--

ALTER TABLE ONLY federated_transaction
    ADD CONSTRAINT fed_trans_constr_txn_origin_domain UNIQUE (txn_id, origin_server, domain_id);


--
-- Name: federated_transaction federated_transaction_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY federated_transaction
    ADD CONSTRAINT federated_transaction_pkey PRIMARY KEY (id);


--
-- Name: filter filter_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_pkey PRIMARY KEY (id);


--
-- Name: incoming_transaction income_trans_constr_trans_domain; Type: CONSTRAINT;
--

ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT income_trans_constr_trans_domain UNIQUE (transaction_id, domain_id);


--
-- Name: incoming_transaction incoming_transaction_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT incoming_transaction_pkey PRIMARY KEY (id);


--
-- Name: media media_constr_media_domain; Type: CONSTRAINT;
--

ALTER TABLE ONLY media
    ADD CONSTRAINT media_constr_media_domain UNIQUE (media_id, domain_id);


--
-- Name: media media_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);


--
-- Name: notification_actions notification_actions_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT notification_actions_pkey PRIMARY KEY (notification_id, action_id);


--
-- Name: notification notification_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- Name: one_time_key one_time_key_constr_key_user_algorithm; Type: CONSTRAINT;
--

ALTER TABLE ONLY one_time_key
    ADD CONSTRAINT one_time_key_constr_key_user_algorithm UNIQUE (key_id, user_id, algorithm);


--
-- Name: one_time_key one_time_key_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY one_time_key
    ADD CONSTRAINT one_time_key_pkey PRIMARY KEY (id);


--
-- Name: one_time_key_signature one_time_key_sign_constr_key_device_algorithm; Type: CONSTRAINT;
--

ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_constr_key_device_algorithm UNIQUE (one_time_key, device_id, algorithm);


--
-- Name: one_time_key_signature one_time_key_signature_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_signature_pkey PRIMARY KEY (id);


--
-- Name: openid openid_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY openid
    ADD CONSTRAINT openid_pkey PRIMARY KEY (id);


--
-- Name: outgoing_queue outcome_queue_constr_event_target; Type: CONSTRAINT;
--

ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outcome_queue_constr_event_target UNIQUE (event_id, target);


--
-- Name: outgoing_transaction outcome_trans_constr_trans_domain; Type: CONSTRAINT;
--

ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outcome_trans_constr_trans_domain UNIQUE (transaction_id, domain_id);


--
-- Name: outgoing_queue outgoing_queue_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outgoing_queue_pkey PRIMARY KEY (id);


--
-- Name: outgoing_transaction outgoing_transaction_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outgoing_transaction_pkey PRIMARY KEY (id);


--
-- Name: password_auth password_auth_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY password_auth
    ADD CONSTRAINT password_auth_pkey PRIMARY KEY (id);


--
-- Name: pdu pdu_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY pdu
    ADD CONSTRAINT pdu_pkey PRIMARY KEY (transaction_id, event_id);


--
-- Name: push_action push_action_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY push_action
    ADD CONSTRAINT push_action_pkey PRIMARY KEY (id);


--
-- Name: push_condition push_condition_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY push_condition
    ADD CONSTRAINT push_condition_pkey PRIMARY KEY (id);


--
-- Name: push_rule_actions push_rule_actions_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT push_rule_actions_pkey PRIMARY KEY (push_rule_id, action_id);


--
-- Name: push_rule push_rule_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY push_rule
    ADD CONSTRAINT push_rule_pkey PRIMARY KEY (id);


--
-- Name: pusher pusher_constr_pushkey_user; Type: CONSTRAINT;
--

ALTER TABLE ONLY pusher
    ADD CONSTRAINT pusher_constr_pushkey_user UNIQUE (pushkey, user_id);


--
-- Name: pusher_data pusher_data_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY pusher_data
    ADD CONSTRAINT pusher_data_pkey PRIMARY KEY (id);


--
-- Name: pusher pusher_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY pusher
    ADD CONSTRAINT pusher_pkey PRIMARY KEY (id);


--
-- Name: redacts redacts_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY redacts
    ADD CONSTRAINT redacts_pkey PRIMARY KEY (id);


--
-- Name: room room_constr_room_domain; Type: CONSTRAINT;
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_constr_room_domain UNIQUE (room_id, domain_id);


--
-- Name: room_event_filter room_event_filter_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_event_filter
    ADD CONSTRAINT room_event_filter_pkey PRIMARY KEY (id);


--
-- Name: room_filter room_filter_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_pkey PRIMARY KEY (id);


--
-- Name: room room_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- Name: room_report room_report_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_report
    ADD CONSTRAINT room_report_pkey PRIMARY KEY (id);


--
-- Name: room_servers room_server_constr_room_server; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_servers
    ADD CONSTRAINT room_server_constr_room_server UNIQUE (room_id, server);


--
-- Name: room_servers room_servers_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_servers
    ADD CONSTRAINT room_servers_pkey PRIMARY KEY (id);


--
-- Name: room_state room_state_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_state
    ADD CONSTRAINT room_state_pkey PRIMARY KEY (id);


--
-- Name: room_state_snapshot room_state_snapshot_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_pkey PRIMARY KEY (id);


--
-- Name: room_transaction room_trans_constr_txn_sender_room; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_trans_constr_txn_sender_room UNIQUE (txn_id, sender, room_id);


--
-- Name: room_transaction room_transaction_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_transaction_pkey PRIMARY KEY (id);


--
-- Name: ruleset ruleset_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY ruleset
    ADD CONSTRAINT ruleset_pkey PRIMARY KEY (id);


--
-- Name: send_to_device send_to_device_constr_user_device; Type: CONSTRAINT;
--

ALTER TABLE ONLY send_to_device
    ADD CONSTRAINT send_to_device_constr_user_device UNIQUE ("user", device);


--
-- Name: send_to_device send_to_device_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY send_to_device
    ADD CONSTRAINT send_to_device_pkey PRIMARY KEY (id);


--
-- Name: signature signature_constr_event_server; Type: CONSTRAINT;
--

ALTER TABLE ONLY signature
    ADD CONSTRAINT signature_constr_event_server UNIQUE (event_id, server);


--
-- Name: signature signature_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY signature
    ADD CONSTRAINT signature_pkey PRIMARY KEY (id);


--
-- Name: state_filter state_filter_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY state_filter
    ADD CONSTRAINT state_filter_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: thumbnail thumbnail_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY thumbnail
    ADD CONSTRAINT thumbnail_pkey PRIMARY KEY (id);


--
-- Name: token token_constr_device_user; Type: CONSTRAINT;
--

ALTER TABLE ONLY token
    ADD CONSTRAINT token_constr_device_user UNIQUE (device_id, user_id);


--
-- Name: token token_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- Name: pdu uk_7cni9gjcr544pbxfledxijh6q; Type: CONSTRAINT;
--

ALTER TABLE ONLY pdu
    ADD CONSTRAINT uk_7cni9gjcr544pbxfledxijh6q UNIQUE (event_id);


--
-- Name: push_rule_actions uk_csfqy99gnjq2pvq3h5s1aowgm; Type: CONSTRAINT;
--

ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT uk_csfqy99gnjq2pvq3h5s1aowgm UNIQUE (action_id);


--
-- Name: edu uk_n4waslk9fpgow8hopxiwkci70; Type: CONSTRAINT;
--

ALTER TABLE ONLY edu
    ADD CONSTRAINT uk_n4waslk9fpgow8hopxiwkci70 UNIQUE (event_id);


--
-- Name: notification_actions uk_qahnmi95a3alxbddcrvf88blj; Type: CONSTRAINT;
--

ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT uk_qahnmi95a3alxbddcrvf88blj UNIQUE (action_id);


--
-- Name: user user_constr_username_domain; Type: CONSTRAINT;
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_constr_username_domain UNIQUE (username, domain_id);


--
-- Name: user user_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: password_auth FKppfos629e04vpuox0cpxmvf98; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY password_auth
    ADD CONSTRAINT "FKppfos629e04vpuox0cpxmvf98" FOREIGN KEY (id) REFERENCES abstract_auth (id);


--
-- Name: account_data account_data_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY account_data
    ADD CONSTRAINT account_data_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: auth_event auh_event_fk_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY auth_event
    ADD CONSTRAINT auh_event_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: auth_event auth_event_fk_auth; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY auth_event
    ADD CONSTRAINT auth_event_fk_auth FOREIGN KEY (auth_id) REFERENCES room_state (id);


--
-- Name: device device_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY device
    ADD CONSTRAINT device_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: device_key device_key_fk_device; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_fk_device FOREIGN KEY (device_id) REFERENCES device (id);


--
-- Name: device_key device_key_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: event_filter_not_senders event_filter_fk_not_senders; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event_filter_not_senders
    ADD CONSTRAINT event_filter_fk_not_senders FOREIGN KEY (id) REFERENCES event_filter (id);


--
-- Name: event_filter_not_types event_filter_fk_not_types; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event_filter_not_types
    ADD CONSTRAINT event_filter_fk_not_types FOREIGN KEY (id) REFERENCES event_filter (id);


--
-- Name: event_filter_senders event_filter_fk_senders; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event_filter_senders
    ADD CONSTRAINT event_filter_fk_senders FOREIGN KEY (id) REFERENCES event_filter (id);


--
-- Name: event_filter_types event_filter_fk_types; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event_filter_types
    ADD CONSTRAINT event_filter_fk_types FOREIGN KEY (id) REFERENCES event_filter (id);


--
-- Name: event_graph event_fk_children; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event_graph
    ADD CONSTRAINT event_fk_children FOREIGN KEY (children_id) REFERENCES event (id);


--
-- Name: event event_fk_content; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_content FOREIGN KEY (content_id) REFERENCES event_content (id);


--
-- Name: event event_fk_domain; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


--
-- Name: event_graph event_fk_parents; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event_graph
    ADD CONSTRAINT event_fk_parents FOREIGN KEY (parents_id) REFERENCES event (id);


--
-- Name: event event_fk_redacts; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_redacts FOREIGN KEY (redacts_id) REFERENCES redacts (id);


--
-- Name: event event_fk_room; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


--
-- Name: federated_transaction fed_trans_fk_domain; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY federated_transaction
    ADD CONSTRAINT fed_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


--
-- Name: edu fed_trans_fk_edu_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY edu
    ADD CONSTRAINT fed_trans_fk_edu_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: edu fed_trans_fk_edu_trans; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY edu
    ADD CONSTRAINT fed_trans_fk_edu_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


--
-- Name: pdu fed_trans_fk_pdu_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY pdu
    ADD CONSTRAINT fed_trans_fk_pdu_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: pdu fed_trans_fk_pdu_trans; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY pdu
    ADD CONSTRAINT fed_trans_fk_pdu_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


--
-- Name: filter filter_fk_account_data; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_fk_account_data FOREIGN KEY (account_data_id) REFERENCES event_filter (id);


--
-- Name: filter_event_fields filter_fk_event_fields; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY filter_event_fields
    ADD CONSTRAINT filter_fk_event_fields FOREIGN KEY (id) REFERENCES filter (id);


--
-- Name: filter_event_format filter_fk_event_format; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY filter_event_format
    ADD CONSTRAINT filter_fk_event_format FOREIGN KEY (id) REFERENCES filter (id);


--
-- Name: filter filter_fk_presence; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_fk_presence FOREIGN KEY (presence_id) REFERENCES event_filter (id);


--
-- Name: filter filter_fk_room; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_fk_room FOREIGN KEY (room_id) REFERENCES room_filter (id);


--
-- Name: incoming_transaction income_trans_fk_domain; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT income_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


--
-- Name: incoming_transaction income_trans_fk_trans; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT income_trans_fk_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


--
-- Name: media media_fk_domain; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY media
    ADD CONSTRAINT media_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


--
-- Name: media_remote_addresses media_fk_remote_addresses; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY media_remote_addresses
    ADD CONSTRAINT media_fk_remote_addresses FOREIGN KEY (id) REFERENCES media (id);


--
-- Name: notification_actions notif_fk_actions; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT notif_fk_actions FOREIGN KEY (action_id) REFERENCES push_action (id);


--
-- Name: notification notif_fk_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY notification
    ADD CONSTRAINT notif_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: notification_actions notif_fk_notif; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT notif_fk_notif FOREIGN KEY (notification_id) REFERENCES notification (id);


--
-- Name: notification notif_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY notification
    ADD CONSTRAINT notif_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: one_time_key one_time_key_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY one_time_key
    ADD CONSTRAINT one_time_key_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: one_time_key_signature one_time_key_sign_device; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_device FOREIGN KEY (device_id) REFERENCES device (id);


--
-- Name: one_time_key_signature one_time_key_sign_fk_one_time_key; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_fk_one_time_key FOREIGN KEY (one_time_key) REFERENCES one_time_key (id);


--
-- Name: openid openid_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY openid
    ADD CONSTRAINT openid_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: outgoing_queue outcome_queue_fk_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outcome_queue_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: outgoing_queue outcome_queue_fk_trans; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outcome_queue_fk_trans FOREIGN KEY (transaction_id) REFERENCES outgoing_transaction (id);


--
-- Name: outgoing_transaction outcome_trans_fk_domain; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outcome_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


--
-- Name: outgoing_transaction outcome_trans_fk_trans; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outcome_trans_fk_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


--
-- Name: push_condition push_condition_fk_rule; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY push_condition
    ADD CONSTRAINT push_condition_fk_rule FOREIGN KEY (rule_id) REFERENCES push_rule (id);


--
-- Name: push_rule_actions push_fule_fk_action; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT push_fule_fk_action FOREIGN KEY (action_id) REFERENCES push_action (id);


--
-- Name: push_rule_actions push_rule_fk_push_rule_actions; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT push_rule_fk_push_rule_actions FOREIGN KEY (push_rule_id) REFERENCES push_rule (id);


--
-- Name: push_rule push_rule_fk_ruleset; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY push_rule
    ADD CONSTRAINT push_rule_fk_ruleset FOREIGN KEY (ruleset_id) REFERENCES ruleset (id);


--
-- Name: pusher_data pusher_data_fk_pusher; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY pusher_data
    ADD CONSTRAINT pusher_data_fk_pusher FOREIGN KEY (pusher_id) REFERENCES pusher (id);


--
-- Name: pusher pusher_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY pusher
    ADD CONSTRAINT pusher_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: room_event_filter_not_rooms room_event_filter_fk_not_rooms; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_event_filter_not_rooms
    ADD CONSTRAINT room_event_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES room_event_filter (id);


--
-- Name: room_event_filter_not_senders room_event_filter_fk_not_senders; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_event_filter_not_senders
    ADD CONSTRAINT room_event_filter_fk_not_senders FOREIGN KEY (id) REFERENCES room_event_filter (id);


--
-- Name: room_event_filter_not_types room_event_filter_fk_not_types; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_event_filter_not_types
    ADD CONSTRAINT room_event_filter_fk_not_types FOREIGN KEY (id) REFERENCES room_event_filter (id);


--
-- Name: room_event_filter_rooms room_event_filter_fk_rooms; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_event_filter_rooms
    ADD CONSTRAINT room_event_filter_fk_rooms FOREIGN KEY (id) REFERENCES room_event_filter (id);


--
-- Name: room_event_filter_senders room_event_filter_fk_sender; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_event_filter_senders
    ADD CONSTRAINT room_event_filter_fk_sender FOREIGN KEY (id) REFERENCES room_event_filter (id);


--
-- Name: room_event_filter_types room_event_filter_fk_types; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_event_filter_types
    ADD CONSTRAINT room_event_filter_fk_types FOREIGN KEY (id) REFERENCES room_event_filter (id);


--
-- Name: room_filter room_filter_fk_account_data; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_account_data FOREIGN KEY (account_data_id) REFERENCES room_event_filter (id);


--
-- Name: room_filter room_filter_fk_ephemeral; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_ephemeral FOREIGN KEY (ephemeral_id) REFERENCES room_event_filter (id);


--
-- Name: room_filter_not_rooms room_filter_fk_not_rooms; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_filter_not_rooms
    ADD CONSTRAINT room_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES room_filter (id);


--
-- Name: room_filter_rooms room_filter_fk_rooms; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_filter_rooms
    ADD CONSTRAINT room_filter_fk_rooms FOREIGN KEY (id) REFERENCES room_filter (id);


--
-- Name: room_filter room_filter_fk_state; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_state FOREIGN KEY (state_id) REFERENCES state_filter (id);


--
-- Name: room_filter room_filter_fk_timeline; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_timeline FOREIGN KEY (timeline_id) REFERENCES room_event_filter (id);


--
-- Name: room room_fk_domain; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


--
-- Name: room room_fk_last_state; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room
    ADD CONSTRAINT room_fk_last_state FOREIGN KEY (latest_state_id) REFERENCES room_state_snapshot (id);


--
-- Name: room_report room_report_fk_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_report
    ADD CONSTRAINT room_report_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: room_report room_report_fk_room; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_report
    ADD CONSTRAINT room_report_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


--
-- Name: room_servers room_servers_fk_room; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_servers
    ADD CONSTRAINT room_servers_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


--
-- Name: room_state room_state_fk_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state
    ADD CONSTRAINT room_state_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: room_state room_state_fk_room_state; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state
    ADD CONSTRAINT room_state_fk_room_state FOREIGN KEY (room_state_id) REFERENCES room_state_snapshot (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_avatar; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_avatar FOREIGN KEY (avatar_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_canon_alias; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_canon_alias FOREIGN KEY (canonical_alias_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_encryption; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_encryption FOREIGN KEY (encryption_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_guest_access; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_guest_access FOREIGN KEY (guest_access_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_history; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_history FOREIGN KEY (history_visibility_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_initial; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_initial FOREIGN KEY (initial_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_join_rules; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_join_rules FOREIGN KEY (join_rules_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_name; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_name FOREIGN KEY (name_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_permissions; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_permissions FOREIGN KEY (permissions_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_prev; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_prev FOREIGN KEY (prev_id) REFERENCES room_state_snapshot (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_room; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_server_acl; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_server_acl FOREIGN KEY (server_acl_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_tombstone; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_tombstone FOREIGN KEY (tombstone_id) REFERENCES room_state (id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_topic; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_topic FOREIGN KEY (topic_id) REFERENCES room_state (id);


--
-- Name: room_transaction room_trans_fk_content; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_trans_fk_content FOREIGN KEY (content_id) REFERENCES event_content (id);


--
-- Name: room_transaction room_trans_fk_room; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_trans_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


--
-- Name: ruleset ruleset_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY ruleset
    ADD CONSTRAINT ruleset_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: send_to_device send_to_device_fk_content; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY send_to_device
    ADD CONSTRAINT send_to_device_fk_content FOREIGN KEY (content_id) REFERENCES event_content (id);


--
-- Name: signature sign_fk_event; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY signature
    ADD CONSTRAINT sign_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


--
-- Name: state_filter_not_rooms state_filter_fk_not_rooms; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY state_filter_not_rooms
    ADD CONSTRAINT state_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES state_filter (id);


--
-- Name: state_filter_not_senders state_filter_fk_not_senders; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY state_filter_not_senders
    ADD CONSTRAINT state_filter_fk_not_senders FOREIGN KEY (id) REFERENCES state_filter (id);


--
-- Name: state_filter_not_types state_filter_fk_not_types; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY state_filter_not_types
    ADD CONSTRAINT state_filter_fk_not_types FOREIGN KEY (id) REFERENCES state_filter (id);


--
-- Name: state_filter_rooms state_filter_fk_rooms; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY state_filter_rooms
    ADD CONSTRAINT state_filter_fk_rooms FOREIGN KEY (id) REFERENCES state_filter (id);


--
-- Name: state_filter_senders state_filter_fk_senders; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY state_filter_senders
    ADD CONSTRAINT state_filter_fk_senders FOREIGN KEY (id) REFERENCES state_filter (id);


--
-- Name: state_filter_types state_filter_fk_types; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY state_filter_types
    ADD CONSTRAINT state_filter_fk_types FOREIGN KEY (id) REFERENCES state_filter (id);


--
-- Name: tag tag_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: thumbnail thumbnail_fk_media; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY thumbnail
    ADD CONSTRAINT thumbnail_fk_media FOREIGN KEY (media_id) REFERENCES media (id);


--
-- Name: token token_fk_device; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY token
    ADD CONSTRAINT token_fk_device FOREIGN KEY (device_id) REFERENCES device (id);


--
-- Name: token token_fk_user; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY token
    ADD CONSTRAINT token_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


--
-- Name: user user_fk_auth; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_fk_auth FOREIGN KEY (auth_id) REFERENCES abstract_auth (id);


--
-- Name: user user_fk_domain; Type: FK CONSTRAINT;
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);
