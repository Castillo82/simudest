
-- NOTA: si ya esta creada la DATABASE basta con ejecutar el script del create schema simudest en adelante

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9
-- Dumped by pg_dump version 12.9

-- Started on 2022-04-19 13:27:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE simudest;
--
-- TOC entry 2919 (class 1262 OID 16572)
-- Name: simudest; Type: DATABASE; Schema: -; Owner: simudest
--

CREATE DATABASE simudest WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';


ALTER DATABASE simudest OWNER TO simudest;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 2919
-- Name: DATABASE simudest; Type: COMMENT; Schema: -; Owner: simudest
--

COMMENT ON DATABASE simudest IS 'Base de datos del simulador de destinos.';


--
-- TOC entry 7 (class 2615 OID 16782)
-- Name: simudest; Type: SCHEMA; Schema: -; Owner: simudest
--

CREATE SCHEMA simudest;


ALTER SCHEMA simudest OWNER TO simudest;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 16784)
-- Name: convocatoria; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.convocatoria (
    id_convo character varying(36) NOT NULL,
    nombre character varying(100) NOT NULL,
    id_user character varying(36) NOT NULL,
    id_organ integer NOT NULL,
    id_espec integer NOT NULL,
    estado character varying(5) NOT NULL,
    nopositores integer NOT NULL,
    nplazas integer NOT NULL
);


ALTER TABLE simudest.convocatoria OWNER TO simudest;

--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE convocatoria; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.convocatoria IS 'Almacena las convocatorias.';


--
-- TOC entry 205 (class 1259 OID 16787)
-- Name: eleccion; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.eleccion (
    id_plaza character varying(36) NOT NULL,
    id_user character varying(36) NOT NULL,
    orden smallint NOT NULL
);


ALTER TABLE simudest.eleccion OWNER TO simudest;

--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE eleccion; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.eleccion IS 'Almacena las elecciones de plazas de cada opositor';


--
-- TOC entry 206 (class 1259 OID 16790)
-- Name: especialidad; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.especialidad (
    id_espec integer NOT NULL,
    nombre character varying(60) NOT NULL,
    id_grupo integer NOT NULL
);


ALTER TABLE simudest.especialidad OWNER TO simudest;

--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 206
-- Name: TABLE especialidad; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.especialidad IS 'Almacena las distintas especialidades de los grupos y subgrupos de funcionarios';


--
-- TOC entry 207 (class 1259 OID 16793)
-- Name: especialidad_id_espec_seq; Type: SEQUENCE; Schema: simudest; Owner: simudest
--

CREATE SEQUENCE simudest.especialidad_id_espec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE simudest.especialidad_id_espec_seq OWNER TO simudest;

--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 207
-- Name: especialidad_id_espec_seq; Type: SEQUENCE OWNED BY; Schema: simudest; Owner: simudest
--

ALTER SEQUENCE simudest.especialidad_id_espec_seq OWNED BY simudest.especialidad.id_espec;


--
-- TOC entry 208 (class 1259 OID 16795)
-- Name: grupo; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.grupo (
    id_grupo integer NOT NULL,
    nombre character varying(4) NOT NULL
);


ALTER TABLE simudest.grupo OWNER TO simudest;

--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 208
-- Name: TABLE grupo; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.grupo IS 'Tabla que almacena los distintos grupos y subgrupos de funcionarios';


--
-- TOC entry 209 (class 1259 OID 16798)
-- Name: grupo_id_grupo_seq; Type: SEQUENCE; Schema: simudest; Owner: simudest
--

CREATE SEQUENCE simudest.grupo_id_grupo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE simudest.grupo_id_grupo_seq OWNER TO simudest;

--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 209
-- Name: grupo_id_grupo_seq; Type: SEQUENCE OWNED BY; Schema: simudest; Owner: simudest
--

ALTER SEQUENCE simudest.grupo_id_grupo_seq OWNED BY simudest.grupo.id_grupo;


--
-- TOC entry 210 (class 1259 OID 16800)
-- Name: opositor; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.opositor (
    id_convo character varying(36) NOT NULL,
    id_user character varying(36) NOT NULL,
    validado boolean NOT NULL,
    orden smallint
);


ALTER TABLE simudest.opositor OWNER TO simudest;

--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE opositor; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.opositor IS 'Almacena la relacion entre usuarios y convocatorias, que permite indicar los opositores de la convocatoria y si estan validados';


