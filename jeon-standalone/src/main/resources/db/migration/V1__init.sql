-- V1 Initialize


CREATE SEQUENCE abstract_auth_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE abstract_auth
(
    type character varying(32) NOT NULL,
    id   bigint                NOT NULL
);

ALTER TABLE ONLY abstract_auth
    ADD CONSTRAINT abstract_auth_pkey PRIMARY KEY (id);


CREATE SEQUENCE account_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE account_data
(
    id      bigint NOT NULL,
    content text,
    room    text,
    type    text,
    user_id bigint
);

ALTER TABLE ONLY account_data
    ADD CONSTRAINT account_data_constr_room_user_type UNIQUE (room, user_id, type);

ALTER TABLE ONLY account_data
    ADD CONSTRAINT account_data_pkey PRIMARY KEY (id);


CREATE SEQUENCE action_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE auth_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE auth_event
(
    id       bigint NOT NULL,
    auth_id  bigint,
    event_id bigint
);

ALTER TABLE ONLY auth_event
    ADD CONSTRAINT auth_event_pkey PRIMARY KEY (id);


CREATE SEQUENCE device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE device
(
    id      bigint NOT NULL,
    created timestamp with time zone,
    device  text,
    user_id bigint
);

ALTER TABLE ONLY device
    ADD CONSTRAINT device_constr_device_user UNIQUE (device, user_id);

ALTER TABLE ONLY device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);


CREATE SEQUENCE device_key_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_constr_device_user_algorithm UNIQUE (device_id, user_id, algorithm);

ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_pkey PRIMARY KEY (id);


CREATE TABLE domain
(
    domain text NOT NULL
);

ALTER TABLE ONLY domain
    ADD CONSTRAINT domain_pkey PRIMARY KEY (domain);


CREATE TABLE edu
(
    transaction_id bigint NOT NULL,
    event_id       bigint NOT NULL
);

ALTER TABLE ONLY edu
    ADD CONSTRAINT edu_pkey PRIMARY KEY (transaction_id, event_id);

ALTER TABLE ONLY edu
    ADD CONSTRAINT edu_constr_event_id UNIQUE (event_id);


CREATE SEQUENCE event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE event
(
    "DTYPE"       character varying(31) NOT NULL,
    id            bigint                NOT NULL,
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
    domain_id     text                  NOT NULL,
    redacts_id    bigint,
    room_id       bigint
);

ALTER TABLE ONLY event
    ADD CONSTRAINT event_constr_domain_event UNIQUE (domain_id, event_id);

ALTER TABLE ONLY event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


CREATE SEQUENCE event_content_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE event_content
(
    id      bigint NOT NULL,
    content text
);

ALTER TABLE ONLY event_content
    ADD CONSTRAINT event_content_pkey PRIMARY KEY (id);


CREATE SEQUENCE event_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE event_filter
(
    id bigint NOT NULL
);

ALTER TABLE ONLY event_filter
    ADD CONSTRAINT event_filter_pkey PRIMARY KEY (id);


CREATE TABLE event_filter_not_senders
(
    id          bigint NOT NULL,
    not_senders text
);


CREATE TABLE event_filter_not_types
(
    id        bigint NOT NULL,
    not_types text
);


CREATE TABLE event_filter_senders
(
    id      bigint NOT NULL,
    senders text
);


CREATE TABLE event_filter_types
(
    id    bigint NOT NULL,
    types text
);


CREATE TABLE event_graph
(
    parents_id  bigint NOT NULL,
    children_id bigint NOT NULL
);


CREATE SEQUENCE fed_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE federated_transaction
(
    id               bigint NOT NULL,
    origin_server    text,
    origin_server_ts timestamp with time zone,
    received         timestamp with time zone,
    txn_id           text,
    domain_id        text
);

ALTER TABLE ONLY federated_transaction
    ADD CONSTRAINT fed_trans_constr_txn_origin_domain UNIQUE (txn_id, origin_server, domain_id);

ALTER TABLE ONLY federated_transaction
    ADD CONSTRAINT federated_transaction_pkey PRIMARY KEY (id);


CREATE SEQUENCE filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE filter
(
    id              bigint NOT NULL,
    user_id         bigint,
    user_ordinal_id bigint,
    account_data_id bigint,
    presence_id     bigint,
    room_id         bigint
);

ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_constr_user_id UNIQUE (user_id, user_ordinal_id);

ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_pkey PRIMARY KEY (id);


