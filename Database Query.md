Cơ sở dữ liệu

Đầu vào của bài toán sẽ gồm có 2 thực thể là User (người dùng) và Role (quyền hạn). Hai thực thể này có mối liên kết là Many to Many, nên cơ sở dữ liệu sẽ gồm có 3 bảng như sau:

Bảng user:

CREATE TABLE `user` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `email` varchar(255) NOT NULL,
 `password` varchar(60) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
Do mình sẽ lựa chọn thuật toán Bcrypt để mã hóa mật khẩu nên trường password sẽ có độ dài 60 ký tự.

Bảng role:

CREATE TABLE `role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

Bảng user_role:

CREATE TABLE `user_role` (
 `user_id` int(11) NOT NULL,
 `role_id` int(11) NOT NULL,
 PRIMARY KEY (`user_id`,`role_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8
