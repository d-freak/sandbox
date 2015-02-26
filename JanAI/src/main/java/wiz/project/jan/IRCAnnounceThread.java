/**
 * IRCAnnounceThread.java
 */

package wiz.project.jan;

import wiz.project.jan.event.JanEvent;
import wiz.project.jan.player.HumanPlayer;
import wiz.project.jan.player.Player;



/**
 * IRC向け実況者
 */
public class IRCAnnounceThread extends AnnounceThread {
    
    /**
     * コンストラクタ
     */
    public IRCAnnounceThread() {
    }
    
    
    
    /**
     * 常駐処理
     */
    @Override
    protected void process() {
        final GameInfo info = _infoQueue.poll();
        final JanEvent event = _eventQueue.poll();
        if (info == null || event == null) {
            return;
        }
        
        switch (event.getSource()) {
        case STAND_BY_COMPLETE:
            System.out.println("---- Game Start!! ----");
            break;
        case WAIT_DISCARD:
            {
                final Player activePlayer = info.getActivePlayer();
                if (activePlayer instanceof HumanPlayer) {
                    System.out.println(activePlayer.getHand().toString());
                }
            }
            break;
        default:
            // 何もしない
            return;
        }
    }
    
}

