#!/bin/bash
set -e

DB_USER=shapesapi_user
DB_PASSWORD=shapesapi_password
DB_NAME=shapes_db
DB_SCHEMA=shapes_schema

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $DB_USER WITH PASSWORD '$DB_PASSWORD';
  CREATE ROLE audit;
  GRANT audit TO $DB_USER;

  CREATE DATABASE $DB_NAME;

  -- Grant privileges
  GRANT CONNECT, CREATE ON DATABASE $DB_NAME TO $DB_USER;
  GRANT CONNECT ON DATABASE $DB_NAME TO audit;

  -- Create schema as superuser
  \c $DB_NAME
  CREATE SCHEMA $DB_SCHEMA;
  GRANT ALL PRIVILEGES ON SCHEMA $DB_SCHEMA TO $DB_USER;
EOSQL