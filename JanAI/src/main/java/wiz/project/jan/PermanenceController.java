/**
 * PermanenceController.java
 */

package wiz.project.jan;

import java.io.IOException;



/**
 * 永続化処理
 */
interface PermanenceController {
    
    /**
     * ゲーム情報をバックアップ
     * 
     * @param info ゲーム情報。
     * @param destPath 永続化先のパス。
     * @throws IOException ファイル入出力に失敗。
     */
    public void backupGame(final GameInfo info, final String destPath) throws IOException;
    
    /**
     * ゲーム情報を復元
     * 
     * @param sourcePath 永続化ファイルのパス。
     * @return ゲーム情報。
     * @throws IOException ファイル入出力に失敗。
     */
    public GameInfo restore(final String sourcePath) throws IOException;
    
}

