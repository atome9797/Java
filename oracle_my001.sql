--//// ����
--//////////////////////////////////////
--1) employees���̺��� �ڵ�����ȣ ��3�ڸ���515�� 
--����� ��ȣ�� ������������ �����Ͻÿ�

SELECT phone_number,employee_id
FROM employees
WHERE phone_number like '515%'
ORDER BY employee_id ASC;

--2)employee���̺��� 2008��1�� ���ķ� �Ի��� ����� ����Ͻÿ�.

SELECT first_name,hire_date
FROM employees
WHERE hire_date >='08/01/01';

--3)employee���̺��� ����� even�� ���ԵǾ��ִ� ����� ������ ���������� �����Ͻÿ�

SELECT first_name,salary
FROM employees
WHERE first_name like '%even%'
ORDER BY salary DESC;


--4)employee���̺��� �޿��� 5000�̻��̰�, Ŀ�̼��� null���� �ƴҶ� ����� ,�޿�,Ŀ�̼��� ȣ���Ͻÿ�.

SELECT first_name,salary,commission_pct
FROM employees
WHERE salary >=5000 AND commission_pct is not null;

--5)�������� 'FI_ACCOUNT' �� ����߿��� �޿��� 7000�̻��� �����(first_name) ����(job_id) �޿�(salary)�� ����Ͻÿ�.

SELECT first_name,job_id,salary
FROM employees
WHERE job_id ='FI_ACCOUNT' AND salary>=7000;

