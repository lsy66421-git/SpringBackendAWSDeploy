package com.cafe.util;

public class BoardPage {
    // 기존 5개 인수를 받는 메서드 (검색 기능이 없을 때 사용)
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
                                   int pageNum, String reqUrl) {
        String pagingStr = "";
        if (pageSize == 0) {
            return ""; // 또는 오류 메시지 반환
        }
        // 전체 페이지 수 계산   
        int totalPages = (int)(Math.ceil((double)totalCount / pageSize));
	     // 이전 페이지 블록 바로가기 출력
	     int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
	     if (pageTemp != 1) {
	         pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
	         pagingStr += "&nbsp;";
	         pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
	                   + "'>[이전 블록]</a>";
	     }
	     // 각 페이지 번호 출력
	     int blockCount = 1;
	     while (blockCount <= blockPage && pageTemp <= totalPages) {
	         if (pageTemp == pageNum) {
	             // 현재 페이지는 링크를 걸지 않음
	             pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
	         } else {
	             pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
	                       + "'>" + pageTemp + "</a>&nbsp;";
	         }
	         pageTemp++;
	         blockCount++;
	     }
	     // 다음 페이지 블록 바로가기 출력
	     if (pageTemp <= totalPages) {
	         pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
	                   + "'>[다음 블록]</a>";
	         pagingStr += "&nbsp;";
	         pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
	                   + "'>[마지막 페이지]</a>";
	     }
	     return pagingStr;
     }
     
    // ★★★ 검색 기능을 위한 7개 인수를 받는 오버로딩된 메서드 추가 ★★★
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
                                   int pageNum, String reqUrl, String searchField, 
                                   String searchWord) {
        
        String pagingStr = "";
        if (pageSize == 0) {
            return ""; 
        }
        
        // 1. 검색 파라미터가 있을 경우 URL에 추가될 문자열 생성
        String addParam = "";
        if (searchWord != null && !searchWord.equals("")) {
            // URL에 'pageNum' 외의 추가 파라미터를 '&'로 시작하도록 구성
            addParam += "&searchField=" + searchField;
            addParam += "&searchWord=" + searchWord;
        }

        // 전체 페이지 수 계산
        int totalPages = (int)(Math.ceil((double)totalCount / pageSize));

	     // 이전 페이지 블록 바로가기 출력
	     int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
	     if (pageTemp != 1) {
	         // 첫 페이지 링크에 검색 조건 추가
	         // BoardPage가 ?pageNum=1을 붙이므로, addParam은 &로 시작해야 합니다.
	         pagingStr += "<a href='" + reqUrl + "?pageNum=1" + addParam + "'>[첫 페이지]</a>";
	         pagingStr += "&nbsp;";
	         // 이전 블록 링크에 검색 조건 추가
	         pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
	                   + addParam + "'>[이전 블록]</a>";
	     }

	     // 각 페이지 번호 출력
	     int blockCount = 1;
	     while (blockCount <= blockPage && pageTemp <= totalPages) {
	         if (pageTemp == pageNum) {
	             // 현재 페이지는 링크를 걸지 않음
	             pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
	         } else {
	             // 페이지 번호 링크에 검색 조건 추가
	             pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
	                       + addParam + "'>" + pageTemp + "</a>&nbsp;";
	         }
	         pageTemp++;
	         blockCount++;
	     }

	     // 다음 페이지 블록 바로가기 출력
	     if (pageTemp <= totalPages) {
	         // 다음 블록 링크에 검색 조건 추가
	         pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
	                   + addParam + "'>[다음 블록]</a>";
	         pagingStr += "&nbsp;";
	         // 마지막 페이지 링크에 검색 조건 추가
	         pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
	                   + addParam + "'>[마지막 페이지]</a>";
	     }
	     return pagingStr;
     }
 }