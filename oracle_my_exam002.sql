--1)employees���̺��� first_name���� ��ҹ��� ���о���
--'Ja'�� ���Ե� first_name,salary�� ����϶�
SELECT first_name,salary
FROM employees
WHERE upper(first_name) like upper('%JA%') ;  


--2)�ο��� 50������ �μ��� ���ؼ� null�̾ƴ� �μ��� �������� ����Ͻÿ�.
SELECT department_id,count(*)
FROM employees
WHERE department_id is not null  --null�� ���� 
GROUP BY department_id    --�μ��� �׷� �������
HAVING department_id <=50; --50���� ���� �߰�(AND�ǹ�)


--3) employees ���̺��� �Ʒ��� ���ó�� �Ի��ο��� ����ϴ� SELECT������ �ۼ��Ͽ���
    --<���:  2001		2002		2003
   	                1             7          6   >

SELECT sum(decode ( to_char(hire_date,'yyyy'),'2001',1,0)) as "2001" ,
sum(decode (to_char(hire_date,'yyyy'),'2002',1,0))as"2002",
sum(decode (to_char(hire_date,'yyyy'),'2003',1,0))as "2003"
FROM employees;


--4)�Ի��Ͽ��� ���� 1-3�̸� '1��б�',4-6�̸� '2��б�' , 7-9���̸� '3��б�' ,10-12�̸� '4��б�'
--�� ó���ϰ� �����(first_name),�Ի���(hire_date),�б�� ����Ͻÿ�

SELECT first_name,hire_date, case when  to_char(hire_date,'mm')<=3             then '1��б�'
                                                        when  ltrim(to_char(hire_date,'mm'),'0') <=6    then '2��б�'
                                                        when  ltrim(to_char(hire_date,'mm'),'0') <=9    then '3��б�'
                                                        when  ltrim(to_char(hire_date,'mm'),'0') <=12   then '4��б�'                                                       
                                                end as "�б�"
FROM employees;

--5)decode (�÷� ,����1,��1,����2,��2,����3,��3,�׹��ǰ�)
--department_id���� 10�̸� 'AA', 20�̸� 'BB' , 30�̸� 'CC', �׹��� ���� 'DD'
--java�� switch_case�� ���

SELECT first_name, department_id,decode(department_id, 10, 'ACCOUNTING', 20, 'RESEARCH' ,30 ,'SALES' ,40 ,'OPERATIONS' ,'OTHERS') AS code
FROM employees;

