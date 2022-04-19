
-- usuarios
INSERT INTO simudest.usuario (id_user, email, nombre, apellido1, apellido2, admin, contra) VALUES ('asd-asdasdasd', 'asd@asd.asd', 'Alberto', 'Castillo', 'Jimenez', true, '{bcrypt}$2a$10$oKPsvfh.NXZjkec7ZpTOZOhOfpYk9f37jAmEtEj16Z63mc/jvbX2C');
INSERT INTO simudest.usuario (id_user, email, nombre, apellido1, apellido2, admin, contra) VALUES ('8a09e0b47f78f5f7017f78f6394d0000', '123123@123.123', '123', '123', '123', false, '{bcrypt}$2a$10$rml0Iu9jzZ0TzHTAGLlmC.rCm1FEYrJrN552YE0scrm9147swLv/W');
INSERT INTO simudest.usuario (id_user, email, nombre, apellido1, apellido2, admin, contra) VALUES ('8a09e0847f79325e017f793789380000', 'asd@asd.test', 'test', 'test', 'test', false, '{bcrypt}$2a$10$7zc/rcEbDrScu1Lj2gkbV.NwR1pIJc5.AquobU9U2I3oz3aWHP9y6');
INSERT INTO simudest.usuario (id_user, email, nombre, apellido1, apellido2, admin, contra) VALUES ('4028b8817f7b2793017f7b28007c0000', '123123@123.1231', '123', '123', '123', false, '{bcrypt}$2a$10$TnpA5lH0ru0nIN6RnPtKreZMXIMc2mWhK9UBxl0upX..GTEzzYB3i');
INSERT INTO simudest.usuario (id_user, email, nombre, apellido1, apellido2, admin, contra) VALUES ('4028b8817f827e7f017f827f1ac70000', 'mir@mir.mir', 'Miriam', 'sin', 'cuenta', false, '{bcrypt}$2a$10$ZCrAxoNlCT2utQJQG7xDpO3gvzz4KkAnnWQ93WhbUY5t9cfBJt0VC');
INSERT INTO simudest.usuario (id_user, email, nombre, apellido1, apellido2, admin, contra) VALUES ('8a00ede48018ab39018018acdc8d0000', 'asdasdasd@asd.asd', 'asdasdasd', 'asdasdasd', 'asdasdasd', false, '{bcrypt}$2a$10$wR8Uv44ykZ7cPONXc96UEegVeVOudqf/VwjfxkPR.VtV8iAOKRkua');




-- convocatorias
INSERT INTO simudest.convocatoria (id_convo, nombre, id_user, id_organ, id_espec, estado, nopositores, nplazas) VALUES ('123123123', 'Convocatoria 2019-2020', 'asd-asdasdasd', 2, 3, 'ON', 34, 34);



-- plazas


-- opositores
INSERT INTO simudest.opositor (id_convo, id_user, validado, orden) VALUES ('123123123', '8a00ede48018ab39018018acdc8d0000', true, 12);
