//객체란 데이터를 저장 처리 기본 단위
// 인스턴스란 객체를 복사 변수에 저장 (new 키워드 사용)
// 프로퍼티란 객체의 특징 속성
// 메서드란 객체의 함수

// 인스턴스는 객체의 프로퍼티와 메스드를 그대로 물려 받는다.
// 프로퍼티와 메서드 사용 : 인스턴스명. 프로퍼티명 또는 .메스드명

// 인스턴스 사용 예시 (Date 객체 사용)
let now = new Date();
console.log(`현재 시각은 ${now.toLocaleString()}`);

// 내장 객체 -Array 객체 (배열)

// 1. 배열 만들기
let arr1 = new Array();
let arr2 = new Array(4);
let arr3 = ["one" , "two" , "three" , "four"];
let arr4 = Array("one" , "two" , "three" , "four");

// 2. Array 객체의 프로퍼티 length : 배열안의 데이터 갯수
let numbers = ["one" , "two" , "three" , "four"];
for(let i = 0 ; i < numbers.length ; i++){
    console.log(`${numbers[i]}`);
}

// 3. Array 객체의 메서드들
//     concat : 2의 배열 합 새로운 배열 만듬
let nums = [1, 2, 3];
let chars = [`a`, `b`, `c`, `d`];

let numsChars = nums.concat(chars);
let charsNums = chars.concat(nums);

console.log(`concat() 메서드 : ${numsChars}`);
console.log(`concat() 메서드 : ${charsNums}`);

//     join() 메서드 : 배열 요소들 연결 문자열 만듬 (구분자 지정 가능)
let string1 = nums.join();
console.log(`join() 메서드 : ${string1}`);

let string2 = chars.join(`/`);   // `/` 구분자
console.log(`join() 메서드 : ${string2}`);

//   push() , unshift() 메서드 : 기존 배열 맨끝, 맨앞에 요소 추가
let push1 = nums.push(4,5);
console.log(`push() 메서드 : length - ${nums.length} / 배열 -${nums}`);

let unshift1 = nums.unshift(0);
console.log(`unshift() 메서드 : length - ${nums.length} / 배열 -${nums}`);

//   pop(), shift() 메서드 : 기존 배열의 맨 끝, 맨 앞의 요소를 1개씩 꺼내어 변수에 저장하는 메서드
let pop1 = chars.pop();
console.log(`pop()메서드 : length - ${chars.length} / 배열 - ${chars}`);
console.log(`pop() 메소드의 반환값 : ${pop1}`);

let shift1 = chars.shift();
console.log(`shift()메서드 : length - ${chars.length} / 배열 - ${chars}`);
console.log(`shift() 메소드의 반환값 : ${shift1}`);

//     splice() 메서드 : 중간에 요소를 추가하거나 삭제하는 메서드
let study = [`html`, `css`, `javascript`, `jquery`, `react`, `nodejs`];
//      인수가 1개일 경우 : 지정 인덱스부터 맨끝가지 요소 삭제 및 배열 반환
let js = study.splice(2);
console.log(`반환된 js 배열 : ${js}`);
console.log(`원래의 study 배열 : ${study}`);
//      인수가 2개일 경우 : 지정한 첫번째 인수부터 삭제할 요소의 갯수 두번째 인수 만큼 삭제하고 배열 반환
let jquery =  js.splice(1 , 1);
console.log(`반환된 jquery 배열 : ${jquery}`);
console.log(`원래의 js 배열 : ${js}`);
//      인수가 3개 이상일 경우 지정한 첫번째 인수부터 삭제할 요소의 갯수 두번째 인수 만큼 삭제하고 배열 반환한
//         다음 세번째 요소 추가
let modernJs = js.splice(1, 0, `typescript`, `java`, `c++`);
console.log(`반환된 modernJs 배열 : ${modernJs}`);
console.log(`원래의 js 배열 : ${js}`);

// slice() 메서드 : 기존 배열을 바꾸지 않으면서 요소를 축출하는 메서드
let colors =  ["red", "green", "blue", "white", "black"];
//     인수가 1개일 때 : 지정한 첫번째 인덱스 인수부터 마지막 요소까지 축출
let slice1 = colors.slice(2);
console.log(`slice() 메서드 : ${slice1}`);
console.log(`원래의 js 배열 : ${colors}`);
//     인구사 2개일 때 지정한 인덱스 첫번째 인수 부터 두번째 인수 직전의 인덱스까지 축출
let slice2 = colors.slice(2, 4);
console.log(`slice() 메서드 : ${slice2}`);
console.log(`원래의 js 배열 : ${colors}`);

// 내장 객체 - Date 객체

// Date 객체 만들기
let today = new Date(); // 오늘 날짜

let date1 = new Date("2025-04-21");
let date2 = new Date("2025-04-21T12:00:00");

