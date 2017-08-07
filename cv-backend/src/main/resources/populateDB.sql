INSERT INTO users (first_name, last_name, mail, login, birth_date, hobbies, telephone,password) VALUES
  ('Maxime', 'Zarrin', 'maxime.zarrin@viseo.com','MZA4571','10/06/1994','mastermind actions','0614253647','reverseflash'),
  ('Thomas','Naulin','thomas.naulin@viseo.com','TNA7852','06/15/1994','Sous-fifre du mastermind','0669584714',''),
  ('Geoffrey','Ehrmann','geoffrey.ehrmann@viseo.com','GEH1524','10/25/1994','pizzas','0678451223',''),
  ('Farah','Touati','farah.touati@viseo.com','FTO5968','08/28/1994','lecture','0695157535','');


INSERT INTO languages (label) VALUES
  ('Anglais'),
  ('Espagnol'),
  ('Allemand'),
  ('Portugais'),
  ('Japonais');

INSERT INTO users_languages (user_id, language_id) VALUES
  ('1','1'),
  ('1','2'),
  ('2','3'),
  ('3','4'),
  ('4','5');

INSERT INTO clients (label, description) VALUES
  ('Henri','Projet pour Henri'),
  ('Orange','Interface pour data'),
  ('Société Générale','Gestion pour pré trading');

INSERT INTO type_missions (label) VALUES
  ('Mission'),
  ('Séminaire');

INSERT INTO missions (title, begin_date, end_date, client_id, description, type_id) VALUES
  ('Viseo Intern','02/15/2017','07/22/2017','1','Développement du démonstrateur DigiMarket','1'),
  ('Collaborateur 360','06/01/2017','07/20/2017','3','Outils pour que les RH travaillent','2'),
  ('DigiMarket','06/15/2017','07/15/2017','2','Implémentation nouvel espace de vente','1'),
  ('i-learning','06/04/2017','06/12/2017','3','Jeu pour devenir plus intelligent','1');

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
  ('Python','Web');

INSERT INTO missions_skills (missions_id, skills_id) VALUES
  ('1','4'),
  ('1','3'),
  ('2','2'),
  ('2','1'),
  ('2','3');

INSERT INTO users_missions (user_id, missions_id) VALUES
  (1,2),
  (1,4),
  (2,3),
  (4,1);