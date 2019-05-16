--
-- PostgreSQL database dump
--

-- Dumped from database version 11.3 (Debian 11.3-1)
-- Dumped by pg_dump version 11.3 (Debian 11.3-1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: abstract_auth; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.abstract_auth (
    type character varying(31) NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE public.abstract_auth OWNER TO jeon;

--
-- Name: abstract_auth_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.abstract_auth_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.abstract_auth_id_seq OWNER TO jeon;

--
-- Name: account_data; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.account_data (
    id bigint NOT NULL,
    content character varying(255),
    room character varying(255),
    type character varying(255),
    user_id bigint
);


ALTER TABLE public.account_data OWNER TO jeon;

--
-- Name: account_data_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.account_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_data_id_seq OWNER TO jeon;

--
-- Name: action_id; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.action_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.action_id OWNER TO jeon;

--
-- Name: auth_event; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.auth_event (
    id bigint NOT NULL,
    auth_id bigint,
    event_id bigint
);


ALTER TABLE public.auth_event OWNER TO jeon;

--
-- Name: auth_event_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.auth_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_event_id_seq OWNER TO jeon;

--
-- Name: device; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.device (
    id bigint NOT NULL,
    created timestamp without time zone,
    device character varying(255),
    user_id bigint
);


ALTER TABLE public.device OWNER TO jeon;

--
-- Name: device_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.device_id_seq OWNER TO jeon;

--
-- Name: device_key; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.device_key (
    id bigint NOT NULL,
    algorithm character varying(255),
    created timestamp without time zone,
    key character varying(255),
    signature character varying(255),
    device_id bigint,
    user_id bigint
);


ALTER TABLE public.device_key OWNER TO jeon;

--
-- Name: device_key_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.device_key_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.device_key_id_seq OWNER TO jeon;

--
-- Name: domain; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.domain (
    domain character varying(255) NOT NULL
);


ALTER TABLE public.domain OWNER TO jeon;

--
-- Name: edu; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.edu (
    transaction_id bigint NOT NULL,
    event_id bigint NOT NULL
);


ALTER TABLE public.edu OWNER TO jeon;

--
-- Name: event; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event (
    "DTYPE" character varying(31) NOT NULL,
    id bigint NOT NULL,
    created timestamp without time zone,
    depth bigint,
    event_id character varying(255),
    hash character varying(255),
    origin_server character varying(255),
    received timestamp without time zone,
    sender character varying(255),
    txn_id character varying(255),
    type character varying(255),
    content_id bigint,
    domain_id character varying(255) NOT NULL,
    redacts_id bigint,
    room_id bigint
);


ALTER TABLE public.event OWNER TO jeon;

--
-- Name: event_content; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event_content (
    id bigint NOT NULL,
    content character varying(255)
);


ALTER TABLE public.event_content OWNER TO jeon;

--
-- Name: event_content_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.event_content_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.event_content_id_seq OWNER TO jeon;

--
-- Name: event_filter; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event_filter (
    id bigint NOT NULL
);


ALTER TABLE public.event_filter OWNER TO jeon;

--
-- Name: event_filter_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.event_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.event_filter_id_seq OWNER TO jeon;

--
-- Name: event_filter_not_senders; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event_filter_not_senders (
    id bigint NOT NULL,
    not_senders character varying(255)
);


ALTER TABLE public.event_filter_not_senders OWNER TO jeon;

--
-- Name: event_filter_not_types; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event_filter_not_types (
    id bigint NOT NULL,
    not_types character varying(255)
);


ALTER TABLE public.event_filter_not_types OWNER TO jeon;

--
-- Name: event_filter_senders; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event_filter_senders (
    id bigint NOT NULL,
    senders character varying(255)
);


ALTER TABLE public.event_filter_senders OWNER TO jeon;

--
-- Name: event_filter_types; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event_filter_types (
    id bigint NOT NULL,
    types character varying(255)
);


ALTER TABLE public.event_filter_types OWNER TO jeon;

--
-- Name: event_graph; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.event_graph (
    parents_id bigint NOT NULL,
    children_id bigint NOT NULL
);


ALTER TABLE public.event_graph OWNER TO jeon;

--
-- Name: event_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.event_id_seq OWNER TO jeon;

--
-- Name: fed_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.fed_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fed_transaction_id_seq OWNER TO jeon;

--
-- Name: federated_transaction; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.federated_transaction (
    id bigint NOT NULL,
    origin_server character varying(255),
    origin_server_ts timestamp without time zone,
    received timestamp without time zone,
    txn_id character varying(255),
    domain_id character varying(255)
);


ALTER TABLE public.federated_transaction OWNER TO jeon;

--
-- Name: filter; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.filter (
    id bigint NOT NULL,
    account_data_id bigint,
    presence_id bigint,
    room_id bigint
);


ALTER TABLE public.filter OWNER TO jeon;

--
-- Name: filter_event_fields; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.filter_event_fields (
    id bigint NOT NULL,
    event_fields character varying(255)
);


ALTER TABLE public.filter_event_fields OWNER TO jeon;

--
-- Name: filter_event_format; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.filter_event_format (
    id bigint NOT NULL,
    event_format character varying(255)
);


ALTER TABLE public.filter_event_format OWNER TO jeon;

--
-- Name: filter_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.filter_id_seq OWNER TO jeon;

--
-- Name: incoming_transaction; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.incoming_transaction (
    id bigint NOT NULL,
    parsed timestamp without time zone,
    received timestamp without time zone,
    domain_id character varying(255),
    transaction_id bigint
);


ALTER TABLE public.incoming_transaction OWNER TO jeon;

--
-- Name: incoming_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.incoming_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.incoming_transaction_id_seq OWNER TO jeon;

--
-- Name: media; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.media (
    id bigint NOT NULL,
    allow_remote boolean,
    created timestamp without time zone,
    filename character varying(255),
    media_id character varying(255),
    path character varying(255),
    size bigint,
    domain_id character varying(255)
);


ALTER TABLE public.media OWNER TO jeon;

--
-- Name: media_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.media_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.media_id_seq OWNER TO jeon;

--
-- Name: media_remote_addresses; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.media_remote_addresses (
    id bigint NOT NULL,
    remote_address character varying(255)
);


ALTER TABLE public.media_remote_addresses OWNER TO jeon;

--
-- Name: notification; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.notification (
    id bigint NOT NULL,
    profile_tag character varying(255),
    read boolean,
    room_id character varying(255),
    ts bigint,
    event_id bigint,
    user_id bigint
);


ALTER TABLE public.notification OWNER TO jeon;

--
-- Name: notification_actions; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.notification_actions (
    notification_id bigint NOT NULL,
    action_id bigint NOT NULL
);


ALTER TABLE public.notification_actions OWNER TO jeon;

--
-- Name: notification_id; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.notification_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notification_id OWNER TO jeon;

--
-- Name: one_time_key; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.one_time_key (
    id bigint NOT NULL,
    algorithm character varying(255),
    created timestamp without time zone,
    key character varying(255),
    key_id character varying(255),
    user_id bigint
);


ALTER TABLE public.one_time_key OWNER TO jeon;

--
-- Name: one_time_key_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.one_time_key_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.one_time_key_id_seq OWNER TO jeon;

--
-- Name: one_time_key_signature; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.one_time_key_signature (
    id bigint NOT NULL,
    algorithm character varying(255),
    signature character varying(255),
    device_id bigint,
    one_time_key bigint
);


ALTER TABLE public.one_time_key_signature OWNER TO jeon;

--
-- Name: one_time_key_signature_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.one_time_key_signature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.one_time_key_signature_id_seq OWNER TO jeon;

--
-- Name: openid; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.openid (
    id bigint NOT NULL,
    access_token character varying(255),
    created timestamp without time zone,
    expires timestamp without time zone,
    server_name character varying(255),
    token_type character varying(255),
    user_id bigint
);


ALTER TABLE public.openid OWNER TO jeon;

--
-- Name: openid_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.openid_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.openid_id_seq OWNER TO jeon;

--
-- Name: outgoing_queue; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.outgoing_queue (
    id bigint NOT NULL,
    created timestamp without time zone,
    sent_to_transaction timestamp without time zone,
    target character varying(255),
    event_id bigint,
    transaction_id bigint
);


ALTER TABLE public.outgoing_queue OWNER TO jeon;

--
-- Name: outgoing_queue_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.outgoing_queue_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.outgoing_queue_id_seq OWNER TO jeon;

--
-- Name: outgoing_transaction; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.outgoing_transaction (
    id bigint NOT NULL,
    attempt_message character varying(255),
    created timestamp without time zone,
    sending_attempt integer,
    sent timestamp without time zone,
    target character varying(255),
    domain_id character varying(255),
    transaction_id bigint
);


ALTER TABLE public.outgoing_transaction OWNER TO jeon;

--
-- Name: outgoing_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.outgoing_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.outgoing_transaction_id_seq OWNER TO jeon;

--
-- Name: password_auth; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.password_auth (
    password character varying(255),
    id bigint NOT NULL
);


ALTER TABLE public.password_auth OWNER TO jeon;

--
-- Name: pdu; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.pdu (
    transaction_id bigint NOT NULL,
    event_id bigint NOT NULL
);


ALTER TABLE public.pdu OWNER TO jeon;

--
-- Name: push_action; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.push_action (
    type character varying(31) NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE public.push_action OWNER TO jeon;

--
-- Name: push_condition; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.push_condition (
    id bigint NOT NULL,
    "is" character varying(255),
    key character varying(255),
    kind character varying(255),
    pattern character varying(255),
    rule_id bigint
);


ALTER TABLE public.push_condition OWNER TO jeon;

--
-- Name: push_condition_id; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.push_condition_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.push_condition_id OWNER TO jeon;

--
-- Name: push_rule; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.push_rule (
    id bigint NOT NULL,
    "default" boolean,
    enabled boolean,
    pattern character varying(255),
    rule_id character varying(255),
    ruleset_id bigint
);


ALTER TABLE public.push_rule OWNER TO jeon;

--
-- Name: push_rule_actions; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.push_rule_actions (
    push_rule_id bigint NOT NULL,
    action_id bigint NOT NULL
);


ALTER TABLE public.push_rule_actions OWNER TO jeon;

--
-- Name: push_rule_id; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.push_rule_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.push_rule_id OWNER TO jeon;

--
-- Name: pusher; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.pusher (
    id bigint NOT NULL,
    app_display_name character varying(255),
    app_id character varying(255),
    device_display_name character varying(255),
    kind character varying(255),
    lang character varying(255),
    profile_tag character varying(255),
    pushkey character varying(255),
    user_id bigint
);


ALTER TABLE public.pusher OWNER TO jeon;

--
-- Name: pusher_data; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.pusher_data (
    id bigint NOT NULL,
    format character varying(255),
    url character varying(255),
    pusher_id bigint
);


ALTER TABLE public.pusher_data OWNER TO jeon;

--
-- Name: pusher_data_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.pusher_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pusher_data_id_seq OWNER TO jeon;

--
-- Name: pusher_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.pusher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pusher_id_seq OWNER TO jeon;

--
-- Name: redacts; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.redacts (
    id bigint NOT NULL,
    reason character varying(255),
    txn_id character varying(255)
);


ALTER TABLE public.redacts OWNER TO jeon;

--
-- Name: redacts_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.redacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.redacts_id_seq OWNER TO jeon;

--
-- Name: room; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room (
    id bigint NOT NULL,
    created timestamp without time zone,
    room_id character varying(255),
    version character varying(255),
    visible boolean,
    domain_id character varying(255),
    latest_state_id bigint
);


ALTER TABLE public.room OWNER TO jeon;

--
-- Name: room_event_filter; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_event_filter (
    id bigint NOT NULL,
    contains_url boolean,
    "limit" bigint
);


ALTER TABLE public.room_event_filter OWNER TO jeon;

--
-- Name: room_event_filter_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_event_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_event_filter_id_seq OWNER TO jeon;

--
-- Name: room_event_filter_not_rooms; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_event_filter_not_rooms (
    id bigint NOT NULL,
    not_rooms character varying(255)
);


ALTER TABLE public.room_event_filter_not_rooms OWNER TO jeon;

--
-- Name: room_event_filter_not_senders; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_event_filter_not_senders (
    id bigint NOT NULL,
    not_senders character varying(255)
);


ALTER TABLE public.room_event_filter_not_senders OWNER TO jeon;

--
-- Name: room_event_filter_not_types; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_event_filter_not_types (
    id bigint NOT NULL,
    not_types character varying(255)
);


ALTER TABLE public.room_event_filter_not_types OWNER TO jeon;

--
-- Name: room_event_filter_rooms; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_event_filter_rooms (
    id bigint NOT NULL,
    rooms character varying(255)
);


ALTER TABLE public.room_event_filter_rooms OWNER TO jeon;

--
-- Name: room_event_filter_senders; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_event_filter_senders (
    id bigint NOT NULL,
    senders character varying(255)
);


ALTER TABLE public.room_event_filter_senders OWNER TO jeon;

--
-- Name: room_event_filter_types; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_event_filter_types (
    id bigint NOT NULL,
    types character varying(255)
);


ALTER TABLE public.room_event_filter_types OWNER TO jeon;

--
-- Name: room_filter; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_filter (
    id bigint NOT NULL,
    include_leave boolean,
    "limit" bigint,
    account_data_id bigint,
    ephemeral_id bigint,
    state_id bigint,
    timeline_id bigint
);


ALTER TABLE public.room_filter OWNER TO jeon;

--
-- Name: room_filter_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_filter_id_seq OWNER TO jeon;

--
-- Name: room_filter_not_rooms; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_filter_not_rooms (
    id bigint NOT NULL,
    not_rooms character varying(255)
);


ALTER TABLE public.room_filter_not_rooms OWNER TO jeon;

--
-- Name: room_filter_rooms; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_filter_rooms (
    id bigint NOT NULL,
    rooms character varying(255)
);


ALTER TABLE public.room_filter_rooms OWNER TO jeon;

--
-- Name: room_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_id_seq OWNER TO jeon;

--
-- Name: room_report; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_report (
    id bigint NOT NULL,
    reason character varying(255),
    score bigint,
    event_id bigint,
    room_id bigint
);


ALTER TABLE public.room_report OWNER TO jeon;

--
-- Name: room_report_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_report_id_seq OWNER TO jeon;

--
-- Name: room_server_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_server_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_server_id_seq OWNER TO jeon;

--
-- Name: room_servers; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_servers (
    id bigint NOT NULL,
    server character varying(255),
    room_id bigint
);


ALTER TABLE public.room_servers OWNER TO jeon;

--
-- Name: room_state; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_state (
    id bigint NOT NULL,
    state_key character varying(255),
    event_id bigint,
    room_state_id bigint
);


ALTER TABLE public.room_state OWNER TO jeon;

--
-- Name: room_state_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_state_id_seq OWNER TO jeon;

--
-- Name: room_state_snapshot; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_state_snapshot (
    id bigint NOT NULL,
    created timestamp without time zone,
    avatar_id bigint,
    canonical_alias_id bigint,
    encryption_id bigint,
    guest_access_id bigint,
    history_visibility_id bigint,
    initial_id bigint,
    join_rules_id bigint,
    name_id bigint,
    permissions_id bigint,
    prev_id bigint,
    room_id bigint,
    server_acl_id bigint,
    tombstone_id bigint,
    topic_id bigint
);


ALTER TABLE public.room_state_snapshot OWNER TO jeon;

--
-- Name: room_state_snapshot_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_state_snapshot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_state_snapshot_id_seq OWNER TO jeon;

--
-- Name: room_transaction; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.room_transaction (
    id bigint NOT NULL,
    created timestamp without time zone,
    sender character varying(255),
    txn_id character varying(255),
    type character varying(255),
    content_id bigint,
    room_id bigint
);


ALTER TABLE public.room_transaction OWNER TO jeon;

--
-- Name: room_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.room_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_transaction_id_seq OWNER TO jeon;

--
-- Name: ruleset; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.ruleset (
    id bigint NOT NULL,
    user_id bigint
);


ALTER TABLE public.ruleset OWNER TO jeon;

--
-- Name: ruleset_id; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.ruleset_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ruleset_id OWNER TO jeon;

--
-- Name: send_to_device; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.send_to_device (
    id bigint NOT NULL,
    device character varying(255),
    "user" character varying(255),
    content_id bigint
);


ALTER TABLE public.send_to_device OWNER TO jeon;

--
-- Name: send_to_device_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.send_to_device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.send_to_device_id_seq OWNER TO jeon;

--
-- Name: signature; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.signature (
    id bigint NOT NULL,
    key character varying(255),
    server character varying(255),
    signature character varying(255),
    event_id bigint
);


ALTER TABLE public.signature OWNER TO jeon;

--
-- Name: signature_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.signature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.signature_id_seq OWNER TO jeon;

--
-- Name: state_filter; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.state_filter (
    id bigint NOT NULL,
    contains_url boolean,
    include_redundant_members boolean,
    lazy_load_members boolean,
    "limit" bigint
);


ALTER TABLE public.state_filter OWNER TO jeon;

--
-- Name: state_filter_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.state_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.state_filter_id_seq OWNER TO jeon;

--
-- Name: state_filter_not_rooms; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.state_filter_not_rooms (
    id bigint NOT NULL,
    not_rooms character varying(255)
);


ALTER TABLE public.state_filter_not_rooms OWNER TO jeon;

--
-- Name: state_filter_not_senders; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.state_filter_not_senders (
    id bigint NOT NULL,
    not_senders character varying(255)
);


ALTER TABLE public.state_filter_not_senders OWNER TO jeon;

--
-- Name: state_filter_not_types; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.state_filter_not_types (
    id bigint NOT NULL,
    not_types character varying(255)
);


ALTER TABLE public.state_filter_not_types OWNER TO jeon;

--
-- Name: state_filter_rooms; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.state_filter_rooms (
    id bigint NOT NULL,
    rooms character varying(255)
);


ALTER TABLE public.state_filter_rooms OWNER TO jeon;

--
-- Name: state_filter_senders; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.state_filter_senders (
    id bigint NOT NULL,
    senders character varying(255)
);


ALTER TABLE public.state_filter_senders OWNER TO jeon;

--
-- Name: state_filter_types; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.state_filter_types (
    id bigint NOT NULL,
    types character varying(255)
);


ALTER TABLE public.state_filter_types OWNER TO jeon;

--
-- Name: tag; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.tag (
    id bigint NOT NULL,
    name character varying(255),
    "order" double precision,
    user_id bigint
);


ALTER TABLE public.tag OWNER TO jeon;

--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tag_id_seq OWNER TO jeon;

--
-- Name: thumbnail; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.thumbnail (
    id bigint NOT NULL,
    allow_remote boolean,
    created timestamp without time zone,
    height bigint,
    method character varying(255),
    path character varying(255),
    size bigint,
    width bigint,
    media_id bigint
);


ALTER TABLE public.thumbnail OWNER TO jeon;

--
-- Name: thumbnail_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.thumbnail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.thumbnail_id_seq OWNER TO jeon;

--
-- Name: token; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public.token (
    id bigint NOT NULL,
    created timestamp without time zone,
    expires timestamp without time zone,
    last_seen timestamp without time zone,
    last_seen_ip character varying(255),
    token character varying(255),
    device_id bigint,
    user_id bigint
);


ALTER TABLE public.token OWNER TO jeon;

--
-- Name: token_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.token_id_seq OWNER TO jeon;

--
-- Name: user; Type: TABLE; Schema: public; Owner: jeon
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    admin boolean,
    created timestamp without time zone,
    currently_active boolean,
    last_active_ago timestamp without time zone,
    presence character varying(255),
    state_msg character varying(255),
    username character varying(255),
    auth_id bigint,
    domain_id character varying(255)
);


ALTER TABLE public."user" OWNER TO jeon;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: jeon
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO jeon;

--
-- Name: abstract_auth abstract_auth_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.abstract_auth
    ADD CONSTRAINT abstract_auth_pkey PRIMARY KEY (id);


--
-- Name: account_data account_data_constr_room_user_type; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.account_data
    ADD CONSTRAINT account_data_constr_room_user_type UNIQUE (room, user_id, type);


--
-- Name: account_data account_data_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.account_data
    ADD CONSTRAINT account_data_pkey PRIMARY KEY (id);


--
-- Name: auth_event auth_event_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.auth_event
    ADD CONSTRAINT auth_event_pkey PRIMARY KEY (id);


--
-- Name: device device_constr_device_user; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_constr_device_user UNIQUE (device, user_id);


--
-- Name: device_key device_key_constr_device_user_algorithm; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.device_key
    ADD CONSTRAINT device_key_constr_device_user_algorithm UNIQUE (device_id, user_id, algorithm);


--
-- Name: device_key device_key_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.device_key
    ADD CONSTRAINT device_key_pkey PRIMARY KEY (id);


--
-- Name: device device_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);


--
-- Name: domain domain_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.domain
    ADD CONSTRAINT domain_pkey PRIMARY KEY (domain);


--
-- Name: edu edu_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.edu
    ADD CONSTRAINT edu_pkey PRIMARY KEY (transaction_id, event_id);


--
-- Name: event event_constr_domain_event; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_constr_domain_event UNIQUE (domain_id, event_id);


--
-- Name: event_content event_content_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_content
    ADD CONSTRAINT event_content_pkey PRIMARY KEY (id);


--
-- Name: event_filter event_filter_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_filter
    ADD CONSTRAINT event_filter_pkey PRIMARY KEY (id);


--
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- Name: federated_transaction fed_trans_constr_txn_origin_domain; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.federated_transaction
    ADD CONSTRAINT fed_trans_constr_txn_origin_domain UNIQUE (txn_id, origin_server, domain_id);


--
-- Name: federated_transaction federated_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.federated_transaction
    ADD CONSTRAINT federated_transaction_pkey PRIMARY KEY (id);


--
-- Name: filter filter_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.filter
    ADD CONSTRAINT filter_pkey PRIMARY KEY (id);


--
-- Name: incoming_transaction income_trans_constr_trans_domain; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.incoming_transaction
    ADD CONSTRAINT income_trans_constr_trans_domain UNIQUE (transaction_id, domain_id);


--
-- Name: incoming_transaction incoming_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.incoming_transaction
    ADD CONSTRAINT incoming_transaction_pkey PRIMARY KEY (id);


--
-- Name: media media_constr_media_domain; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_constr_media_domain UNIQUE (media_id, domain_id);


--
-- Name: media media_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);


