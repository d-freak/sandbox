/**
 * JanController.java
 */

package wiz.project.jan;

import java.util.List;

import wiz.project.jan.exception.JanException;



/**
 * 麻雀ゲームの処理
 */
interface JanController {
    
    /**
     * ターン交代
     * 
     * @param info ゲーム情報。
     * @throws JanException 麻雀ゲームの例外。
     */
    public void changeTurn(final GameInfo info) throws JanException;
    
    /**
     * ゲーム開始準備
     * 
     * @param info ゲーム情報。
     * @param playerNameList プレイヤー名のリスト。
     * @throws JanException 麻雀ゲームの例外。
     */
    public void standBy(final GameInfo info, final List<String> playerNameList) throws JanException;
    
    /**
     * ゲームを開始
     * 
     * @param info ゲーム情報。
     * @throws JanException 麻雀ゲームの例外。
     */
    public void start(final GameInfo info) throws JanException;
    
}

