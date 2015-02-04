package org.corenel.core.util;


import java.util.StringTokenizer;

public class TableIDGenerator {
	private static char[] postCharOfBranchId =new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};  
	
	//BranchId Of BRANCH_INFO TABLE Generate.
	public static String getNextBranchId(String currentBranchId) throws Exception {
		StringTokenizer st = new StringTokenizer(currentBranchId, "_");
		String nextId = st.nextToken();
		if(nextId.length() > 8) {
			nextId = nextId.substring(0,8);
		}
		String currentNum  = String.format("%3s",st.nextToken());
		char currPostChar =  currentNum.charAt(0); //앞 문자 찾기
		char nextPostChar = 0;
		int nextPrefixNum =  Integer.parseInt(currentNum.substring(1))+1;
		if(nextPrefixNum == 100) {
			//앞문자 자리수 증가
			for(int idx = 0; idx < postCharOfBranchId.length; idx++) {
				if(currPostChar == postCharOfBranchId[idx]) {
					if(idx != postCharOfBranchId.length-1) {
						nextPostChar = postCharOfBranchId[idx+1];
						break;
					} else {
						throw new Exception("ID가 최대값에 도달하였습니다. ID정책을 재수립하여 적용해주세요."); //ID(8length)_Z99 일때.
					}
				}
			}
			nextPrefixNum = 0;
		} else if(currPostChar == ' ') {
			nextPostChar = '0';
		} else {
			nextPostChar = currPostChar;
		}
		return new StringBuilder(nextId).append("_").append(nextPostChar).append(String.format("%02d", nextPrefixNum)).toString();
	}
	
}
