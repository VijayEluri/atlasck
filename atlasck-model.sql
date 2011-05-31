CREATE TABLE current_version (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NULL,
  passwd VARCHAR(255) NULL,
  maintenance BOOL NULL DEFAULT false,
  http_auth BOOL NULL DEFAULT false,
  PRIMARY KEY(id)
)
TYPE=InnoDB;

CREATE TABLE visitors (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nickname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  ip_address VARCHAR(255) NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(id),
  UNIQUE INDEX visitors_email_unique(email)
)
TYPE=InnoDB;

CREATE TABLE questions (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  visitors_id INTEGER UNSIGNED NOT NULL,
  title VARCHAR(255) NOT NULL,
  question TEXT NOT NULL,
  visible BOOL NULL DEFAULT 1,
  email_answer BOOL NULL DEFAULT 0,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(id),
  INDEX questions_FKIndex1(visitors_id),
  FOREIGN KEY(visitors_id)
    REFERENCES visitors(id)
      ON DELETE RESTRICT
      ON UPDATE RESTRICT
)
TYPE=InnoDB;

CREATE TABLE answers (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  questions_id INTEGER UNSIGNED NOT NULL,
  answer TEXT NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(id),
  INDEX answers_FKIndex1(questions_id),
  FOREIGN KEY(questions_id)
    REFERENCES questions(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
TYPE=InnoDB;

