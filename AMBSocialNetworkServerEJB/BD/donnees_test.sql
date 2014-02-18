-- Titre :             Insertion de données de test pour la base AMBSocialNetwork
-- Version :           0.1
-- Date création :     16 janvier 2014
-- Date modification : 18 février 2014
-- Auteur :            Philippe Tanguy
-- Description :       À compléter...

-- +----------------------------------------------------------------------------------------------+
-- | Effacement des anciennes valeurs si elle existent                                            |
-- +----------------------------------------------------------------------------------------------+

delete from est_ami;
delete from commentaire;
delete from utilisateur;
delete from service;
delete from poi;
delete from point;

-- +----------------------------------------------------------------------------------------------+
-- | Réinitialisation des séquences                                                               |
-- +----------------------------------------------------------------------------------------------+

select setval('commentaire_id_seq', 1, false);  
select setval('point_id_seq',       1, false);
select setval('utilisateur_id_seq', 1, false);

-- +----------------------------------------------------------------------------------------------+
-- | Insertion de données de test                                                                 |
-- +----------------------------------------------------------------------------------------------+

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

insert into point(latitude,longitude) values ('48.377833', '-4.489533');  --  1
insert into point(latitude,longitude) values ('48.378883', '-4.486483');  --  2
insert into point(latitude,longitude) values ('48.380883', '-4.490665');  --  3
insert into point(latitude,longitude) values ('48.378583', '-4.4869');    --  4
insert into point(latitude,longitude) values ('48.38065',  '-4.4899');    --  5
insert into point(latitude,longitude) values ('48.3809',   '-4.4897');    --  6
insert into point(latitude,longitude) values ('48.378233', '-4.486466');  --  7
insert into point(latitude,longitude) values ('48.380216', '-4.491166');  --  8
insert into point(latitude,longitude) values ('48.380366', '-4.490766');  --  9
insert into point(latitude,longitude) values ('48.3797',   '-4.4913');    -- 10
insert into point(latitude,longitude) values ('48.380816', '-4.49');      -- 11
insert into point(latitude,longitude) values ('48.3824',   '-4.486216');  -- 12
insert into point(latitude,longitude) values ('48.38046',  '-4.4911');    -- 13
insert into point(latitude,longitude) values ('48.37815',  '-4.4879');    -- 14
insert into point(latitude,longitude) values ('48.377266', '-4.48905');   -- 15
insert into point(latitude,longitude) values ('48.3823',   '-4.48655');   -- 16

insert into service(id,type,description) values (1,  'carburant',      'Carburant');
insert into service(id,type,description) values (2,  'ordure',         'Récupération des ordures ménagères');
insert into service(id,type,description) values (3,  'ordure',         'Récupération des ordures ménagères');
insert into service(id,type,description) values (4,  'wc',             'Toilettes');
insert into service(id,type,description) values (5,  'wc',             'Toilettes');
insert into service(id,type,description) values (6,  'douche',         'Douches');
insert into service(id,type,description) values (7,  'douche',         'Douches');
insert into service(id,type,description) values (8,  'supermarche',    'Supermarché');
insert into service(id,type,description) values (9,  'supermarche',    'Supermarché');
insert into service(id,type,description) values (10, 'manutention',    'Zone de manutention');
insert into service(id,type,description) values (11, 'capitainerie',   'Capitainerie');
insert into service(id,type,description) values (12, 'capitainerie',   'Douane');
insert into service(id,type,description) values (13, 'parking',        'Parking');
insert into service(id,type,description) values (14, 'visiteur',       null);
insert into service(id,type,description) values (15, 'visiteur',       null);
insert into service(id,type,description) values (16, 'administration', 'administration');