// Date 메서드들
// Date의 날짜 시간 정보 가져오는 메서드
console.log(today.getFullYear()); // 4자리 년도
console.log(today.getMonth() + 1); // 0 ~ 11까지 월, +1 필요
console.log(today.getDate()); // 일
console.log(today.getDay()); // 0~6까지, 0 일요일 ~ 6 토요일
console.log(today.getTime());  // 1970년 1월 1일 0시 0분 기준 밀리초
console.log(today.getHours());  // 0~23까지 시간
console.log(today.getMinutes()); // 0~59까지의 분
console.log(today.getSeconds());  // 0~59까지 초
console.log(today.getMilliseconds()); // 0~999까지 밀리초
console.log(today);

// Date의 날짜 시간 정보를 저장하는 메서드들
let setDate1 = new Date();
// setDate1의 날짜 시간을 2024년 1월 1일 0시 10분 20.5초
setDate1.setFullYear(2024);
setDate1.setMonth(0);
setDate1.setDate(1);
setDate1.setHours(0);
setDate1.setMinutes(10);
setDate1.setSeconds(20);
setDate1.setMilliseconds(500);

console.log(setDate1);

// Date 날짜 시간 형식 바꿔 보기
console.log(today.toLocaleString());  // 지정한 날짜와 시간을 현지 시간 기준으로 표시
console.log(today.toString());  // 문자열로 표시

// 실습 - D-day 계산
let nowDate = new Date();
let firstDay = new Date("1966-04-21");
let toNow = now.getTime();
let toFirst = firstDay.getTime();
let passedTime = toNow - toFirst;

passedTime = Math.round(passedTime/(1000*60*60*24));

document.getElementById("result").innerText = passedTime.toLocaleString();

// 내장 객체 - Math 객체
//전체 응모자 중 당첨자 번호 뽑기
// random 0~1 사이 무작위
// floor 소수점 이하 버림
let seed = prompt("전체 응모자 수 : ");
let winner = prompt("당첨자 수 : ");
let picked = (Math.floor(Math.random() * seed) + 1);

let all = document.getElementById("all");
let sel = document.getElementById("sel");

all.innerHTML = `전체 응모자 수 : ${seed}`;
let winnerArr = new Array();
for( let i = 0 ; i < winner  ; i++ ){
    let picked = (Math.floor(Math.random() * seed) + 1);
    if(winnerArr.indexOf(picked) != -1){
        i -= 1;
        continue;
    }
    else{
        winnerArr.push(picked);
        sel.innerHTML += `당첨자 : ${picked.toLocaleString()}<br>`;
    }
    // sel.innerHTML += `당첨자 : ${picked.toLocaleString()}<br>`;
}

// ceil() 메서드 : 소수점 이하 올림
// max()
// min()
// round() 반올림

// window 객체 - open 메서드
//   open(경로, 창 이름, 창 옵션) : 팝업창을 띄우는 메서드
//        경로 : 팝업창에 표시할 문서 파일 경로 똔ㄴ 사이트 주소
//        창 이름 : 팝업 창의 이름 (중복해서 띄우지 않게 지정하는 이름)
//        옵션 : left, top 창의 위치 지정, width, height

// window.open(`../notice.html`, `pop` `width=500, height=400, left=100, top=100`);

// (function(){
//     // window.open() 메소드는 팝업차단 등의 이유로 창을 띄우지 못할 시에 null 반환
//     let newWin = window.open(`./notice.html`, `pop` `width=500, height=400, left=500, top=100`);
//     if(newWin == null){
//         alert("팝업이 차단되었습니다. 해제해 주세요.");
//     }
// }());

// window.close() : 팝업창 닫는 메서드
let myWin;

function openWin(){
    let opt = `width=400, height=350, left=500, top=200`;
    myWin = window.open("./notice.html", "pop1", opt);
}
function closeWin(){
    myWin.close();
}

// 브라우저 객체 - Location
//    location 객체의 프로퍼티 알아보기
console.log(`location.href : ${location.href}`);
console.log(`location.host : ${location.host}`);
console.log(`location.protocol : ${location.protocol}`);

let assignBtn = document.getElementById(`assignBtn`); // 페이지 이동 및 뒤로가기 가능
let replaceBtn = document.getElementById(`replaceBtn`); // 페이지 교체, 뒤로가기 불가
let reloadBtn = document.getElementById(`reloadBtn`);
assignBtn.onclick = function(){
    location.assign(`https://naver.com`);
}
replaceBtn.onclick = function(){
    location.replace(`https://naver.com`);
}
reloadBtn.onclick = function(){
    location.reload();
}

// 브라우저 객체 - screen 객체 (화면 관련하여 저장된 객체)
console.log(`screen.availWidth : ${screen.availWidth}`);  // 사용가능한 가로 넓이
console.log(`screen.width : ${screen.width}`);  // 화면 자체 가로 넓이
console.log(`screen.availHeight : ${screen.availHeight}`); // 사용가능한 세로 높이
console.log(`screen.height : ${screen.height}`);  // 화면 자체 세로 높이