--
-- Name: notification_actions notification_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.notification_actions
    ADD CONSTRAINT notification_actions_pkey PRIMARY KEY (notification_id, action_id);


--
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- Name: one_time_key one_time_key_constr_key_user_algorithm; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.one_time_key
    ADD CONSTRAINT one_time_key_constr_key_user_algorithm UNIQUE (key_id, user_id, algorithm);


--
-- Name: one_time_key one_time_key_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.one_time_key
    ADD CONSTRAINT one_time_key_pkey PRIMARY KEY (id);


--
-- Name: one_time_key_signature one_time_key_sign_constr_key_device_algorithm; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_constr_key_device_algorithm UNIQUE (one_time_key, device_id, algorithm);


--
-- Name: one_time_key_signature one_time_key_signature_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.one_time_key_signature
    ADD CONSTRAINT one_time_key_signature_pkey PRIMARY KEY (id);


--
-- Name: openid openid_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.openid
    ADD CONSTRAINT openid_pkey PRIMARY KEY (id);


--
-- Name: outgoing_queue outcome_queue_constr_event_target; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_queue
    ADD CONSTRAINT outcome_queue_constr_event_target UNIQUE (event_id, target);


--
-- Name: outgoing_transaction outcome_trans_constr_trans_domain; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_transaction
    ADD CONSTRAINT outcome_trans_constr_trans_domain UNIQUE (transaction_id, domain_id);