CREATE TABLE filter_event_fields
(
    id           bigint NOT NULL,
    event_fields text
);


CREATE TABLE filter_event_format
(
    id           bigint NOT NULL,
    event_format text
);


CREATE SEQUENCE incoming_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE incoming_transaction
(
    id             bigint NOT NULL,
    parsed         timestamp with time zone,
    received       timestamp with time zone,
    domain_id      text,
    transaction_id bigint
);

ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT income_trans_constr_trans_domain UNIQUE (transaction_id, domain_id);

ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT incoming_transaction_pkey PRIMARY KEY (id);


CREATE SEQUENCE media_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY media
    ADD CONSTRAINT media_constr_media_domain UNIQUE (media_id, domain_id);

ALTER TABLE ONLY media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);


CREATE TABLE media_remote_addresses
(
    id             bigint NOT NULL,
    remote_address text
);


CREATE SEQUENCE notification_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


CREATE TABLE notification_actions
(
    notification_id bigint NOT NULL,
    action_id       bigint NOT NULL
);

ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT notification_actions_pkey PRIMARY KEY (notification_id, action_id);

ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT notification_actions_constr_action UNIQUE (action_id);


CREATE SEQUENCE one_time_key_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE one_time_key
(
    id        bigint NOT NULL,
    algorithm text,
    created   timestamp with time zone,
    key       text,
    key_id    text,
    user_id   bigint
);

ALTER TABLE ONLY one_time_key
    ADD CONSTRAINT one_time_key_constr_key_user_algorithm UNIQUE (key_id, user_id, algorithm);

ALTER TABLE ONLY one_time_key
    ADD CONSTRAINT one_time_key_pkey PRIMARY KEY (id);


CREATE SEQUENCE one_time_key_signature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE one_time_key_signature
(
    id           bigint NOT NULL,
    algorithm    text,
    signature    text,
    device_id    bigint,
    one_time_key bigint
);

ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_constr_key_device_algorithm UNIQUE (one_time_key, device_id, algorithm);

ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_signature_pkey PRIMARY KEY (id);


CREATE SEQUENCE openid_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY openid
    ADD CONSTRAINT openid_pkey PRIMARY KEY (id);


CREATE SEQUENCE outgoing_queue_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE outgoing_queue
(
    id                  bigint NOT NULL,
    created             timestamp with time zone,
    sent_to_transaction timestamp with time zone,
    target              text,
    event_id            bigint,
    transaction_id      bigint
);

ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outcome_queue_constr_event_target UNIQUE (event_id, target);


ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outgoing_queue_pkey PRIMARY KEY (id);


CREATE SEQUENCE outgoing_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outcome_trans_constr_trans_domain UNIQUE (transaction_id, domain_id);

ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outgoing_transaction_pkey PRIMARY KEY (id);


CREATE TABLE password_auth
(
    password text,
    id       bigint NOT NULL
);

ALTER TABLE ONLY password_auth
    ADD CONSTRAINT password_auth_pkey PRIMARY KEY (id);


CREATE TABLE pdu
(
    transaction_id bigint NOT NULL,
    event_id       bigint NOT NULL
);

ALTER TABLE ONLY pdu
    ADD CONSTRAINT pdu_pkey PRIMARY KEY (transaction_id, event_id);

ALTER TABLE ONLY pdu
    ADD CONSTRAINT pku_constr_event_id UNIQUE (event_id);


CREATE TABLE push_action
(
    type character varying(31) NOT NULL,
    id   bigint                NOT NULL
);

ALTER TABLE ONLY push_action
    ADD CONSTRAINT push_action_pkey PRIMARY KEY (id);


CREATE SEQUENCE push_condition_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE push_condition
(
    id      bigint NOT NULL,
    "is"    text,
    key     text,
    kind    text,
    pattern text,
    rule_id bigint
);

ALTER TABLE ONLY push_condition
    ADD CONSTRAINT push_condition_pkey PRIMARY KEY (id);


CREATE SEQUENCE push_rule_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE push_rule
(
    id         bigint NOT NULL,
    "default"  boolean,
    enabled    boolean,
    pattern    text,
    rule_id    text,
    ruleset_id bigint
);

