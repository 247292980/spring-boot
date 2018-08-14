INSERT INTO `role`.`sys_role` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `role`.`sys_role` (`id`, `name`) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO `role`.`sys_user` (`id`, `password`, `username`) VALUES ('1', '1', '1');
INSERT INTO `role`.`sys_user` (`id`, `password`, `username`) VALUES ('2', '2', '2');

INSERT INTO `role`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('1', '1');
INSERT INTO `role`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('2', '2');
