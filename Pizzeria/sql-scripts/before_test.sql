DELETE FROM `dish` WHERE `dish_id` BETWEEN -100 AND -1;
INSERT INTO `dish` (`dish_id`, `name`, `price`, `type`, `weight`, `description`, `image_url`) VALUES
(-1, 'Тестовое блюдо 1', 21, 'DRINK', 500, 'Описание 1', NULL),
(-2, 'Тестовое блюдо 2', 153, 'PIZZA', 560, 'Описание 2', NULL),
(-3, 'Тестовое блюдо 3', 163, 'LUNCH', 840, 'Описание 3', NULL),
(-4, 'Тестовое блюдо 4', 159, 'DESSERT', 630, 'Описание 4', NULL),
(-5, 'Тестовое блюдо 5', 178, 'SECOND_DISH', 540, 'Описание 5', NULL);