-- 그룹화 관련된 함수
-- 1. rollup 함수 - 그룹화한 컬럼들의 소계를 나타내는 함수
--      N+1개의 그룹화가 이루어진다.
--      예를 들어, deptno, job 을 그룹화 했다면
--         deptno, job 그룹1개
--         deptno 그룹 1개
--         전체 그룹 1개
--  예제) 부서와 직업별 평균 급여 및 사원수와 부서별 평균 급여와 사원수,
--        전체 사원의 평균 급여와 사원수를 나타내시오.
--       1. 부서와 직업별 평균 급여 및 사원수
--       2. 부서별 평균 급여와 사원수
--       3. 전체 사원의 평균 급여와 사원수
select deptno, job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
       from emp
group by deptno,job
union all
select deptno, null job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
from emp
group by deptno
union all
select null deptno, null job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
from emp
order by deptno, job;
-- 위 코드를 rollup으로 사용
--    N+1개의 그룹으로 소계를 낸다. 순서대로.
select deptno, job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
from emp
group by rollup(deptno,job);
-- 예제 ) 교수(professor) 테이블에서 부서별, 직책별(position)
--        인원수와 급여 합계, 부서별 인원수와 급여 합계,
--        전체 인원수와 급여합계를 나타내시오.
select deptno, position, count(*) as "CNT_Professor", sum(pay) as "Sum_Pay"
from professor
group by rollup(deptno,position)
order by deptno;
-- 위의 쿼리문에서 직책별 소계만 보고 싶다.
select deptno, position, count(*) as "CNT_Professor", sum(pay) as "Sum_Pay"
from professor
group by position, rollup(deptno);

-- 2. cube 함수 - 소계를 나타내는 함수
--    2의 N제곱개의 그룹을 나타낸다.
-- 예제 ) 부서별 평균 급여 및 사원수와 직급별 평균 급여와 사원수,
--        전체 사원의 평균 급여와 사원수를 나타내시오.
--       1. 부서와 직업별 평균 급여 및 사원수
--       2. 부서별 평균 급여와 사원수
--       3. 전체 사원의 평균 급여와 사원수
select deptno, job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
       from emp
group by deptno,job
union all
select deptno, null job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
from emp
group by deptno
union all
select null deptno, job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
from emp
group by job
union all
select null deptno, null job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
from emp
order by deptno, job;
--  cube 함수 : 2의 N제곱만큼 소계를 낸다. 모든 경우의 수.
select deptno, job, round(avg(sal),1) as "AVG_SAL",
       count(*) as "CNT_EMP"
from emp
group by cube(deptno,job)
order by deptno, job;

-- 기준 데이터 3개로 rollup과 cube 실습
--  1. rollup의 경우
select deptno, job, mgr, count(*)
from emp
where ename not like 'king'
group by rollup(deptno, job, mgr);
select deptno, job, mgr, count(*)
from emp
where ename not like 'king'
group by cube(deptno, job, mgr)
order by deptno, job, mgr;

-- 3.grouping sets 함수 - 소계가 아닌 여러가지를 그룹화 할 때 깔끔하게 볼 수 있다.
--  예제 ) 학년별 전공별 인원수를 따로 출력
select grade, deptno1, count(*)
from student
group by grouping sets(grade, deptno1)
order by grade, deptno1;
--  예제 ) emp에서 부서번호별, 직급별 인원수 따로 출력
select deptno, job, count(*)
from emp
group by grouping sets(deptno, job)
order by deptno, job;

-- 4. listagg 함수 - 그룹화 내용을 연결시켜서 나타내는 함수
--    주의할 점 : 출력되는 값이 4000byte가 넘어가면 오류 발생
--   예제) emp에서 부서별 인원의 이름을 출력
select deptno, listagg(ename, ', ')
from emp
group by deptno
order by deptno;
--  emp에서 부서별 인원의 이름을 순서대로 출력
select deptno, listagg(ename, ', ') within group(order by ename)
from emp
group by deptno
order by deptno;

-- 5. pivot 함수 - row 단위를 column 단위로 변경
select deptno, job, max(sal)
from emp
group by deptno, job
order by deptno, job;
--- 먼저, pivot을 사용하지 않고 작성하는 방법
select deptno,
       max(decode(job, 'CLERK', sal)) as CLERK,
       max(decode(job, 'MANAGER', sal)) as MANAGER,
       max(decode(job, 'PRESIDENT', sal)) as PRESIDENT,
       max(decode(job, 'ANALYST', sal)) as ANALYST,
       max(decode(job, 'SALESMAN', sal)) as SALESMAN