--
-- Name: outgoing_queue outgoing_queue_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_queue
    ADD CONSTRAINT outgoing_queue_pkey PRIMARY KEY (id);


--
-- Name: outgoing_transaction outgoing_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_transaction
    ADD CONSTRAINT outgoing_transaction_pkey PRIMARY KEY (id);


--
-- Name: password_auth password_auth_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.password_auth
    ADD CONSTRAINT password_auth_pkey PRIMARY KEY (id);


--
-- Name: pdu pdu_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pdu
    ADD CONSTRAINT pdu_pkey PRIMARY KEY (transaction_id, event_id);


--
-- Name: push_action push_action_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_action
    ADD CONSTRAINT push_action_pkey PRIMARY KEY (id);


--
-- Name: push_condition push_condition_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_condition
    ADD CONSTRAINT push_condition_pkey PRIMARY KEY (id);


--
-- Name: push_rule_actions push_rule_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_rule_actions
    ADD CONSTRAINT push_rule_actions_pkey PRIMARY KEY (push_rule_id, action_id);


--
-- Name: push_rule push_rule_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_rule
    ADD CONSTRAINT push_rule_pkey PRIMARY KEY (id);


--
-- Name: pusher pusher_constr_pushkey_user; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pusher
    ADD CONSTRAINT pusher_constr_pushkey_user UNIQUE (pushkey, user_id);


