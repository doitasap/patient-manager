create sequence IF NOT EXISTS hibernate_sequence start with 1 increment by 1;
create table IF NOT EXISTS pm_code (
                         code_id varchar(10) not null,
                         code_name varchar(10),
                         code_group_id varchar(10),
                         primary key (code_id)
);

create table IF NOT EXISTS pm_code_group (
                               code_group_id varchar(10) not null,
                               code_group_name varchar(10),
                               description varchar(10),
                               primary key (code_group_id)
);

create table IF NOT EXISTS pm_hospital (
                             hospital_id bigint not null,
                             hospital_name varchar(45),
                             nursing_institution_num varchar(20),
                             owner_name varchar(10),
                             primary key (hospital_id)
);

create table IF NOT EXISTS pm_patient (
                            patient_id bigint not null,
                            birth varchar(10),
                            patient_enroll_num varchar(13),
                            patient_name varchar(45),
                            phone varchar(20),
                            sexual_code varchar(10),
                            hospital_id bigint,
                            primary key (patient_id)
);

create table IF NOT EXISTS pm_visit (
                          visit_id bigint not null,
                          visit_date timestamp,
                          visit_state varchar(10),
                          hospital_id bigint,
                          patient_id bigint,
                          primary key (visit_id)
);

alter table pm_patient
    add constraint IF NOT EXISTS idAndHospitalId unique (patient_id, hospital_id);
alter table pm_visit
    add constraint IF NOT EXISTS idAndPatientIdAndHospitalId unique (visit_id, patient_id, hospital_id);
alter table pm_code
    add constraint IF NOT EXISTS FK5hq445dus5ujms8ku00b5ugsj
        foreign key (code_group_id)
            references pm_code_group;
alter table pm_patient
    add constraint IF NOT EXISTS FKkgbgp1sp8rveb6vcs91340liv
        foreign key (hospital_id)
            references pm_hospital;
alter table pm_visit
    add constraint IF NOT EXISTS FKn52984926lpewn4x9chx7b44x
        foreign key (hospital_id)
            references pm_hospital;
alter table pm_visit
    add constraint IF NOT EXISTS FK14mw6qexqfocyi08nlvlxehas
        foreign key (patient_id)
            references pm_patient;