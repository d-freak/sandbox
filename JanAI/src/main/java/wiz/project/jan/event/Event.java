/**
 * Event.java
 */

package wiz.project.jan.event;



/**
 * イベント
 */
public enum Event {
    
    /**
     * ゲーム開始準備完了
     */
    STAND_BY_COMPLETE,
    
    /**
     * ターン交代
     */
    CHANGE_TURN,
    
    /**
     * ツモ切り
     */
    DISCARD_DRAW,
    
    /**
     * 指定牌を切る
     */
    DISCARD_JAN_PAI,
    
    /**
     * 打牌待ち
     */
    WAIT_DISCARD,
    
    /**
     * ゲーム情報永続化失敗
     */
    PERMANENCE_GAME_INFO_FAILURE,
    
    /**
     * 空のイベント
     */
    BLANK,
    
}

