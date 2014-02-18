-- Titre :             Création base AMBSocialNetwork
-- Version :           0.1
-- Date création :     16 janvier 2014
-- Date modification : 18 février 2014
-- Auteur :            Philippe Tanguy
-- Description :       À compléter...

-- +----------------------------------------------------------------------------------------------+
-- | Suppression des tables et des types                                                          |
-- +----------------------------------------------------------------------------------------------+

drop table commentaire;
drop table poi;
drop table service;
drop table point;
drop table est_ami;
drop table utilisateur;

--drop type type_service;
--drop type type_poi;

-- +----------------------------------------------------------------------------------------------+
-- | Création des tables                                                                          |
-- +----------------------------------------------------------------------------------------------+

-- ------------------------------------------------------------------------------------------------

-- Commentaires table "utilisateur" :
--   * Identification de l'utilisateur par l'email (pas de nom de login).
--   * Limitations du partage de position de l'utilisateur :
--     Attribut "partage_position" : true (position partagée), false (non).
--     Attribut "partage_position_public" pris en compte uniquement si le partage
--     de position est à true : true (partage à tous les utilisateurs), false
--     (accessible uniquement à la liste d'amis et aux groupes).
--   * Pour la démo, mots de passe stockés en clair.

create table utilisateur
(
  id                        serial primary key,
  nom                       varchar(30) not null,
  prenom                    varchar(30),
  email                     varchar(70) unique not null,
  mot_de_passe              varchar(10) not null,
  url_avatar                varchar(255),
  description               text,
  partage_position          boolean default false,
  partage_position_public   boolean default false,
  latitude                  real default -1,
  longitude                 real default -1,
  cap                       integer default -1,
  vitesse                   integer default -1
);

-- ------------------------------------------------------------------------------------------------

-- Commentaires table "est_ami" :
--   * L'amitié est à sens unique : u1 déclare qu'il "est ami" avec u2.
--   * Pour une amitié à double sens, u2 doit déclarer qu'il est aussi ami avec u1.
--   * Dans un objectif de simplification, pas d'acquitement.

create table est_ami
(
  utilisateur_source integer not null references utilisateur,
  utilisateur_cible  integer not null references utilisateur,
  primary key (utilisateur_source, utilisateur_cible)
);

-- ------------------------------------------------------------------------------------------------

--create type type_service as enum ('administration', 'capitainerie', 'carburant', 'douche', 'manutention', 'ordure', 'parking', 'supermarche', 'visiteur', 'wc');
--create type type_poi     as enum ('remarque', 'peche', 'securite');

-- ------------------------------------------------------------------------------------------------

create table point
(
  id        serial      primary key,
  latitude  varchar(10) not null,
  longitude varchar(11) not null
);

-- ------------------------------------------------------------------------------------------------

create table service
(
  id          integer primary key references point,
  type        varchar(15) not null check (type in ('administration',
                                                   'capitainerie',
                                                   'carburant',
                                                   'douche',
                                                   'manutention',
                                                   'ordure',
                                                   'parking',
                                                   'supermarche',
                                                   'visiteur',
                                                   'wc')),
  description text
);

-- ------------------------------------------------------------------------------------------------

create table poi
(
  id   integer primary key references point,
  type varchar(8) not null check (type in ('remarque',
                                           'peche',
                                           'securite'))
);

-- ------------------------------------------------------------------------------------------------

-- V1 de la table
-- create table commentaire
-- (
--   utilisateur                integer not null references utilisateur,
--   centre_interet             integer not null references utilisateur,
--   contenu                    text,
--   url_photo                  varchar(255),
--   date_publication           date,
--   partage_commentaire        boolean default false,
--   partage_commentaire_public boolean default true,
--   primary key (utilisateur, centre_interet)
-- );

-- Commentaires table "commentaire" :
--   * Limitations du partage du commentaire :
--     Attribut "partage_commentaire" : true (commentaire partagé), false (non).
--       ==> un commentaire est forcément partagé : soit en public soit en privé.
--     Attribut "partage_commentaire_public" pris en compte uniquement si le partage
--     de commentaire est à true : true (partage à tous les utilisateurs), false
--     (accessible uniquement à la liste d'amis et aux groupes).

create table commentaire
(
  id                         serial primary key,
  utilisateur                integer not null references utilisateur,
  point                      integer not null references point,
  contenu                    text,
  url_photo                  varchar(255),
  date_publication           timestamp,
  -- partage_commentaire        boolean default false,
  partage_commentaire_public boolean default true
);

-- ------------------------------------------------------------------------------------------------
