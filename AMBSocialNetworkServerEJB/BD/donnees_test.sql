delete from est_ami;
delete from commentaire;
delete from utilisateur;
delete from centre_interet;

insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('TANGUY',    'Philippe',    'philippe.tanguy@telecom-bretagne.eu',  'amb', 'Beau et riche...',             false, false); -- 01
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('SIMONNET',  'Mathieu',     'mathieu.simonnet@telecom-bretagne.eu', 'amb', 'Assez beau et assez riche...', false, false); -- 02
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('TURPIN',    'Mathieu',     'mathieu.turpin@telecom-bretagne.eu',   'amb', 'Beau, riche et jeune !!!',     false, false); -- 03
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('DUPONT',    'Marcel',      'marcel.dupont@quelquepart.com',        'amb', 'Utilisateur lambda',           false, false); -- 04
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('DURAND',    'ROBERT',      'robert.durand@quelquepart.com',        'amb', 'Utilisateur lambda',           false, false); -- 05
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('DUSCHMOLL', 'Joséphine',   'josephine.duschmoll@quelquepart.com',  'amb', 'Utilisateur lambda',           false, false); -- 06
insert into utilisateur(nom,prenom,email,mot_de_passe,description,partage_position,partage_position_public) values ('TAGADA',    'Tsoin-Tsoin', 'tsoin-tsoin.tagada@quelquepart.com',   'amb', 'Utilisateur lambda',           false, false); -- 07

insert into est_ami values (6, 4); -- DUSCHMOLL -> DUPONT
insert into est_ami values (6, 5); -- DUSCHMOLL -> DURAND
insert into est_ami values (6, 7); -- DUSCHMOLL -> TAGADA
insert into est_ami values (1, 7); -- TANGUY    -> TAGADA

insert into centre_interet(nom,description,latitude,longitude) values ('carburant',      'Carburant',                          '48.377833', '-4.489533');
insert into centre_interet(nom,description,latitude,longitude) values ('ordure',         'Récupération des ordures ménagères', '48.378883', '-4.486483');
insert into centre_interet(nom,description,latitude,longitude) values ('ordure',         'Récupération des ordures ménagères', '48.380883', '-4.490665');
insert into centre_interet(nom,description,latitude,longitude) values ('wc',             'Toilettes',                          '48.378583', '-4.4869');
insert into centre_interet(nom,description,latitude,longitude) values ('wc',             'Toilettes',                          '48.38065',  '-4.4899');
insert into centre_interet(nom,description,latitude,longitude) values ('douche',         'Douches',                            '48.3809',   '-4.4897');
insert into centre_interet(nom,description,latitude,longitude) values ('douche',         'Douches',                            '48.378233', '-4.486466');
insert into centre_interet(nom,description,latitude,longitude) values ('supermarche',    'Supermarché',                        '48.380216', '-4.491166');
insert into centre_interet(nom,description,latitude,longitude) values ('supermarche',    'Supermarché',                        '48.380366', '-4.490766');
insert into centre_interet(nom,description,latitude,longitude) values ('manutention',    'Zone de manutention',                '48.3797',   '-4.4913');
insert into centre_interet(nom,description,latitude,longitude) values ('capitainerie',   'Capitainerie',                       '48.380816', '-4.49');
insert into centre_interet(nom,description,latitude,longitude) values ('capitainerie',   'Douane',                             '48.3824',   '-4.486216');
insert into centre_interet(nom,description,latitude,longitude) values ('parking',        'Parking',                            '48.38046',  '-4.4911');
insert into centre_interet(nom,description,latitude,longitude) values ('visiteur',       null,                                 '48.37815',  '-4.4879');
insert into centre_interet(nom,description,latitude,longitude) values ('visiteur',       null,                                 '48.377266', '-4.48905');
insert into centre_interet(nom,description,latitude,longitude) values ('administration', 'administration',                     '48.3823',   '-4.48655');
