// // 변수 : 프로그램에서 데이트를 담아 두는 공간
// // 변수 사용 -> let 변수명
// let width;
// let height;
// width = 200;
// height = 50;
// let area = width * height;
// console.log("넓이 : " + area);

// //상수 : 변수 중 하나, 한번 값을 할당하면 값이 바뀌지 않는다. 바꾸면 안됨
// // 상수 사용 -> const 변수명
// const PI = 3.14;
// let radius = 10;
// let area2 = PI * radius * radius;
// console.log("원의 넓이 : " + area2);

// // 자료형 - 컴퓨터가 처리할 수 있는 데이트의 형태
// // 숫자 (number)
// console.log(typeof(123456789));
// console.log(typeof(0.1));
// console.log(0.1 + 0.2); // 자바스크립트는 실수 계산을 정밀하게 하지 못 한다.

// // 문자열 (string) - ` ` or " "
// console.log(typeof("글자문자"));
// console.log(typeof("12345"));

// // 템플릿 리터럴 - 한 문자열에 변수랑 텍스트를 같이 사용
// //               변수 : ${변수명}
// let name = "홍길동";
// let age = 25;
// console.log(`이름은 ${name}이고, 나이는 ${age}입니다.`);

// // 논리형 (boolean) - true , false
// console.log(typeof(true));
// console.log(typeof(false));

// // undefined -변수 할당 안됨
// // null - 변수 무효 상태

// // 배열 - 하나 변수 여러값 저장 복합 유형
// //     배열 사용법 -> let 변수명 = [값1, 값2, 값3, ...]
// let spring = "봄";
// let summer = "여름";
// let fall = "가을";
// let winter = "겨울";
// let season = ["봄", "여름", "가을", "겨울"];
// console.log(season);
// console.log(season[0]);
// console.log(season[1]);
// console.log(season[2]);
// console.log(season[3]);

// // 자동형 형변환
// // 문자열 * 숫자 -> 숫자
// // 문자열 + 숫자 -> 문지
// let text = `50`;
// let num = 5;
// console.log(text * num);
// console.log(text + num);

// // 문자열 + 숫자 -> 숫자형 변환, 변환 함수 사용
// console.log(Number(text) + num);

// // 형변환 함수
// // Number() : 문자열, 논리형 -> 숫자
// // parseInt() : 정수로
// // parseFloat() : 실수
// //String() : 문자열
// // Boolean() : 논리형, 문자열 "", 숫자 0을 제외한 모든값은 True
// console.log(Number(`123`));
// console.log(Number("123ABC")); // NaN - 알 수 없다.
// console.log(parseInt("123")); // 123
// console.log(parseInt("123.45")); // 123
// console.log(parseInt("123ABC")); // 123 (숫자만)
// console.log(parseFloat("123")); // 123
// console.log(parseFloat("123.45")); // 123.45
// console.log(parseFloat("123.45ABC")); // 123.45 (숫자만)
// console.log(String(true)); // true 문자열
// console.log(Boolean(1)); // true 논리형
// console.log(Boolean("")); // false 논리형, 문자열 빈칸
// console.log(Boolean(0)); // false 논리형, 숫자 0
// console.log(Boolean(-1)); // true 논리형

// // 연산자 - 데이터기리 연산 부호
// // 산술 연산자 : +, -, *, / (나눈 값), % (나눈 나머지), ++, --
// console.log( 10/3 );
// console.log( 10%3 );
// let num2 = 1;
// console.log(num2++); //num2를 먼저 출력하고 +1 한다.
// console.log(num2);   // +1된 num 출력
// console.log(++num2); // +1한 후 num 출력
// console.log(num2); // num 출력

// // 할당 연산자 : 내 자신을 산술연산할 때 사용
// // =, +=, -=, *=, /=, %=
// let num3 = 10;
// let num4 = 2;
// num3 += num4;
// console.log(num3);

// // 비교 연산자 : 피연산자 2개의 값을 비교해서 boolean 형태로 나타낸다.
// //        <, <=< >, >=, == (같다), === (같다), != (다르다), !== (다르다)
// let test1 = "3";
// let test2 = 3;
// console.log(test1 == test2);  // 값만 비교한다.
// console.log(test1 === test2); // 값과 자료형까지 비교한다.