ALTER TABLE ONLY push_rule
    ADD CONSTRAINT push_rule_pkey PRIMARY KEY (id);


CREATE TABLE push_rule_actions
(
    push_rule_id bigint NOT NULL,
    action_id    bigint NOT NULL
);

ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT push_rule_actions_pkey PRIMARY KEY (push_rule_id, action_id);

ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT push_rule_actions_constr_action_id UNIQUE (action_id);


CREATE SEQUENCE pusher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY pusher
    ADD CONSTRAINT pusher_constr_pushkey_user UNIQUE (pushkey, user_id);

ALTER TABLE ONLY pusher
    ADD CONSTRAINT pusher_pkey PRIMARY KEY (id);


CREATE SEQUENCE pusher_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE pusher_data
(
    id        bigint NOT NULL,
    format    text,
    url       text,
    pusher_id bigint
);

ALTER TABLE ONLY pusher_data
    ADD CONSTRAINT pusher_data_pkey PRIMARY KEY (id);


CREATE SEQUENCE redacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE redacts
(
    id     bigint NOT NULL,
    reason text,
    txn_id text
);

ALTER TABLE ONLY redacts
    ADD CONSTRAINT redacts_pkey PRIMARY KEY (id);


CREATE SEQUENCE room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY room
    ADD CONSTRAINT room_constr_room_domain UNIQUE (room_id, domain_id);

ALTER TABLE ONLY room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


CREATE SEQUENCE room_event_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE room_event_filter
(
    id           bigint NOT NULL,
    contains_url boolean,
    "limit"      bigint
);

ALTER TABLE ONLY room_event_filter
    ADD CONSTRAINT room_event_filter_pkey PRIMARY KEY (id);


CREATE TABLE room_event_filter_not_rooms
(
    id        bigint NOT NULL,
    not_rooms text
);


CREATE TABLE room_event_filter_not_senders
(
    id          bigint NOT NULL,
    not_senders text
);


CREATE TABLE room_event_filter_not_types
(
    id        bigint NOT NULL,
    not_types text
);


CREATE TABLE room_event_filter_rooms
(
    id    bigint NOT NULL,
    rooms text
);


CREATE TABLE room_event_filter_senders
(
    id      bigint NOT NULL,
    senders text
);


CREATE TABLE room_event_filter_types
(
    id    bigint NOT NULL,
    types text
);


CREATE SEQUENCE room_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_pkey PRIMARY KEY (id);


CREATE TABLE room_filter_not_rooms
(
    id        bigint NOT NULL,
    not_rooms text
);


CREATE TABLE room_filter_rooms
(
    id    bigint NOT NULL,
    rooms text
);


CREATE SEQUENCE room_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE room_report
(
    id       bigint NOT NULL,
    reason   text,
    score    bigint,
    event_id bigint,
    room_id  bigint
);

ALTER TABLE ONLY room_report
    ADD CONSTRAINT room_report_pkey PRIMARY KEY (id);


CREATE SEQUENCE room_server_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE room_servers
(
    id      bigint NOT NULL,
    server  text,
    room_id bigint
);

ALTER TABLE ONLY room_servers
    ADD CONSTRAINT room_server_constr_room_server UNIQUE (room_id, server);

ALTER TABLE ONLY room_servers
    ADD CONSTRAINT room_servers_pkey PRIMARY KEY (id);


CREATE SEQUENCE room_state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE room_state
(
    id            bigint NOT NULL,
    state_key     text,
    event_id      bigint,
    room_state_id bigint
);

ALTER TABLE ONLY room_state
    ADD CONSTRAINT room_state_pkey PRIMARY KEY (id);


CREATE SEQUENCE room_state_snapshot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_pkey PRIMARY KEY (id);


CREATE SEQUENCE room_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_trans_constr_txn_sender_room UNIQUE (txn_id, sender, room_id);

ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_transaction_pkey PRIMARY KEY (id);


CREATE SEQUENCE ruleset_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE ruleset
(
    id      bigint NOT NULL,
    user_id bigint
);

ALTER TABLE ONLY ruleset
    ADD CONSTRAINT ruleset_pkey PRIMARY KEY (id);


