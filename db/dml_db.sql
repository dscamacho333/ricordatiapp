USE ricordati_db;

# Insert account status data

INSERT INTO ACCOUNT_STATUS(name) VALUES('Activa');
INSERT INTO ACCOUNT_STATUS(name) VALUES('Inactiva');

# Insert task status data

INSERT INTO TASK_STATUS(name, is_active) VALUES('Pendiente', TRUE);
INSERT INTO TASK_STATUS(name, is_active ) VALUES('Hecha', TRUE);

# Insert task priority level data

INSERT INTO TASK_PRIORITY_LEVEL(name, is_active) VALUES('Baja', TRUE);
INSERT INTO TASK_PRIORITY_LEVEL(name, is_active) VALUES('Media', TRUE);
INSERT INTO TASK_PRIORITY_LEVEL(name, is_active) VALUES('Alta', TRUE);

# Insert task reminder time data

INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(5, 'MINUTO', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(15, 'MINUTO', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(30, 'MINUTO', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(45, 'MINUTO', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(1, 'HORA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(3, 'HORA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(6, 'HORA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(9, 'HORA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(12, 'HORA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(1, 'DIA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(2, 'DIA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(3, 'DIA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(4, 'DIA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(5, 'DIA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(1, 'SEMANA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(2, 'SEMANA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(3, 'SEMANA', TRUE);
INSERT INTO TASK_REMINDER_TIME(time, time_unit, is_active) VALUES(1, 'MES', TRUE);

