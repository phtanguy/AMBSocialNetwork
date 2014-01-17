delete from utilisateur;
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('TANGUY',    'Philippe',    'philippe.tanguy@telecom-bretagne.eu',  'amb', 'Beau et riche...',             false, false); -- 01
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('SIMONNET',  'Mathieu',     'mathieu.simonnet@telecom-bretagne.eu', 'amb', 'Assez beau et assez riche...', false, false); -- 02
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('TURPIN',    'Mathieu',     'mathieu.turpin@telecom-bretagne.eu',   'amb', 'Beau, riche et jeune !!!',     false, false); -- 03
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('DUPONT',    'Marcel',      'marcel.dupont@quelquepart.com',        'amb', 'Utilisateur lambda',           false, false); -- 04
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('DURAND',    'ROBERT',      'robert.durand@quelquepart.com',        'amb', 'Utilisateur lambda',           false, false); -- 05
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('DUSCHMOLL', 'Joséphine',   'josephine.duschmoll@quelquepart.com',  'amb', 'Utilisateur lambda',           false, false); -- 06
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('TAGADA',    'Tsoin-Tsoin', 'tsoin-tsoin.tagada@quelquepart.com',   'amb', 'Utilisateur lambda',           false, false); -- 07

delete from est_ami;
insert into est_ami values (6, 4); -- DUSCHMOLL -> DUPONT
insert into est_ami values (6, 5); -- DUSCHMOLL -> DURAND
insert into est_ami values (6, 7); -- DUSCHMOLL -> TAGADA
insert into est_ami values (1, 7); -- TANGUY    -> TAGADA