CREATE SEQUENCE send_to_device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE send_to_device
(
    id         bigint NOT NULL,
    device     text,
    "user"     text,
    content_id bigint
);

ALTER TABLE ONLY send_to_device
    ADD CONSTRAINT send_to_device_constr_user_device UNIQUE ("user", device);

ALTER TABLE ONLY send_to_device
    ADD CONSTRAINT send_to_device_pkey PRIMARY KEY (id);


CREATE SEQUENCE signature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE signature
(
    id        bigint NOT NULL,
    key       text,
    server    text,
    signature text,
    event_id  bigint
);

ALTER TABLE ONLY signature
    ADD CONSTRAINT signature_constr_event_server UNIQUE (event_id, server);

ALTER TABLE ONLY signature
    ADD CONSTRAINT signature_pkey PRIMARY KEY (id);


CREATE SEQUENCE state_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE state_filter
(
    id                        bigint NOT NULL,
    contains_url              boolean,
    include_redundant_members boolean,
    lazy_load_members         boolean,
    "limit"                   bigint
);

ALTER TABLE ONLY state_filter
    ADD CONSTRAINT state_filter_pkey PRIMARY KEY (id);


CREATE TABLE state_filter_not_rooms
(
    id        bigint NOT NULL,
    not_rooms text
);


CREATE TABLE state_filter_not_senders
(
    id          bigint NOT NULL,
    not_senders text
);


CREATE TABLE state_filter_not_types
(
    id        bigint NOT NULL,
    not_types text
);


CREATE TABLE state_filter_rooms
(
    id    bigint NOT NULL,
    rooms text
);


CREATE TABLE state_filter_senders
(
    id      bigint NOT NULL,
    senders text
);


CREATE TABLE state_filter_types
(
    id    bigint NOT NULL,
    types text
);


CREATE SEQUENCE tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE tag
(
    id      bigint NOT NULL,
    name    text,
    "order" double precision,
    user_id bigint
);

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


CREATE SEQUENCE thumbnail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY thumbnail
    ADD CONSTRAINT thumbnail_pkey PRIMARY KEY (id);


CREATE SEQUENCE token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY token
    ADD CONSTRAINT token_constr_device_user UNIQUE (device_id, user_id);

ALTER TABLE ONLY token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_constr_username_domain UNIQUE (username, domain_id);

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);







ALTER TABLE ONLY password_auth
    ADD CONSTRAINT password_auth_fk_abstract_auth FOREIGN KEY (id) REFERENCES abstract_auth (id);


ALTER TABLE ONLY account_data
    ADD CONSTRAINT account_data_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY auth_event
    ADD CONSTRAINT auh_event_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY auth_event
    ADD CONSTRAINT auth_event_fk_auth FOREIGN KEY (auth_id) REFERENCES room_state (id);


ALTER TABLE ONLY device
    ADD CONSTRAINT device_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_fk_device FOREIGN KEY (device_id) REFERENCES device (id);


ALTER TABLE ONLY device_key
    ADD CONSTRAINT device_key_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY event_filter_not_senders
    ADD CONSTRAINT event_filter_fk_not_senders FOREIGN KEY (id) REFERENCES event_filter (id);


ALTER TABLE ONLY event_filter_not_types
    ADD CONSTRAINT event_filter_fk_not_types FOREIGN KEY (id) REFERENCES event_filter (id);


ALTER TABLE ONLY event_filter_senders
    ADD CONSTRAINT event_filter_fk_senders FOREIGN KEY (id) REFERENCES event_filter (id);


ALTER TABLE ONLY event_filter_types
    ADD CONSTRAINT event_filter_fk_types FOREIGN KEY (id) REFERENCES event_filter (id);


ALTER TABLE ONLY event_graph
    ADD CONSTRAINT event_fk_children FOREIGN KEY (children_id) REFERENCES event (id);


ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_content FOREIGN KEY (content_id) REFERENCES event_content (id);


ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


ALTER TABLE ONLY event_graph
    ADD CONSTRAINT event_fk_parents FOREIGN KEY (parents_id) REFERENCES event (id);


ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_redacts FOREIGN KEY (redacts_id) REFERENCES redacts (id);


ALTER TABLE ONLY event
    ADD CONSTRAINT event_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


ALTER TABLE ONLY federated_transaction
    ADD CONSTRAINT fed_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


