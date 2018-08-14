#实验数据
CREATE TABLE IF NOT EXISTS t_order_0 (
order_id INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS t_order_item_0 (
item_id INT NOT NULL,
order_id INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS t_order_1 (
order_id INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS t_order_item_1 (
item_id INT NOT NULL,
order_id INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (item_id)
);
INSERT INTO t_order_1 VALUES ('1001', '10');
INSERT INTO t_order_item_0 VALUES ('0', '1001', '10');
INSERT INTO t_order_item_0 VALUES ('1', '1000', '11');
INSERT INTO t_order_item_0 VALUES ('2', '1001', '10');
INSERT INTO t_order_0 VALUES ('1000', '11');
INSERT INTO t_order_item_1 VALUES ('0', '1000', '11');
INSERT INTO t_order_item_1 VALUES ('1', '1000', '11');

