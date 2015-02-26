/**
 * NotStartedException.java
 */

package wiz.project.jan.exception;



/**
 * 未開始例外
 */
public class NotStartedException extends JanException {
    
    /**
     * コンストラクタ
     */
    public NotStartedException() {
        super("Game is not started.");
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     */
    public NotStartedException(final String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     * 
     * @param e 例外オブジェクト。
     */
    public NotStartedException(final Throwable e) {
        super(e);
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     * @param e 例外オブジェクト。
     */
    public NotStartedException(final String message, final Throwable e) {
        super(message, e);
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
}