--
-- Name: pusher_data pusher_data_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pusher_data
    ADD CONSTRAINT pusher_data_pkey PRIMARY KEY (id);


--
-- Name: pusher pusher_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pusher
    ADD CONSTRAINT pusher_pkey PRIMARY KEY (id);


--
-- Name: redacts redacts_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.redacts
    ADD CONSTRAINT redacts_pkey PRIMARY KEY (id);


--
-- Name: room room_constr_room_domain; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_constr_room_domain UNIQUE (room_id, domain_id);


--
-- Name: room_event_filter room_event_filter_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_event_filter
    ADD CONSTRAINT room_event_filter_pkey PRIMARY KEY (id);


--
-- Name: room_filter room_filter_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_filter
    ADD CONSTRAINT room_filter_pkey PRIMARY KEY (id);


--
-- Name: room room_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- Name: room_report room_report_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_report
    ADD CONSTRAINT room_report_pkey PRIMARY KEY (id);


--
-- Name: room_servers room_server_constr_room_server; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_servers
    ADD CONSTRAINT room_server_constr_room_server UNIQUE (room_id, server);


--
-- Name: room_servers room_servers_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_servers
    ADD CONSTRAINT room_servers_pkey PRIMARY KEY (id);


--
-- Name: room_state room_state_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state
    ADD CONSTRAINT room_state_pkey PRIMARY KEY (id);


