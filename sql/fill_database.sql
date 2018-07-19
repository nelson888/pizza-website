USE website;

INSERT INTO `ingredient` VALUES (1,'tomate');
INSERT INTO `ingredient` VALUES (2,'oignon');
INSERT INTO `ingredient` VALUES (3,'poulet');
INSERT INTO `ingredient` VALUES (4,'boeuf');

INSERT INTO `pizza` VALUES (1,'calzone',1050, true);
INSERT INTO `pizza` VALUES (2,'campagnarde',1150, true);
INSERT INTO `pizza` VALUES (3,'three',1350, true);

INSERT INTO `pizza_ingredient` VALUES(1,1);
INSERT INTO `pizza_ingredient` VALUES(1,2);
INSERT INTO `pizza_ingredient` VALUES(1,3);
INSERT INTO `pizza_ingredient` VALUES(2,2);
INSERT INTO `pizza_ingredient` VALUES(3,3);
INSERT INTO `pizza_ingredient` VALUES(2,4);
