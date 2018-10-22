create table tab_user (
   id INT NOT NULL AUTO_INCREMENT,
   user_name VARCHAR(100) NOT NULL,
   PRIMARY KEY ( id )
);



DROP PROCEDURE IF EXISTS `pro_num_user`;
delimiter ;;
CREATE PROCEDURE `pro_num_user`(IN _name varchar(10) ,OUT count_num INT)
    READS SQL DATA
BEGIN
    SELECT COUNT(*) INTO count_num FROM tab_user WHERE user_name=_name;
END
 ;;
delimiter ;