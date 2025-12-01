// 문서객체모델(DOM) : 문서에 있는 HTML 요소를 객체로 받아와서 사용
// 요소에 접근하기
// 1. getElementById(`아이디명`)
// 2. getElementsByClassName(`클래스명`)
// 3. getElementsByTagName(`태그명`)
// 4. querySelector(`선택자`) : 선택자 이용 요소 접근, 1개, 여러개면 첫번째 선택
// 5. querySelectorAll(`선택자`)
//     ** 여러개에접근하는 함수를 사용했다면, 인덱슬를 이용해서 하나씩 접근 가능

// querySelector 이용 요소 접근
let heading = document.querySelector(`#heading`);
console.log(heading);

// querySelectorAll 이용 요소 접근
let list = document.querySelectorAll(`li`);
console.log(list);
console.log(list[0]);
console.log(list[1]);

// 요소의 텍스트에 접근하기
// innerText : 웹 브라우저창에 보이는 텍스트에만 접근
// innerHtml : html 태그 포함 텍스트에 접근
// textContent : 화면에 보이지 않는 텍스트에도 접근

let detail = document.querySelector(`#detail > ul`);
console.log(detail.innerText);
console.log(detail.innerHTML);
console.log(detail.textContent);

// detail.innerText = `<h3>가나다라마바사</h3>`;
// detail.innerHTML = `<h3>변경된 HTML 입니다</h3>`;
detail.textContent = `<h3>변경된 HTML 입니다</h3>`;

// 이벤트를 이용해서 텍스트 변경 및ㅌ 이미지 변경
let heading2 = document.getElementById(`heading2`);
let cup = document.querySelector(`#cup`);
// 함수 직접 선언
heading2.onclick = function(){
    heading2.innerText = "오늘의 커피";
}
cup.onclick = function(){
    cup.src = "../images/19_images/coffee-blue.jpg";
}
// 선언한 함수 이름으로 이벤트 처리
let cat = document.querySelector(`#cat`);
cat.onclick = changePic;

function changePic(){
    cat.src = "../images/19_images/kitty-2.png";
}

// DOM의 event 객체 : 이벤트 정보가 담겨 있다.

// event 객체의 프로프티 중 pageX, pageY, screenX, screenY 이벤트 발생 위치 알아보기
let clickArea = document.querySelector(`#clickArea`);
clickArea.onclick =  function(event){
    console.log(`이벤트 발생 위치 (문서기준) : ${event.pageX}, ${event.pageY}`);
    console.log(`이벤트 발생 위치 (화면기준) : ${event.screenX}, ${event.screenY}`);
    console.log(`이벤트 발생 대상 : ${event.target}`);
    console.log(`이벤트 발생 시간 : ${event.timeStamp}`);
}

let keyInput = document.querySelector("#keyInput");
keyInput.onkeypress = function(event){
    console.log(`키 이벤트 charCode: ${event.charCode}`);
    console.log(`키 이벤트 which : ${event.which}`);
    console.log(`키 이벤트 key : ${event.key}`);
    console.log(`키 이벤트 code : ${event.code}`);
}

// this 예약어
let cat2 = document.querySelector("#cat2");
cat2.onclick = function(){
    console.log(`이미지파일 : ${this.src}`);
}

// 이벤트 처리 방법
// 1. 태그에 on+이벤트명 속성 처리 가능
// 2. 자바스크립트 요소.on+이벤트명 = 함수로 처리 가능
// 3. 자바스크립트 요소.addEventListener(이벤트명, 함수명, 이벤트 캡처여부[true , false(기본값)]) 처리 가능
// addEventListener 사용 사진에 마우스 올리면 이미지 변경
let coffee1 = document.querySelector("#coffee1");
coffee1.addEventListener("mouseover", changePic);
coffee1.addEventListener("mouseout", originPic);


//  사진 변경 함수
function changePic(){
    coffee1.src = "../images/19_images/coffee-blue.jpg";
}

//  사진 원복 함수
function originPic(){
    coffee1.src = "../images/19_images/coffee-gray.jpg";
}

// DOM 이용 CSS 속성 변경
let rect = document.querySelector("#rect");

rect.addEventListener("mouseover", changeRect);

rect.addEventListener("mouseout", originRect);

