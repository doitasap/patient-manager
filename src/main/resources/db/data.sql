insert ignore into pm_code_group(code_group_id, code_group_name, description) values('성별코드', '성별코드', '성별을 표시');
insert ignore into pm_code_group(code_group_id, code_group_name, description) values('방문상태코드', '방문상태코드', '환자방문의 상태');
insert ignore into pm_code_group(code_group_id, code_group_name, description) values('진료과목코드', '진료과목코드', '진료과목');
insert ignore into pm_code_group(code_group_id, code_group_name, description) values('진료유형코드', '진료유형코드', '진료유형');

insert ignore into pm_code(code_group_id, code_id, code_name) values ('성별코드', 'M', '남');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('성별코드', 'F', '여');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('방문상태코드', '1', '방문중');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('방문상태코드', '2', '종료');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('방문상태코드', '3', '취소');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('진료과목코드', '01', '내과');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('진료과목코드', '02', '안과');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('진료유형코드', 'D', '약처방');
insert ignore into pm_code(code_group_id, code_id, code_name) values ('진료유형코드', 'T', '검사');

insert ignore into pm_hospital(hospital_id, hospital_name, nursing_institution_num, owner_name) values (1, '병원1', '54-23-11', '최병원');
insert ignore into pm_hospital(hospital_id, hospital_name, nursing_institution_num, owner_name) values (2, '병원2', '99-88-77', '김돌팔');