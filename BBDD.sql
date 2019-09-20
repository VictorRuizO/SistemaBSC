-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-09-2019 a las 14:43:47
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_bsc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `di` varchar(15) NOT NULL,
  `nombres` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `fecha_nac` date DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `contrasena` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`di`, `nombres`, `apellidos`, `fecha_nac`, `email`, `contrasena`) VALUES
('admin', 'admin', 'admin', '1999-05-01', 'mail@mail.com', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE `area` (
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `area`
--

INSERT INTO `area` (`codigo`, `nombre`, `descripcion`) VALUES
('0001', 'Financiero', 'Este es el área financiera de la empresa'),
('0002', 'Procesos Internos', 'Este es el área de los procesos internos de la empresa'),
('0003', 'Cliente', 'Este es el área enfocada en los clientes de la empresa'),
('0004', 'Aprendizaje y Crecimiento', 'Este es el área de aprendizaje y crecimiento de la empresa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `indicador`
--

CREATE TABLE `indicador` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(600) NOT NULL,
  `cod_objetivo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `indicador`
--

INSERT INTO `indicador` (`codigo`, `descripcion`, `cod_objetivo`) VALUES
(1, 'Ganancias netas obtenidas', 1),
(2, 'Las irregularidades', 2),
(3, 'Area cubierta en la region', 3),
(4, 'Esfuerzo realizado por producto fabricado', 4),
(5, 'Numero de clienes atendidos en un area determinada', 5),
(6, 'Popularidad de la empresa', 6),
(7, 'Visibilidad de la empresa en medios electronicos', 7),
(8, 'Creacion de equipos directivos y de docentes para compartir el conosimineto generado', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `iniciativa`
--

CREATE TABLE `iniciativa` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(600) NOT NULL,
  `cod_objetivo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `iniciativa`
--

INSERT INTO `iniciativa` (`codigo`, `descripcion`, `cod_objetivo`) VALUES
(1, ':\'(', 1),
(2, 'Al tener menos irregularidades habra mas claridad y el trabajo sera un poco mas sencillo', 2),
(3, 'General mas ingresos y crecer com empresa', 3),
(4, 'Mas productos con menos esfuerzo realizado', 4),
(5, 'Mas establecimientos genera mas trabajo y mas empleados', 5),
(6, 'Al tener mas clientes fieles las ganacias aumentan', 6),
(7, 'A mayor visibilidad mas probabilidad de nuevas vinculaciones de clientes', 7),
(8, 'Compartir el conocimiento de la empresa en las instituciones educativas', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `meta`
--

CREATE TABLE `meta` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(600) NOT NULL,
  `cod_objetivo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `meta`
--

INSERT INTO `meta` (`codigo`, `descripcion`, `cod_objetivo`) VALUES
(1, 'Generar un mayor ingreso en la empresa para el siguiente periodo', 1),
(2, 'Reducir al mínimo los gastos superfluos y recortar el déficit en al menos un 40%.', 1),
(3, 'Reducir las irregularidades del departamento de finanzas', 2),
(4, 'Ampliacion de la empresa', 3),
(5, 'Automatizar por completo el proceso de produccion para asi satisfacer la demanda', 4),
(6, 'Aumentar el nuemro de clientes y por ende el nuemro de ingresos', 5),
(7, 'Crear mas confiaza con el cliente haciendo que este se vuelva un cliente fiel a la compañia', 6),
(8, 'Llegar a mas personas usando plataformas web', 7),
(9, 'Generar transormacion en las aulas', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objetivo`
--

CREATE TABLE `objetivo` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(600) NOT NULL,
  `cod_area` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `objetivo`
--

INSERT INTO `objetivo` (`codigo`, `descripcion`, `cod_area`) VALUES
(1, 'Aumentar las ventas\nUn factor clave de éxito importante que es vital para cualquier compañía es aumentar las ventas.', '0001'),
(2, 'Preparar a los departamentos de finanzas y de cobro para la auditoría anual con el menor margen de irregularidades posible.', '0001'),
(3, 'Incrementar el personal fijo contratado y ampliar las coordinaciones existentes a nivel regional.', '0002'),
(4, 'Rentabilizar el modelo de producción hasta convertirlo en un sistema autónomo.', '0002'),
(5, 'Crear puestos de trabajo de manera estable en el lugar de ubicación del establecimiento y para personas residentes en sus cercanías.', '0003'),
(6, 'Mejorar la estrategia de fidelización de clientes', '0003'),
(7, 'Incrementar la visibilidad de la empresa en Internet aumentando los canales', '0004'),
(8, 'Apoyar a las instituciones educativas mediante el asesoramiento y formación al equipo directivo y docente sobre las habilidades tecnológicas y metodológicas necesarias para generar la transformación en las aulas.', '0004');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `di` varchar(15) NOT NULL,
  `nombres` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `fecha_nac` date DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `contrasena` varchar(20) NOT NULL,
  `cod_area` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`di`, `nombres`, `apellidos`, `fecha_nac`, `email`, `contrasena`, `cod_area`) VALUES
('1116253695', 'Francisco', 'Lopeda', '1987-04-17', 'fffrancisco@gmail.com', '159', '0004'),
('1116542321', 'Daniela', 'Villanueva Perez', '1997-11-13', 'danielita5000@gmail.com', '1234', '0001'),
('1126152365', 'Andres Mauricio', 'Pineda Pineda', '1999-01-10', 'hanros112@hotmail.com', '1234', '0002'),
('201561147', 'Marcela Francisca', 'Aragon Villa', '1989-05-18', 'marcelita@gmail.com', '1234', '0003');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`di`);

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `indicador`
--
ALTER TABLE `indicador`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK5` (`cod_objetivo`);

--
-- Indices de la tabla `iniciativa`
--
ALTER TABLE `iniciativa`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK6` (`cod_objetivo`);

--
-- Indices de la tabla `meta`
--
ALTER TABLE `meta`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK4` (`cod_objetivo`);

--
-- Indices de la tabla `objetivo`
--
ALTER TABLE `objetivo`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK3` (`cod_area`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`di`),
  ADD KEY `FK1` (`cod_area`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `indicador`
--
ALTER TABLE `indicador`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `iniciativa`
--
ALTER TABLE `iniciativa`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `meta`
--
ALTER TABLE `meta`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `objetivo`
--
ALTER TABLE `objetivo`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `indicador`
--
ALTER TABLE `indicador`
  ADD CONSTRAINT `FK5` FOREIGN KEY (`cod_objetivo`) REFERENCES `objetivo` (`codigo`);

--
-- Filtros para la tabla `iniciativa`
--
ALTER TABLE `iniciativa`
  ADD CONSTRAINT `FK6` FOREIGN KEY (`cod_objetivo`) REFERENCES `objetivo` (`codigo`);

--
-- Filtros para la tabla `meta`
--
ALTER TABLE `meta`
  ADD CONSTRAINT `FK4` FOREIGN KEY (`cod_objetivo`) REFERENCES `objetivo` (`codigo`);

--
-- Filtros para la tabla `objetivo`
--
ALTER TABLE `objetivo`
  ADD CONSTRAINT `FK3` FOREIGN KEY (`cod_area`) REFERENCES `area` (`codigo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK1` FOREIGN KEY (`cod_area`) REFERENCES `area` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;