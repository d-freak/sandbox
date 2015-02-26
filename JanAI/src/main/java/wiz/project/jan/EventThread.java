/**
 * EventThread.java
 */

package wiz.project.jan;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import wiz.project.jan.event.Event;
import wiz.project.jan.event.JanEvent;
import wiz.project.jan.exception.JanException;
import wiz.thread.ResidentThread;



/**
 * イベント処理スレッド
 */
class EventThread extends ResidentThread implements Observer {
    
    /**
     * コンストラクタ
     * 
     * @param core 麻雀コントローラ。
     */
    public EventThread(final JanController core) {
        _core = core;
    }
    
    
    
    /**
     * 状態更新通知時の処理
     * 
     * @param target 監視対象。
     * @param param 更新パラメータ。
     */
    public void update(final Observable target, final Object param) {
        if (!(target instanceof GameInfo)) {
            // 何もしない
            return;
        }
        if (!(param instanceof JanEvent)) {
            // 何もしない
            return;
        }
        
        _infoQueue.add((GameInfo)target);
        _eventQueue.add((JanEvent)param);
    }
    
    
    
    /**
     * スレッド処理
     */
    @Override
    protected void process() {
        final GameInfo info = _infoQueue.poll();
        final JanEvent event = _eventQueue.poll();
        if (info == null || event == null) {
            return;
        }
        
        switch (event.getSource()) {
        case CHANGE_TURN:
            try {
                _core.changeTurn(info);
            }
            catch (final JanException e) {}
            break;
        case DISCARD_DRAW:
            {
                final JanPai activeDraw = info.getActiveDraw();
                info.removeActiveDraw();
                info.notifyObservers(new JanEvent(Event.DISCARD_JAN_PAI, activeDraw));
            }
            break;
        case DISCARD_JAN_PAI:
            {
                info.removeActiveDraw();
                info.notifyObservers(new JanEvent(Event.CHANGE_TURN));
            }
            break;
        default:
            // 何もしない
            return;
        }
    }
    
    
    
    /**
     * 麻雀コントローラ
     */
    private final JanController _core;
    
    /**
     * ゲーム情報キュー
     */
    protected Queue<GameInfo> _infoQueue = new SynchronousQueue<>();
    
    /**
     * 麻雀イベントキュー
     */
    protected Queue<JanEvent> _eventQueue = new SynchronousQueue<>();
    
}

