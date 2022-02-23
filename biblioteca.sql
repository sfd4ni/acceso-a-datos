-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-02-2022 a las 18:40:57
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `autorid` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nacionalidad` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `autores`
--

INSERT INTO `autores` (`autorid`, `nombre`, `apellidos`, `nacionalidad`) VALUES
(1, 'Edgar', 'Alan Poe', 'estadounidense'),
(2, 'Miguel', 'de Cervantes Saavedra', 'espanol'),
(3, 'Franz', 'Kafka', 'checho'),
(5, 'Gabriel', 'García Márquez', 'colombiano'),
(8, 'Jane', 'Austen', 'británica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor_libro`
--

CREATE TABLE `autor_libro` (
  `autorlibroid` int(11) NOT NULL,
  `fklibroid` int(11) NOT NULL,
  `fkautorid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `autor_libro`
--

INSERT INTO `autor_libro` (`autorlibroid`, `fklibroid`, `fkautorid`) VALUES
(16, 1, 2),
(1, 3, 3),
(36, 4, 3),
(37, 4, 5),
(38, 4, 8),
(4, 5, 3),
(5, 5, 5),
(6, 5, 8),
(33, 6, 2),
(34, 6, 3),
(35, 6, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `clienteid` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `direccion` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`clienteid`, `nombre`, `apellidos`, `direccion`) VALUES
(1, 'Daniel', 'Barroso Rocío', 'Camino la arbeja'),
(2, 'Roberto', 'Sandez', 'Camino la arbeja'),
(3, 'Ramón', 'Sandez', 'Camino la arbeja'),
(5, 'Sara', 'González', 'Avda Constitución nº 190'),
(6, 'Silvia', 'Martínez Sosa', 'Camino Carrero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `ejemplarid` int(11) NOT NULL,
  `localizacion` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `fklibroid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`ejemplarid`, `localizacion`, `fklibroid`) VALUES
(1, '5E', 1),
(5, '7W', 1),
(6, '7A', 3),
(7, '8E', 3),
(8, '9O', 3),
(9, '3E', 4),
(10, '4Y', 4),
(11, '4E', 5),
(13, '7H', 6),
(14, '6A', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `libroid` int(11) NOT NULL,
  `editorial` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `titulo` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`libroid`, `editorial`, `titulo`) VALUES
(1, 'fortuna', 'Don Quijote de La Mancha'),
(3, 'Lee', 'La metamorfosis'),
(4, 'Livrea', 'Lo que el viento se dejó'),
(5, 'Livrea', 'Orgullo y Prejuicio'),
(6, 'Robusta', 'La Sagrada Familia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operadores`
--

CREATE TABLE `operadores` (
  `operadorid` int(11) NOT NULL,
  `nick` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `password` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `operadores`
--

INSERT INTO `operadores` (`operadorid`, `nick`, `password`) VALUES
(1, 'admin', '$2a$10$JPloab7MjcINCAz84Irj5O4jxbmi0AdLMWW4bE1AhupyjKmHk9e.e'),
(3, 'rigobe', '$2a$10$bXwm0LU8KFDjKeBSrwMD8usDYJjSPnFJve/e2.OiioiLY1wp73PsK');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `prestamoid` int(11) NOT NULL,
  `fkejemplarid` int(11) NOT NULL,
  `fkclienteid` int(11) NOT NULL,
  `fechaprestamo` bigint(20) NOT NULL,
  `fechadevolucion` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`prestamoid`, `fkejemplarid`, `fkclienteid`, `fechaprestamo`, `fechadevolucion`) VALUES
(1, 1, 1, 1640995200000, 1646265600000),
(2, 1, 1, 1672531200000, 1711238400000),
(11, 8, 5, 1640995200000, 1640995200000),
(12, 5, 5, 1640995200000, 1640995200000),
(14, 5, 5, 1645574400000, 1640995200000),
(15, 10, 5, 1645574400000, NULL),
(16, 11, 2, 1645574400000, 1640995200000),
(17, 5, 3, 1645574400000, 1803945600000),
(18, 11, 6, 1645574400000, 1648080000000),
(21, 5, 1, 1645574400000, 1710806400000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`autorid`);

--
-- Indices de la tabla `autor_libro`
--
ALTER TABLE `autor_libro`
  ADD PRIMARY KEY (`autorlibroid`),
  ADD UNIQUE KEY `fklibroid` (`fklibroid`,`fkautorid`),
  ADD KEY `fkautorid` (`fkautorid`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`clienteid`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`ejemplarid`),
  ADD KEY `ejemplares_ibfk_1` (`fklibroid`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`libroid`);

--
-- Indices de la tabla `operadores`
--
ALTER TABLE `operadores`
  ADD PRIMARY KEY (`operadorid`),
  ADD UNIQUE KEY `nick` (`nick`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`prestamoid`),
  ADD UNIQUE KEY `fkejemplarid` (`fkejemplarid`,`fkclienteid`,`fechaprestamo`),
  ADD KEY `prestamos_ibfk_1` (`fkclienteid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autores`
--
ALTER TABLE `autores`
  MODIFY `autorid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `autor_libro`
--
ALTER TABLE `autor_libro`
  MODIFY `autorlibroid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `clienteid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `ejemplarid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `libroid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `operadores`
--
ALTER TABLE `operadores`
  MODIFY `operadorid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `prestamoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `autor_libro`
--
ALTER TABLE `autor_libro`
  ADD CONSTRAINT `autor_libro_ibfk_1` FOREIGN KEY (`fkautorid`) REFERENCES `autores` (`autorid`),
  ADD CONSTRAINT `autor_libro_ibfk_2` FOREIGN KEY (`fklibroid`) REFERENCES `libros` (`libroid`);

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `ejemplares_ibfk_1` FOREIGN KEY (`fklibroid`) REFERENCES `libros` (`libroid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`fkclienteid`) REFERENCES `clientes` (`clienteid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`fkejemplarid`) REFERENCES `ejemplares` (`ejemplarid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
