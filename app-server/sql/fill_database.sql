-- TODO DELETE UNUSED TABLES
USE website;

INSERT INTO `role` VALUES ('USER');
INSERT INTO `role` VALUES ('ADMIN');
INSERT INTO `role` VALUES ('COOK');


INSERT INTO `ingredient` VALUES (1,'tomate');
INSERT INTO `ingredient` VALUES (2,'oignon');
INSERT INTO `ingredient` VALUES (3,'poulet');
INSERT INTO `ingredient` VALUES (4,'boeuf');

INSERT INTO `user` VALUES(1,'t@hotmail.fr','kurosaki', 'password');
INSERT INTO `user` VALUES(2,'b@hotmail.fr','kuchiki', 'password');
INSERT INTO `user` VALUES(3,'a@hotmail.fr','abarai', 'password');
INSERT INTO `user` VALUES(4,'v@hotmail.fr','sousuke', 'password');

INSERT INTO `user_role` VALUES(1,'COOK');
INSERT INTO `user_role` VALUES(2,'COOK');
INSERT INTO `user_role` VALUES(2,'USER');
INSERT INTO `user_role` VALUES(3,'USER');
INSERT INTO `user_role` VALUES(4,'USER');

INSERT INTO `pizza` VALUES (1,'calzone', 'voici une pizza',1050, 1);
INSERT INTO `pizza` VALUES (2,'campagnarde', 'voici une autre pizza' ,1150, 2);
INSERT INTO `pizza` VALUES (3,'orientale', 'voici une pizza',1350, 1);
INSERT INTO `pizza` VALUES (4,'veggie', 'voici une pizza', 1350, 1);
INSERT INTO `pizza` VALUES (5,'supreme', 'voici une pizza', 1350, 1);
INSERT INTO `pizza` VALUES (6,'spicy', 'voici une pizza', 1350, 2);

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

