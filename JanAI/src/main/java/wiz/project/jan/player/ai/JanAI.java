/**
 * JanAI.java
 */

package wiz.project.jan.player.ai;

import wiz.project.jan.GameInfo;
import wiz.project.jan.GameState;
import wiz.project.jan.JanPai;
import wiz.project.jan.event.Event;
import wiz.project.jan.event.JanEvent;



/**
 * 麻雀AI
 */
public abstract class JanAI {
    
    /**
     * コンストラクタをサブクラスに限定許可
     * 
     * @param info ゲーム情報。
     */
    protected JanAI(final GameInfo info) {
        _info = info;
    }
    
    
    
    /**
     * ツモ牌更新時の処理
     * 
     * @param draw ツモ牌。
     */
    public abstract void onUpdateDraw(final JanPai draw);
    
    
    
    /**
     * ツモ切り
     */
    protected final void discardDraw() {
        _info.setState(GameState.PROCESSING);
        _info.notifyObservers(new JanEvent(Event.DISCARD_DRAW));
    }
    
    
    
    /**
     * ゲーム情報
     */
    private final GameInfo _info;
    
}

