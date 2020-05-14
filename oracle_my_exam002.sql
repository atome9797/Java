--1)employees테이블에서 first_name에서 대소문자 구분없이
--'Ja'가 포함된 first_name,salary를 출력하라
SELECT first_name,salary
FROM employees
WHERE upper(first_name) like upper('%JA%') ;  


--2)인원이 50이하인 부서에 대해서 null이아닌 부서별 직원수를 출력하시오.
SELECT department_id,count(*)
FROM employees
WHERE department_id is not null  --null값 제외 
GROUP BY department_id    --부서별 그룹 만들어줌
HAVING department_id <=50; --50이하 조건 추가(AND의미)


--3) employees 테이블에서 아래의 결과처럼 입사인원을 출력하는 SELECT문장을 작성하여라
    --<출력:  2001		2002		2003
   	                1             7          6   >

SELECT sum(decode ( to_char(hire_date,'yyyy'),'2001',1,0)) as "2001" ,
sum(decode (to_char(hire_date,'yyyy'),'2002',1,0))as"2002",
sum(decode (to_char(hire_date,'yyyy'),'2003',1,0))as "2003"
FROM employees;


--4)입사일에서 월이 1-3이면 '1사분기',4-6이면 '2사분기' , 7-9월이면 '3사분기' ,10-12이면 '4사분기'
--로 처리하고 사원명(first_name),입사일(hire_date),분기로 출력하시오

SELECT first_name,hire_date, case when  to_char(hire_date,'mm')<=3             then '1사분기'
                                                        when  ltrim(to_char(hire_date,'mm'),'0') <=6    then '2사분기'
                                                        when  ltrim(to_char(hire_date,'mm'),'0') <=9    then '3사분기'
                                                        when  ltrim(to_char(hire_date,'mm'),'0') <=12   then '4사분기'                                                       
                                                end as "분기"
FROM employees;

--5)decode (컬럼 ,조건1,값1,조건2,값2,조건3,값3,그밖의값)
--department_id값이 10이면 'AA', 20이면 'BB' , 30이면 'CC', 그밖의 값은 'DD'
--java의 switch_case와 비슷

SELECT first_name, department_id,decode(department_id, 10, 'ACCOUNTING', 20, 'RESEARCH' ,30 ,'SALES' ,40 ,'OPERATIONS' ,'OTHERS') AS code
FROM employees;

