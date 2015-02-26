/**
 * GameState.java
 */

package wiz.project.jan;



/**
 * ゲームの状態
 */
public enum GameState {
    
    /**
     * 打牌待機中
     */
    IDLE,
    
    /**
     * 処理中
     */
    PROCESSING,
    
    /**
     * 鳴き確認中
     */
    CONFIRMING,
    
    /**
     * 未実行
     */
    CLOSE,
    
}

