-- Departmental Result System: 3NF schema (SQLite).
-- Grades and GPA are derived values, so they are NOT stored (keeps the schema in 3NF).
CREATE TABLE IF NOT EXISTS student (
  id          INTEGER PRIMARY KEY AUTOINCREMENT,
  matric_no   TEXT NOT NULL UNIQUE,
  full_name   TEXT NOT NULL,
  department  TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS course (
  code          TEXT PRIMARY KEY,
  title         TEXT NOT NULL,
  credit_units  INTEGER NOT NULL CHECK (credit_units BETWEEN 1 AND 6)
);

CREATE TABLE IF NOT EXISTS semester (
  id       INTEGER PRIMARY KEY AUTOINCREMENT,
  session  TEXT NOT NULL,
  term     TEXT NOT NULL CHECK (term IN ('First', 'Second')),
  UNIQUE (session, term)
);

CREATE TABLE IF NOT EXISTS result (
  id           INTEGER PRIMARY KEY AUTOINCREMENT,
  student_id   INTEGER NOT NULL REFERENCES student(id),
  course_code  TEXT    NOT NULL REFERENCES course(code),
  semester_id  INTEGER NOT NULL REFERENCES semester(id),
  score        REAL    NOT NULL CHECK (score BETWEEN 0 AND 100),
  UNIQUE (student_id, course_code, semester_id)
);
