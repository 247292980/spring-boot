CREATE DATABASE test;
CREATE TABLE public.user(
   id integer NOT NULL,
   name character(100),
    CONSTRAINT user_pkey PRIMARY KEY (id)
);
