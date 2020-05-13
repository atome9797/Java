--//// 문제
--//////////////////////////////////////
--1) employees테이블에서 핸드폰번호 앞3자리가515인 
--사원의 번호를 오름차순으로 정렬하시오

SELECT phone_number,employee_id
FROM employees
WHERE phone_number like '515%'
ORDER BY employee_id ASC;

--2)employee테이블에서 2008년1월 이후로 입사한 사원을 출력하시오.

SELECT first_name,hire_date
FROM employees
WHERE hire_date >='08/01/01';

--3)employee테이블에서 사원명에 even이 포함되어있는 사원의 봉급이 높은순으로 나열하시오

SELECT first_name,salary
FROM employees
WHERE first_name like '%even%'
ORDER BY salary DESC;


--4)employee테이블에서 급여가 5000이상이고, 커미션이 null값이 아닐때 사원명 ,급여,커미션을 호출하시오.

SELECT first_name,salary,commission_pct
FROM employees
WHERE salary >=5000 AND commission_pct is not null;

--5)업무명이 'FI_ACCOUNT' 인 사원중에서 급여가 7000이상인 사원명(first_name) 업무(job_id) 급여(salary)를 출력하시오.

SELECT first_name,job_id,salary
FROM employees
WHERE job_id ='FI_ACCOUNT' AND salary>=7000;

