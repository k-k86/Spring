INSERT INTO admin_user(user_id,user_password,admin_level)VALUES('admin','$2a$10$9A/9n207QnTJFbh9USIN6eBiqkpzTI1X/Q00bMtsSxj06NmMvVAqG','ADMIN');
INSERT INTO admin_user(user_id,user_password,admin_level)VALUES('user','$2a$10$9A/9n207QnTJFbh9USIN6eBiqkpzTI1X/Q00bMtsSxj06NmMvVAqG','USER');

INSERT INTO department(department_id,department_name)VALUES('000','無所属');
INSERT INTO department(department_id,department_name)VALUES('101','総務部A');
INSERT INTO department(department_id,department_name)VALUES('203','経理部B');
INSERT INTO department(department_id,department_name)VALUES('202','システム開発部B');
INSERT INTO department(department_id,department_name)VALUES('201','総務部B');
INSERT INTO department(department_id,department_name)VALUES('102','システム開発部A');
INSERT INTO department(department_id,department_name)VALUES('103','経理部A');
INSERT INTO department(department_id,department_name)VALUES('500','研修生');
INSERT INTO department(department_id,department_name)VALUES('204','教育部');

INSERT INTO employee(employee_name,employee_name_kana,employee_age,employee_address,employee_mail,employee_phone,employee_hiredate,department_id)VALUES
('田中太郎','タナカタロウ',35,'東京都新宿区1-1-1','tanaka.taro@example.com','090-1234-5678','2010-04-01','000'),
('佐藤花子','サトウハナコ',28,'東京都渋谷区2-2-2','sato.hanako@example.com','080-2345-6789','2015-06-15','101'),
('鈴木一郎','スズキイチロウ',45,'神奈川県横浜市3-3-3','suzuki.ichiro@example.com','070-3456-7890','2005-03-10','201'),
('高橋直子','タカハシナオコ',32,'埼玉県さいたま市4-4-4','takahashi.naoko@example.com','090-4567-8901','2012-07-21','102'),
('伊藤次郎','イトウジロウ',29,'千葉県千葉市5-5-5','ito.jiro@example.com','080-5678-9012','2016-08-30','202'),
('渡辺久美子','ワタナベクミコ',37,'東京都江東区6-6-6','watanabe.kumiko@example.com','070-6789-0123','2011-09-11','203'),
('山本武','ヤマモトタケシ',42,'東京都品川区7-7-7','yamamoto.takeshi@example.com','090-7890-1234','2008-05-01','204'),
('中村美咲','ナカムラミサキ',30,'東京都目黒区8-8-8','nakamura.misaki@example.com','080-8901-2345','2014-11-20','000'),
('小林裕子','コバヤシヒロコ',27,'神奈川県川崎市9-9-9','kobayashi.hiroko@example.com','070-9012-3456','2017-02-28','101'),
('加藤弘','カトウヒロシ',50,'埼玉県所沢市10-10-10','kato.hiroshi@example.com','090-0123-4567','2000-12-01','201'),
('石井敦','イシイアツシ',33,'千葉県船橋市11-11-11','ishii.atsushi@example.com','080-1234-5678','2013-03-15','102'),
('松本玲子','マツモトレイコ',26,'東京都大田区12-12-12','matsumoto.reiko@example.com','070-2345-6789','2018-04-10','202'),
('清水健','シミズケン',38,'東京都杉並区13-13-13','shimizu.ken@example.com','090-3456-7890','2010-01-01','203'),
('斎藤由美','サイトウユミ',31,'神奈川県藤沢市14-14-14','saito.yumi@example.com','080-4567-8901','2013-05-23','204'),
('林健太','ハヤシケンタ',29,'埼玉県川越市15-15-15','hayashi.kenta@example.com','070-5678-9012','2016-07-19','000'),
('森田奈々','モリタナナ',34,'千葉県柏市16-16-16','morita.nana@example.com','090-6789-0123','2011-02-15','101'),
('橋本大輔','ハシモトダイスケ',41,'東京都世田谷区17-17-17','hashimoto.daisuke@example.com','080-7890-1234','2009-03-20','201'),
('山田綾','ヤマダアヤ',36,'東京都中野区18-18-18','yamada.aya@example.com','070-8901-2345','2008-06-25','102'),
('井上翔','イノウエショウ',29,'神奈川県鎌倉市19-19-19','inoue.sho@example.com','090-9012-3456','2016-09-13','202'),
('岡田美香','オカダミカ',32,'埼玉県春日部市20-20-20','okada.mika@example.com','080-0123-4567','2012-08-22','203'),
('菅原陽子','スガワラヨウコ',33,'千葉県成田市21-21-21','sugawara.yoko@example.com','070-1234-5678','2013-10-11','204'),
('福田真一','フクダシンイチ',44,'東京都港区22-22-22','fukuda.shinichi@example.com','090-2345-6789','2007-04-01','000'),
('近藤大樹','コンドウダイキ',39,'東京都文京区23-23-23','kondo.daiki@example.com','080-3456-7890','2008-05-12','101'),
('原田亜希','ハラダアキ',27,'神奈川県相模原市24-24-24','harada.aki@example.com','070-4567-8901','2017-07-07','201'),
('内田輝','ウチダアキラ',48,'埼玉県狭山市25-25-25','uchida.akira@example.com','090-5678-9012','2002-10-10','102'),
('青木絵美','アオキエミ',31,'千葉県市原市26-26-26','aoki.emi@example.com','080-6789-0123','2014-12-12','202'),
('池田信','イケダマコト',34,'東京都足立区27-27-27','ikeda.makoto@example.com','070-7890-1234','2011-11-11','203'),
('長谷川太','ハセガワフトシ',42,'東京都北区28-28-28','hasegawa.futoshi@example.com','090-8901-2345','2006-08-08','204'),
('大塚夏希','オオツカナツキ',28,'神奈川県平塚市29-29-29','otsuka.natsuki@example.com','080-9012-3456','2015-02-02','000'),
('松田亮','マツダリョウ',45,'埼玉県熊谷市30-30-30','matsuda.ryo@example.com','070-0123-4567','2005-06-06','101');
	