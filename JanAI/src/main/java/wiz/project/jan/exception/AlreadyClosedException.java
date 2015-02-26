/**
 * AlreadyClosedException.java
 */

package wiz.project.jan.exception;



/**
 * 終了済み例外
 */
public class AlreadyClosedException extends JanException {
    
    /**
     * コンストラクタ
     */
    public AlreadyClosedException() {
        super("Game is over.");
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     */
    public AlreadyClosedException(final String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     * 
     * @param e 例外オブジェクト。
     */
    public AlreadyClosedException(final Throwable e) {
        super(e);
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     * @param e 例外オブジェクト。
     */
    public AlreadyClosedException(final String message, final Throwable e) {
        super(message, e);
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
}