--
-- TOC entry 211 (class 1259 OID 16803)
-- Name: organismo; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.organismo (
    id_organ integer NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE simudest.organismo OWNER TO simudest;

--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE organismo; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.organismo IS 'Almacena los distintos organismos a los que puede pertenecer una convocatoria.';


--
-- TOC entry 212 (class 1259 OID 16806)
-- Name: organismo_id_organ_seq; Type: SEQUENCE; Schema: simudest; Owner: simudest
--

CREATE SEQUENCE simudest.organismo_id_organ_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE simudest.organismo_id_organ_seq OWNER TO simudest;

--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 212
-- Name: organismo_id_organ_seq; Type: SEQUENCE OWNED BY; Schema: simudest; Owner: simudest
--

ALTER SEQUENCE simudest.organismo_id_organ_seq OWNED BY simudest.organismo.id_organ;


--
-- TOC entry 213 (class 1259 OID 16808)
-- Name: plaza; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.plaza (
    id_plaza character varying(36) NOT NULL,
    codigo character varying(20),
    ministerio character varying(100),
    centro character varying(150),
    id_provi integer,
    localidad character varying(80),
    denominacion character varying(60),
    nivel smallint,
    c_especifico numeric(8,2),
    id_convo character varying(36) NOT NULL
);


ALTER TABLE simudest.plaza OWNER TO simudest;

--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE plaza; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.plaza IS 'Almacena las plazas de cada oposición.';


--
-- TOC entry 214 (class 1259 OID 16811)
-- Name: provincia; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.provincia (
    id_provi integer NOT NULL,
    nombre character varying(30) NOT NULL
);


ALTER TABLE simudest.provincia OWNER TO simudest;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 214
-- Name: TABLE provincia; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.provincia IS 'Almacena las provincias.';


--
-- TOC entry 215 (class 1259 OID 16814)
-- Name: provincia_id_provi_seq; Type: SEQUENCE; Schema: simudest; Owner: simudest
--

CREATE SEQUENCE simudest.provincia_id_provi_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE simudest.provincia_id_provi_seq OWNER TO simudest;

--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 215
-- Name: provincia_id_provi_seq; Type: SEQUENCE OWNED BY; Schema: simudest; Owner: simudest
--

ALTER SEQUENCE simudest.provincia_id_provi_seq OWNED BY simudest.provincia.id_provi;


--
-- TOC entry 216 (class 1259 OID 16816)
-- Name: usuario; Type: TABLE; Schema: simudest; Owner: simudest
--

CREATE TABLE simudest.usuario (
    id_user character varying(36) NOT NULL,
    email character varying(256) NOT NULL,
    nombre character varying(40) NOT NULL,
    apellido1 character varying(60) NOT NULL,
    apellido2 character varying(60),
    admin boolean NOT NULL,
    contra character varying(72) NOT NULL
);


ALTER TABLE simudest.usuario OWNER TO simudest;

--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 216
-- Name: TABLE usuario; Type: COMMENT; Schema: simudest; Owner: simudest
--

COMMENT ON TABLE simudest.usuario IS 'Almacena los usuarios de la aplicación, independientemente de si son administradores, organizadores u opositores.';


--
-- TOC entry 2755 (class 2604 OID 16849)
-- Name: especialidad id_espec; Type: DEFAULT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.especialidad ALTER COLUMN id_espec SET DEFAULT nextval('simudest.especialidad_id_espec_seq'::regclass);


--
-- TOC entry 2756 (class 2604 OID 16850)
-- Name: grupo id_grupo; Type: DEFAULT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.grupo ALTER COLUMN id_grupo SET DEFAULT nextval('simudest.grupo_id_grupo_seq'::regclass);


--
-- TOC entry 2757 (class 2604 OID 16851)
-- Name: organismo id_organ; Type: DEFAULT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.organismo ALTER COLUMN id_organ SET DEFAULT nextval('simudest.organismo_id_organ_seq'::regclass);


--
-- TOC entry 2758 (class 2604 OID 16852)
-- Name: provincia id_provi; Type: DEFAULT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.provincia ALTER COLUMN id_provi SET DEFAULT nextval('simudest.provincia_id_provi_seq'::regclass);


--
-- TOC entry 2760 (class 2606 OID 16854)
-- Name: convocatoria convocatoria_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.convocatoria
    ADD CONSTRAINT convocatoria_pkey PRIMARY KEY (id_convo);


--
-- TOC entry 2762 (class 2606 OID 16856)
-- Name: eleccion eleccion_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.eleccion
    ADD CONSTRAINT eleccion_pkey PRIMARY KEY (id_plaza, id_user);


--
-- TOC entry 2764 (class 2606 OID 16858)
-- Name: especialidad especialidad_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.especialidad
    ADD CONSTRAINT especialidad_pkey PRIMARY KEY (id_espec);


--
-- TOC entry 2766 (class 2606 OID 16860)
-- Name: grupo grupo_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.grupo
    ADD CONSTRAINT grupo_pkey PRIMARY KEY (id_grupo);


--
-- TOC entry 2768 (class 2606 OID 16862)
-- Name: opositor opositor_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.opositor
    ADD CONSTRAINT opositor_pkey PRIMARY KEY (id_convo, id_user);


--
-- TOC entry 2770 (class 2606 OID 16864)
-- Name: organismo organismo_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.organismo
    ADD CONSTRAINT organismo_pkey PRIMARY KEY (id_organ);


--
-- TOC entry 2773 (class 2606 OID 16866)
-- Name: plaza plaza_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.plaza
    ADD CONSTRAINT plaza_pkey PRIMARY KEY (id_plaza);


--
-- TOC entry 2775 (class 2606 OID 16868)
-- Name: provincia provincia_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (id_provi);


--
-- TOC entry 2777 (class 2606 OID 16870)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_user);


