/**
 * VSAIJanController.java
 */

package wiz.project.jan;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wiz.project.jan.event.Event;
import wiz.project.jan.event.JanEvent;
import wiz.project.jan.exception.JanException;
import wiz.project.jan.player.HumanPlayer;
import wiz.project.jan.player.Player;
import wiz.project.jan.player.RobotPlayer;
import wiz.project.jan.player.ai.JanAI;
import wiz.project.jan.player.ai.SimpleAI;
import wiz.project.jan.util.JanPaiUtil;



/**
 * AI対戦麻雀処理
 */
class VSAIJanController implements JanController {
    
    /**
     * コンストラクタ
     */
    public VSAIJanController() {
    }
    
    
    
    /**
     * ターン交代
     */
    public void changeTurn(final GameInfo info) throws JanException {
        if (info == null) {
            throw new NullPointerException("Game information is null.");
        }
        
        final Wind activePlayerWind = info.getActivePlayerWind();
        info.setActivePlayerWind(activePlayerWind.getNext().getNext());  // 東と西のみ
        onTurn(info);
    }
    
    /**
     * ゲーム開始準備
     */
    public void standBy(final GameInfo info, final List<String> playerNameList) throws JanException {
        if (info == null) {
            throw new NullPointerException("Game information is null.");
        }
        if (playerNameList == null) {
            throw new NullPointerException("Player list is null.");
        }
        if (playerNameList.isEmpty()) {
            throw new IllegalArgumentException("Player list is empty.");
        }
        
        // 席決め＆親決め
        // TODO 今は固定
        final JanAI ai = new SimpleAI(info);
        final Player ton = new RobotPlayer(ai, "JanBOT", Wind.TON);
        final Player sha = new HumanPlayer(playerNameList.get(0), Wind.SHA);
        
        // 牌山生成
        final List<JanPai> deck = createDeck();
        info.setDeck(deck);
        
        // 配牌
        ton.setHand(new Hand(new ArrayList<JanPai>(deck.subList( 0, 13))));
        sha.setHand(new Hand(new ArrayList<JanPai>(deck.subList(13, 26))));
        info.setPlayer(Wind.TON, ton);
        info.setPlayer(Wind.SHA, sha);
        
        // システム設定
        info.setDrawIndex(13 * 2);
        info.setDeadWallIndex(0);  // TODO 嶺上牌の開始地点を設定
        info.setRemainCount(21 * 2);  // TODO とりあえずタイマンchmは21枚ツモれることにした
        info.setActivePlayerWind(Wind.TON);
        info.notifyObservers(new JanEvent(Event.STAND_BY_COMPLETE));
    }
    
    /**
     * ゲームを開始
     */
    public void start(final GameInfo info) throws JanException {
        info.setState(GameState.PROCESSING);
        onTurn(info);
    }
    
    
    
    /**
     * 牌山を生成
     * 
     * @return 牌山。
     */
    private List<JanPai> createDeck() {
        final List<JanPai> deck = JanPaiUtil.createAllJanPaiList();
        Collections.shuffle(deck, new SecureRandom());
        return deck;
    }
    
    /**
     * 牌をツモる
     * 
     * @param info ゲーム情報。
     * @return ツモ牌。
     */
    private JanPai draw(final GameInfo info) {
        final List<JanPai> deck = info.getDeck();
        final int drawIndex = info.getDrawIndex();
        final int remainCount = info.getRemainCount();
        
        final JanPai draw = deck.get(drawIndex);
        info.setDrawIndex(drawIndex + 1);
        info.setRemainCount(remainCount - 1);
        return draw;
    }
    
    /**
     * ターン中の処理
     * 
     * @param info ゲーム情報。
     */
    private void onTurn(final GameInfo info) {
        final Wind activePlayerWind = info.getActivePlayerWind();
        final JanPai draw = draw(info);
        info.setActiveDraw(draw);
        info.notifyObservers(new JanEvent(Event.WAIT_DISCARD, activePlayerWind));
    }
    
}

