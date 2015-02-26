/**
 * TestPlay.java
 */

package wiz.project.jan;

import java.util.Arrays;

import wiz.project.jan.exception.JanException;



/**
 * テストプレイ
 */
public class TestPlay {
    
    /**
     * コンストラクタ利用禁止
     */
    private TestPlay() {
    }
    
    
    
    /**
     * エントリポイント
     */
    public static void main(final String[] paramList) throws JanException, InterruptedException {
        try (final AnnounceThread announcer = new IRCAnnounceThread()) {
            announcer.run();
            GameMaster.getInstance().initialize(announcer);
            GameMaster.getInstance().onStart(Arrays.asList("TestPlayer"));
            
            while (true) {
                Thread.sleep(5000);
            }
        }
    }
    
}

