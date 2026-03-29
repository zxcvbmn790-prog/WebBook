<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty sessionScope.loginUser and sessionScope.loginUser ne 'admin'}">
<style>
.chat-popup-overlay {
    position: fixed;
    inset: 0;
    background: rgba(15, 23, 42, 0.28);
    z-index: 9998;
    display: none;
}
.chat-popup-overlay.open { display: block; }

.chat-popup {
    position: fixed;
    right: 32px;
    bottom: 32px;
    width: 360px;
    height: 520px;
    background: #fff;
    border: 1px solid var(--line);
    border-radius: 24px;
    box-shadow: 0 24px 64px rgba(15, 23, 42, 0.2);
    z-index: 9999;
    display: none;
    flex-direction: column;
    overflow: hidden;
}
.chat-popup.open { display: flex; }

.chat-popup-header {
    padding: 18px 20px;
    background: var(--accent);
    color: #fff;
    display: flex;
    align-items: center;
    gap: 12px;
}
.chat-popup-avatar {
    width: 42px;
    height: 42px;
    border-radius: 50%;
    background: rgba(255,255,255,0.14);
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 18px;
}
.chat-popup-title { font-size: 15px; font-weight: 700; }
.chat-popup-subtitle {
    font-size: 12px;
    color: rgba(255,255,255,0.72);
    margin-top: 2px;
}
.chat-popup-subtitle::before {
    content: '';
    display: inline-block;
    width: 7px;
    height: 7px;
    border-radius: 50%;
    background: #22c55e;
    margin-right: 6px;
}
.chat-popup-close {
    margin-left: auto;
    border: none;
    background: transparent;
    color: rgba(255,255,255,0.78);
    font-size: 18px;
    cursor: pointer;
}
.chat-popup-close:hover { color: #fff; }

.chat-popup-messages {
    flex: 1;
    overflow-y: auto;
    padding: 16px;
    background: #f8fafc;
    display: flex;
    flex-direction: column;
    gap: 12px;
}
.chat-empty {
    margin: auto 0;
    text-align: center;
    color: var(--sub);
    font-size: 13px;
    line-height: 1.8;
}
.chat-empty i {
    display: block;
    font-size: 36px;
    color: #c7d2fe;
    margin-bottom: 10px;
}
.chat-row { display: flex; align-items: flex-end; gap: 8px; }
.chat-row.mine { flex-direction: row-reverse; }
.chat-meta-wrap { max-width: 230px; }
.chat-name {
    font-size: 11px;
    font-weight: 700;
    color: var(--sub);
    margin-bottom: 4px;
}
.chat-bubble {
    padding: 10px 14px;
    border-radius: 18px;
    font-size: 14px;
    line-height: 1.5;
    word-break: break-word;
}
.chat-row.theirs .chat-bubble {
    background: #fff;
    border: 1px solid var(--line);
    border-bottom-left-radius: 4px;
}
.chat-row.mine .chat-bubble {
    background: var(--primary);
    color: #fff;
    border-bottom-right-radius: 4px;
}
.chat-time {
    font-size: 11px;
    color: var(--sub);
    margin-top: 4px;
}
.chat-popup-input {
    padding: 14px 16px;
    border-top: 1px solid var(--line);
    display: flex;
    gap: 10px;
    background: #fff;
}
.chat-popup-input input {
    flex: 1;
    border: 1px solid var(--line);
    border-radius: 12px;
    padding: 10px 14px;
    background: #f8fafc;
}
.chat-popup-input input:focus {
    outline: none;
    border-color: var(--primary);
    box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.08);
}
.chat-popup-send {
    width: 42px;
    height: 42px;
    border: none;
    border-radius: 12px;
    background: var(--primary);
    color: #fff;
    cursor: pointer;
}
.chat-popup-send:hover { opacity: 0.9; }

@media (max-width: 768px) {
    .chat-popup {
        width: calc(100vw - 24px);
        height: 70vh;
        right: 12px;
        bottom: 12px;
    }
}
</style>

<div class="chat-popup-overlay" id="chatPopupOverlay"></div>
<div class="chat-popup" id="chatPopup">
    <div class="chat-popup-header">
        <div class="chat-popup-avatar"><i class="fa-solid fa-headset"></i></div>
        <div>
            <div class="chat-popup-title">BOOK FOREST 실시간 상담</div>
            <div class="chat-popup-subtitle">상담 가능</div>
        </div>
        <button type="button" class="chat-popup-close" id="chatPopupClose">
            <i class="fa-solid fa-xmark"></i>
        </button>
    </div>

    <div class="chat-popup-messages" id="chatMsgBox">
        <div class="chat-empty" id="chatEmptyHint">
            <i class="fa-solid fa-comment-dots"></i>
            문의사항을 남겨주시면<br>관리자가 순서대로 답변합니다.
        </div>
    </div>

    <div class="chat-popup-input">
        <input type="text" id="chatInput" placeholder="메시지를 입력하세요..." autocomplete="off">
        <button type="button" class="chat-popup-send" id="chatSendBtn">
            <i class="fa-solid fa-paper-plane"></i>
        </button>
    </div>
