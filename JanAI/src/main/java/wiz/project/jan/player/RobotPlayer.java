/**
 * RobotPlayer.java
 */

package wiz.project.jan.player;

import java.util.Observable;

import wiz.project.jan.GameInfo;
import wiz.project.jan.JanPai;
import wiz.project.jan.Wind;
import wiz.project.jan.event.JanEvent;
import wiz.project.jan.player.ai.JanAI;



/**
 * NPC
 */
public final class RobotPlayer extends Player {
    
    /**
     * コンストラクタ
     * 
     * @param ai AI。
     */
    public RobotPlayer(final JanAI ai) {
        _AI = ai;
    }
    
    /**
     * コンストラクタ
     * 
     * @param ai AI。
     * @param name プレイヤー名。
     * @param wind 風。
     */
    public RobotPlayer(final JanAI ai, final String name, final Wind wind) {
        super(name, wind);
        _AI = ai;
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public RobotPlayer(final RobotPlayer source) {
        super(source);
        _AI = source._AI;
    }
    
    
    
    /**
     * ディープコピーを取得
     * 
     * @return ディープコピー。
     */
    @Override
    public RobotPlayer clone() {
        return new RobotPlayer(this);
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
            }
            break;
        case DISCARD_JAN_PAI:
            if (info.getActivePlayerWind() == _wind) {
                discard(event.getJanPai());
            }
            break;
        default:
            // 何もしない
            return;
        }
    }
    
    
    
    /**
     * 打牌処理
     * 
     * @param pai 対象牌。
     */
    public void discard(final JanPai pai) {
        _hand.removeJanPai(pai);
    }
    
    /**
     * ツモ牌更新処理
     * 
     * @param draw ツモ牌。
     */
    public void updateDraw(final JanPai draw) {
        _hand.addJanPai(draw);
        _AI.onUpdateDraw(draw);
    }
    
    
    
    /**
     * AI
     */
    private final JanAI _AI;
    
}