function changeRect(){
    rect.style.backgroundColor = "green";
    rect.style.borderRadius = "50%";
}

function originRect(){
    rect.style.backgroundColor = "";
    rect.style.borderRadius = "";
}

// DOM에서 노드를 추가 삭제
// 노드 추가 순서
//   1. 노드 만들기 : createElement()
//   2. 텍스트 노드 만들기 : createTextNode()
//   3. 부모-자식 노드 연결 : appendChild()

let newItem = document.createElement("li");
let newText = document.createTextNode("아이템4");
newItem.appendChild(newText);

let parent = document.querySelector("#itemList");
parent.appendChild(newItem);

// 속성 넣어 새로운 노드 추가
let newImg = document.createElement("img");
newImg.src = "../images/19_images/books.png";
newImg.width =100;
newImg.height = 100;

parent.appendChild(newImg);

// 노드를 삭제 함수 : 요소.remove()
let removeBtn = document.querySelector("#removeBtn");
removeBtn.addEventListener("click", removeImg);

function removeImg(){
    let img = document.querySelector("#itemList > img");
    img.remove();
}

// 버튼 이용 노드 추가 삭제

let createBtn = document.querySelector("#createBtn");
createBtn.addEventListener("click", createLi);

let deleteBtn = document.querySelector("#deleteBtn");
deleteBtn.addEventListener("click", deleteLi);

let numLi = 1

function createLi(){
    let parent2 = document.querySelector("#nodeList");
    let newLi = document.createElement("li");
    let newText2 = document.createTextNode(numLi+" 노드추가");
    numLi++;
    newLi.appendChild(newText2);
    parent2.appendChild(newLi);
}

function deleteLi(){
let currentLi = document.querySelector("#nodeList > li");
currentLi.remove();
}

// let numLi = 5

// function createLi(){
//     let parent2 = document.querySelector("#nodeList");

//     for( i =1 ; i <= numLi ; i++){
//         let newLi = document.createElement("li");
//         let newText = document.createTextNode(i+" 노드추가");
//         newLi.appendChild(newText);
//         parent2.appendChild(newLi);
//     }
// }

// function deleteLi(){
//     while(numLi > 0){
//         let newLi = document.querySelector("#nodeList > li");
//         newLi.remove();
//     }
// }

let checkbox =  document.querySelector("#agree");  // 체크박스 요소 접근
let proceedBtn = document.querySelector("#proceed");  // proceed 버튼 접근

// 체크박스를 체크하면 enabled 클래스 추가 / 제크 해제하면 disabled 클래스 추가
checkbox.addEventListener("change", function(){
    // if(this.checked){
    //     proceedBtn.classList.add("enablde");
    //     proceedBtn.classList.remove("disabled");
    //     proceedBtn.disabled = false;

    // }
    // else {
    //     proceedBtn.classList.add("enablde");
    //     proceedBtn.classList.remove("disabled");
    //     proceedBtn.disabled = true;
    // }
    proceedBtn.classList.toggle(`enabled`, this.checked);
    proceedBtn.classList.toggle(`disabled`, !this.checked);
    proceedBtn.disabled = !this.checked;
})

// 실습1. 제크 클릭 체크 텍스트 중간 줄 긋기
let checks = document.querySelectorAll(".check");
for(let i = 0 ; i < checks.length ; i++){
    checks[i].addEventListener("click", function(){
        this.style.color = "#ccc";
        // 아래 parenNode는 현재 지정된 대상의 부모 노드에 접근
        this.parentNode.style.color = "#ccc";
        this.parentNode.style.textDecoration = "line-through";
    })
}

// 입력한 행과 열로 테이블 만들기

function drawTable(){
    let rCount = document.querySelector("#rCount").value;
    let cCount = document.querySelector("#cCount").value;

    let newTable = document.createElement("table");
    for(let i = 0 ;  i < rCount ; i++){
        let newRow = document.createElement("tr");
        for(let j = 0 ; j < cCount ;  j++){
            let newCell = document.createElement("td");
            let newText = document.createTextNode(i + " , " + j);
            newCell.appendChild(newText);
            newRow.appendChild(newCell);
        }
        newTable.appendChild(newRow);
    }
    let contents = document.querySelector("#contents");
    contents.appendChild(newTable);
}