--
-- TOC entry 2771 (class 1259 OID 16889)
-- Name: index_convo; Type: INDEX; Schema: simudest; Owner: simudest
--

CREATE INDEX index_convo ON simudest.plaza USING btree (id_convo);


--
-- TOC entry 2778 (class 2606 OID 16891)
-- Name: convocatoria fk_convo_espec; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.convocatoria
    ADD CONSTRAINT fk_convo_espec FOREIGN KEY (id_espec) REFERENCES simudest.especialidad(id_espec);


--
-- TOC entry 2779 (class 2606 OID 16896)
-- Name: convocatoria fk_convo_organ; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.convocatoria
    ADD CONSTRAINT fk_convo_organ FOREIGN KEY (id_organ) REFERENCES simudest.organismo(id_organ);


--
-- TOC entry 2780 (class 2606 OID 16901)
-- Name: convocatoria fk_convo_user; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.convocatoria
    ADD CONSTRAINT fk_convo_user FOREIGN KEY (id_user) REFERENCES simudest.usuario(id_user);


--
-- TOC entry 2781 (class 2606 OID 16906)
-- Name: eleccion fk_elec_plaza; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.eleccion
    ADD CONSTRAINT fk_elec_plaza FOREIGN KEY (id_plaza) REFERENCES simudest.plaza(id_plaza);


--
-- TOC entry 2782 (class 2606 OID 16911)
-- Name: eleccion fk_elec_user; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.eleccion
    ADD CONSTRAINT fk_elec_user FOREIGN KEY (id_user) REFERENCES simudest.usuario(id_user);


--
-- TOC entry 2783 (class 2606 OID 16916)
-- Name: especialidad fk_espec_grupo; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.especialidad
    ADD CONSTRAINT fk_espec_grupo FOREIGN KEY (id_grupo) REFERENCES simudest.grupo(id_grupo);


--
-- TOC entry 2784 (class 2606 OID 16921)
-- Name: opositor fk_opo_convo; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.opositor
    ADD CONSTRAINT fk_opo_convo FOREIGN KEY (id_convo) REFERENCES simudest.convocatoria(id_convo);


--
-- TOC entry 2785 (class 2606 OID 16926)
-- Name: opositor fk_opo_user; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.opositor
    ADD CONSTRAINT fk_opo_user FOREIGN KEY (id_user) REFERENCES simudest.usuario(id_user);


--
-- TOC entry 2786 (class 2606 OID 16931)
-- Name: plaza fk_plaza_convo; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.plaza
    ADD CONSTRAINT fk_plaza_convo FOREIGN KEY (id_convo) REFERENCES simudest.convocatoria(id_convo);


--
-- TOC entry 2787 (class 2606 OID 16936)
-- Name: plaza fk_plaza_provi; Type: FK CONSTRAINT; Schema: simudest; Owner: simudest
--

ALTER TABLE ONLY simudest.plaza
    ADD CONSTRAINT fk_plaza_provi FOREIGN KEY (id_provi) REFERENCES simudest.provincia(id_provi);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 2919
-- Name: DATABASE simudest; Type: ACL; Schema: -; Owner: simudest
--

REVOKE ALL ON DATABASE simudest FROM simudest;
GRANT CREATE,CONNECT ON DATABASE simudest TO simudest;
GRANT TEMPORARY ON DATABASE simudest TO simudest WITH GRANT OPTION;


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA simudest; Type: ACL; Schema: -; Owner: simudest
--

REVOKE ALL ON SCHEMA simudest FROM simudest;
GRANT ALL ON SCHEMA simudest TO simudest WITH GRANT OPTION;


-- Completed on 2022-04-19 13:27:19

--
-- PostgreSQL database dump complete
--

