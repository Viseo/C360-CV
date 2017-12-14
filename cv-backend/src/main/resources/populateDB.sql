INSERT INTO languages VALUES (1,'Français');
INSERT INTO type_missions (id,label) VALUES
  (1,'Séminaire');
INSERT INTO users (last_update_date,id, first_name, last_name, mail, login, position, experience, birth_date, hobbies, telephone,password, picture, admin) VALUES
  (TIMESTAMP 'yesterday', 200000, 'GU', 'Leo', 'test1@test.com','AAA1221','Stagiaire','3','01/01/1994','mastermind actions','0614253647','bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a','../../static/users/maxime.png','false');
INSERT INTO clients (id,label, domain, description) VALUES
  (1,'Henri','CONSEIL','Projet pour Henri comme ilearning et un supermarché virtuel');
INSERT INTO missions (id, title, begin_date, end_date, client_id, description, type_id) VALUES
  (1,'Viseo Intern','01/01/2016','01/01/2017','1','Développement du démonstrateur DigiMarket','1');
-- INSERT INTO missions (id, title, begin_date, end_date, client_id, description, type_id) VALUES
--   (2,'DigiMarket','06/15/2017','07/15/2017','2','Implémentation nouvel espace de vente','1');
INSERT INTO users_missions VALUES (200000,1);
-- INSERT INTO users_missions VALUES (200000,2);
INSERT INTO users_languages (user_id, language_id) VALUES
  (200000,1);
INSERT INTO skills (id, label, domain) VALUES
  (1,'Taiga','Outils');
INSERT INTO missions_skills(missions_id, skills_id) VALUES (1, 1);