</div>

<script>
(function() {
    const CURRENT = '${sessionScope.loginUser}';
    const ROOM_ID = CURRENT;
    const ctx = '${pageContext.request.contextPath}';

    const openBtn = document.getElementById('headerChatBtn');
    const overlay = document.getElementById('chatPopupOverlay');
    const popup = document.getElementById('chatPopup');
    const closeBtn = document.getElementById('chatPopupClose');
    const msgBox = document.getElementById('chatMsgBox');
    const input = document.getElementById('chatInput');
    const sendBtn = document.getElementById('chatSendBtn');
    const emptyHint = document.getElementById('chatEmptyHint');

    let ws = null;
    let historyLoaded = false;
    let manuallyClosed = true;

    function openPopup() {
        popup.classList.add('open');
        overlay.classList.add('open');
        manuallyClosed = false;
        if (!historyLoaded) {
            loadHistory();
        }
        connectWebSocket();
        setTimeout(function() {
            scrollBottom();
            input.focus();
        }, 50);
    }

    function closePopup() {
        popup.classList.remove('open');
        overlay.classList.remove('open');
        manuallyClosed = true;
    }

    function loadHistory() {
        historyLoaded = true;
        fetch(ctx + '/chat/history?roomId=' + encodeURIComponent(ROOM_ID))
            .then(function(response) { return response.json(); })
            .then(function(messages) {
                if (messages && messages.length > 0) {
                    emptyHint.style.display = 'none';
                    messages.forEach(function(message) {
                        renderMessage(message.sender, message.content, message.admin || message.isAdmin, message.sentAt);
                    });
                    scrollBottom();
                }
            })
            .catch(function() {});
    }

    function connectWebSocket() {
        if (ws && (ws.readyState === WebSocket.OPEN || ws.readyState === WebSocket.CONNECTING)) {
            return;
        }

        const scheme = location.protocol === 'https:' ? 'wss://' : 'ws://';
        ws = new WebSocket(scheme + location.host + ctx + '/ws/chat?roomId=' + encodeURIComponent(ROOM_ID));

        ws.onmessage = function(event) {
            const data = JSON.parse(event.data);
            const isMine = data.sender === CURRENT && !(data.admin || data.isAdmin);
            if (!isMine) {
                emptyHint.style.display = 'none';
                renderMessage(data.sender, data.content, data.admin || data.isAdmin, data.sentAt);
                scrollBottom();
            }
        };

        ws.onclose = function() {
            if (!manuallyClosed && popup.classList.contains('open')) {
                setTimeout(connectWebSocket, 2000);
            }
        };
    }

    function sendMessage() {
        const content = input.value.trim();
        if (!content) {
            return;
        }
        if (!ws || ws.readyState !== WebSocket.OPEN) {
            alert('채팅 서버에 연결 중입니다. 잠시 후 다시 시도해 주세요.');
            return;
        }

        ws.send(JSON.stringify({ content: content }));
        emptyHint.style.display = 'none';
        renderMessage(CURRENT, content, false, null);
        input.value = '';
        scrollBottom();
    }

    function renderMessage(sender, content, isAdmin, sentAt) {
        const isMine = sender === CURRENT && !isAdmin;
        const row = document.createElement('div');
        row.className = 'chat-row ' + (isMine ? 'mine' : 'theirs');

        const wrap = document.createElement('div');
        wrap.className = 'chat-meta-wrap';

        if (!isMine) {
            const name = document.createElement('div');
            name.className = 'chat-name';
            name.textContent = isAdmin ? '관리자' : sender;
            wrap.appendChild(name);
        }

        const bubble = document.createElement('div');
        bubble.className = 'chat-bubble';
        bubble.textContent = content;
        wrap.appendChild(bubble);

        if (sentAt) {
            const time = document.createElement('div');
            time.className = 'chat-time';
            time.textContent = sentAt.length >= 16 ? sentAt.substring(11, 16) : sentAt;
            wrap.appendChild(time);
        }

        row.appendChild(wrap);
        msgBox.appendChild(row);
    }

    function scrollBottom() {
        msgBox.scrollTop = msgBox.scrollHeight;
    }

    if (openBtn) {
        openBtn.addEventListener('click', function(event) {
            event.preventDefault();
            if (popup.classList.contains('open')) {
                closePopup();
            } else {
                openPopup();
            }
        });
    }
    closeBtn.addEventListener('click', closePopup);
    overlay.addEventListener('click', closePopup);
    sendBtn.addEventListener('click', sendMessage);
    input.addEventListener('keydown', function(event) {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault();
            sendMessage();
        }
    });
})();
</script>
</c:if>