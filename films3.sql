-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июл 16 2019 г., 12:20
-- Версия сервера: 10.1.38-MariaDB
-- Версия PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `films3`
--

DELIMITER $$
--
-- Процедуры
--
CREATE DEFINER=`eugeny`@`localhost` PROCEDURE `film_data` ()  select * from film$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `actor`
--

CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `birthday` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `actor`
--

INSERT INTO `actor` (`id`, `name`, `birthday`) VALUES
(1, 'Тим Роббинс', '1958-10-15'),
(2, 'Морган Фриман', '1937-05-31'),
(3, 'Джон Траволта', '1954-02-17'),
(4, 'Сэмюэл Л. Джексон', '1948-12-20'),
(5, 'Брюс Уиллис', '1955-03-18'),
(6, 'Курт Рассел', '1951-03-16'),
(7, 'Ума Турман', '1970-04-28'),
(8, 'Алан Рикман', '1946-02-20'),
(9, 'Пол Глисон', '1939-05-03'),
(10, 'Джим Керри', '1962-01-16'),
(11, 'Дженнифер Энистон', '1969-02-10'),
(12, 'Камерон Диас', '1972-08-29'),
(13, 'Шон Янг', '1959-11-19'),
(14, 'Кортни Кокс', '1964-06-14');

-- --------------------------------------------------------

--
-- Структура таблицы `film`
--

CREATE TABLE `film` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `studio_id` int(11) DEFAULT NULL,
  `realese` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `film`
--

INSERT INTO `film` (`id`, `title`, `studio_id`, `realese`) VALUES
(1, 'Побег из Шоушенка', 2, 1994),
(2, 'Криминальное чтиво', 1, 1994),
(3, 'Омерзительная восьмерка', 4, 2015),
(5, 'Брюс всемогущий', 5, 2003),
(6, 'Маска', 7, 1994),
(7, 'Эйс Вентура', 7, 1996),
(8, ' Крепкий Орешек', 4, 1988);

-- --------------------------------------------------------

--
-- Структура таблицы `film_actor`
--

CREATE TABLE `film_actor` (
  `actors_id` int(11) DEFAULT NULL,
  `films_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `film_actor`
--

INSERT INTO `film_actor` (`actors_id`, `films_id`) VALUES
(5, 2),
(7, 2),
(3, 2),
(4, 2),
(1, 1),
(2, 1),
(4, 3),
(6, 3),
(10, 5),
(11, 5),
(2, 5),
(10, 6),
(12, 6),
(10, 7),
(13, 7),
(14, 7),
(5, 8),
(8, 8),
(9, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `studio`
--

CREATE TABLE `studio` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `studio`
--

INSERT INTO `studio` (`id`, `title`) VALUES
(1, 'Miramax'),
(2, 'Columbia Pictures'),
(4, '20th Century Fox'),
(5, 'Universal Studios'),
(7, 'Warner Brothers'),
(8, 'Paramount Pictures');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id`),
  ADD KEY `film_studio_id_fk` (`studio_id`);

--
-- Индексы таблицы `film_actor`
--
ALTER TABLE `film_actor`
  ADD KEY `actor_film_actor_id_fk` (`actors_id`),
  ADD KEY `actor_film_film_id_fk` (`films_id`);

--
-- Индексы таблицы `studio`
--
ALTER TABLE `studio`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `actor`
--
ALTER TABLE `actor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `film`
--
ALTER TABLE `film`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `studio`
--
ALTER TABLE `studio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `film`
--
ALTER TABLE `film`
  ADD CONSTRAINT `film_studio_id_fk` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`id`);

--
-- Ограничения внешнего ключа таблицы `film_actor`
--
ALTER TABLE `film_actor`
  ADD CONSTRAINT `actor_film_actior_id_fk` FOREIGN KEY (`actors_id`) REFERENCES `actor` (`id`),
  ADD CONSTRAINT `actor_film_film_id_fk` FOREIGN KEY (`films_id`) REFERENCES `film` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
