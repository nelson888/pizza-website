USE website;

INSERT INTO `role` VALUE ('USER');
INSERT INTO `role` VALUE ('ADMIN');


INSERT INTO `ingredient` VALUES (1,'tomate');
INSERT INTO `ingredient` VALUES (2,'oignon');
INSERT INTO `ingredient` VALUES (3,'poulet');
INSERT INTO `ingredient` VALUES (4,'boeuf');

INSERT INTO `user` VALUES(1,'t@hotmail.fr','kurosaki', 'ichigo', 'password');
INSERT INTO `user` VALUES(2,'b@hotmail.fr','kuchiki', 'rukia', 'password');
INSERT INTO `user` VALUES(3,'a@hotmail.fr','abarai', 'renji', 'password');
INSERT INTO `user` VALUES(4,'v@hotmail.fr','sousuke', 'aizen', 'password');

INSERT INTO `user_role` VALUES(1,'COOK');
INSERT INTO `user_role` VALUES(2,'COOK');
INSERT INTO `user_role` VALUES(2,'USER');
INSERT INTO `user_role` VALUES(3,'USER');
INSERT INTO `user_role` VALUES(4,'USER');

INSERT INTO `pizza` VALUES (1,'calzone',1050, true, 1);
INSERT INTO `pizza` VALUES (2,'campagnarde',1150, true, 2);
INSERT INTO `pizza` VALUES (3,'orientale',1350, true, 1);
INSERT INTO `pizza` VALUES (4,'veggie',1350, true, 1);
INSERT INTO `pizza` VALUES (5,'supreme',1350, true, 1);
INSERT INTO `pizza` VALUES (6,'spicy',1350, true, 2);

-- tomate
INSERT INTO `pizza_ingredient` VALUES(1,1);
INSERT INTO `pizza_ingredient` VALUES(4,1);
INSERT INTO `pizza_ingredient` VALUES(6,1);

-- oignon
INSERT INTO `pizza_ingredient` VALUES(2,2);
INSERT INTO `pizza_ingredient` VALUES(3,2);

-- poulet
INSERT INTO `pizza_ingredient` VALUES(2,3);

-- boeuf
INSERT INTO `pizza_ingredient` VALUES(3,4);
INSERT INTO `pizza_ingredient` VALUES(5,4);
INSERT INTO `pizza_ingredient` VALUES(6,4);

INSERT INTO `order_table` VALUES(1,1, 'DELIVERY');
INSERT INTO `order_pizza` VALUES(1,1);