ALTER TABLE ONLY edu
    ADD CONSTRAINT fed_trans_fk_edu_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY edu
    ADD CONSTRAINT fed_trans_fk_edu_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


ALTER TABLE ONLY pdu
    ADD CONSTRAINT fed_trans_fk_pdu_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY pdu
    ADD CONSTRAINT fed_trans_fk_pdu_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_fk_account_data FOREIGN KEY (account_data_id) REFERENCES event_filter (id);


ALTER TABLE ONLY filter_event_fields
    ADD CONSTRAINT filter_fk_event_fields FOREIGN KEY (id) REFERENCES filter (id);


ALTER TABLE ONLY filter_event_format
    ADD CONSTRAINT filter_fk_event_format FOREIGN KEY (id) REFERENCES filter (id);


ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_fk_presence FOREIGN KEY (presence_id) REFERENCES event_filter (id);


ALTER TABLE ONLY filter
    ADD CONSTRAINT filter_fk_room FOREIGN KEY (room_id) REFERENCES room_filter (id);


ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT income_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


ALTER TABLE ONLY incoming_transaction
    ADD CONSTRAINT income_trans_fk_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


ALTER TABLE ONLY media
    ADD CONSTRAINT media_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


ALTER TABLE ONLY media_remote_addresses
    ADD CONSTRAINT media_fk_remote_addresses FOREIGN KEY (id) REFERENCES media (id);


ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT notif_fk_actions FOREIGN KEY (action_id) REFERENCES push_action (id);


ALTER TABLE ONLY notification
    ADD CONSTRAINT notif_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY notification_actions
    ADD CONSTRAINT notif_fk_notif FOREIGN KEY (notification_id) REFERENCES notification (id);


ALTER TABLE ONLY notification
    ADD CONSTRAINT notif_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY one_time_key
    ADD CONSTRAINT one_time_key_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_device FOREIGN KEY (device_id) REFERENCES device (id);


ALTER TABLE ONLY one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_fk_one_time_key FOREIGN KEY (one_time_key) REFERENCES one_time_key (id);


ALTER TABLE ONLY openid
    ADD CONSTRAINT openid_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outcome_queue_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY outgoing_queue
    ADD CONSTRAINT outcome_queue_fk_trans FOREIGN KEY (transaction_id) REFERENCES outgoing_transaction (id);


ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outcome_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


ALTER TABLE ONLY outgoing_transaction
    ADD CONSTRAINT outcome_trans_fk_trans FOREIGN KEY (transaction_id) REFERENCES federated_transaction (id);


ALTER TABLE ONLY push_condition
    ADD CONSTRAINT push_condition_fk_rule FOREIGN KEY (rule_id) REFERENCES push_rule (id);


ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT push_rule_fk_action FOREIGN KEY (action_id) REFERENCES push_action (id);


ALTER TABLE ONLY push_rule_actions
    ADD CONSTRAINT push_rule_fk_push_rule_actions FOREIGN KEY (push_rule_id) REFERENCES push_rule (id);


ALTER TABLE ONLY push_rule
    ADD CONSTRAINT push_rule_fk_ruleset FOREIGN KEY (ruleset_id) REFERENCES ruleset (id);


ALTER TABLE ONLY pusher_data
    ADD CONSTRAINT pusher_data_fk_pusher FOREIGN KEY (pusher_id) REFERENCES pusher (id);


