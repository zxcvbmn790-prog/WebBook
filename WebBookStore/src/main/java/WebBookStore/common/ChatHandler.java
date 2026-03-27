package WebBookStore.common;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {
    // 연결된 모든 세션을 저장할 리스트
    private static List<WebSocketSession> sessionList = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session); // 사용자가 접속하면 리스트에 추가
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 메시지가 오면, 접속한 모든 사람에게 전달
        for (WebSocketSession s : sessionList) {
            s.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessionList.remove(session); // 접속 끊기면 리스트에서 제거
    }
}