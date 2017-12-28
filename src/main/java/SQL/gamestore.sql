-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2016 at 06:33 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gamestore`
--
CREATE DATABASE IF NOT EXISTS `gamestore` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gamestore`;

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
  `address_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `address_line_1` varchar(50) NOT NULL,
  `address_line_2` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `county_state` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`address_id`, `user_id`, `address_line_1`, `address_line_2`, `city`, `county_state`, `country`) VALUES
(1, 1, '56 Goldbrook Sq', 'CastleKnock', 'Dublin 15', 'Dublin', 'Ireland'),
(2, 2, '123 Bothar Bui', 'Diswellstown Road', 'Clonsilla ', 'Dublin', 'Ireland'),
(3, 3, '20 Rock Road', 'BlackRock', 'BlackRock', 'Dublin', 'Ireland'),
(4, 4, '2 Vernon Drive', ' Clontarf', 'Dublin City', 'Dublin', 'Ireland'),
(5, 5, '2 Moyglare Village', 'Maynooth', 'Maynooth', 'Kildare', 'Ireland');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `total_amount_due` double(10,2) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `order_date` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `total_amount_due`, `total_quantity`, `address_id`, `order_date`) VALUES
(1, 4, 28.20, 2, 4, '2016-12-15'),
(2, 4, 28.28, 1, 4, '2016-12-15'),
(3, 5, 90.38, 3, 5, '2016-12-15');

-- --------------------------------------------------------

--
-- Table structure for table `order_lines`
--

DROP TABLE IF EXISTS `order_lines`;
CREATE TABLE `order_lines` (
  `line_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sale_price` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_lines`
--

INSERT INTO `order_lines` (`line_id`, `order_id`, `product_id`, `quantity`, `sale_price`) VALUES
(1, 1, 10, 1, 10.99),
(2, 1, 2, 1, 17.21),
(3, 2, 9, 1, 28.28),
(4, 3, 6, 1, 24.59),
(5, 3, 7, 1, 14.75),
(6, 3, 4, 1, 51.04);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(75) NOT NULL,
  `product_desc` varchar(1000) NOT NULL,
  `product_price` double(10,2) NOT NULL,
  `vat_percentage` double(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `steam_app_id` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `product_desc`, `product_price`, `vat_percentage`, `stock`) VALUES
(1, 'Overwatch', 'In a time of global crisis, an international task force of heroes banded together to restore peace to a war-torn world. This organization, known as Overwatch, ended the crisis and helped maintain peace for a generation, inspiring an era of exploration, innovation, and discovery.', 39.99, 0.23, 15);

INSERT INTO `products` (`product_id`, `product_name`, `product_desc`, `product_price`, `vat_percentage`, `stock`, `steam_app_id`) VALUES
(2, 'Counter Strike: Global Offensive', 'Counter-Strike: Global Offensive (CS: GO) will expand upon the team-based action gameplay that it pioneered when it was launched 14 years ago. CS: GO features new maps, characters, and weapons and delivers updated versions of the classic CS content (de_dust, etc.).', 13.99, 0.23, 19, 730),
(3, 'Fallout: New Vegas', 'Welcome to Vegas. New Vegas. Enjoy your stay!', 4.78, 0.23, 10, 22380),
(4, 'Sid Meier''s Civilization V: Complete', 'Create, discover, and download new player-created maps, scenarios, interfaces, and more!', 45.17, 0.13, 12, 8930),
(5, 'The Binding of Isaac: Rebirth', 'The Binding of Isaac: Rebirth is a randomly generated action RPG shooter with heavy Rogue-like elements. Following Isaac on his journey players will find bizarre treasures that change Isaacâ??s form giving him super human abilities and enabling him to fight off droves of mysterious creatures, discover secrets ', 14.99, 0.23, 17, 250900),
(6, 'Rust', 'The only aim in Rust is to survive. To do this you will need to overcome struggles such as hunger, thirst and cold. Build a fire. Build a shelter. Kill animals for meat. Protect yourself from other players, and kill them for meat. Create alliances with other players and form a town. Whatever it takes to survive.', 19.99, 0.23, 8, 252490),
(7, 'Lethal League', 'Lethal League is a competitive projectile fighting game where you have to hit an anti-gravity ball into the face of your opponent to win. The ball speeds up with every strike, up to explosively extreme velocities. Play intense local matches with friends and foes, challenge yourself in the singleplayer mode ', 11.99, 0.23, 22, 261180),
(8, 'Grand Theft Auto', 'Expand your criminal empire with a new frontier of illicit profiteering to explore: dominating the cityâ??s trade in stolen luxury vehicles with GTA Online: Import/Export, the latest enterprising extension of the CEO gameplay introduced with Further Adventures in Finance and Felony.', 59.99, 0.23, 11, 271590),
(9, 'Snow: Legendary Pack', 'SNOW is the only open world, winter sports game. Explore a massive mountain, customize your character with clothing and equipment from the biggest brands, and compete in events to be the best.', 22.99, 0.23, 11, 244930),
(10, 'Terraria', 'Dig, fight, explore, build! Nothing is impossible in this action-packed adventure game. Four Pack also available!', 9.99, 0.10, 11, 105600);

