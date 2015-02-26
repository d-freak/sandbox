/**
 * AlreadyStartedException.java
 */

package wiz.project.jan.exception;



/**
 * 開始済み例外
 */
public class AlreadyStartedException extends JanException {
    
    /**
     * コンストラクタ
     */
    public AlreadyStartedException() {
        super("Game is already started.");
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     */
    public AlreadyStartedException(final String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     * 
     * @param e 例外オブジェクト。
     */
    public AlreadyStartedException(final Throwable e) {
        super(e);
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     * @param e 例外オブジェクト。
     */
    public AlreadyStartedException(final String message, final Throwable e) {
        super(message, e);
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
}