FROM EMP
GROUP BY DEPTNO
ORDER BY DEPTNO;
--  PIVOT 함수 이용해서 만들어 보기
SELECT * FROM ( SELECT DEPTNO, JOB, SAL FROM EMP )
PIVOT ( MAX(SAL)
               FOR JOB IN ( 'CLERK', 'MANAGER', 'PRESIDENT', 'ANALYST', 'SALESMAN')
)
ORDER BY DEPTNO;
--   ROW가 JOB 되고 COLUMN이 DEPTNO가 되도록 변경
SELECT * FROM (SELECT DEPTNO, JOB, SAL FROM EMP)
PIVOT (MAX(SAL) FOR DEPTNO IN ( '10' AS "부서10", '20' AS "부서20", '30' AS "부서30"))
ORDER BY JOB;
--  CAL 테이블 이용 달력 만들기
SELECT WEEKNO, DAY, DAYNO FROM CAL;
-- PIVOT 없이 만들기
select WEEKNO,
       max(decode(DAY, 'SUN', DAYNO)) as SUN,
       max(decode(DAY, 'MON', DAYNO)) as MON,
       max(decode(DAY, 'TUE', DAYNO)) as TUE,
       max(decode(DAY, 'WED', DAYNO)) as WED,
       max(decode(DAY, 'THU', DAYNO)) as THU,
       max(decode(DAY, 'FRI', DAYNO)) as FRI,
       max(decode(DAY, 'SAT', DAYNO)) as SAT
FROM CAL
GROUP BY WEEKNO
ORDER BY WEEKNO;
--  PIVOT 사용
SELECT * FROM (SELECT WEEKNO, DAY, DAYNO FROM CAL)
PIVOT (MAX(DAYNO) FOR DAY IN ('SUN', 'MON', 'TUE', 'WED', 
                              'THU', 'FRI', 'SAT'))
ORDER BY WEEKNO;

--  6. RANK 함수 - 순위 출력
--  예제) EMP에서 SMITH 이름순 몇번째
SELECT RANK('SMITH') WITHIN GROUP(ORDER BY ENAME) AS "RANK"
FROM EMP;
SELECT ENAME FROM EMP ORDER BY ENAME;
--  예제) 전체 순위 확인
SELECT ENAME, RANK() OVER(ORDER BY ENAME DESC) AS "ENAME_RANK"
FROM EMP;
-- 예제) 전체 순위에서 그롭화 하기
SELECT ENAME, DEPTNO, SAL, RANK() OVER(PARTITION BY DEPTNO ORDER BY SAL) AS " DEPTNO_RANK"
FROM EMP;

-- 7.  DENSE_RANK - 순위 출력
--     사용방법 RANK 동일
--   차이점 - RANK : 동일 순위 갯수 인정, DENSE_RANK : 동일 순위 갯수 불인정
SELECT EMPNO, ENAME, SAL,
       RANK() OVER(ORDER BY SAL DESC) SAL_RANK,
       DENSE_RANK() OVER(ORDER BY SAL DESC) SAL_DENSE_RANK
FROM EMP;

-- 8. ROW_NUMBER - 순서를 매겨주나는 함수
--      RANK와 사용법 동일
--     데이터가 동일해도 ROW ID를 이용하여 무조건 순위 매김
SELECT EMPNO, ENAME, SAL,
       RANK() OVER(ORDER BY SAL DESC) SAL_RANK,
       DENSE_RANK() OVER(ORDER BY SAL DESC) SAL_DENSE_RANK,
       ROW_NUMBER() OVER(ORDER BY SAL DESC) SAL_ROW_NUMBER
FROM EMP;

--  RANK와 DENSE_RANK, ROW_NUMBER에 정렬기준 여러개 지정하기
SELECT EMPNO, ENAME, SAL,
       RANK() OVER(ORDER BY SAL DESC, EMPNO) SAL_RANK,
       DENSE_RANK() OVER(ORDER BY SAL DESC, EMPNO) SAL_DENSE,
       ROW_NUMBER() OVER(ORDER BY SAL DESC, EMPNO) SAL_ROW
FROM EMP;

-- 9. SUM() OVER - 누저된 총 합을 구한다.
--  예제) 판매(PANMAE) 테이블에서 판매점코드(P_STORE)가 1000번인
--        지점의 날짜순으로 일별 매출과 누적 합을 구하라.
SELECT P_DATE, P_CODE, P_QTY, P_TOTAL,
       SUM(P_TOTAL) OVER(ORDER BY P_DATE) "TOTAL"
FROM PANMAE
WHERE P_STORE = 1000;
--  예제) 판메테이블에서 판매코드가 1000번인 지점의 판매내역을
--        제품코드별로 분류한 후 판매일자, 제품코드, 판매량, 판매금액
--        누적판매금액을 출력
SELECT P_DATE, P_CODE, P_QTY, P_TOTAL,
       SUM(P_TOTAL) OVER(PARTITION BY P_CODE ORDER BY P_DATE) "TOTAL"
FROM PANMAE
WHERE P_STORE = 1000;
SELECT * FROM emp;
--  1번 문제) emp에서 각 부서별 급여 평균, 최대, 최소 및 부서 인원 출력
SELECT DEPTNO, trunc(avg(SAL)), max(sal), min(sal), count(*)
FROM EMP
group by deptno
ORDER BY DEPTNO;

-- 2번 문제) emp에서 각 직책 사원수가 3명 이상인 직책 사원수 출력
select job, count(*)
from emp
group by job
having count(*) >= 3;

--  문제3. 사원들의 입사연도(hiredate)를 기준으로 부서별(deptno)로 몇명이 입사했는지 출력
SELECT
    TO_CHAR(hiredate, 'YYYY') AS HIRE_YEAR,
    deptno,
    COUNT(*) AS EMPLOYEE_COUNT