--
-- Name: room_state_snapshot room_state_snapshot_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_pkey PRIMARY KEY (id);


--
-- Name: room_transaction room_trans_constr_txn_sender_room; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_transaction
    ADD CONSTRAINT room_trans_constr_txn_sender_room UNIQUE (txn_id, sender, room_id);


--
-- Name: room_transaction room_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_transaction
    ADD CONSTRAINT room_transaction_pkey PRIMARY KEY (id);


--
-- Name: ruleset ruleset_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.ruleset
    ADD CONSTRAINT ruleset_pkey PRIMARY KEY (id);


--
-- Name: send_to_device send_to_device_constr_user_device; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.send_to_device
    ADD CONSTRAINT send_to_device_constr_user_device UNIQUE ("user", device);


--
-- Name: send_to_device send_to_device_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.send_to_device
    ADD CONSTRAINT send_to_device_pkey PRIMARY KEY (id);


--
-- Name: signature signature_constr_event_server; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.signature
    ADD CONSTRAINT signature_constr_event_server UNIQUE (event_id, server);


--
-- Name: signature signature_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.signature
    ADD CONSTRAINT signature_pkey PRIMARY KEY (id);


--
-- Name: state_filter state_filter_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.state_filter
    ADD CONSTRAINT state_filter_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: thumbnail thumbnail_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.thumbnail
    ADD CONSTRAINT thumbnail_pkey PRIMARY KEY (id);