/*
INSERT INTO users (first_name, last_name, mail, login, position, experience, birth_date, hobbies, telephone,password, picture, admin) VALUES
  ('Maxime', 'Zarrin', 'maxime.zarrin@viseo.com','MZA4571','Stagiaire','3','10/06/1994','mastermind actions','0614253647','fd907eb6a93e49bb0ca5aa22f68bb2d66e0218dc','../../static/users/maxime.png','false'),
  ('Thomas','Naulin','thomas.naulin@viseo.com','TNA7852','Sénior','7','06/15/1994','Sous-fifre du mastermind','0669584714','7c4a8d09ca3762af61e59520943dc26494f8941b','../../static/users/thomas.png','true'),
  ('Geoffrey','Ehrmann','geoffrey.ehrmann@viseo.com','GEH1524','Manager','14','10/25/1994','pizzas','0678451223','9cf95dacd226dcf43da376cdb6cbba7035218921','../../static/users/geoffrey.png','true'),
  ('Farah','Touati','farah.touati@viseo.com','FTO5968','Junior','0','08/28/1994','lecture','0695157535','7c4a8d09ca3762af61e59520943dc26494f8941b','../../static/users/mocha.svg','false'),
  ('Admin','Admin','admin.admin@viseo.com','ADM1234','Sénior','20','01/01/1970','développer des applis en dormant','0601010101','dd94709528bb1c83d08f3088d4043f4742891f4f','../../static/users/ReverseFlash_wallapaper.png','true'),
  ('Emmanuelle','Lerandy','lerandy@viseo.com','ELE3642','Sénior','12','07/16/1994','Aller chez le kiné','0601010101','dd94709528bb1c83d08f3088d4043f4742891f4f','../../static/users/emma.png','true'),
  ('Lydia','Ouamar','ouamar@viseo.com','LOU0000','Stagiaire','0','01/01/1970','hein','0679218948','02acd07068be87c6ace4ea97079c406d3e8da7fd','../../static/users/mocha.svg','false'),
  ('Rocky','Balboa','tin.tintintin@tintin.tiiiin','RBA9999','Sénior','45','01/01/1970','coup de latte dans la gueule','0679218948','c4ae472af23bf4650c11980c0691608b9ea3da12','../../static/users/rocky.png','true');


INSERT INTO languages (label) VALUES
  ('Anglais'),
  ('Espagnol'),
  ('Allemand'),
  ('Portugais'),
  ('Vietnamien'),
  ('Japonais'),
  ('Français');

INSERT INTO users_languages (user_id, language_id) VALUES
  ('1','1'),
  ('1','2'),
  ('2','3'),
  ('3','4'),
  ('4','5'),
  ('2','6'),
  ('5','1'),
  ('5','7'),
  ('5','4');

INSERT INTO clients (label, domain, description) VALUES
  ('Henri','CONSEIL','Projet pour Henri comme ilearning et un supermarché virtuel'),
  ('Orange','FINANCE','Interface pour faire des traitements de données , big data'),
  ('Société Générale','BANQUE','Gestion de données pour faire du prétrading et regarder les "data scientists" perdre l argent du peuple');

INSERT INTO type_missions (label) VALUES
  ('Mission'),
  ('Séminaire');

INSERT INTO missions (title, begin_date, end_date, client_id, description, type_id) VALUES
  ('Viseo Intern','02/15/2017','07/22/2017','1','Développement du démonstrateur DigiMarket','1'),
  ('Collaborateur 360','06/01/2017','07/20/2017','3','Outils pour que les RH travaillent','2'),
  ('DigiMarket','06/15/2017','07/15/2017','2','Implémentation nouvel espace de vente','1'),
  ('i-learning','06/04/2017','06/12/2017','1','Jeu pour devenir plus intelligent','1'),
  ('Orange Business','08/15/2016','12/04/2016','1','Génération de la carte bancaire à empreintes digitales ','1'),
  ('Orange Business Suite','05/01/2017','06/20/2017','3','Gestion application pour clients mécontents','2'),
  ('Chez Moi','06/22/2017','07/31/2017','2','Développement interface de bord pour voiture autonome','1'),
  ('Stage de 3ème','02/25/2017','08/25/2017','3','Stage dobservation de la marine au développement informatique','1');


INSERT INTO skills (label, domain) VALUES
  ('Taiga','Outils'),
  ('Axure','Outils'),
  ('Balsamiq','Outils'),
  ('Jira','Outils'),
  ('Photoshop','Outils'),
  ('React Native','Mobile'),
  ('Android','Mobile'),
  ('iOS','Mobile'),
  ('Cycle en V','Méthodologie'),
  ('Kanban','Méthodologie'),
  ('Lean','Méthodologie'),
  ('Scrum','Méthodologie'),
  ('Lean startup','Méthodologie'),
  ('Less','Méthodologie'),
  ('Rup','Méthodologie'),
  ('Safe','Méthodologie'),
  ('Javascript','Web'),
  ('Angular','Web'),
  ('Bootstrap','Web'),
  ('CSS','Web'),
  ('HTML','Web'),
  ('Python','Web'),
  ('Apache Derby','Base de données'),
  ('Microsoft Access','Base de données'),
  ('Microsoft SQL Server','Base de données'),
  ('MySQL','Base de données'),
  ('Oracle Database','Base de données'),
  ('PostgreSQL','Base de données'),
  ('Bootstrap','Frameworks'),
  ('Cake PHP','Frameworks'),
  ('Google Guava','Frameworks'),
  ('Hibernate','Frameworks'),
  ('JUnit','Frameworks'),
  ('JQuery','Frameworks'),
  ('Node.js','Frameworks'),
  ('Laravel','Frameworks'),
  ('Phalcon','Frameworks'),
  ('PHPUnit','Frameworks'),
  ('Spring','Frameworks'),
  ('Symfony','Frameworks'),
  ('Zend','Frameworks');

INSERT INTO missions_skills (missions_id, skills_id) VALUES
  ('1','4'),
  ('1','3'),
  ('2','2'),
  ('2','1'),
  ('1','8'),
  ('2','9'),
  ('3','22'),
  ('4','18'),
  ('5','35'),
  ('6','40'),
  ('7','11'),
  ('8','27'),
  ('5','31');

INSERT INTO users_missions (user_id, missions_id) VALUES
  (1,2),
  (2,4),
  (3,3),
  (4,1),
  (5,5),
  (1,6),
  (2,7),
  (3,8),
  (4,3),
  (5,6);
*/