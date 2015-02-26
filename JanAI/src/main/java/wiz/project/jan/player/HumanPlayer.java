/**
 * HumanPlayer.java
 */

package wiz.project.jan.player;

import java.util.Observable;

import wiz.project.jan.GameInfo;
import wiz.project.jan.GameState;
import wiz.project.jan.JanPai;
import wiz.project.jan.Wind;
import wiz.project.jan.event.JanEvent;



/**
 * 中身入りプレイヤー
 */
public final class HumanPlayer extends Player {
    
    /**
     * コンストラクタ
     */
    public HumanPlayer() {
    }
    
    /**
     * コンストラクタ
     * 
     * @param name プレイヤー名。
     * @param wind 風。
     */
    public HumanPlayer(final String name, final Wind wind) {
        super(name, wind);
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public HumanPlayer(final HumanPlayer source) {
        super(source);
    }
    
    
    
    /**
     * ディープコピーを取得
     * 
     * @return ディープコピー。
     */
    @Override
    public HumanPlayer clone() {
        return new HumanPlayer(this);
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
        
        final GameInfo info = (GameInfo)target;
        final JanEvent event = (JanEvent)param;
        switch (event.getSource()) {
        case WAIT_DISCARD:
            if (info.getActivePlayerWind() == _wind) {
                updateDraw(info.getActiveDraw());
                info.setState(GameState.IDLE);
            }
            break;
        default:
            // 何もしない
            return;
        }
    }
    
    
    
    /**
     * ツモ牌更新処理
     * 
     * @param draw ツモ牌。
     */
    public void updateDraw(final JanPai draw) {
        _hand.addJanPai(draw);
    }
    
}

