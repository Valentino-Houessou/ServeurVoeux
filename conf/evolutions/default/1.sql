# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ec (
  code_ec                   varchar(255) not null,
  libelle                   varchar(255),
  volume_horaire_cours      double,
  volume_horaire_td         double,
  constraint pk_ec primary key (code_ec))
;

create table preferences (
  id_preference             bigint auto_increment not null,
  jour                      varchar(255),
  ref_utilisateurs_id_utilisateur bigint,
  valide                    integer,
  constraint ck_preferences_valide check (valide in (0,1,2)),
  constraint pk_preferences primary key (id_preference))
;

create table utilisateurs (
  id_utilisateur            bigint auto_increment not null,
  nom                       varchar(255),
  prenom                    varchar(255),
  email                     varchar(255),
  telephone                 varchar(255),
  adresse                   varchar(255),
  mdp                       varchar(255),
  poste                     integer,
  constraint ck_utilisateurs_poste check (poste in (0,1,2,3,4,5,6)),
  constraint pk_utilisateurs primary key (id_utilisateur))
;

create table voeux (
  id_voeux                  bigint auto_increment not null,
  libelle_voeux             varchar(255),
  commentaire               varchar(255),
  volume_horaire_cours_assure double,
  priorite                  boolean,
  volume_horaire_td_assure  double,
  ref_utilisateurs_id_utilisateur bigint,
  valide                    integer,
  constraint ck_voeux_valide check (valide in (0,1,2)),
  constraint pk_voeux primary key (id_voeux))
;

create sequence ec_seq;

alter table preferences add constraint fk_preferences_ref_utilisateur_1 foreign key (ref_utilisateurs_id_utilisateur) references utilisateurs (id_utilisateur) on delete restrict on update restrict;
create index ix_preferences_ref_utilisateur_1 on preferences (ref_utilisateurs_id_utilisateur);
alter table voeux add constraint fk_voeux_ref_utilisateurs_2 foreign key (ref_utilisateurs_id_utilisateur) references utilisateurs (id_utilisateur) on delete restrict on update restrict;
create index ix_voeux_ref_utilisateurs_2 on voeux (ref_utilisateurs_id_utilisateur);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ec;

drop table if exists preferences;

drop table if exists utilisateurs;

drop table if exists voeux;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ec_seq;

