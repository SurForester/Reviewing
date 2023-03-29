CREATE TABLE IF NOT EXISTS public.dim_movies
(
    id               integer                                             NOT NULL,
    movie_name       character varying(256) COLLATE pg_catalog."default" NOT NULL,
    movie_durability integer                                             NOT NULL,
    CONSTRAINT dim_movies_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dim_movies
    OWNER to postgres;

insert into dim_movies
values (1, 'Буратино', 120),
       (2, 'Унесённые ветром', 120),
       (3, 'Ералаш (новые серии)', 60),
       (4, 'Братва и кольцо (ч.1)', 120),
       (5, 'Братва и кольцо (ч.2)', 120),
       (6, 'Братва и кольцо (ч.3)', 120);



CREATE TABLE IF NOT EXISTS public.movie_poster
(
    id            integer             not null,
    mp_date       date                NOT NULL,
    mp_time_begin time with time zone NOT NULL,
    movie_id      integer             NOT NULL,
    ticket_cost   money               NOT NULL,
    CONSTRAINT movie_poster_primary_key PRIMARY KEY (id),
    CONSTRAINT movie_foreign_key FOREIGN KEY (movie_id)
        REFERENCES public.dim_movies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movie_poster
    OWNER to postgres;

insert into movie_poster
values (1, '2023-04-01', '09:00:00+05:00', 1, '200,00 ?'),
       (2, '2023-04-01', '11:10:00+05:00', 2, '250,00 ?'),
       (3, '2023-04-01', '12:20:00+05:00', 3, '300,00 ?'),
       (4, '2023-04-01', '14:00:00+05:00', 4, '350,00 ?'),
       (5, '2023-04-01', '16:30:00+05:00', 5, '400,00 ?'),
       (6, '2023-04-01', '19:00:00+05:00', 6, '400,00 ?'),
       (7, '2023-04-01', '21:00:00+05:00', 4, '400,00 ?'),
       (8, '2023-04-01', '23:10:00+05:00', 1, '500,00 ?');



CREATE TABLE IF NOT EXISTS public.movie_tickets
(
    id         bigint  NOT NULL,
    mp_id      integer NOT NULL,
    ticket_row integer NOT NULL,
    ticket_set integer NOT NULL,
    CONSTRAINT tickets_primary_key PRIMARY KEY (id),
    CONSTRAINT unique_movie_sets UNIQUE (id, mp_id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movie_tickets
    OWNER to postgres;

insert into movie_tickets
values (1, 1, 5, 7),
       (2, 1, 5, 11),
       (3, 1, 3, 9),
       (4, 2, 3, 4),
       (5, 2, 3, 5),
       (6, 2, 4, 9),
       (7, 2, 4, 10),
       (8, 3, 7, 8),
       (9, 3, 7, 9),
       (10, 3, 5, 7),
       (11, 3, 4, 7),
       (12, 3, 3, 5),
       (13, 4, 9, 9),
       (14, 4, 11, 7),
       (15, 5, 5, 7),
       (16, 5, 5, 8),
       (17, 5, 5, 9),
       (18, 6, 7, 7),
       (19, 6, 7, 8),
       (20, 6, 7, 9),
       (21, 6, 9, 7),
       (22, 6, 9, 8),
       (23, 7, 3, 5),
       (24, 7, 3, 6),
       (25, 8, 8, 5),
       (26, 8, 8, 7);