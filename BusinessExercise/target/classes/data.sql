insert into role (name) values ('Administrador');
insert into role (name) values ('Consultor');

insert into employee (name, first_surname, second_surname, dni, address, role_id) values ('Paco', 'Martinez', 'Fernandez', '11223344M', 'Malaga', 1);
insert into employee (name, first_surname, second_surname, dni, address, role_id) values ('Elena', 'Ruiz', 'Fernandez', '22334455B', 'Malaga', 2);
insert into employee (name, first_surname, second_surname, dni, address, role_id) values ('Maria', 'Martinez', 'Rueca', '33445566R', 'Malaga', 2);

insert into department (name) values ('Contabilidad');
insert into department (name) values ('Administracion');
insert into department (name) values ('Desarrollo');
insert into department (name) values ('Direccion');

insert into employees_working_in_department (employee_id, department_id) values (1, 2);
insert into employees_working_in_department (employee_id, department_id) values (1, 1);
insert into employees_working_in_department (employee_id, department_id) values (2, 3);
insert into employees_working_in_department (employee_id, department_id) values (3, 4);