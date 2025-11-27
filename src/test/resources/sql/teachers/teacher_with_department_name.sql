SELECT
t.first_name,
t.last_name,
d.department_name
FROM teachers t JOIN departments d ON t.department_id = d.department_id
WHERE t.teacher_id = ?;