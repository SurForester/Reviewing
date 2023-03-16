-- 1. ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность»,
-- «фильм 2», «время начала», «длительность»;

select
    dm.movie_name as "фильм1",
    mp.mp_time_begin as "время начала",
    dm.movie_durability as "длительность",
    dm2.movie_name as "фильм2",
    mp2.mp_time_begin as "время начала",
    dm2.movie_durability as "длительность"
from movie_poster mp
         join dim_movies dm on mp.movie_id = dm.id
         join movie_poster mp2 on mp2.mp_time_begin > mp.mp_time_begin and
                                  mp2.mp_time_begin < (mp.mp_time_begin + dm.movie_durability * interval '1 minute')
         join dim_movies dm2 on mp2.movie_id = dm2.id
order by mp.mp_time_begin asc;


-- 2. перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма»,
-- «длительность перерыва»;

select
    mp1.movie_id,
    mp1.mp_time_begin,
    dm1.movie_durability,
    min(mp2.mp_time_begin)
-- ( min(mp2.mp_time_begin) - (mp1.mp_time_begin + dm1.movie_durability * interval '1 minute') ) as break
from movie_poster mp1
         join dim_movies dm1 on mp1.movie_id = dm1.id
         left join movie_poster mp2 on mp1.mp_time_begin < mp2.mp_time_begin
group by mp1.mp_time_begin;
--HAVING break >= 30
--ORDER BY break DESC;

-- 3. список фильмов, для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму
-- (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

SELECT (select dm.movie_name from dim_movies as dm where dm.id = qry.movie_id) as "Фильм",
       qry."Всего билетов", qry."Среднее на фильм", qry."Кассовый сбор"
from
    (select
         s.movie_id,
         count(*) as "Всего билетов",
         count(*) / count(distinct b.mp_id) as "Среднее на фильм",
         sum(s.ticket_cost) as "Кассовый сбор"
     from movie_poster s
              join movie_tickets b on s.id = b.mp_id
     GROUP BY s.movie_id
     ORDER BY "Кассовый сбор" desc
    ) as qry
union
select 'Итого' as "Фильм",
       count(*) as "Всего билетов",
       count(b.mp_id) / count(distinct b.mp_id) as "Среднее на фильм",
       sum(s.ticket_cost) as "Кассовый сбор"
from movie_poster s
         join movie_tickets b on s.id = b.mp_id;

-- 4. число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

select 'c 9 до 15' as "интервал", sum(tickets) as "посетителей", sum(summ) as "кассовый сбор"
from
    (select extract(hour from s.mp_time_begin) mh, count(b.*) tickets, sum(s.ticket_cost) summ
     from movie_poster s
              join movie_tickets b on s.id = b.mp_id
     group by extract(hour from s.mp_time_begin)
     HAVING (extract(hour from s.mp_time_begin) >= 9 and extract(hour from s.mp_time_begin) < 15)) as qry
union
select 'c 15 до 18' as "интервал", sum(tickets) as "посетителей", sum(summ) as "кассовый сбор"
from
    (select extract(hour from s.mp_time_begin) mh, count(b.*) tickets, sum(s.ticket_cost) summ
     from movie_poster s
              join movie_tickets b on s.id = b.mp_id
     group by extract(hour from s.mp_time_begin)
     HAVING (extract(hour from s.mp_time_begin) >= 15 and extract(hour from s.mp_time_begin) < 18)) as qry
union
select 'c 18 до 21' as "интервал", sum(tickets) as "посетителей", sum(summ) as "кассовый сбор"
from
    (select extract(hour from s.mp_time_begin) mh, count(b.*) tickets, sum(s.ticket_cost) summ
     from movie_poster s
              join movie_tickets b on s.id = b.mp_id
     group by extract(hour from s.mp_time_begin)
     HAVING (extract(hour from s.mp_time_begin) >= 18 and extract(hour from s.mp_time_begin) < 21)) as qry
union
select 'c 21 до 23' as "интервал", sum(tickets) as "посетителей", sum(summ) as "кассовый сбор"
from
    (select extract(hour from s.mp_time_begin) mh, count(b.*) tickets, sum(s.ticket_cost) summ
     from movie_poster s
              join movie_tickets b on s.id = b.mp_id
     group by extract(hour from s.mp_time_begin)
     HAVING (extract(hour from s.mp_time_begin) >= 21 and extract(hour from s.mp_time_begin) <= 23)) as qry