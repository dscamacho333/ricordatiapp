USE ricordati_db;

CREATE TABLE ACCOUNT_STATUS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL
);

CREATE TABLE ACCOUNT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    account_status_id BIGINT NOT NULL,
    created_at DATE NOT NULL,
    role ENUM('ADMIN', 'REGULAR_USER'),

    CONSTRAINT fk_account_status
        FOREIGN KEY (account_status_id)
        REFERENCES ACCOUNT_STATUS(id)
);

CREATE TABLE USER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(25) NOT NULL,
    middle_name VARCHAR(25),
    last_name VARCHAR(25) NOT NULL,
    second_last_name VARCHAR(25),
    date_of_birth DATE NOT NULL,
    government_id VARCHAR(10) NOT NULL UNIQUE,
    account_id BIGINT NOT NULL UNIQUE,

    CONSTRAINT fk_user_account
        FOREIGN KEY (account_id)
        REFERENCES ACCOUNT(id)
);

CREATE TABLE TASK_STATUS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE TASK_PRIORITY_LEVEL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE TASK_REMINDER_TIME (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    time INT NOT NULL,
    time_unit ENUM(
        'MINUTO',
        'HORA',
        'DÍA',
        'SEMANA',
        'MES',
        'AÑO'
    ) NOT NULL,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE TASK_CATEGORY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    description VARCHAR(150) NOT NULL,
    user_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL,

    CONSTRAINT fk_task_category_user
        FOREIGN KEY (user_id)
        REFERENCES USER(id)
);

CREATE TABLE TASK (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_category_id BIGINT NOT NULL,
    title VARCHAR(25) NOT NULL,
    description VARCHAR(150) NOT NULL,
    creation_date DATE NOT NULL,
    due_date DATETIME NOT NULL,
    completion_date DATETIME,
    task_status_id BIGINT NOT NULL,
    task_priority_level_id BIGINT NOT NULL,
    task_reminder_time_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL,

    CONSTRAINT fk_task_category
        FOREIGN KEY (task_category_id)
        REFERENCES TASK_CATEGORY(id),

    CONSTRAINT fk_task_status
        FOREIGN KEY (task_status_id)
        REFERENCES TASK_STATUS(id),

    CONSTRAINT fk_task_priority
        FOREIGN KEY (task_priority_level_id)
        REFERENCES TASK_PRIORITY_LEVEL(id),

    CONSTRAINT fk_task_reminder
        FOREIGN KEY (task_reminder_time_id)
        REFERENCES TASK_REMINDER_TIME(id),

    CONSTRAINT fk_task_user
        FOREIGN KEY (user_id)
        REFERENCES USER(id)
);


CREATE TABLE ONE_TIME_PIN (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    otp VARCHAR(6) NOT NULL,
    type ENUM(
        'REGISTRATION',
        'PASSWORD_RESET',
        'MFA'
    ) NOT NULL,
    expires_At DATETIME NOT NULL,
    is_used BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    
    CONSTRAINT fk_one_time_pin_user
        FOREIGN KEY (user_id)
        REFERENCES USER(id)
);