FROM
    emp
GROUP BY
    TO_CHAR(hiredate, 'YYYY'),
    deptno
ORDER BY
    HIRE_YEAR,
    deptno;

--  문제4.  추가수당(comm)이 있는 사람과 없는 사람 수 출력
SELECT
    CASE
        WHEN comm IS NULL OR comm = 0 THEN '추가수당 없음'
        ELSE '추가수당 있음'
    END AS "추가수당 여부",
    COUNT(*) AS "인원수"
FROM
    emp
GROUP BY
    CASE
        WHEN comm IS NULL OR comm = 0 THEN '추가수당 없음'
        ELSE '추가수당 있음'
    END;
    
SELECT
    NVL2(comm, '추가수당 있음', '추가수당 없음') AS "추가수당 여부",
    COUNT(*) AS "인원수"
FROM
    emp
GROUP BY
    NVL2(comm, '추가수당 있음');
    
-- 문제5.  각 부서(deptno)의 입사 연도별(hitrdate) 사원수, 최고급여(sal), 급여합, 평균급여를 출력
--        각 부서별 사원수, 최고급여, 급여합, 평균급여 출력
--        전체 사원수, 최고급여, 급여합, 평균급여 출력
--        위 3가지 사항을 한 쿼리로 출력
SELECT
    deptno AS "부서번호",
    TO_CHAR(hiredate, 'YYYY') AS "입사연도",
    COUNT(*) AS "사원수",
    MAX(sal) AS "최고급여",
    SUM(sal) AS "급여합",
    trunc(AVG(sal), 1) AS "평균급여"
FROM emp
GROUP BY deptno, TO_CHAR(hiredate, 'YYYY')
ORDER BY "부서번호", "입사연도";

SELECT
    deptno AS "부서번호",
    COUNT(*) AS "사원수",
    MAX(sal) AS "최고급여",
    SUM(sal) AS "급여합",
    trunc(AVG(sal), 1) AS "평균급여"
FROM emp
GROUP BY deptno
ORDER BY "부서번호";

SELECT
    COUNT(*) AS "전체사원수",
    MAX(sal) AS "최고급여",
    SUM(sal) AS "급여합",
    trunc(AVG(sal), 1) AS "평균급여"
FROM emp;

SELECT
    deptno AS "부서번호",
    TO_CHAR(hiredate, 'YYYY') AS "입사연도",
    COUNT(*) AS "사원수",
    MAX(sal) AS "최고급여",
    SUM(sal) AS "급여합",
    trunc(AVG(sal), 1) AS "평균급여"
FROM emp
GROUP BY ROLLUP(deptno, TO_CHAR(hiredate, 'YYYY'))
ORDER BY "부서번호", "입사연도";

--  책 p212 문제 5번 : emp 테이블을 사용하여 직원들의 급여와 전체급여의 누적급여 금액을 출력하라. 단, 급여는 오름차순으로 정렬.
SELECT deptno, ename, sal,
       SUM(sal) OVER(ORDER BY sal) "TOTAL"
FROM emp;

-- 책 p213 문제8번 : 부서별로 급여 누적 합계가 나오도록 작성. 단, 부서번호로 오름차순 정렬
SELECT deptno, ename, sal,
       SUM(sal) OVER(PARTITION BY deptno order BY sal) "TOTAL"
FROM emp;

-- 책 p212. 6번문제 : fruit 테이블을 참고해서 아래와 같이 만드세요.
select * from FRUIT;
SELECT * FROM (SELECT NAME, PRICE FROM FRUIT)
PIVOT (MAX(PRICE) FOR NAME IN ('apple', 'grape', 'orange'));


-- 책 P210. 2번 3번 문제
SELECT * FROM STUDENT;
SELECT SUBSTR(BIRTHDAY, 4,2) AS "BIR_MON" FROM STUDENT;
SELECT * FROM (SELECT SUBSTR(BIRTHDAY, 4,2) AS "BIR_MON" FROM STUDENT)
PIVOT (COUNT(BIR_MON)
        FOR BIR_MON IN ('01' AS "JAN", '2' AS "Feb ", '03' AS "Mar ", 
        '04' AS "Apr ", '05' AS "May ", '06' AS "Jun ", '07' AS "Jul", 
        '08' AS "Aug ", '09' AS "Sep ", '10' AS "Oct ", '11' AS "Nov", 
        '12' AS "Dec "));
SELECT SUBSTR(TEL, 1,3) AS "TEL_NUM" FROM STUDENT;
SELECT * FROM (SELECT regexp_SUBSTR(TEL, '[^)]+',1,1) AS "TEL_NUM" FROM STUDENT)
PIVOT (COUNT(TEL_NUM)
        FOR TEL_NUM
        IN ('02' AS "Seoul", '031' AS "Gyeonggi", '051' AS "Busan", 
        '052' AS "Ulsan", '053' AS "Daegu", '055' AS "Gyeongsangnam"));

-- JOIN : 수평적 결합으로 테이블 두 개를 붙인다.











