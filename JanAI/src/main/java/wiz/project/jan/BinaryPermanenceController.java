/**
 * BinaryPermanenceController.java
 */

package wiz.project.jan;

import java.io.IOException;



/**
 * バイナリファイル永続化処理
 */
class BinaryPermanenceController implements PermanenceController {
    
    /**
     * コンストラクタ
     */
    public BinaryPermanenceController() {
    }
    
    
    
    /**
     * ゲーム情報をバックアップ
     */
    public void backupGame(final GameInfo info, final String destPath) throws IOException {
    }
    
    /**
     * ゲーム情報を復元
     */
    public GameInfo restore(final String sourcePath) throws IOException {
        return null;
    }
    
}

