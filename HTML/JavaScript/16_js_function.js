// 함수 : 일련의 동작을 모아서 적은 것
// 함수 정의 : 함수 호출 실행
// 함수 정의 > function 함수면() {실행문}
// 함수 호출 > 함수명()

// 함수 정의 예제
// function addNumber(){
//     let num1 = 2;
//     let num2 = 3;
//     let sum = num1 + num2;
//     console.log(`addNumber() 함수 호출 sum : ${sum}`);
// }
// addNumber();

// var 변수 : 호이스팅 및 변수 재선언 문제로 지금은 잘 사용하지 않는 변수 예약어
// var i = 10;
// var i = 20;
// i = 30
// // var 변수 호이스팅
// function displayNumber(){
//     console.log(`i is ${i}`);
//     console.log(`j is ${j}`);
//     var j = 20;
// }
// displayNumber();

// function displayNumber2(){
//     let i;
//     console.log(`i is ${i}`);
//     console.log(`j is ${j}`);
//     let j = 10;
// }
// displayNumber2();

// 매개변수 (num1 , num2) , 인수 (2 , 3) , return 반환문
function addNumber2(num1 , num2){
    let sum = num1 + num2;
    return sum;
}
let result = addNumber2(2 , 3);
document.write(`<h3>두 수를 더 한 값 ${result}</h3>`)

// 매개 변수에 기본값 지정, 마지막 매개 변수부터 역순으로 지정해야 한다.
function multipleNumber(a = 1 , b = 1 , c = 1){
    return a * b * c;
}
let mnrst = multipleNumber();
console.log(`multipleNumber 반환값 : ${mnrst}`)
let mnrst2 = multipleNumber(2, 5, 6);
console.log(`multipleNumber 반환값 : ${mnrst2}`)

// 익명 함수 : 함수 이름 없고 변수에 함수를 정의
let sum = function(a, b){
    return a + b;
}
console.log(`익명 함수 sum 결과 : ${sum(2,5)}`);

// 즉시 실행 함수 : 페이지가 로딩될 때 바로 실행 하는 함수
(function(){
    console.log(`즉시 실행하는 함수가 실행되었습니다.`)
}());

(function(a, b){
    console.log(`즉시 실행하는 함수 실행 : ${a}, ${b}`)
}(100, 200));

// 화살표 함수 표현식 : 익명함수만 사용 가능 +> 표기 사용
// .//------------------------------------------------------------
// 기본 익명 함수
let hi =function(){
    return alert("안녕하세요");
}
// 기본 화살표 함수 변경
let hi2 = () => {return alert("안녕하세요");}
// 리턴문 하나만 있을 경우
let hi3 = () => alert("안녕하세요");
//------------------------------------------------------------------
let hello = function(name){
    console.log(`${name}님 안녕하세요`);
}
let hello2 = name => {console.log(`${name}님 안녕하세요`);}
//---------------------------------------------------------------------
let numSum = function(a, b){
    return a + b;
}
let numSum2 = (a, b) => {return a + b}
let numSum3 = (a, b) => a + b