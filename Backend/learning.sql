-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2024 at 11:56 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `learning`
--

-- --------------------------------------------------------

--
-- Table structure for table `creat_user`
--

CREATE TABLE `creat_user` (
  `id` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone_no` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `creat_user`
--

INSERT INTO `creat_user` (`id`, `username`, `email`, `password`, `phone_no`) VALUES

(1, 'lkdjfs', 'xyz123@gmail.com', '$2y$10$NmagAQa4kZK1v0dTQ.DWu.y0lTHgAaRxk2232j6EBGszyXTOgq/Su', '03924873942'),
(2, 'lkdjfs', 'xyz123@gmail.com', '$2y$10$CTcEio7Iu95dHRyj54/zE.LyssvPPcH8sYKvp0d.jrkuTdYAoRBFy', '03924873942'),
(3, 'gjordflgdj', 'dfdl123@gmail.com', '$2y$10$ZpU3XSvWajVmDDCAfXN4uO997cfcNRzw7cN1Bnl5l/TFsC7o1T8oa', '03924873942'),
(15, 'pppp', 'ppp123@gmail.com', '$2y$10$wTP2Gnwa35p74U5srXo0NelgKQiWaNWCv9ENQ0yXDQ5i9NXa3jbmi', '0392d43422'),
(16, 'ggg', 'ggg123@gmail.com', '193ab5fb176ed32e3f2e2b80b10ba418', '0392d43422'),
(17, 'bilal', 'bilal123@gmail.com', 'c6f057b86584942e415435ffb1fa93d4', '046466464'),
(18, 'ahmed', 'ahmad123@gmail.com', '670b14728ad9902aecba32e22fa4f6bd', '407894646464'),
(19, 'zainab', 'zainab123@gmail.com', '888e931d6360ee143df0d552f955299a', '876464646464'),
(20, 'zainab', 'zainab333@gmail.com', '888e931d6360ee143df0d552f955299a', '84546466464'),
(21, 'zainab', 'zainab444@gmail.com', '888e931d6360ee143df0d552f955299a', '54546464664');

-- --------------------------------------------------------

--
-- Table structure for table `interest`
--

CREATE TABLE `interest` (
  `id` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `one` varchar(100) NOT NULL,
  `two` varchar(100) NOT NULL,
  `three` varchar(100) NOT NULL,
  `four` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interest`
--

INSERT INTO `interest` (`id`, `username`, `one`, `two`, `three`, `four`) VALUES
(1, 'lkdjfs', 'algorithm', 'data stuctures', 'web', 'testing'),
(2, 'lkdjfs', 'Algorithm', 'no', 'no', 'Testing'),
(3, 'bilal', 'Algorithm', 'no', 'no', 'Testing'),
(4, 'lkdjfs', 'Algorithm', 'no', 'Web Development', 'Testing'),
(5, 'zainab', 'Algorithm', 'Data Structures', 'Web Development', 'no'),
(6, 'zainab', 'Algorithm', 'Data Structures', 'Web Development', 'no'),
(7, 'zainab', 'AI', 'no', 'no', 'no');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `creat_user`
--
ALTER TABLE `creat_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `interest`
--
ALTER TABLE `interest`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `creat_user`
--
ALTER TABLE `creat_user`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `interest`
--
ALTER TABLE `interest`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
