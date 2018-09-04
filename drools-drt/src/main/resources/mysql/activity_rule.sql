

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity_rule
-- ----------------------------
DROP TABLE IF EXISTS `activity_rule`;
CREATE TABLE `activity_rule` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `event` varchar(60) NOT NULL DEFAULT '' COMMENT '事件',
  `rule_value` varchar(200) NOT NULL DEFAULT '' COMMENT '规则值(限200个英文字符)',
  `priority` tinyint(4) NOT NULL DEFAULT '0' COMMENT '规则优先级',
  `awardee_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '奖励发放者的类型',
  `send_award_times` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '发送奖励次数',
  `create_by` varchar(60) NOT NULL DEFAULT 'admin' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT '0001-01-01 01:01:01' COMMENT '创建时间',
  `update_by` varchar(60) DEFAULT NULL COMMENT '修改者',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` char(1) DEFAULT '0' COMMENT '只做标识，不真正删除数据',
  `is_sms_notice` char(1) DEFAULT '0' COMMENT '''是否使用短信通知：0:不使用 1:使用''',
  PRIMARY KEY (`id`),
  KEY `idx_task_id` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='活动任务规则表';

INSERT INTO `drools`.`activity_rule` (`id`, `task_id`, `event`, `rule_value`, `priority`, `awardee_type`, `send_award_times`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `is_sms_notice`) VALUES ('1', '23', 'REGISTER', 'telephone == \"13712750156\"', '0', '1', '-1', 'admin', '0001-01-01 01:01:01', 'lgp', '2018-09-04 16:17:42', '0', '0');
INSERT INTO `drools`.`activity_rule` (`id`, `task_id`, `event`, `rule_value`, `priority`, `awardee_type`, `send_award_times`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `is_sms_notice`) VALUES ('2', '24', 'REGISTER', 'userId== \"123456\"', '0', '2', '-1', 'admin', '0001-01-01 01:01:01', NULL, '2018-09-04 09:18:04', '0', '0');
INSERT INTO `drools`.`activity_rule` (`id`, `task_id`, `event`, `rule_value`, `priority`, `awardee_type`, `send_award_times`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `is_sms_notice`) VALUES ('3', '24', 'REGISTER', 'telephone == \"13712750166\"', '0', '3', '-1', 'admin', '0001-01-01 01:01:01', NULL, '2018-09-04 16:17:24', '0', '0');