// // 논리 연산자 : 여러가지 조건을 같이 확인
// //    OR 연산자 (||) , AND (&&) , NOT (!)
// console.log(1 > 2 || 1 < 2);
// console.log(1 > 2 && 1 < 2);
// console.log(!(1 > 2));

// // 조건문 if-else
// let userNumber = parseInt(prompt("숫자를 입력하세요"));

// if(userNumber !== null){
//     if(userNumber % 3 === 0){
//         alert("3의 배수 입니다");
//     }
//     else if(userNumber % 5 === 0){
//         alert("5의 배수입니다");
//     }
//     else{
//         alert("3과 5의 배수가 아닙니다");
// }   
// }
// else{
//     alert("숫자를 입력해 주세요");
// }

// // 조건 연산자 (삼하 연산자) : 조건 1개, 실행 명령 1개 일때 사용
// // (조건) ? true : false

// let userNumber = prompt(`숫자를 입력하세요`);

// if(userNumber !== null){
//     (parseInt(userNumber) % 3 === 0) ? alert("3의 배수 입니다") : alert("3의 배수가 아닙니다.");
// }
// else{
//     alert("입력이 취소됐습니다.");
// }

// 조건문 (switch) - case문 실행
// let session = prompt("관심 세션을 선택해 주세요. 1-마케팅  2-개발 3-디자인");

// switch(session){
//     case "1": console.log("마케팅 세션은 201호에서 진행합니다");
//         break;
//     case "2": console.log("개발 세션은 202호에서 진행합니다");
//         break;
//     case "3": console.log("디자인 세션은 203호에서 진행합니다");
//         break;
//     default: alert("잘 못 입력했습니다.");
// }

// 반복문 : for(초기값; 조건; 증가식){ 실행 명령 }

// let sum = 0;
// for(let i = 1 ; i < 11 ; i++){
//     sum += i;
// }
// console.log(`1 ~ 10까지 더한 값은 ${sum} 입니다`);

// 중첩 for문
// for(let i = 1 ; i <= 9 ; i++){
//     document.write(`<h3>${i}단</h3>`);
//     for(let j = 1 ; j <= 9 ; j++){
//         document.write(`${i} * ${j} = ${i * j} <br>`);
//     }
// }

// 1 ~ 100까지 숫자들 중 3의 배수만 document.write로 출력
// for(let i = 1; i <= 100; i++){
//     if(i % 3 === 0){
//         document.write(`<h3>${i}은 3의 배수 입니다.</h3><br>`);
//     }
//     else{
//         document.write(`${i % 3}<br>`);
//     }
// }

// while 반복문 : 조건이 false가 될 때까지 실행 반복
//   while(조건){실행문}
// let stars = parseInt(prompt("몇개의 별을 표시 할까요 ?"));
// while(stars > 0){
//     document.write(`*`);
//     stars--;
// }

// do ~ while 반복문 : 무조건 do 문 실행 후 while 반복문 실행
// let stars = parseInt(prompt("몇개의 별을 표시 할까요 ?"));
// do{
//     document.write(`*`);
//     stars--;
// }
// while(stars > 0){
//     document.write(`*`);
//     stars--;
// }

// break 반복문 중단하고 빠져 나옴
// continue 자신 아래의 실행을 하지 않고 다시 조건으로 돌아감
// for( let i = 1 ; i < 10 ; i++){

//     if(i % 2 === 0){continue;}

//     for(let j = 1 ; j < 10 ; j++){
//         document.write(`${i} * ${j} =${i * j}<br>`);
//     }

//     if(i === 7){break;}
// }

// 문제 1 : 월드컵 4년 마다 게최, 2002년 ~ 2050년 개최년도 document.write로 출력
// document.write(`<h3>2002년부터 2050년까지 월드컵 개최 년도</h3>`);
// for(let i = 2002 ; i <= 2050 ; i += 4){
//     document.write(`<h4>${i}년</h4>`);
// }

// 문제 2 : 아래의 별그림 완성
// *****
// ****
// ***
// **
// *
let stars = prompt(`숫자를 입력하세요`);
for(let i = stars ; i >= 1 ; i--){
    for( let j = i ; j >= 1 ; j--){
        document.write(`*`);
    }
    document.write(`<br>`);
}