--
-- Name: token token_constr_device_user; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_constr_device_user UNIQUE (device_id, user_id);


--
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- Name: pdu uk_7cni9gjcr544pbxfledxijh6q; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pdu
    ADD CONSTRAINT uk_7cni9gjcr544pbxfledxijh6q UNIQUE (event_id);


--
-- Name: push_rule_actions uk_csfqy99gnjq2pvq3h5s1aowgm; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_rule_actions
    ADD CONSTRAINT uk_csfqy99gnjq2pvq3h5s1aowgm UNIQUE (action_id);


--
-- Name: edu uk_n4waslk9fpgow8hopxiwkci70; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.edu
    ADD CONSTRAINT uk_n4waslk9fpgow8hopxiwkci70 UNIQUE (event_id);


--
-- Name: notification_actions uk_qahnmi95a3alxbddcrvf88blj; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.notification_actions
    ADD CONSTRAINT uk_qahnmi95a3alxbddcrvf88blj UNIQUE (action_id);


--
-- Name: user user_constr_username_domain; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_constr_username_domain UNIQUE (username, domain_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: password_auth FKppfos629e04vpuox0cpxmvf98; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.password_auth
    ADD CONSTRAINT "FKppfos629e04vpuox0cpxmvf98" FOREIGN KEY (id) REFERENCES public.abstract_auth(id);


--
-- Name: account_data account_data_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.account_data
    ADD CONSTRAINT account_data_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: auth_event auh_event_fk_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.auth_event
    ADD CONSTRAINT auh_event_fk_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: auth_event auth_event_fk_auth; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.auth_event
    ADD CONSTRAINT auth_event_fk_auth FOREIGN KEY (auth_id) REFERENCES public.room_state(id);


--
-- Name: device device_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: device_key device_key_fk_device; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.device_key
    ADD CONSTRAINT device_key_fk_device FOREIGN KEY (device_id) REFERENCES public.device(id);


--
-- Name: device_key device_key_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.device_key
    ADD CONSTRAINT device_key_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: event_filter_not_senders event_filter_fk_not_senders; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_filter_not_senders
    ADD CONSTRAINT event_filter_fk_not_senders FOREIGN KEY (id) REFERENCES public.event_filter(id);


--
-- Name: event_filter_not_types event_filter_fk_not_types; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_filter_not_types
    ADD CONSTRAINT event_filter_fk_not_types FOREIGN KEY (id) REFERENCES public.event_filter(id);


--
-- Name: event_filter_senders event_filter_fk_senders; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_filter_senders
    ADD CONSTRAINT event_filter_fk_senders FOREIGN KEY (id) REFERENCES public.event_filter(id);


--
-- Name: event_filter_types event_filter_fk_types; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_filter_types
    ADD CONSTRAINT event_filter_fk_types FOREIGN KEY (id) REFERENCES public.event_filter(id);


--
-- Name: event_graph event_fk_children; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_graph
    ADD CONSTRAINT event_fk_children FOREIGN KEY (children_id) REFERENCES public.event(id);


--
-- Name: event event_fk_content; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk_content FOREIGN KEY (content_id) REFERENCES public.event_content(id);


--
-- Name: event event_fk_domain; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk_domain FOREIGN KEY (domain_id) REFERENCES public.domain(domain);


--
-- Name: event_graph event_fk_parents; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event_graph
    ADD CONSTRAINT event_fk_parents FOREIGN KEY (parents_id) REFERENCES public.event(id);


--
-- Name: event event_fk_redacts; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk_redacts FOREIGN KEY (redacts_id) REFERENCES public.redacts(id);


--
-- Name: event event_fk_room; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_fk_room FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: federated_transaction fed_trans_fk_domain; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.federated_transaction
    ADD CONSTRAINT fed_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES public.domain(domain);


--
-- Name: edu fed_trans_fk_edu_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.edu
    ADD CONSTRAINT fed_trans_fk_edu_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: edu fed_trans_fk_edu_trans; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.edu
    ADD CONSTRAINT fed_trans_fk_edu_trans FOREIGN KEY (transaction_id) REFERENCES public.federated_transaction(id);


--
-- Name: pdu fed_trans_fk_pdu_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pdu
    ADD CONSTRAINT fed_trans_fk_pdu_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: pdu fed_trans_fk_pdu_trans; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pdu
    ADD CONSTRAINT fed_trans_fk_pdu_trans FOREIGN KEY (transaction_id) REFERENCES public.federated_transaction(id);


--
-- Name: filter filter_fk_account_data; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.filter
    ADD CONSTRAINT filter_fk_account_data FOREIGN KEY (account_data_id) REFERENCES public.event_filter(id);


--
-- Name: filter_event_fields filter_fk_event_fields; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.filter_event_fields
    ADD CONSTRAINT filter_fk_event_fields FOREIGN KEY (id) REFERENCES public.filter(id);


--
-- Name: filter_event_format filter_fk_event_format; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.filter_event_format
    ADD CONSTRAINT filter_fk_event_format FOREIGN KEY (id) REFERENCES public.filter(id);


--
-- Name: filter filter_fk_presence; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.filter
    ADD CONSTRAINT filter_fk_presence FOREIGN KEY (presence_id) REFERENCES public.event_filter(id);


--
-- Name: filter filter_fk_room; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.filter
    ADD CONSTRAINT filter_fk_room FOREIGN KEY (room_id) REFERENCES public.room_filter(id);


--
-- Name: incoming_transaction income_trans_fk_domain; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.incoming_transaction
    ADD CONSTRAINT income_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES public.domain(domain);


--
-- Name: incoming_transaction income_trans_fk_trans; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.incoming_transaction
    ADD CONSTRAINT income_trans_fk_trans FOREIGN KEY (transaction_id) REFERENCES public.federated_transaction(id);


--
-- Name: media media_fk_domain; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_fk_domain FOREIGN KEY (domain_id) REFERENCES public.domain(domain);


--
-- Name: media_remote_addresses media_fk_remote_addresses; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.media_remote_addresses
    ADD CONSTRAINT media_fk_remote_addresses FOREIGN KEY (id) REFERENCES public.media(id);


--
-- Name: notification_actions notif_fk_actions; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.notification_actions
    ADD CONSTRAINT notif_fk_actions FOREIGN KEY (action_id) REFERENCES public.push_action(id);


--
-- Name: notification notif_fk_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notif_fk_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: notification_actions notif_fk_notif; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.notification_actions
    ADD CONSTRAINT notif_fk_notif FOREIGN KEY (notification_id) REFERENCES public.notification(id);


--
-- Name: notification notif_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notif_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: one_time_key one_time_key_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.one_time_key
    ADD CONSTRAINT one_time_key_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: one_time_key_signature one_time_key_sign_device; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_device FOREIGN KEY (device_id) REFERENCES public.device(id);


--
-- Name: one_time_key_signature one_time_key_sign_fk_one_time_key; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.one_time_key_signature
    ADD CONSTRAINT one_time_key_sign_fk_one_time_key FOREIGN KEY (one_time_key) REFERENCES public.one_time_key(id);


--
-- Name: openid openid_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.openid
    ADD CONSTRAINT openid_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: outgoing_queue outcome_queue_fk_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_queue
    ADD CONSTRAINT outcome_queue_fk_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: outgoing_queue outcome_queue_fk_trans; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_queue
    ADD CONSTRAINT outcome_queue_fk_trans FOREIGN KEY (transaction_id) REFERENCES public.outgoing_transaction(id);


--
-- Name: outgoing_transaction outcome_trans_fk_domain; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_transaction
    ADD CONSTRAINT outcome_trans_fk_domain FOREIGN KEY (domain_id) REFERENCES public.domain(domain);


--
-- Name: outgoing_transaction outcome_trans_fk_trans; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.outgoing_transaction
    ADD CONSTRAINT outcome_trans_fk_trans FOREIGN KEY (transaction_id) REFERENCES public.federated_transaction(id);


--
-- Name: push_condition push_condition_fk_rule; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_condition
    ADD CONSTRAINT push_condition_fk_rule FOREIGN KEY (rule_id) REFERENCES public.push_rule(id);


--
-- Name: push_rule_actions push_fule_fk_action; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_rule_actions
    ADD CONSTRAINT push_fule_fk_action FOREIGN KEY (action_id) REFERENCES public.push_action(id);


--
-- Name: push_rule_actions push_rule_fk_push_rule_actions; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_rule_actions
    ADD CONSTRAINT push_rule_fk_push_rule_actions FOREIGN KEY (push_rule_id) REFERENCES public.push_rule(id);


--
-- Name: push_rule push_rule_fk_ruleset; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.push_rule
    ADD CONSTRAINT push_rule_fk_ruleset FOREIGN KEY (ruleset_id) REFERENCES public.ruleset(id);


--
-- Name: pusher_data pusher_data_fk_pusher; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pusher_data
    ADD CONSTRAINT pusher_data_fk_pusher FOREIGN KEY (pusher_id) REFERENCES public.pusher(id);


--
-- Name: pusher pusher_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.pusher
    ADD CONSTRAINT pusher_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: room_event_filter_not_rooms room_event_filter_fk_not_rooms; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_event_filter_not_rooms
    ADD CONSTRAINT room_event_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES public.room_event_filter(id);


--
-- Name: room_event_filter_not_senders room_event_filter_fk_not_senders; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_event_filter_not_senders
    ADD CONSTRAINT room_event_filter_fk_not_senders FOREIGN KEY (id) REFERENCES public.room_event_filter(id);


--
-- Name: room_event_filter_not_types room_event_filter_fk_not_types; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_event_filter_not_types
    ADD CONSTRAINT room_event_filter_fk_not_types FOREIGN KEY (id) REFERENCES public.room_event_filter(id);


--
-- Name: room_event_filter_rooms room_event_filter_fk_rooms; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_event_filter_rooms
    ADD CONSTRAINT room_event_filter_fk_rooms FOREIGN KEY (id) REFERENCES public.room_event_filter(id);


--
-- Name: room_event_filter_senders room_event_filter_fk_sender; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_event_filter_senders
    ADD CONSTRAINT room_event_filter_fk_sender FOREIGN KEY (id) REFERENCES public.room_event_filter(id);


--
-- Name: room_event_filter_types room_event_filter_fk_types; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_event_filter_types
    ADD CONSTRAINT room_event_filter_fk_types FOREIGN KEY (id) REFERENCES public.room_event_filter(id);


--
-- Name: room_filter room_filter_fk_account_data; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_filter
    ADD CONSTRAINT room_filter_fk_account_data FOREIGN KEY (account_data_id) REFERENCES public.room_event_filter(id);


--
-- Name: room_filter room_filter_fk_ephemeral; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_filter
    ADD CONSTRAINT room_filter_fk_ephemeral FOREIGN KEY (ephemeral_id) REFERENCES public.room_event_filter(id);


--
-- Name: room_filter_not_rooms room_filter_fk_not_rooms; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_filter_not_rooms
    ADD CONSTRAINT room_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES public.room_filter(id);


--
-- Name: room_filter_rooms room_filter_fk_rooms; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_filter_rooms
    ADD CONSTRAINT room_filter_fk_rooms FOREIGN KEY (id) REFERENCES public.room_filter(id);


--
-- Name: room_filter room_filter_fk_state; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_filter
    ADD CONSTRAINT room_filter_fk_state FOREIGN KEY (state_id) REFERENCES public.state_filter(id);


--
-- Name: room_filter room_filter_fk_timeline; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_filter
    ADD CONSTRAINT room_filter_fk_timeline FOREIGN KEY (timeline_id) REFERENCES public.room_event_filter(id);


--
-- Name: room room_fk_domain; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_fk_domain FOREIGN KEY (domain_id) REFERENCES public.domain(domain);


--
-- Name: room room_fk_last_state; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_fk_last_state FOREIGN KEY (latest_state_id) REFERENCES public.room_state_snapshot(id);


--
-- Name: room_report room_report_fk_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_report
    ADD CONSTRAINT room_report_fk_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: room_report room_report_fk_room; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_report
    ADD CONSTRAINT room_report_fk_room FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: room_servers room_servers_fk_room; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_servers
    ADD CONSTRAINT room_servers_fk_room FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: room_state room_state_fk_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state
    ADD CONSTRAINT room_state_fk_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: room_state room_state_fk_room_state; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state
    ADD CONSTRAINT room_state_fk_room_state FOREIGN KEY (room_state_id) REFERENCES public.room_state_snapshot(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_avatar; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_avatar FOREIGN KEY (avatar_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_canon_alias; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_canon_alias FOREIGN KEY (canonical_alias_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_encryption; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_encryption FOREIGN KEY (encryption_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_guest_access; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_guest_access FOREIGN KEY (guest_access_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_history; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_history FOREIGN KEY (history_visibility_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_initial; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_initial FOREIGN KEY (initial_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_join_rules; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_join_rules FOREIGN KEY (join_rules_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_name; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_name FOREIGN KEY (name_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_permissions; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_permissions FOREIGN KEY (permissions_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_prev; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_prev FOREIGN KEY (prev_id) REFERENCES public.room_state_snapshot(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_room; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_room FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_server_acl; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_server_acl FOREIGN KEY (server_acl_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_tombstone; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_tombstone FOREIGN KEY (tombstone_id) REFERENCES public.room_state(id);


--
-- Name: room_state_snapshot room_state_snapshot_fk_topic; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_state_snapshot
    ADD CONSTRAINT room_state_snapshot_fk_topic FOREIGN KEY (topic_id) REFERENCES public.room_state(id);


--
-- Name: room_transaction room_trans_fk_content; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_transaction
    ADD CONSTRAINT room_trans_fk_content FOREIGN KEY (content_id) REFERENCES public.event_content(id);


--
-- Name: room_transaction room_trans_fk_room; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.room_transaction
    ADD CONSTRAINT room_trans_fk_room FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: ruleset ruleset_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.ruleset
    ADD CONSTRAINT ruleset_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: send_to_device send_to_device_fk_content; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.send_to_device
    ADD CONSTRAINT send_to_device_fk_content FOREIGN KEY (content_id) REFERENCES public.event_content(id);


--
-- Name: signature sign_fk_event; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.signature
    ADD CONSTRAINT sign_fk_event FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- Name: state_filter_not_rooms state_filter_fk_not_rooms; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.state_filter_not_rooms
    ADD CONSTRAINT state_filter_fk_not_rooms FOREIGN KEY (id) REFERENCES public.state_filter(id);


--
-- Name: state_filter_not_senders state_filter_fk_not_senders; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.state_filter_not_senders
    ADD CONSTRAINT state_filter_fk_not_senders FOREIGN KEY (id) REFERENCES public.state_filter(id);


--
-- Name: state_filter_not_types state_filter_fk_not_types; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.state_filter_not_types
    ADD CONSTRAINT state_filter_fk_not_types FOREIGN KEY (id) REFERENCES public.state_filter(id);


--
-- Name: state_filter_rooms state_filter_fk_rooms; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.state_filter_rooms
    ADD CONSTRAINT state_filter_fk_rooms FOREIGN KEY (id) REFERENCES public.state_filter(id);


--
-- Name: state_filter_senders state_filter_fk_senders; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.state_filter_senders
    ADD CONSTRAINT state_filter_fk_senders FOREIGN KEY (id) REFERENCES public.state_filter(id);


--
-- Name: state_filter_types state_filter_fk_types; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.state_filter_types
    ADD CONSTRAINT state_filter_fk_types FOREIGN KEY (id) REFERENCES public.state_filter(id);


--
-- Name: tag tag_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: thumbnail thumbnail_fk_media; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.thumbnail
    ADD CONSTRAINT thumbnail_fk_media FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- Name: token token_fk_device; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_fk_device FOREIGN KEY (device_id) REFERENCES public.device(id);


--
-- Name: token token_fk_user; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_fk_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: user user_fk_auth; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_fk_auth FOREIGN KEY (auth_id) REFERENCES public.abstract_auth(id);


--
-- Name: user user_fk_domain; Type: FK CONSTRAINT; Schema: public; Owner: jeon
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_fk_domain FOREIGN KEY (domain_id) REFERENCES public.domain(domain);


--
-- PostgreSQL database dump complete
--

