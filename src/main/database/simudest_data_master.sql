
-- grupos
INSERT INTO simudest.grupo (id_grupo, nombre) VALUES (1, 'A1');
INSERT INTO simudest.grupo (id_grupo, nombre) VALUES (2, 'A2');
INSERT INTO simudest.grupo (id_grupo, nombre) VALUES (3, 'B');
INSERT INTO simudest.grupo (id_grupo, nombre) VALUES (4, 'C1');
INSERT INTO simudest.grupo (id_grupo, nombre) VALUES (5, 'C2');
INSERT INTO simudest.grupo (id_grupo, nombre) VALUES (6, 'E');


-- especialidades
INSERT INTO simudest.especialidad (id_espec, nombre, id_grupo) VALUES (1, 'Superior Administrativa', 1);
INSERT INTO simudest.especialidad (id_espec, nombre, id_grupo) VALUES (2, 'Gestión Administrativa', 2);
INSERT INTO simudest.especialidad (id_espec, nombre, id_grupo) VALUES (3, 'General Administrativa', 4);
INSERT INTO simudest.especialidad (id_espec, nombre, id_grupo) VALUES (4, 'Auxiliar Administrativa', 5);
INSERT INTO simudest.especialidad (id_espec, nombre, id_grupo) VALUES (5, 'Superior de Sistemas e Informática', 1);
INSERT INTO simudest.especialidad (id_espec, nombre, id_grupo) VALUES (6, 'Gestión/Técnica de Sistemas e Informática', 2);
INSERT INTO simudest.especialidad (id_espec, nombre, id_grupo) VALUES (7, 'Auxiliar/administrativa de Sistemas e Informática', 4);


-- organismos
INSERT INTO simudest.organismo (id_organ, nombre) VALUES (1, 'Administración General del Estado');
INSERT INTO simudest.organismo (id_organ, nombre) VALUES (2, 'Comunidad de Madrid');
INSERT INTO simudest.organismo (id_organ, nombre) VALUES (3, 'Ayuntamiento de Madrid');
INSERT INTO simudest.organismo (id_organ, nombre) VALUES (4, 'Junta de Comunidades de Castilla la Mancha');
INSERT INTO simudest.organismo (id_organ, nombre) VALUES (5, 'Junta de Andalucía');
INSERT INTO simudest.organismo (id_organ, nombre) VALUES (6, 'Junta de Castilla y León');


-- provincias
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (2, 'Albacete');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (3, 'Alicante/Alacant');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (4, 'Almería');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (1, 'Araba/Álava');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (33, 'Asturias');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (5, 'Avila');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (6, 'Badajoz');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (7, 'Balears, Illes');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (8, 'Barcelona');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (48, 'Bizkaia');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (9, 'Burgos');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (10, 'Cáceres');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (11, 'Cádiz');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (39, 'Cantabria');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (12, 'Castellón/Castelló');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (51, 'Ceuta');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (13, 'Ciudad Real');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (14, 'Córdoba');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (15, 'Coruña, A');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (16, 'Cuenca');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (20, 'Gipuzkoa');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (17, 'Girona');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (18, 'Granada');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (19, 'Guadalajara');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (21, 'Huelva');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (22, 'Huesca');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (23, 'Jaén');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (24, 'León');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (27, 'Lugo');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (25, 'Lleida');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (28, 'Madrid');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (29, 'Málaga');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (52, 'Melilla');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (30, 'Murcia');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (31, 'Navarra');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (32, 'Ourense');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (34, 'Palencia');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (35, 'Palmas, Las');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (36, 'Pontevedra');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (26, 'Rioja, La');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (37, 'Salamanca');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (38, 'Santa Cruz de Tenerife');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (40, 'Segovia');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (41, 'Sevilla');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (42, 'Soria');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (43, 'Tarragona');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (44, 'Teruel');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (45, 'Toledo');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (46, 'Valencia/València');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (47, 'Valladolid');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (49, 'Zamora');
INSERT INTO simudest.provincia (id_provi, nombre) VALUES (50, 'Zaragoza');




-- secuencias

SELECT pg_catalog.setval('simudest.especialidad_id_espec_seq', 100, false);
SELECT pg_catalog.setval('simudest.grupo_id_grupo_seq', 100, false);
SELECT pg_catalog.setval('simudest.organismo_id_organ_seq', 100, false);
SELECT pg_catalog.setval('simudest.provincia_id_provi_seq', 100, true);