-- --------------------------------------------------------

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images` (
  `image_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `image_url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_images`
--

INSERT INTO `product_images` (`image_id`, `product_id`, `image_url`) VALUES
(1, 1, 'image/product_image/overwatch_2.jpg'),
(2, 1, 'image/product_image/overwatch_1.png'),
(3, 2, 'image/product_image/cs_go_1.jpg'),
(4, 2, 'image/product_image/cs_go_2.jpg'),
(5, 3, 'image/product_image/fnv_1.jpg'),
(7, 3, 'image/product_image/fnv_3.jpg'),
(8, 4, 'image/product_image/sid_meiers_civilization_v.jpg'),
(9, 4, 'image/product_image/sid_meiers_civilization_v_1.jpg'),
(10, 5, 'image/product_image/the_binding_of_isaac_rebirth.jpg'),
(11, 5, 'image/product_image/the_binding_of_isaac_rebirth_1.jpg'),
(12, 5, 'image/product_image/the_binding_of_isaac_rebirth_2.gif'),
(13, 6, 'image/product_image/rust.jpg'),
(14, 6, 'image/product_image/rust_1.jpg'),
(15, 6, 'image/product_image/rust_2.jpg'),
(16, 7, 'image/product_image/lethal_league.jpg'),
(17, 7, 'image/product_image/lethal_league_1.png'),
(18, 7, 'image/product_image/lethal_league_2.jpg'),
(19, 8, 'image/product_image/gta_1.jpg'),
(20, 9, 'image/product_image/snow_1.jpg'),
(21, 10, 'image/product_image/ter_1.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(64) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `last_password_change` date NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `pass`, `salt`, `first_name`, `last_name`, `last_password_change`, `is_admin`) VALUES
(1, 'aaronmccabe21', 'aaronmccabe21@gmail.com', 'O8Ugu4P298wbYll5vjMRWCiFTSvgHocTOO0W9clFM9w=', 'HFA1prcbuzVUNxyQBRvvK5rAiazxTA1iAe4f4Fi9/tQ=', 'Aaron', 'McCabe', '2016-12-15', 1),
(2, 'ConnorPakenham', 'connorpakenham@gmail.com', 'ZERnCHMCF0DCuNzQG9M1wZefQrH7NA/ArY9Hhzlxpow=', 'IBlvlk721lD6YGGXLxnV7qhI1UInUCqxrIDXHNLpqHE=', 'Connor', 'Pakenham', '2016-12-15', 1),
(3, 'RenDlny', 'rendlny@gmail.com', 'bIpAzWnqFNkjUHVJIeh4Fn4blWY8alk5tKoW1WHTrY0=', 'h8ry5vXkUI8nYnu+IuXKdZ91uKEGq5h1VsoNNmIqr7E=', 'Lauren', 'Delaney', '2016-12-15', 1),
(4, 'Ally_Lawl96', 'alexlawler@gmail.com', 'dLMVQgwHvx2YX5Rj//R3bk0SokmbEMVMRXI/dc/7M3A=', 'UcpRFHCLFM2CIxr1/xFT1869lpAKot1NrFIBmSJUzC4=', 'Alex', 'Lawler', '2016-12-15', 0),
(5, 'rosara_t96', 'rosara_t96@gmail.com', 'tOmgG8rSIvFRiVhWokdfNKKTCoi8sD5FBbfSUSCqI8U=', 'PmmqHAwkBWtW52jd4EP8XTH/9MZnRp0K2DjN77+qVQE=', 'Rosara', 'Traynor', '2016-12-15', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`address_id`),
  ADD KEY `user_address_fk` (`user_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_order_fk` (`user_id`),
  ADD KEY `address_order_fk` (`address_id`);

--
-- Indexes for table `order_lines`
--
ALTER TABLE `order_lines`
  ADD PRIMARY KEY (`line_id`),
  ADD KEY `order_lines_fk` (`order_id`),
  ADD KEY `product_order_fk` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD UNIQUE KEY `product_name` (`product_name`);

--
-- Indexes for table `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`image_id`),
  ADD KEY `product_images_fk` (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username_uq` (`username`),
  ADD UNIQUE KEY `email_uq` (`email`),
  ADD UNIQUE KEY `salt_uq` (`salt`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `order_lines`
--
ALTER TABLE `order_lines`
  MODIFY `line_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `product_images`
--
ALTER TABLE `product_images`
  MODIFY `image_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `user_address_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `address_order_fk` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`address_id`),
  ADD CONSTRAINT `user_order_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `order_lines`
--
ALTER TABLE `order_lines`
  ADD CONSTRAINT `order_lines_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `product_order_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `product_images_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
