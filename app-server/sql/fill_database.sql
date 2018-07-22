USE website;

INSERT INTO `ingredient` VALUES (1,'tomate');
INSERT INTO `ingredient` VALUES (2,'oignon');
INSERT INTO `ingredient` VALUES (3,'poulet');
INSERT INTO `ingredient` VALUES (4,'boeuf');

INSERT INTO `pizza` VALUES (1,'calzone',1050, true);
INSERT INTO `pizza` VALUES (2,'campagnarde',1150, true);
INSERT INTO `pizza` VALUES (3,'orientale',1350, true);
INSERT INTO `pizza` VALUES (4,'veggie',1350, true);
INSERT INTO `pizza` VALUES (5,'supreme',1350, true);
INSERT INTO `pizza` VALUES (6,'spicy',1350, true);

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


INSERT INTO `user` VALUES(1,'t@hotmail.fr','kurosaki', 'ichigo', 'password');
INSERT INTO `user` VALUES(2,'b@hotmail.fr','kuchiki', 'rukia', 'password');
INSERT INTO `user` VALUES(3,'a@hotmail.fr','abarai', 'renji', 'password');
INSERT INTO `user` VALUES(4,'v@hotmail.fr','sousuke', 'aizen', 'password');


INSERT INTO `order_table` VALUES(1,1, 'DELIVERY');
INSERT INTO `order_pizza` VALUES(1,1);