/**
 * AnnounceThread.java
 */

package wiz.project.jan;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import wiz.project.jan.event.JanEvent;
import wiz.thread.ResidentThread;



/**
 * ゲーム実況者
 */
public abstract class AnnounceThread extends ResidentThread implements Observer {
    
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
        
        _infoQueue.add(((GameInfo)target).clone());  // 状態を固定するためにディープコピーする
        _eventQueue.add((JanEvent)param);
    }
    
    
    
    /**
     * ゲーム情報キュー
     */
    protected Queue<GameInfo> _infoQueue = new SynchronousQueue<>();
    
    /**
     * 麻雀イベントキュー
     */
    protected Queue<JanEvent> _eventQueue = new SynchronousQueue<>();
    
}

