--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-08-17 14:47:16 EEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 16963)
-- Name: costs; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE costs (
    id bigint NOT NULL,
    shift_id integer,
    sum double precision NOT NULL,
    date timestamp without time zone NOT NULL,
    description character varying(255)
);


ALTER TABLE costs OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16961)
-- Name: costs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE costs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE costs_id_seq OWNER TO postgres;

--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 182
-- Name: costs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE costs_id_seq OWNED BY costs.id;


--
-- TOC entry 179 (class 1259 OID 16924)
-- Name: income; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE income (
    id bigint NOT NULL,
    shift_id integer,
    sum double precision NOT NULL,
    date timestamp without time zone NOT NULL,
    description character varying(255)
);


ALTER TABLE income OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16922)
-- Name: income_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE income_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE income_id_seq OWNER TO postgres;

--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 178
-- Name: income_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE income_id_seq OWNED BY income.id;


--
-- TOC entry 177 (class 1259 OID 16877)
-- Name: shifts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE shifts (
    id bigint NOT NULL,
    wallet_id integer,
    user_id integer,
    start_sum double precision NOT NULL,
    finish_sum double precision,
    bank_sum double precision,
    start_date timestamp without time zone NOT NULL,
    finish_date timestamp without time zone
);


ALTER TABLE shifts OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16875)
-- Name: shifts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE shifts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shifts_id_seq OWNER TO postgres;

--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 176
-- Name: shifts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE shifts_id_seq OWNED BY shifts.id;


--
-- TOC entry 173 (class 1259 OID 16850)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    delete_flag boolean
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16848)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 172
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 175 (class 1259 OID 16861)
-- Name: wallets; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wallets (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    delete_flag boolean NOT NULL,
    availability_flag boolean NOT NULL
);


ALTER TABLE wallets OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16859)
-- Name: wallets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wallets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wallets_id_seq OWNER TO postgres;

--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 174
-- Name: wallets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wallets_id_seq OWNED BY wallets.id;



CREATE TABLE profileimage (
    id bigint NOT NULL,
    user_id integer NOT NULL,
    image bytea NOT NULL
);

ALTER TABLE profileimage OWNER TO postgres;


CREATE SEQUENCE profileimage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE profileimage_id_seq OWNER TO postgres;

ALTER SEQUENCE profileimage_id_seq OWNED BY profileimage.id;

--
-- TOC entry 1955 (class 2604 OID 16966)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY costs ALTER COLUMN id SET DEFAULT nextval('costs_id_seq'::regclass);


--
-- TOC entry 1954 (class 2604 OID 16927)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY income ALTER COLUMN id SET DEFAULT nextval('income_id_seq'::regclass);


--
-- TOC entry 1953 (class 2604 OID 16880)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY shifts ALTER COLUMN id SET DEFAULT nextval('shifts_id_seq'::regclass);


--
-- TOC entry 1951 (class 2604 OID 16853)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 1952 (class 2604 OID 16864)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wallets ALTER COLUMN id SET DEFAULT nextval('wallets_id_seq'::regclass);


ALTER TABLE ONLY profileimage ALTER COLUMN id SET DEFAULT nextval('wallets_id_seq'::regclass);


--
-- TOC entry 1965 (class 2606 OID 16968)
-- Name: costs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--


ALTER TABLE ONLY profileimage
    ADD CONSTRAINT profileimage_pkey PRIMARY KEY (id);


ALTER TABLE ONLY costs
    ADD CONSTRAINT costs_pkey PRIMARY KEY (id);


--
-- TOC entry 1963 (class 2606 OID 16929)
-- Name: income_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY income
    ADD CONSTRAINT income_pkey PRIMARY KEY (id);


--
-- TOC entry 1961 (class 2606 OID 16882)
-- Name: shifts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY shifts
    ADD CONSTRAINT shifts_pkey PRIMARY KEY (id);


--
-- TOC entry 1957 (class 2606 OID 16858)
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 1959 (class 2606 OID 16866)
-- Name: wallets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wallets
    ADD CONSTRAINT wallets_pkey PRIMARY KEY (id);


--
-- TOC entry 1969 (class 2606 OID 16969)
-- Name: costs_shift_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--


ALTER TABLE ONLY profileimage
    ADD CONSTRAINT profileimage_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


ALTER TABLE ONLY costs
    ADD CONSTRAINT costs_shift_id_fkey FOREIGN KEY (shift_id) REFERENCES shifts(id);


--
-- TOC entry 1968 (class 2606 OID 16930)
-- Name: income_shift_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY income
    ADD CONSTRAINT income_shift_id_fkey FOREIGN KEY (shift_id) REFERENCES shifts(id);


--
-- TOC entry 1967 (class 2606 OID 16888)
-- Name: shifts_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY shifts
    ADD CONSTRAINT shifts_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 1966 (class 2606 OID 16883)
-- Name: shifts_wallet_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY shifts
    ADD CONSTRAINT shifts_wallet_id_fkey FOREIGN KEY (wallet_id) REFERENCES wallets(id);


-- Completed on 2015-08-17 14:47:16 EEST

--
-- PostgreSQL database dump complete
--

