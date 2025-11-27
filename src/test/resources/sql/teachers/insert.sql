INSERT INTO teachers (first_name, last_name, email, department_id)
VALUES(?,?,?,?)
RETURNING teacher_id;