ALTER TABLE ONLY pusher
    ADD CONSTRAINT pusher_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY room_event_filter_not_rooms
    ADD CONSTRAINT room_event_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_event_filter_not_senders
    ADD CONSTRAINT room_event_filter_fk_not_senders FOREIGN KEY (id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_event_filter_not_types
    ADD CONSTRAINT room_event_filter_fk_not_types FOREIGN KEY (id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_event_filter_rooms
    ADD CONSTRAINT room_event_filter_fk_rooms FOREIGN KEY (id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_event_filter_senders
    ADD CONSTRAINT room_event_filter_fk_sender FOREIGN KEY (id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_event_filter_types
    ADD CONSTRAINT room_event_filter_fk_types FOREIGN KEY (id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_account_data FOREIGN KEY (account_data_id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_ephemeral FOREIGN KEY (ephemeral_id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room_filter_not_rooms
    ADD CONSTRAINT room_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES room_filter (id);


ALTER TABLE ONLY room_filter_rooms
    ADD CONSTRAINT room_filter_fk_rooms FOREIGN KEY (id) REFERENCES room_filter (id);


ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_state FOREIGN KEY (state_id) REFERENCES state_filter (id);


ALTER TABLE ONLY room_filter
    ADD CONSTRAINT room_filter_fk_timeline FOREIGN KEY (timeline_id) REFERENCES room_event_filter (id);


ALTER TABLE ONLY room
    ADD CONSTRAINT room_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


ALTER TABLE ONLY room
    ADD CONSTRAINT room_fk_last_state FOREIGN KEY (latest_state_id) REFERENCES room_state_snapshot (id);


ALTER TABLE ONLY room_report
    ADD CONSTRAINT room_report_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY room_report
    ADD CONSTRAINT room_report_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


ALTER TABLE ONLY room_servers
    ADD CONSTRAINT room_servers_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


ALTER TABLE ONLY room_state
    ADD CONSTRAINT room_state_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY room_state
    ADD CONSTRAINT room_state_fk_room_state FOREIGN KEY (room_state_id) REFERENCES room_state_snapshot (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_avatar FOREIGN KEY (avatar_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_canon_alias FOREIGN KEY (canonical_alias_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_encryption FOREIGN KEY (encryption_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_guest_access FOREIGN KEY (guest_access_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_history FOREIGN KEY (history_visibility_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_initial FOREIGN KEY (initial_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_join_rules FOREIGN KEY (join_rules_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_name FOREIGN KEY (name_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_permissions FOREIGN KEY (permissions_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_prev FOREIGN KEY (prev_id) REFERENCES room_state_snapshot (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_server_acl FOREIGN KEY (server_acl_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_tombstone FOREIGN KEY (tombstone_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_topic FOREIGN KEY (topic_id) REFERENCES room_state (id);


ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_trans_fk_content FOREIGN KEY (content_id) REFERENCES event_content (id);


ALTER TABLE ONLY room_transaction
    ADD CONSTRAINT room_trans_fk_room FOREIGN KEY (room_id) REFERENCES room (id);


ALTER TABLE ONLY ruleset
    ADD CONSTRAINT ruleset_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY send_to_device
    ADD CONSTRAINT send_to_device_fk_content FOREIGN KEY (content_id) REFERENCES event_content (id);


ALTER TABLE ONLY signature
    ADD CONSTRAINT sign_fk_event FOREIGN KEY (event_id) REFERENCES event (id);


ALTER TABLE ONLY state_filter_not_rooms
    ADD CONSTRAINT state_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES state_filter (id);


ALTER TABLE ONLY state_filter_not_senders
    ADD CONSTRAINT state_filter_fk_not_senders FOREIGN KEY (id) REFERENCES state_filter (id);


ALTER TABLE ONLY state_filter_not_types
    ADD CONSTRAINT state_filter_fk_not_types FOREIGN KEY (id) REFERENCES state_filter (id);


ALTER TABLE ONLY state_filter_rooms
    ADD CONSTRAINT state_filter_fk_rooms FOREIGN KEY (id) REFERENCES state_filter (id);


ALTER TABLE ONLY state_filter_senders
    ADD CONSTRAINT state_filter_fk_senders FOREIGN KEY (id) REFERENCES state_filter (id);


ALTER TABLE ONLY state_filter_types
    ADD CONSTRAINT state_filter_fk_types FOREIGN KEY (id) REFERENCES state_filter (id);


ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY thumbnail
    ADD CONSTRAINT thumbnail_fk_media FOREIGN KEY (media_id) REFERENCES media (id);


ALTER TABLE ONLY token
    ADD CONSTRAINT token_fk_device FOREIGN KEY (device_id) REFERENCES device (id);


ALTER TABLE ONLY token
    ADD CONSTRAINT token_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_fk_auth FOREIGN KEY (auth_id) REFERENCES abstract_auth (id);


ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_fk_domain FOREIGN KEY (domain_id) REFERENCES domain (domain);


ALTER TABLE ONLY "filter"
    ADD CONSTRAINT filter_fk_user FOREIGN KEY (user_id) REFERENCES "user" (id);

