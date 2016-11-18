package com.parcel.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.parcel.entity.ChattingVO;

@Component
public class EchoHandler extends TextWebSocketHandler {
	
	private Logger logger = LoggerFactory.getLogger(TextWebSocketHandler.class);
	
	private Map<String, ArrayList<WebSocketSession>> groupChattingMap;
	private List<WebSocketSession> errorList;
	
	public EchoHandler() {
		groupChattingMap = new HashMap<String, ArrayList<WebSocketSession>>();
		errorList = new ArrayList<WebSocketSession>();
	}
	
	
	/**
	 * 접속관련 이벤트 메서드
	 * @param WebSocketSession 접속한 사용자
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session){
		logger.info(session.getId() + "님 접속 시도");
		logger.info("연결  IP : " + session.getRemoteAddress().getHostString());
		//이 부분에서 도저히 사용자를 구별할 수 없어서
	}
	
	
	/**
	 * 2가지 이벤트 처리
	 * 1. send : 클라이언트가 서버로 메시지 보냄
	 * 2. emit : 서버에 연결되어 있는 클라이언트들에게 메시지를 보냄
	 * @param WebSocketSession 메시지를 보낸 클라이언트
	 * @param TextMessage 메시지의 내용
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		logger.info(session.getId() + "님이 메시지 전송 : " + message.getPayload());
		
		ChattingVO messageVo = ChattingVO.convertMessage(message.getPayload());
		try {
			if (messageVo.getType().equals("1")) { //접속
				logger.info("그룹 채팅 접속 요청");
				//tempConnectionList 에 있으면 꺼내서 맵으로 그룹 맞춰서
				
				if (groupChattingMap.containsKey(messageVo.getGroup())) {//해당 그룹의 리스트가 있는가? 
					groupChattingMap.get(messageVo.getGroup()).add(session); //있으면 리스트에 세션 추가
				} else { //없으면 리스트를 만들고 세션추가한뒤 맵에 추가
					ArrayList<WebSocketSession> list = new ArrayList<WebSocketSession>(); 
					list.add(session);
					groupChattingMap.put(messageVo.getGroup(), list);
				}
					
				
				//임시리스트에 세션이 없으면 무시해준다.
			} else if (messageVo.getType().equals("3")) { //나감
				logger.info("그룹 채팅 나가기 요청");
				if (groupChattingMap.containsKey(messageVo.getGroup())) { //map에 해당 그룹의 리스트가 있음
					if (groupChattingMap.get(messageVo.getGroup()).contains(session)) { //리스트에 사용자가 있음
						groupChattingMap.get(messageVo.getGroup()).remove(session); //리스트에서 사용자 삭제
						
						if (groupChattingMap.get(messageVo.getGroup()).size() == 0) {//리스트에서 사용자가 전부 나가면 삭제
							logger.info("사용자가 없어 리스트 삭제함");
							groupChattingMap.remove(messageVo.getGroup());
						}
					}
				} 
			} else if (messageVo.getType().equals("2")){
				//메시지 전송
				if (groupChattingMap.containsKey(messageVo.getGroup())) {
					logger.info("그룹 채팅 메시지 전송");
					for (WebSocketSession user : groupChattingMap.get(messageVo.getGroup())) {
						//보낸 사용자는 받지 않기 위한 조건문
						if (!session.getId().equals(user.getId())) {
							try { //유저가 그냥 창을 꺼버리거나 리프레쉬 해버린경우 빈 session이 남는데 해당 세션떄문에 에러나면 제거하고 동작하게함
								user.sendMessage(new TextMessage(messageVo.getName()  
										+ " -> " 
										+ messageVo.getMessage()));								
							} catch (Exception e) {
								logger.debug(e.getMessage());
								//바로 삭제하면 안되고 리스트에 담아뒀다가 나중에 삭제한다.
								//groupChattingMap.get(messageVo.getGroup()).remove(user);
								errorList.add(user);
							}
						}
					}
				}
				for (WebSocketSession errorSession : errorList) {
					try {
						groupChattingMap.get(messageVo.getGroup()).remove(errorSession);						
					} catch (Exception e) {
						logger.debug(e.getMessage());
					}
				}
				errorList.clear();
				
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 클라이언트가 서버와 연결종료 
	 * @param WebSocketSession 연결을 끊은 클라이언트
	 * @param CloseStatus 연결 상태(확인 필요)
	 */ 
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
		logger.info(session.getId() + "님 접속종료");
		logger.info(status.getReason() + " " + status.getCode() + " " + status.toString());
	}
	

	
}
