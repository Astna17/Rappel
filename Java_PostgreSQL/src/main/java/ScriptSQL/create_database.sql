CREATE ROLE prog_admin LOGIN PASSWORD '123456';

CREATE DATABASE library_management OWNER prog_admin;

\cd library_management prog_admin
