/**
 * SimpleAI.java
 */

package wiz.project.jan.player.ai;

import wiz.project.jan.GameInfo;
import wiz.project.jan.JanPai;



/**
 * ツモ切りAI
 */
public final class SimpleAI extends JanAI {
    
    /**
     * コンストラクタ
     * 
     * @param info ゲーム情報。
     */
    public SimpleAI(final GameInfo info) {
        super(info);
    }
    
    
    
    /**
     * ツモ牌更新時の処理
     */
    @Override
    public void onUpdateDraw(final JanPai draw) {
        discardDraw();
    }
    
}

